package gb_stand;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.gb_stand.HomePage;
import org.example.gb_stand.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
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
        driver = new ChromeDriver(); //Инициализируем поле driver хромДрайвером
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //При ненахождении нужного элемента, driver прежде чем выкинуть исключение, будет ожидать 10сек.
        driver.manage().window().setSize(new Dimension(1300, 722)); //Устанавливаем размер окна для работы автоматизированного ПО.
        driver.get("https://test-stand.gb.ru/"); //Открываем Тестовый Стенд
        new LoginPage(driver).signInLogin("Evgeniy34", "0e80d10840");
        new LoginPage(driver).checkPositive("Evgeniy34");
        Thread.sleep(2000);
//        driver.manage().deleteAllCookies();//Удалить все cookie
//        Cookie cookie = new Cookie("X-Auth-Token", "4cab6cf7e5263d8c3e578ce38eee5cff");//Логинимся с помощью передачи X-Auth-Token. Добавить значение X-Auth-Token в хедер запроса.
//        driver.manage().addCookie(cookie);
//        //Обновить страницу
//        driver.navigate().refresh();
//        Thread.sleep(2000); //Приостанавливаемся выполнение на 2сек.
    }

    @Test
    @DisplayName("Создать новый пост.")
    void createNewPost() throws InterruptedException {
        new HomePage(driver).createNewPosts();
    }
    @Test
    @DisplayName("Переход между страницами.")
    void checkPagination() throws InterruptedException {
        new HomePage(driver).checkPagination();
    }
    @Test
    @DisplayName("Изменить сортировку постов")
    void checkSort() throws InterruptedException {
        new HomePage(driver).modificationSort();
    }
    @Test
    @DisplayName("Проверка работы переключателя 'Not my Posts'")
    void checkSwitch() throws InterruptedException {
        new HomePage(driver).modificationSwitch();
    }

    @Test
    @DisplayName("Переход на страницу О нас, Возврат на главную, проверка что пост содержит: картинку, заголовок, описание")
    void transitionPageAboutAndHomeCheckPost() throws InterruptedException {
        new HomePage(driver).linkAboutAndReturnHomeCheckImgTitleDescription();
    }

    @AfterEach
    void ternDown() {
        driver.quit();
    }
}
