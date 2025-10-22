package ru.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import ru.practicum.page.object.HomePage;

import static org.hamcrest.CoreMatchers.not;

public class ConstructorNavigationTest extends BaseTest {
    HomePage homePage;

    @Test
    @DisplayName("Проверка перехода к разделу «Булки»")
    @Description("Проверяется переключение активности вкладки 'Булки': клик по ссылке должен установить её активным элементом")
    public void checkBunsSectionTransitionTest() {
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();

        homePage.clickSaucesLink();
        Assert.assertThat(homePage.getClassNameOfBunsLink(),
                not(CoreMatchers.containsString("tab_tab_type_current__2BEPc")));

        homePage.clickBunsLink();
        homePage.waitUntilBunsLinkIsActive();

        Assert.assertThat(homePage.getClassNameOfBunsLink(),
                CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    @Description("Проверяется правильность активации раздела 'Соусы' при клике на соответствующую ссылку")
    public void checkTransitionToSaucesTest() {
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();

        Assert.assertThat(homePage.getClassNameOfSaucesLink(),
                not(CoreMatchers.containsString("tab_tab_type_current__2BEPc")));

        homePage.clickSaucesLink();
        homePage.waitUntilSaucesLinkIsActive();

        Assert.assertThat(homePage.getClassNameOfSaucesLink(),
                CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинки»")
    @Description("Проверяется правильная смена активности вкладки 'Начинки' при нажатии на неё")
    public void checkTransitionToFillingsTest() {
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();

        Assert.assertThat(homePage.getClassNameOfFillingsLink(),
                not(CoreMatchers.containsString("tab_tab_type_current__2BEPc")));

        homePage.clickFillingsLink();
        homePage.waitUntilFillingsLinkIsActive();

        Assert.assertThat(homePage.getClassNameOfFillingsLink(),
                CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }
}