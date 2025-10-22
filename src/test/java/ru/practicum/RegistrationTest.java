package ru.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.practicum.model.User;
import ru.practicum.page.object.HomePage;
import ru.practicum.page.object.LoginPage;
import ru.practicum.page.object.ProfilePage;
import ru.practicum.page.object.RegistrationPage;
import ru.practicum.steps.ClientUser;
import ru.practicum.utils.Urls;

public class RegistrationTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    ProfilePage profilePage;

    private final String name = RandomStringUtils.randomAlphabetic(10);
    private final String email = RandomStringUtils.randomAlphanumeric(8) + "@yandex.ru";
    private final String correctPassword = RandomStringUtils.randomAlphabetic(6);
    private final String wrongPassword = RandomStringUtils.randomAlphabetic(5);

    String accessToken;
    ClientUser userClient = new ClientUser();
    User user;

    public void createUser() {
        Response createUser = userClient.createUser(user);
        accessToken = createUser.body().path("accessToken").toString().substring(7);
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверка возможности успешно зарегистрироваться новым пользователем с правильным набором данных")
    public void successfulRegistrationTest() {
        homePage = new HomePage(webDriver);
        homePage.waitForPersonalProfileButton();
        homePage.enterPersonalProfile();
        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegistrationLink();
        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.fillInRegistrationForm(name, email, correctPassword);
        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        Assert.assertEquals(Urls.LOGIN_URL, webDriver.getCurrentUrl());
        loginPage.fillInUserData(email, correctPassword);
        loginPage.clickEnterButton();
        homePage.waitForPersonalProfileButton();
        homePage.enterPersonalProfile();
        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(name, profilePage.getNameText());
        Assert.assertEquals(email.toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @Test
    @DisplayName("Регистрация с некорректным, коротким паролем")
    @Description("Проверка отображения ошибки при попытке зарегистрировать пользователя с неправильным коротким паролем")
    public void shortPasswordRegistrationTest() {
        homePage = new HomePage(webDriver);
        homePage.waitForPersonalProfileButton();
        homePage.enterPersonalProfile();
        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegistrationLink();
        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.fillInRegistrationForm(name, email, wrongPassword);
        Assert.assertEquals("Некорректный пароль", registrationPage.getPasswordFieldErrorText());
        Assert.assertEquals(Urls.REGISTER_URL, webDriver.getCurrentUrl());
    }

    @After
    public void cleanUp() {
        if (accessToken != null && !accessToken.isEmpty()) {
            userClient.deleteUser(accessToken);
        }
    }
}