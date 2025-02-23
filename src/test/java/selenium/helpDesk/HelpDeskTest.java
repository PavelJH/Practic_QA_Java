package selenium.helpDesk;


import core.BaseSeleniumTest;
import helpers.TestValues;
import org.junit.Assert;
import org.junit.Test;
import selenium.ConfigSeleniumProvider;


import static helpers.StringModifier.getUniqueString;

public class HelpDeskTest extends BaseSeleniumTest {

    @Test
    public void checkTicket(){
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
        TicketPage ticketPage = new MainPageTicket().createTicket(summaryOfTheProblem, TestValues.DESCRIPTION, TestValues.EMAIL)
                .openLoginPage()
                .auth(ConfigSeleniumProvider.DEMO_LOGIN, ConfigSeleniumProvider.DEMO_PASSWORD)
                .findTicket(summaryOfTheProblem);
        Assert.assertTrue(ticketPage.getTitle().contains(summaryOfTheProblem));// Получаем ticketPage.getTitle() со страницы и сравниваем его с summaryOfTheProblem
        Assert.assertEquals(ticketPage.getDescription(), TestValues.DESCRIPTION);
        Assert.assertEquals(ticketPage.getSubmitterEmail(), TestValues.EMAIL);

    }

    /**
     * TODO: Сделать проверку страницы, загрузилась ли она
     * нету ли ошибок
     * добавить Waiter Class
     * дописать остальные поля
     * сделать под становления даты и выбора с выпадающего списка
     */



}
