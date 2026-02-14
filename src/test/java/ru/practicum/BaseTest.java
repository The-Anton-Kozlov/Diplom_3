package ru.practicum;

import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import ru.practicum.utils.Urls;
import ru.practicum.utils.DriverFactory;

public class BaseTest {
    @Rule
    public final DriverFactory driverFactory = new DriverFactory();

    protected WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = driverFactory.getDriver();
        webDriver.get(Urls.HOME_URL);
    }

}

