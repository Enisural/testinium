package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class BasketPage {

    public WebDriver driver;

    public BasketPage(WebDriver rdriver) {
        driver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(className = "real-discounted-price")                //Ürün fiyat
    WebElement basketPrice;

    @FindBy(className = "//*[@id=\"submit-cart\"]/div/div[2]/div[3]/div/div[1]/div/div[5]/div[1]/div/ul/li[1]/div[1]")                  //Şuan kaç ürün olduğu
    WebElement productNum;


    @FindBy(className = "/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div[2]/div[6]/div[2]/div[2]/div[3]/div/div[2]/div/a/i")           //Ürün Silme
    WebElement removeProduct;

    @FindBy(xpath = "//*[@id=\"submit-cart\"]/div/div[2]/div[3]/div/div[1]/div/div[5]/div[1]/div/ul/li[3]/div[1]")
    WebElement discount;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div[2]/div[6]/div[2]/div[2]/div[4]/div/div[2]/select/option")
    List<WebElement> selectCount;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div[2]/div[6]/div[2]/div[2]/div[4]/div/div[2]/select")
    WebElement clickCount;


    public void increaseProduct() throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(clickCount).perform();
        clickCount.click();
        Thread.sleep(500);
        selectCount.get(1).click();



    }

    public String countProduct(){

        return productNum.getText();
    }

    public void deleteProduct(){
        Actions action = new Actions(driver);
        action.moveToElement(removeProduct).perform();
        removeProduct.click();
    }

    public String basketPriceTxt(){
        return basketPrice.getText();
    }

    public boolean discountTxt(){
        boolean x = false;
        try{
            x = discount.isDisplayed();
        } catch (Exception e){
            System.out.println("No Element");

        }
        return x;
    }



}
