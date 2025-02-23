package selenium.helpDesk;


import core.BaseSeleniumPage;
import core.BaseSeleniumTest;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.ConfigSeleniumProvider;

public class MainPageTicket extends BaseSeleniumPage { //Всегда наследоваться или не будет работать WebDriver

    @FindBy(xpath = "//select[@id = 'id_queue']")//аннотация, которая позволяет производить поиск именно тогда когда обращаемся к данному элементу
    private WebElement queueList;

    @FindBy(xpath = "//select[@id='id_queue']//option[@value='1']")
    private WebElement queueElementNumberOne;

    @FindBy(id = "id_title")
    private WebElement summaryOfTheProblemField;

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



    public MainPageTicket() {
        driver.get(ConfigSeleniumProvider.URL);//открытие ссылки
        PageFactory.initElements(driver, this);//инициализация после открытия ссылки, всех элементов на этой страницы
    }

    public MainPageTicket createTicket(String summaryOfTheProblemValue, String descriptionValue, String emailValue){
        queueList.click();
        queueElementNumberOne.click();
        summaryOfTheProblemField.sendKeys(summaryOfTheProblemValue);
        descriptionField.sendKeys(descriptionValue);
        dueDate.click();
        dateValue.click();
        emailField.sendKeys(emailValue);
        submitButton.click();
        return this; // Возвращаем страницу, так как далее мы продолжаем с ней работать
    }

    public LoginPage openLoginPage(){
        logInButton.click();
        return new LoginPage();
    }

}
