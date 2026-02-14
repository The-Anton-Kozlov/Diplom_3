package ru.practicum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    private final By enterFormHeading = By.xpath(".//*[text()='Вход']");
    private final By emailInputField = By.xpath(".//fieldset[1]//input");
    private final By passwordInputField = By.xpath(".//fieldset[2]//input");
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    private final By registrationLink = By.className("Auth_link__1fOlj");
    private final By restorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидаем загрузку страницы входа")
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(enterFormHeading));
    }

    @Step("Заполняем форму ввода данных пользователя")
    public void fillInUserData(String email, String password) {
        driver.findElement(emailInputField).clear();
        driver.findElement(emailInputField).sendKeys(email);

        driver.findElement(passwordInputField).clear();
        driver.findElement(passwordInputField).sendKeys(password);
    }

    @Step("Нажимаем на кнопку 'Войти'")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Переходим по ссылке регистрации")
    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    @Step("Переходим по ссылке восстановления пароля")
    public void clickRestorePasswordLink() {
        driver.findElement(restorePasswordLink).click();
    }
}