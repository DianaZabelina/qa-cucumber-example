package glueSteps.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import utils.pageResolver.Optional;
import utils.pageResolver.Page;
import utils.pageResolver.Path;
import utils.pageResolver.Title;

@Title("Your Cart")
@Path("/cart.html")
public class swagLabs_03_CartPage extends Page {

    @Optional
    @FindBy(xpath = "//*[@data-test='shopping-cart-badge']")
    @Title("Shopping cart badge")
    private SelenideElement shoppingCartBadge;

    @FindBy(xpath = "//*[@data-test='cart-list']")
    @Title("Your Cart")
    private SelenideElement yourCart;

    @FindBy(xpath = "//*[@id='remove-sauce-labs-onesie']")
    @Title("Remove Sauce Labs Onesie")
    private SelenideElement removeSauceLabsOnesieButton;

    @FindBy(xpath = "//*[@id='checkout']")
    @Title("Checkout")
    private SelenideElement checkoutButton;
}
