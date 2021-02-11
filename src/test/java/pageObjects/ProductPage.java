package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {

    public WebDriver driver;



    public ProductPage(WebDriver rdriver)
    {

        driver = rdriver;
        PageFactory.initElements(rdriver, this);

    }


    @FindBy (id = "sp-price-highPrice")
    WebElement productPrice;

    @FindBy (id = "add-to-basket")
    WebElement addChart;

    @FindBy (className = "header-cart-hidden-link")
    WebElement goBasket;

    @FindBy(className ="total-item-count" )
    WebElement basketCount;

    public String getPrice(){return productPrice.toString();}

    public void buyItem(){

        Actions action = new Actions(driver);
        action.moveToElement(addChart).perform();
        addChart.click();}

    public String baskCount(){ return basketCount.getText();}
    public void finishShop(){goBasket.click();}
}
