package glueSteps.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import utils.pageResolver.Optional;
import utils.pageResolver.Page;
import utils.pageResolver.Path;
import utils.pageResolver.Title;

@Title("Products")
@Path("/inventory.html")
public class swagLabs_02_ProductsPage extends Page {

    @Optional
    @FindBy(xpath = "//*[@data-test='shopping-cart-badge']")
    @Title("Shopping cart badge")
    private SelenideElement shoppingCartBadge;

    @FindBy(xpath = "//*[@id='add-to-cart-sauce-labs-backpack']")
    @Title("Add to cart Sauce Labs Backpack")
    private SelenideElement addToCartSauceLabsBackpackButton;

    @Optional
    @FindBy(xpath = "//*[@id='remove-sauce-labs-backpack']")
    @Title("Remove Sauce Labs Backpack")
    private SelenideElement removeSauceLabsBackpackButton;

    @FindBy(xpath = "//*[@id='add-to-cart-sauce-labs-bike-light']")
    @Title("Add to cart Sauce Labs Bike Light")
    private SelenideElement addToCartSauceLabsBikeLightButton;

    @Optional
    @FindBy(xpath = "//*[@id='remove-sauce-labs-bike-light']")
    @Title("Remove Sauce Labs Bike Light")
    private SelenideElement removeSauceLabsBikeLightButton;

    @FindBy(xpath = "//*[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
    @Title("Add to cart Sauce Labs Bolt T-Shirt")
    private SelenideElement addToCartSauceLabsBoltTShirtButton;

    @Optional
    @FindBy(xpath = "//*[@id='remove-sauce-labs-bolt-t-shirt']")
    @Title("Remove Sauce Labs Bolt T-Shirt")
    private SelenideElement removeSauceLabsBoltTShirtButton;

    @FindBy(xpath = "//*[@id='add-to-cart-sauce-labs-onesie']")
    @Title("Add to cart Sauce Labs Onesie")
    private SelenideElement addToCartSauceLabsOnesieButton;

    @Optional
    @FindBy(xpath = "//*[@id='remove-sauce-labs-onesie']")
    @Title("Remove Sauce Labs Onesie")
    private SelenideElement removeSauceLabsOnesieButton;

    @FindBy(xpath = "//*[@id='shopping_cart_container']")
    @Title("Shopping cart")
    private SelenideElement shoppingCart;
}
