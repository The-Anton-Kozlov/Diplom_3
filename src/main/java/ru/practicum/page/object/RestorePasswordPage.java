package ru.practicum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RestorePasswordPage {
    WebDriver driver;

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By restoreFormHeading = By.xpath(".//*[text()='Восстановление пароля']");
    private final By rememberedPasswordLink = By.xpath(".//a[text()='Войти']");

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(restoreFormHeading));
    }

    @Step("Нажатие на ссылку 'Вспомнили пароль?'")
    public void clickRememberedPassword() {
        driver.findElement(rememberedPasswordLink).click();
    }
}