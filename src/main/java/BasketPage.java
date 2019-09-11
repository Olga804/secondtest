import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasketPage {
    WebDriver driver;

   By totalPrice = By.xpath("//div[contains(text(),'Итого')]/following-sibling::*/span");
   By ret = By.xpath("//SPAN[@class='pseudo-link'][text()='Вернуть удалённый товар']");

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTotalPriceIs(Integer value){
        String price = driver.findElement(totalPrice).getAttribute("textContent");
        Trash.put("AllPrice", price);
        Assert.assertEquals(value, Trash.get("AllPrice"));
    }


   public void chekPrice(String name, Integer value){
        String product = String.format(".//div/a[contains(text(),'%s')]/../../following-sibling::*//div[@class='item-price']/span", name);
       Trash.put("in basket", driver.findElement(By.xpath (product)).getAttribute("textContent"));
       Assert.assertEquals(Trash.get("in basket"), value);
   }
    public void pressPlus(String name){
        String product = String.format(".//div/a[contains(text(),'%s')]/../../following-sibling::*//div/button[contains(@class,'button_plus')]", name);
        ProductCard.wai( By.xpath(product),By.xpath("//span[contains(text(), 'Моя корзина')]/following-sibling::*"));


    }

    public void delete(String name){
        String product = String.format(".//div/a[contains(text(),'%s')]/../../following-sibling::*[contains(@class, 'button')]/button", name);
        driver.findElement(By.xpath(product)).click();

    }
    protected void isElementPresent(String name, boolean b){
        String product = String.format(".//div/a[contains(text(),'%s')]/../../following-sibling::*[contains(@class, 'button')]/button", name);

        try{
            driver.findElement(By.xpath(product));
            Assert.assertEquals(b, true);
        }
        catch(NoSuchElementException e){
            Assert.assertEquals(b, false);
        }
    }


    public void retern(){
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ret));
        driver.findElement(ret).click();
    }


  public void isSelected(boolean x, boolean y, boolean z, String name ){
      String product1 = String.format(".//div/a[contains(text(),'%s')]" +
              "/../../following-sibling::*//div/button[contains(@class,'button_plus')]" +
              "/../../../following-sibling::*//div/label[contains(text(),'Обычная')]/..",name);
      WebElement checkBox = driver.findElement(By.xpath(product1));
      Assert.assertEquals("x",x,checkBox.getAttribute("class").contains("checked") );
      String product2 = String.format(".//div/a[contains(text(),'%s')]" +
              "/../../following-sibling::*//div/button[contains(@class,'button_plus')]" +
              "/../../../following-sibling::*//div/label[contains(text(),'1 год')]/..",name);
      checkBox = driver.findElement(By.xpath(product2));
      Assert.assertEquals("y",y,checkBox.getAttribute("class").contains("checked") );
      String product3 = String.format(".//div/a[contains(text(),'%s')]" +
              "/../../following-sibling::*//div/button[contains(@class,'button_plus')]" +
              "/../../../following-sibling::*//div/label[contains(text(),'2 года')]/..",name);
      checkBox = driver.findElement(By.xpath(product3));
      Assert.assertEquals("z",z,checkBox.getAttribute("class").contains("checked"));



  }
}
