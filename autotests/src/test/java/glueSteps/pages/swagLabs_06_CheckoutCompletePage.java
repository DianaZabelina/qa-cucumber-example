package glueSteps.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import utils.pageResolver.Page;
import utils.pageResolver.Path;
import utils.pageResolver.Title;

@Title("Checkout Complete")
@Path("/checkout-complete.html")
public class swagLabs_06_CheckoutCompletePage extends Page {

    @FindBy(xpath = "//*[@data-test='complete-header']")
    @Title("Complete-header")
    private SelenideElement completeHeader;

    @FindBy(xpath = "//*[@id='back-to-products']")
    @Title("Back Home")
    private SelenideElement backHomeButton;
}
