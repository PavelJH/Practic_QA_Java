package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

abstract public class BaseSeleniumTest {
    protected WebDriver driver;
    // Переменная для управления headless режимом (true – включить, false – отключить)
    private boolean isHeadless = true;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Если isHeadless равен true, добавляем аргумент для headless режима
        if (isHeadless) {
            options.addArguments("--headless"); // При необходимости можно заменить на "--headless=new"
        }
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        BaseSeleniumPage.setDriver(driver);
    }

    @Rule
    public TestWatcher screenshotTestWatcher = new TestWatcher() {

        private String getTimestamp() {
            return new SimpleDateFormat("yyyyMMdd-HHmmss").format(new java.util.Date());
        }

        @Override
        protected void succeeded(Description description) {
            takeScreenshot("success_" + description.getMethodName() + "_" + getTimestamp());
            attachScreenshot(); // прикрепление скриншота к Allure-отчету
        }

        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot("failure_" + description.getClassName() + "_" + description.getMethodName() + "_" + getTimestamp());
            attachScreenshot(); // прикрепление скриншота к Allure-отчету
        }

        @Override
        protected void finished(Description description) {
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        }
    };

    private void takeScreenshot(String screenshotName) {
        File screenshotDir = new File("screenshots");
        if (!screenshotDir.exists()){
            screenshotDir.mkdirs();
        }

        File[] files = screenshotDir.listFiles();
        if (files != null && files.length >= 10) {
            File oldestFile = files[0];
            for (File file : files) {
                if (file.lastModified() < oldestFile.lastModified()) {
                    oldestFile = file;
                }
            }
            oldestFile.delete();
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(screenshotDir, screenshotName + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Attachment(value = "Скриншот", type = "image/png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @After
    public void tearDown(){
        // Завершение драйвера производится в методе finished() TestWatcher
    }
}
