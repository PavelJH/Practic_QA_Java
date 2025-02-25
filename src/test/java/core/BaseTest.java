//package core;
//
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.After;
//import org.junit.Before;
//
//abstract public class BaseTest {
//    //Абстрактный класс, для того, что бы от этого классам можно было только наследоваться. Мы не можем создать экземпляр этого класса.
//public void setUp(){
//    WebDriverManager.chromedriver().setup();// скачиваем драйвер и автоматически указываем путь, настройки и тд.
//    Configuration.browser = "chrome"; //Выбрать именно com.codeborne.selenide
//    Configuration.webdriverLogsEnabled = true; //это показывает что у нас именно WebDriver библиотека
//    Configuration.browserSize = "1920x1080"; // Размер окна. Configuration.startMaximized = true; - открытие на весь экран
//    Configuration.headless = false ; // Будет ли видно на экране все, что происходит
//}
//@Before //Перед каждым запуском теста, будет происходить инициализация webDriver
//    public void init(){
//    setUp();
//}
//
//@After //После каждого теста, будет закрываться webDriver
//    public void teatDown(){
//    Selenide.closeWebDriver();
//}
//
//
//}


package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

abstract public class BaseTest {

    private static final int MAX_SCREENSHOTS = 10;
    private static final File SCREENSHOT_FOLDER;

    static {
        Configuration.savePageSource = false;
        String projectRoot = System.getProperty("user.dir");
        SCREENSHOT_FOLDER = new File(projectRoot, "screenshots");
        if (!SCREENSHOT_FOLDER.exists()) {
            SCREENSHOT_FOLDER.mkdirs();
        }
        Configuration.reportsFolder = SCREENSHOT_FOLDER.getAbsolutePath();
    }

    @Rule
    public TestName testName = new TestName();

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    @Before
    public void init() {
        setUp();
    }

    @After
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            try {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotName = getClass().getSimpleName() + "_" + testName.getMethodName() + "_" + timestamp;
                Selenide.screenshot(screenshotName);
                manageScreenshotFiles();
            } catch (Exception e) {
                System.err.println("Ошибка при создании скриншота: " + e.getMessage());
            } finally {
                Selenide.closeWebDriver();
            }
        }
    }

    private void manageScreenshotFiles() {
        File[] files = SCREENSHOT_FOLDER.listFiles((dir, name) -> name.endsWith(".png"));
        if (files != null && files.length > MAX_SCREENSHOTS) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
            int filesToDelete = files.length - MAX_SCREENSHOTS;
            for (int i = 0; i < filesToDelete; i++) {
                if (files[i].delete()) {
                    System.out.println("Удален старый скриншот: " + files[i].getName());
                }
            }
        }
    }
}





//package core;
//
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import java.io.File;
//
//@ExtendWith(core.TestWatcherExtension.class) // Подключаем расширение
//abstract public class BaseTest {
//
//    static {
//        // Отключаем сохранение HTML-страницы
//        Configuration.savePageSource = false;
//
//        // Создаем папку для скриншотов
//        String projectRoot = System.getProperty("user.dir");
//        File screenshotsFolder = new File(projectRoot, "screenshots");
//        if (!screenshotsFolder.exists()) {
//            screenshotsFolder.mkdirs();
//        }
//        Configuration.reportsFolder = screenshotsFolder.getAbsolutePath();
//    }
//
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        Configuration.browser = "chrome";
//        Configuration.webdriverLogsEnabled = true;
//        Configuration.browserSize = "1920x1080";
//        Configuration.headless = false;
//    }
//
//    @BeforeEach
//    public void init() {
//        setUp();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        Selenide.closeWebDriver();
//    }
//}
