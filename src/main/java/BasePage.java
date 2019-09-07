import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;

    public BasePage() {
        this.driver = Trash.driver;
        PageFactory.initElements(driver, this);
    }
}
