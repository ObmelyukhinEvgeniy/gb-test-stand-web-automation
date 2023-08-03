package gb_stand;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.gb_stand.HomePage;
import org.example.gb_stand.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class HomePageTest {
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
        new LoginPage(driver).signInLogin("Evgeniy34", "0e80d10840");
        new LoginPage(driver).checkPositive("Evgeniy34");
        Thread.sleep(2000); //Приостанавливаемся выполнение на 3сек.
    }

    @Test
    @Description("Переход между страницами.")
    @DisplayName("Переход между страницами.")
    void checkPagination() throws InterruptedException {
        new HomePage(driver).checkPagination();
    }
    @Test
    @Description("Изменить сортировку постов")
    @DisplayName("Изменить сортировку постов")
    void checkSort() throws InterruptedException {
        new HomePage(driver).modificationSort();
    }
    @Test
    @Description("Проверка работы переключателя 'Not my Posts'")
    @DisplayName("Проверка работы переключателя 'Not my Posts'")
    void checkSwitch() throws InterruptedException {
        new HomePage(driver).modificationSwitch();
    }

    @Test
    @Description("Переход на страницу создания нового поста и возврат на главную")
    @DisplayName("Переход на страницу создания нового поста и возврат на главную")
    void transitionPageCreateNewPost() {
        new HomePage(driver).buttonNewPostAndReturnHome();
    }

    @AfterEach
    void ternDown() {
        driver.quit();
    }
}
