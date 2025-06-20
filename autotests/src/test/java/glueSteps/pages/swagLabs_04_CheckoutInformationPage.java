package glueSteps.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import utils.pageResolver.Optional;
import utils.pageResolver.Page;
import utils.pageResolver.Path;
import utils.pageResolver.Title;

@Title("Checkout Your Information")
@Path("/checkout-step-one.html")
public class swagLabs_04_CheckoutInformationPage extends Page {

    @FindBy(xpath = "//*[@id='first-name']")
    @Title("First-name")
    private SelenideElement firstNameField;

    @FindBy(xpath = "//*[@id='last-name']")
    @Title("Last-name")
    private SelenideElement lastNameField;

    @FindBy(xpath = "//*[@id='postal-code']")
    @Title("Postal-code")
    private SelenideElement postalCodeField;

    @FindBy(xpath = "//*[@id='continue']")
    @Title("Continue")
    private SelenideElement continueButton;

    @Optional
    @FindBy(xpath = "//*[@data-test='error']")
    @Title("Error")
    private SelenideElement errorMessage;
}
