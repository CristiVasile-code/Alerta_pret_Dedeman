package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;
    Actions actions;


    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".header-actions-list li:nth-child(2)>span")
    private WebElement contulMeuBtn;
    @FindBy(css = ".dropdown-buttons .blue-gradient")
    private WebElement loginBtn;
    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div[3]/div/div/div[1]/div/input")
    private WebElement alegeJudet;
    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div[3]/div/div/div[2]/div/input")
    private WebElement alegeLocalitate;
    @FindBy(css = "#app > div > div.wrapper-header.position-relative > div.wrapper-modal.select-locality-modal > div > div > div:nth-child(2) > div > div > ul > li:nth-child(24)")
    private WebElement galati;
    @FindBy(css = "#app > div > div.wrapper-header.position-relative > div.wrapper-modal.select-locality-modal > div > div > div:nth-child(3) > div > div > ul > li:nth-child(64)")
    private WebElement locGal;
    @FindBy(css = "[type=\"email\"]")
    private WebElement emailField;
    @FindBy(css = "[type=\"password\"]")
    private WebElement passField;
    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div[3]/div/div[1]/form/button")
    private WebElement loginButton;
    @FindBy(css = ".product-name")
    private List <WebElement> listOfProductNames;
    @FindBy(css = ".product-price .bold")
    private List<WebElement> listOfProductPrices;
    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div[1]/div/div[2]/div[2]/div[2]/ul/li[1]/a")
    private WebElement favBtn;
    @FindBy(css = ".current span")
    private WebElement pageTitle;

//    Methods
    public void login(){
        actions = new Actions(driver);
        actions.moveToElement(contulMeuBtn).perform();
        actions.moveToElement(loginBtn).perform();
        loginBtn.click();
    }
    public void setAlegeJudet(){
        alegeJudet.click();
        galati.click();
    }
    public void setAlegeLocalitate(){
        alegeLocalitate.click();
        locGal.click();
    }
    public void setEmailField(String email){
        emailField.sendKeys(email);
    }
    public void setPassField(String pass){
        passField.sendKeys(pass);
    }
    public void clickLoginBtn(){
        loginButton.click();
    }
    public void scriu(String data){
        try{
            FileWriter file = new FileWriter("bd.txt",true);
            file.write(data+ "\n");
            file.close();
        } catch (IOException e){e.printStackTrace();}// oare se poate scrie cu Files?
    }
    public void citesc(int index) { // oare se poate citi cu FileWriter sau ceva din gama?
        try {
            List<String> allLines = Files.readAllLines(Paths.get("bd.txt"));
            for (int i=0;i<allLines.size();i++) {
                if(i == index){System.out.println(allLines.get(index));}
            }
        } catch (IOException e){e.printStackTrace();}
    }
    public List<String> citescSiAtat(){
        List<String> allLines = new ArrayList<String>();
        try {
            allLines = Files.readAllLines(Paths.get("bd.txt"));
            for(String s:allLines){
//                System.out.println(s);
//            String[] sSplit =s.split("-",-2);
//                System.out.println(sSplit[0]);
//                System.out.println(sSplit[1]);
                }
        } catch (IOException e){e.printStackTrace();}
        return allLines;
    }
    public List<String> formatAndExtractName(){
        List<String> productName = new ArrayList<String>();
        for(String element:citescSiAtat()){
            productName.add(element.split("-",2)[0]);
//            System.out.println(productName);
        }return productName;
    }
    public List<String> formatAndExtractPrice(){
        List<String> productPrice = new ArrayList<String>();
        for(String element:citescSiAtat()){
            productPrice.add(element.split("-",-2)[1]);
//            System.out.println(productPrice);
        }return productPrice;
    }
    public String unProdus(int index){
        String rez="";
        for(int i = 0; i< formatAndExtractName().size(); i++){
            if(i == index){rez = formatAndExtractName().get(index);}
        }
        return rez;
    }
    public float unPret(int index){
        String rez="";
        for(int i = 0; i< formatAndExtractPrice().size(); i++){
            if(i == index){rez = formatAndExtractPrice().get(index);}
        }
        return Float.parseFloat(rez.trim());
    }
    public void clickFavBtn(){favBtn.click();}
//    public String getPageTitle(){
//        return pageTitle.getText();
//    }
//    public boolean isPageTitleDisp(){
//        return pageTitle.isDisplayed();
//    }
    public List<WebElement> productList(){return listOfProductNames;}
    public List<WebElement> productPriceList(){return listOfProductPrices;}
    public String getProductX(int index){
        return productList().get(index).getText();
    }
    public String getPriceOfProductX(int index){
        return (productPriceList()).get(index).getText();
    }
        public List<String> list(){
        List<String> listOfStrings = new ArrayList<String>();
        for(WebElement element:productList()){
            listOfStrings.add(element.getText());}
        return listOfStrings;
    }


}
