package selenium.helpDesk;

import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TicketPage extends BaseSeleniumPage {

    @FindBy(xpath = "//th[text()='Submitter E-Mail']/following::td[1]")
    private WebElement submitterEmail;

    @FindBy(xpath = "//h3")
    private WebElement title;

    @FindBy(xpath = "//td[@id='ticket-description']//p")
    private WebElement description;

    public TicketPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Далее берем текст из каждого нужного нам xpath
     * @return
     */

    public String getSubmitterEmail(){
        return submitterEmail.getText();
    }

    public String getTitle(){
        return title.getText();
    }

    public String getDescription(){
        return description.getText();
    }



}
