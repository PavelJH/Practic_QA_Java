package selenide.helpDesk;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TicketPageSelenide {

    private final SelenideElement submitterEmail = $x("//th[text()='Submitter E-Mail']/following::td[1]");
    private final SelenideElement title = $x("//h3");
    private final SelenideElement description = $x("//td[@id='ticket-description']//p");

    /**
     * Эти методы — "геттеры" (getters). Каждый из них возвращает объект типа SelenideElement,
     * который представляет собой элемент страницы. Например, метод getSubmitterEmailElement() возвращает элемент email пользователя,
     * getTitleElement() — элемент заголовка, а getDescriptionElement() — элемент описания.
     * Эти методы предоставляют доступ к элементам, чтобы они могли использоваться для дальнейших взаимодействий,
     * например, для проверок или извлечения текста с помощью других методов Selenide (например, shouldHave, getText).
     *
     */

    public SelenideElement getSubmitterEmailElement(){
        return submitterEmail;
    }

    public SelenideElement getTitleElement(){
        return title;
    }

    public SelenideElement getDescriptionElement(){
        return description;
    }
}
