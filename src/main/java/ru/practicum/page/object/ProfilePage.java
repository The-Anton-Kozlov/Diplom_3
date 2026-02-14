package ru.practicum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By textMessage = By.xpath(".//*[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By nameField = By.xpath(".//li[1]//input");
    private final By emailField = By.xpath(".//li[2]//input");


    @Step("Ожидание полной загрузки профиля")
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textMessage));
    }

    @Step("Получение значения поля 'Имя'")
    public String getNameText() {
        return driver.findElement(nameField).getAttribute("value");
    }

    @Step("Получение значения поля 'Электронная почта'")
    public String getEmailText() {
        return driver.findElement(emailField).getAttribute("value");
    }
}