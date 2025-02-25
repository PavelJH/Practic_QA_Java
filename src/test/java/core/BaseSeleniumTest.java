package core;

import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

abstract public class BaseSeleniumTest {
    protected WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
        }

        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot("failure_" + description.getClassName() + "_" + description.getMethodName() + "_" + getTimestamp());
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
        // Создаём папку "screenshots", если её нет
        File screenshotDir = new File("screenshots");
        if (!screenshotDir.exists()){
            screenshotDir.mkdirs();
        }

        // Если в папке уже 10 или более скриншотов, удаляем самый старый
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

    @After
    public void tearDown(){
        // Оставляем метод пустым, так как завершение драйвера
        // производится в методе finished() TestWatcher

        //        driver.close();
        //        driver.quit();
    }
}
