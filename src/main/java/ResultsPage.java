import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {

    WebDriver driver;

    public ResultsPage(WebDriver driver){
        this.driver = driver;
    }

    public void chooseProduct(String name){
        String productXpath = String.format("//a[contains(text(), '%s')]", name);
        WebElement productItem = driver.findElement(By.xpath(productXpath));
        productItem.findElement(By.xpath(productXpath)).click();
    }
}
