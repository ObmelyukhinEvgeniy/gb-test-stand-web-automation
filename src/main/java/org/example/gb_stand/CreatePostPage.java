package org.example.gb_stand;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends RootPage{
    public CreatePostPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[text()='Create Post']")
    private WebElement titleCreatePost;//Заголовок "Create Post"

    public void checkOpenPageCreatePost() {//Проверяем, что появился Заголовок "Create Post"
        Assertions.assertTrue(titleCreatePost.isDisplayed());
    }
}
