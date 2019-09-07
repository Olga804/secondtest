import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/*
1) открыть dns-shop
2) в поиске найти playstation
3) кликнуть по playstation 4 slim black
4) запомнить цену
5) Доп.гарантия - выбрать 2 года
6) дождаться изменения цены и запомнить цену с гарантией
7) Нажать Купить
8) выполнить поиск Detroit
9) запомнить цену
10) нажать купить
11) проверить что цена корзины стала равна сумме покупок
12) перейри в корзину
13) проверить, что для приставки выбрана гарантия на 2 года
14) проверить цену каждого из товаров и сумму
15) удалить из корзины Detroit
16) проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
17) добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
18) нажать вернуть удаленный товар, проверить что Detroit появился в корзине и сумма увеличилась на его значение
 */

public class DNSTest {
    static WebDriver driver;


    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", TestProperties.getInstance().getProperty("path.chrome"));
        driver = new ChromeDriver();
        driver.get(TestProperties.getInstance().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Trash.driver = driver;
    }
    @Test
    public void checkPS(){
        MainPage mainPage = new MainPage();
        mainPage.search("playstation");

        ResultsPage resultsPage = new ResultsPage(driver);
        resultsPage.chooseProduct("PlayStation 4 Slim Black");
        new Select(driver.findElement(By.xpath("//SELECT[@class='form-control select']"))).selectByVisibleText("2 года"); //добавляем PS жмем гарантию на 2 года
        ProductCard productCard = new ProductCard(driver);
        productCard.savePriceOfCurrentProduct("product PS without");
        productCard.dopGarant("2 года");
        // дождаться изменения цены
        productCard.savePriceOfCurrentProduct("product PS with");
        productCard.addToBasket();
        mainPage.search("Detroit");
        productCard.savePriceOfCurrentProduct("product D");
        productCard.addToBasket();
        BasketPage basketPage = productCard.goToBasket();
        //добавить проверку цены приставки с гарантией
        //добавить проверку цены игрушки
        //добавить проверку суммы
        //добавить проверку гарантии
        basketPage.delete();
        //добавить проверку удаления
        //добавить проверку суммы
        basketPage.pressPlus();
        //вставить ожидание надатия плюсика
        basketPage.pressPlus();
        //добавить проверку утроенной цены приставки
        basketPage.retern();
        //добавить проверку возврата
        //добавить проверку цены

    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
}
