package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

abstract public class BaseTest{
    //Абстрактный класс, для того, что бы от этого классам можно было только наследоваться. Мы не можем создать экземпляр этого класса.
public void setUp(){
    WebDriverManager.chromedriver().setup();// скачиваем драйвер и автоматически указываем путь, настройки и тд.
    Configuration.browser = "chrome"; //Выбрать именно com.codeborne.selenide
    Configuration.webdriverLogsEnabled = true; //это показывает что у нас именно WebDriver библиотека
    Configuration.browserSize = "1920x1080"; // Размер окна. Configuration.startMaximized = true; - открытие на весь экран
    Configuration.headless = true ; // Будет ли видно на экране все, что происходит
}
@Before //Перед каждым запуском теста, будет происходить инициализация webDriver
    public void init(){
    setUp();
}

@After //После каждого теста, будет закрываться webDriver
    public void teatDown(){
    Selenide.closeWebDriver();
}


}


//package core;
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.WebDriverRunner;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.TestInfo;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Date;
//import java.lang.reflect.Method;
//
//public abstract class BaseTest {
//
//    private static final int MAX_SCREENSHOTS = 10;
//    private static final File SCREENSHOT_FOLDER;
//
//    // Здесь будем временно хранить название текущего тестового метода
//    private String currentTestMethodName = "UnknownMethod";
//
//    static {
//        Configuration.savePageSource = false;
//        // Отключаем автоматические скриншоты при падении теста
//        Configuration.screenshots = false;
//        // Отключаем логи WebDriver, чтобы не плодились .log файлы
//        Configuration.webdriverLogsEnabled = false;
//
//        String projectRoot = System.getProperty("user.dir");
//        SCREENSHOT_FOLDER = new File(projectRoot, "screenshots");
//        if (!SCREENSHOT_FOLDER.exists()) {
//            SCREENSHOT_FOLDER.mkdirs();
//        }
//        Configuration.reportsFolder = SCREENSHOT_FOLDER.getAbsolutePath();
//    }
//
//    @BeforeEach
//    public void setUp(TestInfo testInfo) {
//        // Устанавливаем имя текущего тест-метода (если есть)
//        this.currentTestMethodName = testInfo.getTestMethod()
//                .map(Method::getName)
//                .orElse("UnknownMethod");
//
//        // Настройка браузера
//        WebDriverManager.chromedriver().setup();
//        Configuration.browser = "chrome";
//        Configuration.browserSize = "1920x1080";
//        Configuration.headless = false;
//    }
//
//    @AfterEach
//    public void tearDown() {
//        if (WebDriverRunner.hasWebDriverStarted()) {
//            try {
//                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//
//                // Формируем имя скриншота: Класс_Метод_дата
//                String screenshotName = getClass().getSimpleName()
//                        + "_" + currentTestMethodName
//                        + "_" + timestamp;
//
//                // Делаем скриншот (теперь только один, т.к. автоскриншоты отключены)
//                Selenide.screenshot(screenshotName);
//
//                // Очищаем старые скриншоты, если слишком много
//                manageScreenshotFiles();
//            } catch (Exception e) {
//                System.err.println("Error while creating screenshot: " + e.getMessage());
//            } finally {
//                Selenide.closeWebDriver();
//            }
//        }
//    }
//
//    private void manageScreenshotFiles() {
//        File[] files = SCREENSHOT_FOLDER.listFiles((dir, name) -> name.endsWith(".png"));
//        if (files != null && files.length > MAX_SCREENSHOTS) {
//            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
//            int filesToDelete = files.length - MAX_SCREENSHOTS;
//            for (int i = 0; i < filesToDelete; i++) {
//                if (files[i].delete()) {
//                    System.out.println("Old screenshot removed: " + files[i].getName());
//                }
//            }
//        }
//    }
//}
