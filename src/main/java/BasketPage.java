import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage {
    WebDriver driver;

    By totalPrice = By.xpath("");
    By plus = By.xpath("//BUTTON[contains(@class, 'button_plus')]");
    By del = By.xpath("(//I[@class='remove-button__icon'])[2]");
    By ret = By.xpath("//SPAN[@class='pseudo-link'][text()='Вернуть удалённый товар']");

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTotalPriceIs(String expectedCost){
        String price = driver.findElement(totalPrice).getAttribute("");
        Assert.assertEquals("Сумма в корзине не соотвествует ожидаемой", expectedCost, price);

    }
    public void pressPlus(){
        driver.findElement(plus).click();
    }
    public void delete(){
        driver.findElement(del).click();

    }
    public void retern(){
        driver.findElement(ret).click();
    }
}
