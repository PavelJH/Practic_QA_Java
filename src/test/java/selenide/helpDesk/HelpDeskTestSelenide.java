package selenide.helpDesk;

import com.github.javafaker.Faker;
import core.BaseTest;
import helpers.TestValues;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import selenium.ConfigSeleniumProvider;
import selenium.helpDesk.TestUtils;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static helpers.StringModifierSelenium.getUniqueString;


public class HelpDeskTestSelenide extends BaseTest {
    private final static String BASE_URL = "https://at-sandbox.workbench.lanit.ru/tickets/submit/";
    private final static String SUMMARY_OF_THE_PROBLEM = "Павел тест ";
    private final static String DESCRIPTION = "Сообщение в техническую поддержку от Павла Селинид";
    private final static String EMAIL = "test@gmail.com";

    private final static String LOGIN = "admin";
    private final static String PASSWORD = "adminat";

    private static final Faker faker = new Faker();

    public static String generateFakeEmail() {
        return faker.internet().emailAddress();
    }

    @Test
    public void checkTicket(){
        String fakeEmail = TestUtils.generateFakeEmail();
        String summaryOfTheProblem = getUniqueString(TestValues.SUMMARY_OF_THE_PROBLEM);

        MainPageSelenide mainPageSelenide = new MainPageSelenide(BASE_URL);
        mainPageSelenide.createTicket(summaryOfTheProblem, TestValues.DESCRIPTION, fakeEmail, ConfigSeleniumProvider.QUEUE_SOME_PRODUCT, mainPageSelenide.getRandomPriority());
        mainPageSelenide.waitForLoading();
        mainPageSelenide.getTitleText().shouldHave(text(summaryOfTheProblem));
        mainPageSelenide.openLoginPage();
        LoginPageSelenide loginPageSelenide = new LoginPageSelenide();
        loginPageSelenide.auth(LOGIN, PASSWORD);
        TicketsPageSelenide ticketsPageSelenide = new TicketsPageSelenide();
        ticketsPageSelenide.findTicket(summaryOfTheProblem);
        TicketPageSelenide ticketPageSelenide = new TicketPageSelenide();

        ticketPageSelenide.getTitleElement().shouldHave(text(summaryOfTheProblem));
        ticketPageSelenide.getDescriptionElement().shouldHave(exactText(TestValues.DESCRIPTION));
        ticketPageSelenide.getSubmitterEmailElement().shouldHave(exactText(fakeEmail));

    }

    @Test
    public void tryScreenshot(){
        MainPageSelenide mainPageSelenide = new MainPageSelenide(BASE_URL);
        mainPageSelenide.waitForLoading();
    }
    @Test
    public void tryScreenshot1(){
        MainPageSelenide mainPageSelenide = new MainPageSelenide(BASE_URL);
        Assert.assertFalse(true);
    }


}
