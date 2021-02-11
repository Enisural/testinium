package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{

    public WebDriver driver;

    public LoginPage(WebDriver rdriver)
    {
        driver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(id = "L-UserNameField")
    WebElement userName;

    @FindBy(id = "L-PasswordField")
    WebElement userPass;

    @FindBy(id = "gg-login-enter")
    WebElement logBtn;

    @FindBy(xpath = "//*[@id=\"gg-login-box\"]/div[1]/div/h1")
    WebElement loginPageText;

    public void clickLoginBtn(){

        logBtn.click();

    }
    public void setEmail(String mail){
        userName.sendKeys(mail);
    }

    public void setPwd(String pass){
        userPass.sendKeys(pass);
    }

    public String isInLoginPage(){
        return loginPageText.getText();
    }

}
