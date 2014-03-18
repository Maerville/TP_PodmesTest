package ru.mail.go;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    private static final String ID_INPUT = "ival";
    private static final String ID_OUTPUT = "oval";
    private static final String ID_REVERSE = "change_conv";
    private static final String ID_FIRST_PLACE = "js-result_1";
    private static final String ATTRIBUTE_STYLE = "style";
    private static final String ATTRIBUTE_VALUE = "value";



    public MainPage (WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstResultClass() {
        return driver.findElement(By.id(ID_FIRST_PLACE)).getAttribute("class");
    }
    public void enterText (String query) {
        driver.findElement(By.id(ID_INPUT)).sendKeys(query);
    }


    public String getInputTextColor(String query) {
        enterText(query);
        return driver.findElement(By.id(ID_INPUT)).getAttribute(ATTRIBUTE_STYLE);
    }

    public String getInputValue (String query) {
        enterText(query);
        return driver.findElement(By.id(ID_INPUT)).getAttribute(ATTRIBUTE_VALUE);
    }

    public String getConvertedValue (String query) {
        enterText(query);

        return driver.findElement(By.id(ID_OUTPUT)).getText();
    }

    public void reverseConverter () {
        driver.findElement(By.id(ID_REVERSE)).click();
    }
}
