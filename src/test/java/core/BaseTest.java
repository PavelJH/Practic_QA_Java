package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

abstract public class BaseTest {
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
