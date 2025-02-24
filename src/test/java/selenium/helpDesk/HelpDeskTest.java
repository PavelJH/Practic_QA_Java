package selenium.helpDesk;

import core.BaseSeleniumTest;
import helpers.TestValues;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import selenium.ConfigSeleniumProvider;
import static helpers.StringModifier.getUniqueString;


public class HelpDeskTest extends BaseSeleniumTest {

    @Test
    public void checkTicket(){
        String queueValue_01 = "Django Helpdesk";
        String queueValue_02 = "Some Product";

        String priorityValue_01 = "1. Critical";
        String priorityValue_02 = "2. High";
        String priorityValue_03 = "3. Normal";
        String priorityValue_04 = "4. Low";
        String priorityValue_05 = "5. Very Low";
//        //String summaryOfTheProblem = "Павел тест" + " " +new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        //String summaryOfTheProblem = "Павел тест";
//        String summaryOfTheProblem = getUniqueString("Павел тест ");
//        String description = "Сообщение в техническую поддержку от Павла Селениум";
//        String email = "test@gmail.com";
        // Перенесены в другой класс TestValues

        String summaryOfTheProblem = getUniqueString(TestValues.SUMMARY_OF_THE_PROBLEM); // Можно на прямую вставить данную строку из класса TestValues

//        MainPageTicket mainPageTicket = new MainPageTicket(); // Инициализируем класс

        /**
         * Так как TicketPage является финальным классом, мы его сразу делаем в тестовый
         */
        TicketPage ticketPage = new MainPageTicket().createTicket(summaryOfTheProblem, TestValues.DESCRIPTION, TestValues.EMAIL, queueValue_02, priorityValue_05)
                .openLoginPage()
                .auth(ConfigSeleniumProvider.DEMO_LOGIN, ConfigSeleniumProvider.DEMO_PASSWORD)
                .findTicket(summaryOfTheProblem);
        Assert.assertTrue(ticketPage.getTitle().contains(summaryOfTheProblem));// Получаем ticketPage.getTitle() со страницы и сравниваем его с summaryOfTheProblem
        Assert.assertEquals(ticketPage.getDescription(), TestValues.DESCRIPTION);
        Assert.assertEquals(ticketPage.getSubmitterEmail(), TestValues.EMAIL);

    }

    /**
     * TODO: Сделать проверку страницы, загрузилась ли она +
     * нету ли ошибок +
     * добавить Waiter Class
     * дописать остальные поля +
     * сделать под становления даты и выбора с выпадающего списка +
     * Скриншот +
     */

    @Test
    public void checkTicketFullVersion(){
        String summaryOfTheProblem = getUniqueString(TestValues.SUMMARY_OF_THE_PROBLEM);


        MainPageTicket mainPageTicket = new MainPageTicket();
        mainPageTicket.createTicket(summaryOfTheProblem, TestValues.DESCRIPTION, TestValues.EMAIL, ConfigSeleniumProvider.QUEUE_SOMEPRODUCT, ConfigSeleniumProvider.PRIORITY_LOW);
        Assert.assertTrue(mainPageTicket.checkTitle().contains(summaryOfTheProblem));
        mainPageTicket.openLoginPage();
        LoginPage loginPage = new LoginPage();
        loginPage.auth(ConfigSeleniumProvider.DEMO_LOGIN, ConfigSeleniumProvider.DEMO_PASSWORD);
        TicketsPage ticketsPage = new TicketsPage();
        ticketsPage.findTicket(summaryOfTheProblem);
        TicketPage ticketPage = new TicketPage();
        Assert.assertTrue(ticketPage.getTitle().contains(summaryOfTheProblem));
        Assert.assertEquals(ticketPage.getDescription(), TestValues.DESCRIPTION);
        Assert.assertEquals(ticketPage.getSubmitterEmail(), TestValues.EMAIL);
    }

    @Test
    public void checkTicketErrorScreenShoot(){
        String summaryOfTheProblem = getUniqueString(TestValues.SUMMARY_OF_THE_PROBLEM);


        MainPageTicket mainPageTicket = new MainPageTicket();
        mainPageTicket.createTicket(summaryOfTheProblem, TestValues.DESCRIPTION_Eng, TestValues.EMAIL, ConfigSeleniumProvider.QUEUE_SOMEPRODUCT, ConfigSeleniumProvider.PRIORITY_LOW);
        //Assert.assertTrue(mainPageTicket.checkTitle().contains(summaryOfTheProblem));
        mainPageTicket.waitForLoading();
        mainPageTicket.openLoginPage();
        LoginPage loginPage = new LoginPage();
        loginPage.auth(ConfigSeleniumProvider.DEMO_LOGIN, ConfigSeleniumProvider.DEMO_PASSWORD);
        TicketsPage ticketsPage = new TicketsPage();
        ticketsPage.findTicket(summaryOfTheProblem);
        TicketPage ticketPage = new TicketPage();
        Assert.assertTrue(ticketPage.getTitle().contains(summaryOfTheProblem));
        Assert.assertEquals(ticketPage.getDescription(), TestValues.DESCRIPTION_Eng);
        Assert.assertEquals(ticketPage.getSubmitterEmail(), TestValues.EMAIL);
    }

    @Test
    public void checkTicketWithWaiters(){
        String summaryOfTheProblem = getUniqueString(TestValues.SUMMARY_OF_THE_PROBLEM);


        MainPageTicket mainPageTicket = new MainPageTicket();
        mainPageTicket.createTicket(summaryOfTheProblem, TestValues.DESCRIPTION, TestValues.EMAIL, ConfigSeleniumProvider.QUEUE_SOMEPRODUCT, ConfigSeleniumProvider.PRIORITY_LOW);
        mainPageTicket.waitForLoading();
        mainPageTicket.openLoginPage();
        LoginPage loginPage = new LoginPage();
        loginPage.auth(ConfigSeleniumProvider.DEMO_LOGIN, ConfigSeleniumProvider.DEMO_PASSWORD);
        TicketsPage ticketsPage = new TicketsPage();
        ticketsPage.findTicket(summaryOfTheProblem);
        TicketPage ticketPage = new TicketPage();
        Assert.assertTrue(ticketPage.getTitle().contains(summaryOfTheProblem));
        Assert.assertEquals(ticketPage.getDescription(), TestValues.DESCRIPTION);
        Assert.assertEquals(ticketPage.getSubmitterEmail(), TestValues.EMAIL);
    }

}
