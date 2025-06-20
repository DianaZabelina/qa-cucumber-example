package glueSteps.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import utils.pageResolver.Page;
import utils.pageResolver.Path;
import utils.pageResolver.Title;

@Title("Main Page")
@Path("/")
public class swagLabs_01_MainPage extends Page {

    @FindBy(xpath = "//*[@id='user-name']")
    @Title("User-name")
    private SelenideElement loginField;

    @FindBy(xpath = "//*[@id='password']")
    @Title("Password")
    private SelenideElement passwordField;

    @FindBy(xpath = "//*[@id='login-button']")
    @Title("Login")
    private SelenideElement enterButton;
}
