package StepDefinitions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Context;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.*;
import pageObjects.*;
import utilities.ScenarioContext;

import static org.junit.Assert.*;


public class Step {
    public WebDriver driver;
    public HomePage hp;
    public LoginPage lp;
    public ProductsPage pp;
    public ProductPage p;
    public BasketPage bp;
    public ScenarioContext context;
    public static Logger logger;

    @Given("the home page of gittigidiyor is displayed")
    public void the_home_page_of_gittigidiyor_is_displayed() throws InterruptedException {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", "/Users/cvsburak/Downloads/chromedriver");
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-gpu");
        options.addArguments("Chrome/8.0");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        logger = Logger.getLogger("trendyol");
        PropertyConfigurator.configure("Log4j.properties");

        logger.info("Gittigidiyor açılıyor");
        driver = new ChromeDriver(options);
        driver.get("https://www.gittigidiyor.com/");
        Thread.sleep(1000);
        boolean a = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div/div/section[1]")).isDisplayed();
        logger.info("Kullanıcı anasayfada");
        assertTrue(a);
        hp=new HomePage(driver);
        p=new ProductPage(driver);
        pp=new ProductsPage(driver);
        bp= new BasketPage(driver);
        context = new ScenarioContext();


        hp.cookieClose();


    }

    @When("the member hover and clicks login")
    public void the_member_clicks_login() throws InterruptedException {
        // Write code here that turns the phrase above into concrete action
        logger.info("Login butonuna tıklanıyor");
        hp.hoverLoginBtn();
        Thread.sleep(500);
        lp=new LoginPage(driver);


    }

    @Then("the member on the login page")
    public void the_member_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(lp.isInLoginPage(), "Üye Girişi");
        logger.info("Kullanıcı" + lp.isInLoginPage() + " sayfasında");
    }
    @When("the member enters username {string} and password {string}")
    public void the_member_enters_username_and_password(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        logger.info(string + " ve " + string2 + " ile giriş yapılıyor");
        lp.setEmail(string);
        lp.setPwd(string2);
    }
    @When("Clicks to login button")
    public void clicks_to_login_button() {
        // Write code here that turns the phrase above into concrete actions
        lp.clickLoginBtn();
    }
    @Then("member must be on home page and login text should change to {string}")
    public void member_must_be_on_home_page_and_login_text_should_change_to(String string) {
        // Write code here that turns the phrase above into concrete actions
        boolean y=hp.isLogged().contains(string);
        assertTrue(y);
        logger.info("Giriş başarılı");
    }
    @When("member write {string} on the search bar")
    public void member_write_on_the_search_bar(String string) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        logger.info(string + " ürünü aratılıyor");
        Thread.sleep(500);
        hp.setSearch(string);

    }

    @And("member clicks the find button")
    public void member_clicks_the_find_button() {
        // Write code here that turns the phrase above into concrete action
        hp.clickSearchBtn();
    }
    @Then("the member on the {string} page")
    public void the_member_on_the_page(String string) {

        assertEquals(pp.isIncompPage(),string);
        logger.info("Kullanıcı " + pp.isIncompPage() + " sayfasında");
    }
    @When("the member goes second page")
    public void the_member_select_random_product_on_the_page() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("ikinci sayfaya geçiliyor");
         pp.pageChanger();



    }
    @Then("member must be on second page and the selects random product")
    public void member_must_be_on_the_products_page() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(pp.getPage(),"2");
        logger.info("Kullanıcı" + pp.getPage() + " sayfasında, random ürün seçiliyor.");
        pp.clickRandomItem();
        String price = pp.itemVal();
        context.setContext(Context.PRODUCT_PRICE, price);
        logger.info("Seçilen ürünün fiyatı: " + pp.itemVal());

    }
    @When("member adds item to the basket")
    public void member_adds_item_to_the_basket() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        logger.info("Sepete ürün ekleniyor");
        p.buyItem();
        Thread.sleep(1000);

    }
    @Then("basket count must increased one")
    public void basket_count_must_increased_one() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(p.baskCount(),"1 Ürün");
        logger.info("Sepetteki ürün sayısı: " + p.baskCount());
        Thread.sleep(1000);

    }
    @When("member clicks the basket and goes to basket page")
    public void member_clicks_the_basket_and_goes_to_basket_page() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("Sepete gidiliyor");
        p.finishShop();

    }
    @Then("member must see the value of item price in the products page and the baskets page must be equal")
    public void member_must_see_the_value_of_item_price_in_the_products_page_and_the_baskets_page_must_be_equal() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        if(!bp.discountTxt()){
            assertEquals(context.getContext(Context.PRODUCT_PRICE), bp.basketPriceTxt());
        } else {
            System.out.println("Üründe indirim var fiyatlar aynı olamaz.");
        }

        Thread.sleep(500);

    }
    @When("member increases the product count to two")
    public void member_increases_the_product_count_to_two() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        logger.info("Ürün sayısı arttırılıyor");
        bp.increaseProduct();
        Thread.sleep(1000);
    }

    @When("member removes items in basket page")
    public void member_removes_items_in_basket_page() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("Ürün sepetten kaldırılıyor");
        bp.deleteProduct();
    }
    @Then("member must see the empty basket page")
    public void member_must_see_the_empty_basket_page() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(bp.countProduct(), "0");
        logger.info("Sepette " + bp.countProduct() + " kadar ürün var.");
    }

}
