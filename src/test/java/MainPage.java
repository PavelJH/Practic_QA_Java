import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/*
Главная страница сайта Appleinsider.com
 */
public class MainPage {
    private final SelenideElement textBoxInput = $x("//input[@class = 'header__search-inp']");

    public MainPage(String url){
        // открывает браузер и сразу заходит на сайт, который нужно в тесте указать в скобках
        // MainPage mainPage = new MainPage(здесь указать сайт);
        Selenide.open(url);
    }

    public void openWebSite(String url) {
        // при таком методе в тесте нужно писать MainPage mainPage = new MainPage(сюда ничего не вставлять);
        // mainPage.openWebSite(BASE_URL);
        Selenide.open(url);
    }

    public void search(String searchString) {
        textBoxInput.setValue(searchString); //передаем сюда textBoxInput и вставляем нужный текст(searchString), который хотим передать
        textBoxInput.sendKeys(Keys.ENTER); //нажимаем на Enter
    }

    public void acceptCookies() {
        // Ищем кнопку с текстом «Принять/Contest»
        SelenideElement cookieButton = $x("//p[@class = 'fc-button-label']");
        if (cookieButton.exists()) {
            cookieButton.shouldBe(visible).click();
        }
    }
}
