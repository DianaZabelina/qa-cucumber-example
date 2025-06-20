package glueSteps.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import utils.pageResolver.Optional;
import utils.pageResolver.Page;
import utils.pageResolver.Path;
import utils.pageResolver.Title;

@Title("Checkout Overview")
@Path("/checkout-step-two.html")
public class swagLabs_05_CheckoutOverviewPage extends Page {

    @Optional
    @FindBy(xpath = "//*[@data-test='shopping-cart-badge']")
    @Title("Shopping cart badge")
    private SelenideElement shoppingCartBadge;

    @FindBy(xpath = "//*[@data-test='cart-list']")
    @Title("Your Cart")
    private SelenideElement yourCart;

    @FindBy(xpath = "//*[@id='finish']")
    @Title("Finish")
    private SelenideElement finishButton;
}
