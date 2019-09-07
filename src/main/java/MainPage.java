import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    @FindBy(xpath = "//input[contains(@class,'ui-autocomplete-input')]")
    private WebElement searchTextField;


    @FindBy(xpath = "(//SPAN[@class='ui-input-search__icon ui-input-search__icon_search'])[2]")
    WebElement searchButton;


    public void search(String text){
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(searchTextField));
        searchTextField.click();
        searchTextField.sendKeys(text);
        searchButton.click();
    }

}
