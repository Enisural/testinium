package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;


public class ProductsPage {

    public WebDriver driver;

    public ProductsPage(WebDriver rdriver)
    {
        driver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(className = "image-container")
    List<WebElement> items;

    @FindBy(id = "sp-price-highPrice")
    WebElement item;

    @FindBy(className = "next-link")
    WebElement pageNum;

    @FindBy(className = "current")
    WebElement currPage;

    @FindBy(className = "search-result-keyword")
    WebElement pageText;



    public void pageChanger(){

        Actions actions=new Actions(driver);
        actions.moveToElement(pageNum).perform();
        pageNum.click();

    }

    public void clickRandomItem() {
        Actions action = new Actions(driver);
        int itemNum = items.size();
        Random rand = new Random();
        int randomNum = rand.nextInt((itemNum - 1) + 1);
        action.moveToElement(items.get(randomNum)).click().perform();
    }

    public String getPage(){
        return currPage.getText();
    }

    public String isIncompPage(){
        return pageText.getText();
    }

    public String itemVal(){return item.getText();}

}

