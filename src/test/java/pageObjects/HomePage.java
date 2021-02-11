package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class HomePage {

    public WebDriver driver;

    public HomePage(WebDriver rdriver)
    {
        driver = rdriver;
        PageFactory.initElements(rdriver, this);
        WebDriverWait wait = new WebDriverWait(driver,30);
    }


    @FindBy(xpath = "//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]/div/div[2]")
    WebElement hoverloginBtn;

    @FindBy(xpath = "//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input")
    WebElement  searchBox;

    @FindBy(xpath = "//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button")
    WebElement  searchBtn;

    @FindBy(xpath = "//*[@id=\"main-header\"]/div[3]/div/div/div/div[3]/div/div[1]/div/div[2]")  //Hesab?m Yaz?s?
    WebElement  myAcc;

    @FindBy(xpath = "//*[contains(text(),'Giriş Yap')]")
    WebElement loginBtn;

    @FindBy (xpath = "//*[@id=\"__next\"]/main/section/section/a")
    WebElement cookie;


    public void hoverLoginBtn() throws InterruptedException {

        Actions action= new Actions(driver);
        action.moveToElement(hoverloginBtn).perform();
        Thread.sleep(500);
        List<WebElement> y= driver.findElements(By.xpath("//*[contains(text(),'Giriş Yap')]"));
        y.get(0).click();

    }

    public void clickLoginBtn(){

        loginBtn.click();

    }

    public void clickSearchBtn(){

        searchBtn.click();
    }

    public String isLogged(){

        return myAcc.getText();
    }

    public void setSearch(String srch){

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-header']/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input")));
        Actions action = new Actions(driver);
        action.moveToElement(searchBox).perform();
        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(srch);

    }
    public void cookieClose(){
        Actions actions=new Actions(driver);
        actions.moveToElement(cookie).perform();
        cookie.click();
    }

}
