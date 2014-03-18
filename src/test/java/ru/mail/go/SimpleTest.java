package ru.mail.go;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SimpleTest {
    private WebDriver driver;

    private static final String MANY_NULLS = "0000000000000000000000000000000000000000000000000000000000000000";
    private static final String VERY_MANY_NULLS = MANY_NULLS + MANY_NULLS + MANY_NULLS + MANY_NULLS +
            MANY_NULLS + MANY_NULLS;
    private static final String BACKSPACE = "\u0008";
    private static final String CORRECT_VALUE = "500";
    private static final String INCORRECT_VALUE = "500AI!";
    private static final String VERY_BIG_VALUE = "1" + VERY_MANY_NULLS;
    private static final String VERY_SMALL_VALUE = BACKSPACE + "0." + VERY_MANY_NULLS + "1";
    private static final String FRACTIONAL_NUMBER = "0,12";
    private static final String QUERY = "http://go.mail.ru/" +
            "search?q=%D0%BA%D0%B3+%D0%B2+%D1%84%D1%83%D0%BD%D1%82%D1%8B&fr=main";

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.navigate().to(QUERY);
        driver.manage()
                .timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testConverterAppearance() {
        Assert.assertEquals("result__li smack-converter",
                new MainPage(driver).getFirstResultClass());

    }

    @Test
    public void testInput() throws Exception {
        Assert.assertEquals(CORRECT_VALUE,
                new MainPage(driver).getInputValue(BACKSPACE + CORRECT_VALUE));
    }


    @Test
    public void testOutput() {
        Assert.assertEquals("1 102,31131",
                new MainPage(driver).getConvertedValue(BACKSPACE + CORRECT_VALUE));
    }

    @Test
    public void testConvertVeryBigValue() {
        Assert.assertEquals("Infinity",
                new MainPage(driver).getConvertedValue(BACKSPACE + VERY_BIG_VALUE));
    }

    @Test
    public void testConvertFractionalNumber () {
        Assert.assertEquals("0,26455",
                new MainPage(driver).getConvertedValue(BACKSPACE + FRACTIONAL_NUMBER));
    }
    @Test
    public void testConvertVerySmallValue() {
        Assert.assertEquals("0",
                new MainPage(driver).getConvertedValue(VERY_SMALL_VALUE));
    }


    @Test
    public void testReverse() {
        MainPage main = new MainPage(driver);
        main.reverseConverter();
        Assert.assertEquals("226,79618",
                new MainPage(driver).getConvertedValue(BACKSPACE + CORRECT_VALUE));
    }


    @Test
    public void testCorrectInputColor() throws Exception {
        Assert.assertEquals("color: rgb(0, 0, 0);",
                new MainPage(driver).getInputTextColor(CORRECT_VALUE));
    }

    @Test
    public void testIncorrectInputColor() throws Exception {
        Assert.assertEquals("color: red;",
                new MainPage(driver).getInputTextColor(INCORRECT_VALUE));
    }


    @AfterMethod
    public void tearDown() throws Exception {
        driver.close();
    }
}
