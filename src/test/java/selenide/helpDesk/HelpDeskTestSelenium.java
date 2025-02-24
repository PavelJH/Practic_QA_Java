package selenide.helpDesk;

import core.BaseTest;
import helpers.TestValues;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;


public class HelpDeskTestSelenium extends BaseTest {
    private final static String BASE_URL = "https://at-sandbox.workbench.lanit.ru/tickets/submit/";
    private final static String SUMMARY_OF_THE_PROBLEM = "Павел тест ";
    private final static String DESCRIPTION = "Сообщение в техническую поддержку от Павла Селинид";
    private final static String EMAIL = "test@gmail.com";

    private final static String LOGIN = "admin";
    private final static String PASSWORD = "adminat";

    @Test
    public void checkHref(){
        MainPageSelenide mainPageSelenide = new MainPageSelenide(BASE_URL);
        mainPageSelenide.createTicket(SUMMARY_OF_THE_PROBLEM, DESCRIPTION, EMAIL);
        mainPageSelenide.openLoginPage();
        LoginPageSelenide loginPageSelenide = new LoginPageSelenide();
        loginPageSelenide.auth(LOGIN, PASSWORD);
        TicketsPageSelenide ticketsPageSelenide = new TicketsPageSelenide();
        ticketsPageSelenide.findTicket(SUMMARY_OF_THE_PROBLEM);
        TicketPageSelenide ticketPageSelenide = new TicketPageSelenide();

        ticketPageSelenide.getTitleElement().shouldHave(text(SUMMARY_OF_THE_PROBLEM));
        ticketPageSelenide.getDescriptionElement().shouldHave(exactText(TestValues.DESCRIPTION));
        ticketPageSelenide.getSubmitterEmailElement().shouldHave(exactText(TestValues.EMAIL));
        int a = 0;




    }

}
