package selenide.helpDesk;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import selenium.helpDesk.LoginPage;

import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPageSelenide {

    private final SelenideElement queueList = $x("//select[@id = 'id_queue']");
    private final SelenideElement queueElementNumberOne = $x("//select[@id='id_queue']//option[@value='1']");
    private final SelenideElement summaryOfTheProblemField = $("#id_title");
    private final SelenideElement priorityList = $x("//select[@id='id_priority']");

    private final SelenideElement descriptionField = $("#id_body");
    private final SelenideElement dueDate = $("#id_due_date");
    private final SelenideElement dateValue = $x("//table[@class='ui-datepicker-calendar']//a[text()='23']");
    private final SelenideElement emailField = $("#id_submitter_email");
    private final SelenideElement submitButton = $x("//button[@type = 'submit']");
    private final SelenideElement logInButton = $("#userDropdown");
    private final SelenideElement summaryOfTheProblemCheck = $x("//table[@class='table table-hover table-bordered table-striped']/caption");
    private final SelenideElement queueTextForWaiter = $x("//th[@colspan='2' and contains(text(), 'Queue')]");


    public MainPageSelenide(String url){
        Selenide.open(url);
    }

    public void selectQueueOptionByVisibleText(String optionTextQueue) {
        queueList.selectOption(optionTextQueue);
    }
    public void selectPriorityOptionByVisibleText(String optionTextPriority) {
        priorityList.selectOption(optionTextPriority);
    }

    public void selectRandomDate() {
        // Генерируем случайное число от 1 до 28 включительно
        int randomDay = ThreadLocalRandom.current().nextInt(1, 29);
        // Формируем xpath с подставленным случайным числом
        String xpath = String.format("//table[@class='ui-datepicker-calendar']//a[text()='%d']", randomDay);
        // Находим элемент и, например, кликаем по нему
        SelenideElement dateElement = $x(xpath);
        dateElement.click();
    }

    public void createTicket(String summaryOfTheProblemValue, String descriptionValue, String emailValue, String optionQueue, String optionPriority){
//        queueList.click();
//        queueElementNumberOne.click();
        selectQueueOptionByVisibleText(optionQueue);
        summaryOfTheProblemField.setValue(summaryOfTheProblemValue);
        descriptionField.setValue(descriptionValue);
//        priorityList.click();
        selectPriorityOptionByVisibleText(optionPriority);
        dueDate.click();
        selectRandomDate();
//        dateValue.click();
        emailField.setValue(emailValue);
        submitButton.click();
    }

    public void waitForLoading() {
        queueTextForWaiter.shouldBe(visible);
    }

    public void openLoginPage(){
        logInButton.click();
        new LoginPage();
    }

    public SelenideElement getTitleText(){
        return summaryOfTheProblemCheck;
    }



}
