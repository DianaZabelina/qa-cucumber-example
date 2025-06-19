package glueSteps.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import utils.pageResolver.Page;
import utils.pageResolver.Path;
import utils.pageResolver.Title;

@Title("Товары")
@Path("/inventory.html")
public class swagLabs_02_InventoryPage extends Page {

    @FindBy(xpath = "//*[@id='add-to-cart-sauce-labs-backpack']")
    @Title("Add to cart Sauce Labs Backpack")
    private SelenideElement AddToCartSauceLabsBackpackButton;
}
