package ru.practicum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By registerOrderButton = By.xpath("//*[text()='Оформить заказ']");
    private final By personalProfileButton = By.xpath("//*[text()='Личный Кабинет']");
    private final By saucesLink = By.xpath("//span[text()='Соусы']/parent::div");
    private final By bunsLink = By.xpath("//span[text()='Булки']/parent::div");
    private final By fillingsLink = By.xpath("//*[text()='Начинки']/parent::div");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку 'Войти в аккаунт'")
    public void clickEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

    @Step("Переходим в раздел 'Личный кабинет'")
    public void enterPersonalProfile() {
        driver.findElement(personalProfileButton).click();
    }

    @Step("Кликаем на вкладку 'Булки'")
    public void clickBunsLink() {
        driver.findElement(bunsLink).click();
    }

    @Step("Кликаем на вкладку 'Соусы'")
    public void clickSaucesLink() {
        driver.findElement(saucesLink).click();
    }

    @Step("Кликаем на вкладку 'Начинки'")
    public void clickFillingsLink() {
        driver.findElement(fillingsLink).click();
    }

    @Step("Получаем класс элемента 'Булки'")
    public String getClassNameOfBunsLink() {
        return driver.findElement(bunsLink).getAttribute("class");
    }

    @Step("Получаем класс элемента 'Соусы'")
    public String getClassNameOfSaucesLink() {
        return driver.findElement(saucesLink).getAttribute("class");
    }

    @Step("Получаем класс элемента 'Начинки'")
    public String getClassNameOfFillingsLink() {
        return driver.findElement(fillingsLink).getAttribute("class");
    }

    @Step("Ожидаем появления кнопки 'Войти в аккаунт'")
    public void waitForEnterAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(enterAccountButton)
        );
    }
    @Step("Ожидание появления кнопки 'Оформить заказ'")
    public void waitForRegisterOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerOrderButton));
    }
    @Step("Ожидаем появление кнопки 'Личный кабинет'")
    public void waitForPersonalProfileButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(personalProfileButton));
    }

    @Step("Ожидаем активность вкладки 'Булки'")
    public void waitUntilBunsLinkIsActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                drv -> getClassNameOfBunsLink().contains("tab_tab_type_current__2BEPc")
        );
    }

    @Step("Ожидаем активность вкладки 'Соусы'")
    public void waitUntilSaucesLinkIsActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                drv -> getClassNameOfSaucesLink().contains("tab_tab_type_current__2BEPc")
        );
    }

    @Step("Ожидаем активность вкладки 'Начинки'")
    public void waitUntilFillingsLinkIsActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                drv -> getClassNameOfFillingsLink().contains("tab_tab_type_current__2BEPc")
        );
    }
}