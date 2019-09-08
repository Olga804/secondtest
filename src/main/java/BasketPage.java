import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.IntrospectionException;


public class BasketPage {
    WebDriver driver;

   By totalPrice = By.xpath("//div[contains(text(),'Итого')]/following-sibling::*/span");
    By plus = By.xpath("//BUTTON[contains(@class, 'button_plus')]");
    By del = By.xpath("(//I[@class='remove-button__icon'])[2]");
    By ret = By.xpath("//SPAN[@class='pseudo-link'][text()='Вернуть удалённый товар']");

   // By onePrice = By.xpath (String.format(".//div/a[contains(text(),'%s')]/../../following-sibling::*//div[@class='item-price']/span"));
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
        String product = String.format(".//div/a[contains(text(),'PlayStation')]/../../following-sibling::*//div/button[contains(@class,'button_plus')]", name);
        driver.findElement(By.xpath(product)).isEnabled();
        driver.findElement(By.xpath(product)).click();
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(product)));
    }
    public void delete(String name){
        String product = String.format(".//div/a[contains(text(),'%s')]/../../following-sibling::*[contains(@class, 'button')]/button", name);
        driver.findElement(By.xpath(product)).click();
       /* boolean path = true;
        while(path){
            try {
                driver.findElement(By.xpath(product));
            } catch (NoSuchElementException e) {
                path=false;
            }
            path = true;
        }
        Assert.assertEquals(path, false);

        */
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
        driver.findElement(ret).click();
    }

}
