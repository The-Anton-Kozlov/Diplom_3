package ru.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.page.object.*;
import ru.practicum.model.User;
import ru.practicum.steps.ClientUser;
import ru.practicum.utils.Urls;

public class AuthorizationTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    ProfilePage profilePage;
    RegistrationPage registrationPage;
    RestorePasswordPage restorePasswordPage;

    String email;
    String password;
    String name;
    String accessToken;
    ClientUser userClient = new ClientUser();
    User user;

    @Before
    public void createUser() {
        name = RandomStringUtils.randomAlphabetic(7);
        email = RandomStringUtils.randomAlphanumeric(6) + "@yandex.ru";
        password = RandomStringUtils.randomAlphabetic(7);
        user = new User(email, password, name);
        Response createUser = userClient.createUser(user);
        accessToken = createUser.body().path("accessToken").toString().substring(7);
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через нажатие кнопки 'Войти' на домашней странице")
    @Description("Авторизация через главную страницу: ввод данных и проверка попадания в личный профиль")
    public void enterAccountByClickingEnterAccountButtonOnHomePageTest() {
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();
        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.fillInUserData(email, password);
        loginPage.clickEnterButton();
        homePage.waitForRegisterOrderButton();
        Assert.assertEquals(Urls.HOME_URL, webDriver.getCurrentUrl());
        homePage.enterPersonalProfile();
        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(email.toLowerCase(), profilePage.getEmailText().toLowerCase());
    }


    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Переход к авторизации через ссылку в личном кабинете и последующее попадание в профиль")
    public void loginViaPersonalProfileButtonTest(){
        homePage = new HomePage(webDriver);
        homePage.waitForPersonalProfileButton();
        homePage.enterPersonalProfile();
        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.fillInUserData(email, password);
        loginPage.clickEnterButton();
        homePage.waitForRegisterOrderButton();
        homePage.enterPersonalProfile();
        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(email.toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Авторизация через форму регистрации: быстрый переход к авторизации и последующий вход в профиль")
    public void loginViaRegistrationFormLinkTest(){
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();
        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegistrationLink();
        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.clickAlreadyRegisteredLink();
        loginPage.waitForPageLoad();
        loginPage.fillInUserData(email, password);
        loginPage.clickEnterButton();
        homePage.waitForRegisterOrderButton();
        homePage.enterPersonalProfile();
        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(email.toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка переход к авторизации из окна восстановления пароля и успешный вход в профиль")
    public void loginUsingRestorePasswordFormLinkTest(){
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();
        homePage.clickEnterAccountButton();
        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRestorePasswordLink();
        restorePasswordPage = new RestorePasswordPage(webDriver);
        restorePasswordPage.waitForPageLoad();
        restorePasswordPage.clickRememberedPassword();
        loginPage.waitForPageLoad();
        loginPage.fillInUserData(email,password);
        loginPage.clickEnterButton();
        homePage.waitForRegisterOrderButton();
        homePage.enterPersonalProfile();
        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();
        Assert.assertEquals(email.toLowerCase(), profilePage.getEmailText().toLowerCase());
    }

    @After
    public void cleanUp() {
        if (accessToken != null && !accessToken.isEmpty()) {
            userClient.deleteUser(accessToken);
        }
    }
}