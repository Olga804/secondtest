import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.util.function.Function;

public class ProductCard {

    By productPrice = By.xpath("//span[contains(@class,'price-value')]");
    By addToBasket = By.xpath("//span[contains(text(),'Купить')]");
    By goToBasket = By.xpath("//a[contains(@rel, 'nofollow noopener')]/span/span");
    By formGarant = By.xpath("//SELECT[@class='form-control select']");


    static WebDriver driver;
    public ProductCard(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void savePriceOfCurrentProduct(String key){
        String value = driver.findElement(productPrice).getAttribute("textContent");
        Trash.put(key, value);
    }

    public void addToBasket(){
        wai(addToBasket,By.xpath("//span[contains(@data-of,'totalPrice')]"));
    }

    public BasketPage goToBasket(){
        driver.findElement(goToBasket).click();
        return new BasketPage(driver);
    }
    public void dopGarant(String text){
        new Select(driver.findElement(formGarant)).selectByVisibleText(text);
    }


    public void checkTotalPriceIs(Integer value){
        String price = driver.findElement(By.xpath("(//span[contains(@data-of,'totalPrice')])[2]")).getAttribute("textContent");
        Trash.put("AllPrice", price);
        Assert.assertEquals(value, Trash.get("AllPrice"));
    }
    public static void wai(By where, By what){
        String oldValue = driver.findElement(what).getAttribute("textContent");
        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !webDriver.findElement(what).getAttribute("textContent").equals(oldValue);
            }
        };
        //действие для изменения значения
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        driver.findElement(where).click();
        wait.until(valueChanged);

    }
}
