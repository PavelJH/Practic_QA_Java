package selenide.helpDesk;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import selenium.helpDesk.LoginPage;
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

    public void createTicket(String summaryOfTheProblemValue, String descriptionValue, String emailValue){
        queueList.click();
        queueElementNumberOne.click();
        summaryOfTheProblemField.setValue(summaryOfTheProblemValue);
        descriptionField.setValue(descriptionValue);
        dueDate.click();
        dateValue.click();
        emailField.setValue(emailValue);
        submitButton.click();
    }
    public LoginPage openLoginPage(){
        logInButton.click();
        return new LoginPage();
    }

}
