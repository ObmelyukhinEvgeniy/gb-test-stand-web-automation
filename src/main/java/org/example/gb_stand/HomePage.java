package org.example.gb_stand;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends RootPage{
    public HomePage(WebDriver driver) {
        super(driver);
    }
    //Элементы страницы Home в стиле pageObject
    @FindBy(xpath = "//span[text()='Home']/..")
    private WebElement linkHome;//Ссылка 'Home'
    @FindBy(xpath = "//a[text()='About']")
    private WebElement linkAbout;//Ссылка 'About'
    @FindBy(xpath = "//a[text()='Contact']")
    private WebElement linkContact;//Ссылка 'Contact'
    @FindBy(xpath = "//a[contains(text(),'Hello')]")
    private WebElement menuHello;//Меню 'Hello'
    @FindBy(xpath = "//span[text()='Profile']/..//span[1]]/..")
    private WebElement menuHelloProfile;//Меню 'Hello' пункт 'Profile'
    @FindBy(xpath = "//span[text()='Login']/..//span[1]")
    private WebElement menuHelloLogin;//Меню 'Hello' пункт 'Login'
    @FindBy(xpath = "//span[text()='Logout']/..//span[1]")
    private WebElement menuHelloLogout;//Меню 'Hello' пункт 'Logout'
    @FindBy(xpath = "//h1[text()='Blog']")
    private WebElement titleBlog;//Заголовок 'Blog'
    @FindBy(xpath = "//button[@id='create-btn']")
    private WebElement buttonPlusCreate;//Кнопка "+"
    @FindBy(xpath = "//i[contains(text(),'sort')]/..")
    private WebElement buttonSort;//Кнопка "Сортировка"
    @FindBy(xpath = "//button[@aria-pressed='false']")
    private WebElement buttonSortFalse;//Кнопка "Сортировка" по умолчанию(от меньшего к большему)
    @FindBy(xpath = "//button[@aria-pressed='true']")
    private WebElement buttonSortTrue;//Кнопка "Сортировка" от большего к меньшему
    @FindBy(xpath = "//button[contains(@id,'SMUI-form-field-1')]")
    private WebElement radioSwitch;//Переключатель 'Not my Posts'
    @FindBy(xpath = "//button[@aria-checked='true']")
    private WebElement radioSwitchTrue;//Переключатель 'Not my Posts' в положении "V"
    @FindBy(xpath = "//button[@aria-checked='false']")
    private WebElement radioSwitchFalse;//Переключатель 'Not my Posts' в положении "-"
    @FindBy(xpath = "//div[@class='content']/div[contains(@class,'posts')]/a[1]")
    private WebElement postContentFirst;//Первый пост в контенте
    @FindBy(xpath = "//div[@class='content']/div[contains(@class,'posts')]/a[1]/h2")
    private WebElement titleContentFirst;//Заголовок первого поста в контенте
    @FindBy(xpath = "//div[@class='content']/div[contains(@class,'posts')]/a[1]/div")
    private WebElement summerContentFirst;//Описание первого поста в контенте
    @FindBy(xpath = "//a[text()='Previous Page']")
    private WebElement paginationPreviousPage;//Пагинация предыдущий пост
    @FindBy(xpath = "//a[text()='Next Page']")
    private WebElement paginationNextPage;//Пагинация следующий пост
    @FindBy(xpath = "//h1[text()='Create Post']")
    private WebElement titleCreatePost;//Заголовок "Create Post"
    @FindBy(xpath="//span[contains(.,'Title')]/following-sibling::input")
    private WebElement fieldTitlePostInput;//Поле ввода заголовка поста
    @FindBy(xpath="//textarea[@maxlength='100']")
    private WebElement fieldDescriptionPostInput;//Поле ввода описания поста
    @FindBy(xpath="//textarea[@aria-controls='SMUI-textfield-helper-text-1']")
    private WebElement fieldContentPostInput;//Поле ввода контента поста
    @FindBy(xpath="//button[@type='submit']")
    private WebElement buttonCreatePost;//Кнопка оформления поста
    @FindBy(xpath = "//h1")
    private WebElement titleNewCreatePost;//Заголовок созданного поста
    @FindBy(xpath = "//div[@class='content']//a[contains(@class,'post')][1]/img")
    private WebElement imgCheck;//Картинка первого поста
    @FindBy(xpath = "//div[@class='content']//a[contains(@class,'post')][1]/h2")
    private WebElement titleCheck;//Заголовок первого поста
    @FindBy(xpath = "//div[@class='content']//a[contains(@class,'post')][1]/div")
    private WebElement descriptionCheck;//Описание первого поста
    @FindBy(xpath = "//h1")
    private WebElement titleProfilePage;//Заголовок страницы профиля
    public void linkAboutAndReturnHomeCheckImgTitleDescription() throws InterruptedException { //Переходим на страницу профиля затем на домашнюю и проверяем что пост содержит картинку, описание, заголовок
        linkAbout.click();
        linkHome.click();
        Thread.sleep(1000);
        Assertions.assertTrue(titleBlog.isDisplayed());
        Assertions.assertTrue(imgCheck.isDisplayed());
        Assertions.assertTrue(titleCheck.isDisplayed());
        Assertions.assertTrue(descriptionCheck.isDisplayed());
    }
    public void createNewPosts() throws InterruptedException { //Создаём новый пост без картинки
        buttonPlusCreate.click();
        Assertions.assertTrue(titleCreatePost.isDisplayed());
        fieldTitlePostInput.sendKeys("Пост созданный автоматизированным ПО");
        fieldDescriptionPostInput.sendKeys("Описание созданное автоматизированным ПО");
        fieldContentPostInput.sendKeys("Контент созданный автоматизированным ПО");
        buttonCreatePost.click();
        Thread.sleep(2000);
        Assertions.assertTrue(titleNewCreatePost.getText().contains("Пост созданный автоматизированным ПО"));
    }
    public void modificationSort() throws InterruptedException { //Работа кнопки сортировки
        Assertions.assertTrue(buttonSortFalse.isDisplayed());
        buttonSort.click();
        Thread.sleep(2000);
        Assertions.assertTrue(buttonSortTrue.isDisplayed());
    }
    public void modificationSwitch() throws InterruptedException {//Работа переключателя 'Not my Posts'
        Assertions.assertTrue(radioSwitchFalse.isDisplayed());
        radioSwitch.click();
        Thread.sleep(2000);
        Assertions.assertTrue(radioSwitchTrue.isDisplayed());
    }
    public void checkPagination() throws InterruptedException {//Работа пагинации
        paginationNextPage.click();
        Thread.sleep(2000);
        paginationPreviousPage.click();
        Thread.sleep(2000);
        Assertions.assertTrue(titleContentFirst.getText().contains("Пост "));
    }

}
