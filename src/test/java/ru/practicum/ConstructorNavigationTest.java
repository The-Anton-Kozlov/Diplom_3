package ru.practicum;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.pageObject.HomePage;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.not;


public class ConstructorNavigationTest extends BaseTest {
    HomePage homePage;


    @Test
    @DisplayName("Проверка перехода к разделу «Булки»")
    public void checkBunsSectionTransitionTest() {
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();
        homePage.clickSaucesLink();
        Assert.assertThat(homePage.getClassNameOfBunsLink(), not(CoreMatchers.containsString("tab_tab_type_current__2BEPc")));
        homePage.clickBunsLink();

        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until((driver) -> homePage.getClassNameOfBunsLink().contains("tab_tab_type_current__2BEPc"));
        Assert.assertThat(homePage.getClassNameOfBunsLink(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }



    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    public void checkTransitionToSaucesTest(){
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();
        Assert.assertThat(homePage.getClassNameOfSaucesLink(), not(CoreMatchers.containsString("tab_tab_type_current__2BEPc")));
        homePage.clickSaucesLink();
        Assert.assertThat(homePage.getClassNameOfSaucesLink(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинки»")
    public void checkTransitionToFillingsTest(){
        homePage = new HomePage(webDriver);
        homePage.waitForEnterAccountButton();
        Assert.assertThat(homePage.getClassNameOfFillingsLink(), not(CoreMatchers.containsString("tab_tab_type_current__2BEPc")));
        homePage.clickFillingsLink();
        Assert.assertThat(homePage.getClassNameOfFillingsLink(), CoreMatchers.containsString("tab_tab_type_current__2BEPc"));
    }
}
