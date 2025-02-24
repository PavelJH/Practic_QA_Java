package selenide.helpDesk;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TicketsPageSelenide {

    private final SelenideElement searchField = $x("//input[@id='search_query']");
    private final SelenideElement ticket = $x("//div[@class='tickettitle']");

    public void findTicket(String str) {
        searchField.setValue(str).pressEnter();
        ticket.click();
    }
}
