package gb_stand;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.gb_stand.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AuthorizationTest {
    static WebDriver driver;
    @BeforeAll
    static void driverRegister() {
        WebDriverManager.chromedriver().setup(); //Установим нужную версию хромДрайвера.
    }
    @BeforeEach
    void driverInitializing() throws InterruptedException {
        driver = new ChromeDriver(); //Инициализируе поле driver хромДрайвером
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //При ненахождении нужного элемента, driver прежде чем выкинуть исключение, будет ожидать 10сек.
        driver.manage().window().setSize(new Dimension(1300, 722)); //Устанавливаем размер окна для работы автоматизированного ПО.
        driver.get("https://test-stand.gb.ru/login"); //Открываем Тестовый Стенд
        Thread.sleep(2000); //Приостанавливаемся выполнение на 3сек.
    }
    @Test
    @Description("Вход с валидными логином и паролем.")
    @DisplayName("Вход с валидными логином и паролем.")
    void positiveValideData() throws InterruptedException {
        new LoginPage(driver).signInLogin("Evgeniy34", "0e80d10840");
        new LoginPage(driver).checkPositive("Evgeniy34");
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с не валидным логином и паролем.")
    @DisplayName("Вход с не валидным логином и паролем.")
    void negativeNoValideData() throws InterruptedException {
        new LoginPage(driver).signInLogin("badLogin", "666321584");
        new LoginPage(driver).checkNegative();
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с пустым полем \"Username\" и валидным паролем")
    @DisplayName("Вход с пустым полем \"Username\" и валидным паролем")
    void negativeFieldEmptyUsername() throws InterruptedException {
        new LoginPage(driver).signInLogin("", "0e80d10840");
        new LoginPage(driver).checkNegative();
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с пустым полем \"Password\" и валидным логином")
    @DisplayName("Вход с пустым полем \"Password\" и валидным логином")
    void negativeFieldEmptyPassword() throws InterruptedException {
        new LoginPage(driver).signInLogin("Evgeniy34", "");
        new LoginPage(driver).checkNegative();
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с пустыми полями \"Username\" и \"Password\".")
    @DisplayName("Вход с пустыми полями \"Username\" и \"Password\".")
    void negativeFieldsEmpty() throws InterruptedException {
        new LoginPage(driver).signInLogin("", "");
        new LoginPage(driver).checkNegative();
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с логином включающим спецсимволы и валидным паролем.")
    @DisplayName("Вход с логином включающим спецсимволы и валидным паролем.")
    void negativeFieldsSimvol() throws InterruptedException {
        new LoginPage(driver).signInLogin("<>?)(*&^%$#@!", "b6a463ac06");
        new LoginPage(driver).checkNegative();
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с логином включающим кирилицу и валидным паролем")
    @DisplayName("Вход с логином включающим кирилицу и валидным паролем")
    void negativeFieldUsernameRus() throws InterruptedException {
        new LoginPage(driver).signInLogin("Охсарон", "41d1b9bfa9");
        new LoginPage(driver).checkNegative();
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с валидными логином из 3х символов и паролем")
    @DisplayName("Вход с валидными логином из 3х символов и паролем")
    void positiveFieldUsername3Simvols() throws InterruptedException {
        new LoginPage(driver).signInLogin("Bn3", "885f00ccee");
        new LoginPage(driver).checkPositive("Bn3");
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с логином длиной менее 3х символов и валидным паролем")
    @DisplayName("Вход с логином длиной менее 3х символов и валидным паролем")
    void negativeFieldUsername2Simvols() throws InterruptedException {
        new LoginPage(driver).signInLogin("k2", "61620957a1");
        new LoginPage(driver).checkNegative();
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с логином длиной 4 символа и валидным паролем")
    @DisplayName("Вход с логином длиной 4 символа и валидным паролем")
    void positiveFieldUsername4Simvols() throws InterruptedException {
        new LoginPage(driver).signInLogin("Qw12", "adf7cfe580");
        new LoginPage(driver).checkPositive("Qw12");
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с валидными логином из 20-ти символов и паролем")
    @DisplayName("Вход с валидными логином из 20-ти символов и паролем")
    void positiveFieldUsername20Simvols() throws InterruptedException {
        new LoginPage(driver).signInLogin("Twenty20Characters20", "d7c7ef1e8e");
        new LoginPage(driver).checkPositive("Twenty20Characters20");
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с логином длиной 19 символов и валидным паролем")
    @DisplayName("Вход с логином длиной 19 символов и валидным паролем")
    void positiveFieldUsername19Simvols() throws InterruptedException {
        new LoginPage(driver).signInLogin("19CharactersUserMor", "88297b729d");
        new LoginPage(driver).checkPositive("19CharactersUserMor");
        Thread.sleep(0);
    }
    @Test
    @Description("Вход с логином длиной более 20 символов и валидным паролем")
    @DisplayName("Вход с логином длиной более 20 символов и валидным паролем")
    void negativeFieldUsername21Simvols() throws InterruptedException {
        new LoginPage(driver).signInLogin("UserMoreThan20Charact", "3482491083");
        new LoginPage(driver).checkNegative();
        Thread.sleep(0);
    }

    @AfterEach
    void ternDown() {
        driver.quit();
    }
}
