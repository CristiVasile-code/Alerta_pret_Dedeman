package Tests;

import Pages.HomePage;
import DataBase.Produse;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    WebDriver driver;
    Produse produs = new Produse();
    //    LoginPage loginPage;
    HomePage homePage;

//    @Before
//    public void initDrive() {
//        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("http://dedeman.ro/ro");
//        homePage = new HomePage(driver);
//        loginPage = new LoginPage(driver);
//        accPage = new AccountPage(driver);
//        logoutPage = new LogoutPage(driver);
//    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Login() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://dedeman.ro/ro");
        homePage = new HomePage(driver);
        homePage.login();
        wait(1);
        homePage.setAlegeJudet();
        wait(1);
        homePage.setAlegeLocalitate();
        wait(1);
        homePage.setEmailField("ing_vasile_cristian@yahoo.co.uk");
        homePage.setPassField("Parola_dedeman2021");
        homePage.clickLoginBtn();
        wait(4);
        homePage.clickFavBtn();
        wait(7);
        scrie();
        compara();
}
//        scrie(7); // scrie il folosesc doar o data ca sa populez baza de date bd.txt
//        scrie(10);
//        homePage.citesc(2); // asta afiseaza linia 2 din fisier. Trebuie modificata sa citeaasca o linie din fisier
        //sa o formateze si sa scoata denumirea si pretul si sa le compare cu denumirea si pretul din lista de favorite
        //oare pretul din favorite se modifica automat cand se modifica in site pretul?
    @Test
    public void operatii() {
        homePage = new HomePage(driver);
//        homePage.citesc(2);
//        homePage.citescSiAtat();
        for(int i=0;i<homePage.formatAndExtractName().size();i++){
        if(homePage.formatAndExtractPrice().get(i) != homePage.getPriceOfProductX(i)){
        System.out.println("s-a modificat pretul produsului "+ homePage.formatAndExtractName().get(0)+" ,noul pret este :"+
                homePage.getPriceOfProductX(i));}}
    }
    //    @Test
    public void scrie() {
        String deScris="";
        for(int i=0;i<homePage.list().size();i++){
         deScris = homePage.getProductX(i) + " - " + homePage.getPriceOfProductX(i);
//        homePage.scriu(homePage.listaP(7));
        homePage.scriu(deScris);}
    }
    public void compara(){
        for(int i=0;i<homePage.formatAndExtractName().size();i++){
//            System.out.println(Float.parseFloat(homePage.formatAndExtractPrice().get(i)));
//            System.out.println(Float.parseFloat(homePage.getPriceOfProductX(i)));
            if(Float.parseFloat(homePage.formatAndExtractPrice().get(i)) != Float.parseFloat(homePage.getPriceOfProductX(i))){
                System.out.println("s-a modificat pretul produsului "+ homePage.formatAndExtractName().get(i)+" ,noul pret este :"+
                        homePage.getPriceOfProductX(i));}
        else System.out.println("nici o modificare");}
    }

//    public void citesteUnR() {
//        String[] linesArr = homePage.citesc().split(System.getProperty("line.separator"));
//        for(int i=0;i<linesArr.length;i++)
//            System.out.println(linesArr[2]);
//
//    }


//    @After
//    public void quit(){
//        driver.close();
//    }
}
