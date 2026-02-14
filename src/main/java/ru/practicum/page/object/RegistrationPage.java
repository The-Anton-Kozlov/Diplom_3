package ru.practicum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By registerFormHeading = By.xpath(".//*[text()='Регистрация']");
    private final By nameInputField = By.xpath(".//fieldset[1]//input");
    private final By emailInputField = By.xpath(".//fieldset[2]//input");
    private final By passwordInputField = By.xpath(".//fieldset[3]//input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordFieldError = By.xpath(".//fieldset[3]//p");
    private final By alreadyRegisteredLink = By.xpath(".//*[text()='Уже зарегистрированы?']/a");


    @Step("Ожидание отображения заголовка 'Регистрация'")
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registerFormHeading));
    }

    @Step("Заполнение регистрационной формы именем '{name}', емейлом '{email}' и паролем '{password}'")
    public void fillInRegistrationForm(String name, String email, String password) {
        driver.findElement(nameInputField).sendKeys(name);
        driver.findElement(emailInputField).sendKeys(email);
        driver.findElement(passwordInputField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    @Step("Получение текста ошибки поля пароля")
    public String getPasswordFieldErrorText() {
        return driver.findElement(passwordFieldError).getText();
    }

    @Step("Нажатие на ссылку 'Уже зарегистрирован?'")
    public void clickAlreadyRegisteredLink() {
        driver.findElement(alreadyRegisteredLink).click();
    }
}