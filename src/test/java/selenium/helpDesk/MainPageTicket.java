package selenium.helpDesk;

import com.github.javafaker.Faker;
import core.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.ConfigSeleniumProvider;
import wait.Wait;

import java.util.Locale;
import java.util.Random;


public class MainPageTicket extends BaseSeleniumPage { //Всегда наследоваться или не будет работать WebDriver
    Wait wait;
    @FindBy(xpath = "//select[@id = 'id_queue']")//аннотация, которая позволяет производить поиск именно тогда когда обращаемся к данному элементу
    private WebElement queueList;

//    @FindBy(xpath = "//select[@id='id_queue']//option[@value='1']")
//    private WebElement queueElementNumberOne;

    @FindBy(id = "id_title")
    private WebElement summaryOfTheProblemField;

    @FindBy(xpath = "//select[@id='id_priority']")
    private WebElement priorityList;

//    @FindBy(xpath = "//select[@id='id_priority']//option[@value='4']")
//    private WebElement priorityValue;

    @FindBy(id = "id_body")
    private WebElement descriptionField;

    @FindBy(id = "id_due_date")
    private WebElement dueDate;

    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']//a[text()='23']")
    private WebElement dateValue;

    @FindBy(id = "id_submitter_email")
    private WebElement emailField;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement submitButton;

    @FindBy(id = "userDropdown")
    private WebElement logInButton;

    @FindBy(xpath = "//table[@class='table table-hover table-bordered table-striped']/caption")
    private WebElement summaryOfTheProblemCheck;

    @FindBy(xpath = "//th[@colspan='2' and contains(text(), 'Queue')]")
    private WebElement queueTextForWaiter;



    public MainPageTicket() {
        driver.get(ConfigSeleniumProvider.URL);//открытие ссылки
        PageFactory.initElements(driver, this);//инициализация после открытия ссылки, всех элементов на этой страницы
    }

//    public String generateFakeDescriptionEng() {
//        Faker faker = new Faker();
//        // Можно сгенерировать, например, абзац текста
//        return faker.lorem().paragraph();
//    }

    public String generateFakeDescriptionRus() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.lorem().paragraph();
    }

    public void selectQueueOptionByVisibleText(String optionText) {
        Select select = new Select(queueList);
        select.selectByVisibleText(optionText);
    }

    public void selectPriorityOptionByVisibleText(String optionText) {
        Select select = new Select(priorityList);
        select.selectByVisibleText(optionText);
    }

//    public void selectDate(String day) {
//        String dynamicXpath = String.format("//table[@class='ui-datepicker-calendar']//a[text()='%s']", day);
//        driver.findElement(By.xpath(dynamicXpath)).click();
//    }

    public void selectRandomDate() {
        // Генерируем случайное число от 1 до 28
        int day = new Random().nextInt(28) + 1;
        // Формируем динамический xpath с подставленным числом
        String dynamicXpath = String.format("//table[@class='ui-datepicker-calendar']//a[text()='%d']", day);
        driver.findElement(By.xpath(dynamicXpath)).click();
    }

    public MainPageTicket createTicket(String summaryOfTheProblemValue, String descriptionValue, String emailValue, String queueValue, String priorityValue){
        selectQueueOptionByVisibleText(queueValue);
//        queueList.click();
        summaryOfTheProblemField.sendKeys(summaryOfTheProblemValue);
        priorityList.click();
        selectPriorityOptionByVisibleText(priorityValue);
        descriptionField.sendKeys(descriptionValue);
        dueDate.click();
        selectRandomDate();
        emailField.sendKeys(emailValue);
        submitButton.click();
        return this; // Возвращаем страницу, так как далее мы продолжаем с ней работать
    }

    public void waitForLoading() {
        wait = new Wait(driver);
        wait.forVisibility(queueTextForWaiter);
    }

    public LoginPage openLoginPage(){
        logInButton.click();
        return new LoginPage();
    }

    public String checkTitle(){
        return summaryOfTheProblemCheck.getText();
    }


}
