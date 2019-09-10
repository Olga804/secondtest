import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProductCard {

    By productPrice = By.xpath("//span[contains(@class,'price-value')]");
    By addToBasket = By.xpath("//span[contains(text(),'Купить')]");
    By goToBasket = By.xpath("//a[contains(@rel, 'nofollow noopener')]/span/span");
    By formGarant = By.xpath("//SELECT[@class='form-control select']");


    WebDriver driver;
    public ProductCard(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void savePriceOfCurrentProduct(String key){
        String value = driver.findElement(productPrice).getAttribute("textContent");
        Trash.put(key, value);
    }

    public void addToBasket(){
        driver.findElement(addToBasket).click();



    }

    public BasketPage goToBasket(){
        driver.findElement(goToBasket).click();
        return new BasketPage(driver);
    }
    public void dopGarant(String text){
        new Select(driver.findElement(formGarant)).selectByVisibleText(text);
    }


    public void checkTotalPriceIs(Integer value){
        String price = driver.findElement(By.xpath("//span[contains(@data-of,'totalPrice')]")).getAttribute("textContent");
        Trash.put("AllPrice", price);
        Assert.assertEquals(value, Trash.get("AllPrice"));
    }
}
