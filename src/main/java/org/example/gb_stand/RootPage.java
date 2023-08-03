package org.example.gb_stand;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RootPage {
    //Объявляем поля
    WebDriver driver;//Создаём объект класса driver который позволяет взаимодействовать с браузером.
    WebDriverWait webDriverWait;//Создаём объект класса webDriverWait который позволяет создавать кастомные методы ожидания в тестах.
    Actions actions;//Создаём объект класса actions, который позволяет имитировать действия пользователя и создавать из них цепочку действий.

    public RootPage(WebDriver driver) {
        this.driver = driver;//Присваеваем полю driver в классе RootPage значение driver переданное в метод RootPage.
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));//При ненахождении нужного элемента, driver прежде чем выкинуть исключение, будет ожидать 10сек.
        actions = new Actions(driver);//Передаём объекту аctions, driver который будем использовать.
        PageFactory.initElements(driver, this);//Регестрирует страницу как PageObject и повторно обновляет поля-элементы когда мы хотим к ним обратиться.
    }


}
