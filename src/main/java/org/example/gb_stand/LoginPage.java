package org.example.gb_stand;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends RootPage{
    //Конструктор принимающий объект driver из класса RootPage.
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Создаём список элементов-полей в стиле pageObject для страницы "Логин".
    @FindBy(xpath = "//input[@type='text']")
    private WebElement fieldLogin;//Поле "Логин".

    @FindBy(xpath = "//input[@type='password']")
    private WebElement fieldPassword;//Поле "Пароль".

    @FindBy(xpath = "//button[@form='login']")
    private WebElement buttonLogin;//Кнопка "Login".

    @FindBy(xpath = "//a[contains(.,'Hello')]")
    private WebElement titleHelloProfile;//Название "Hello, profile" в правом верхнем углу.

    @FindBy(xpath = "//h2[text()='401']")
    private WebElement numberCodeUnauthorized;//Заголовок кода ошибки при вводе неверных данных

    @FindBy(xpath = "//div[@class='content']")
    private WebElement divisionContent;//Область с постами на странице Home

    @FindBy(xpath = "//p[text()='Invalid credentials.']")
    private WebElement titleUnauthorized;//Заголовок при вводе неверных данных

    //Метод выполняющий вход в личный кабинет(ЛК)
    public void signInLogin(String login, String password) {
        fieldLogin.sendKeys(login);
        fieldPassword.sendKeys(password);
        buttonLogin.click();
    }
    //Метод выполняющий проверку сценариев с позитивным исходом
    public void checkPositive(String login) {
        Assertions.assertTrue(titleHelloProfile.getText().contains(login));
        Assertions.assertTrue(divisionContent.isDisplayed());
    }
    //Метод выполняющий проверку сценариев с негативным исходом
    public void checkNegative() {
        Assertions.assertTrue(numberCodeUnauthorized.getText().contains("401"));
        Assertions.assertTrue(titleUnauthorized.getText().contains("Проверьте логин и пароль."));
    }
}
