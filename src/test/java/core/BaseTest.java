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
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import java.io.File;

abstract public class BaseTest {

    // Статический блок для настройки Selenide до запуска тестов
    static {
        // Отключаем сохранение HTML-страницы
        Configuration.savePageSource = false;

        // Получаем корневую директорию проекта
        String projectRoot = System.getProperty("user.dir");
        // Создаем папку "screenshots" в корневой директории проекта
        File screenshotsFolder = new File(projectRoot, "screenshots");
        if (!screenshotsFolder.exists()){
            screenshotsFolder.mkdirs();
        }
        // Устанавливаем путь для сохранения скриншотов
        Configuration.reportsFolder = screenshotsFolder.getAbsolutePath();
    }

    // Правило для перехвата ошибок и создания скриншота при провале теста
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            Selenide.screenshot("failure-" + description.getMethodName());
        }
    };

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    @Before
    public void init(){
        setUp();
    }

    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}


