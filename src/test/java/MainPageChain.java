import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/*
Главная страница сайта Appleinsider.com
 */
public class MainPageChain {
    private final SelenideElement textBoxInput = $x("//input[@class = 'header__search-inp']");

    public MainPageChain(String url) {
        // Открывает браузер и сразу заходит на указанный сайт
        Selenide.open(url);
    }

    public MainPageChain openWebSite(String url) {
        // Открывает указанный сайт и возвращает текущий объект для цепочки вызовов
        Selenide.open(url);
        return this;
    }

    /**
     * Метод для выполнения поиска.
     *
     * @param searchString - поисковый запрос
     * @return объект SearchPage для дальнейшей работы
     */
    public SearchPage search(String searchString) {
        textBoxInput.setValue(searchString);
        textBoxInput.sendKeys(Keys.ENTER);
        return new SearchPage();
    }

    /**
     * Метод для принятия куки, если всплывающее окно отображается.
     *
     * @return текущий объект MainPageChain для цепочки вызовов
     */
    public MainPageChain acceptCookies() {
        // Ищем кнопку с текстом «Принять/Contest»
        SelenideElement cookieButton = $x("//p[@class = 'fc-button-label']");
        if (cookieButton.exists()) {
            cookieButton.shouldBe(visible).click();
        }
        return this;
    }
}
