package selenide.helpDesk;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPageSelenide {

    private final SelenideElement loginField = $("#username");
    private final SelenideElement passwordField = $("#password");
    private final SelenideElement logInButton = $x("//input[@type ='submit']");


    public void auth(String login, String password){
        loginField.setValue(login);
        passwordField.setValue(password).pressEnter();
//        logInButton.click();
    }
}
