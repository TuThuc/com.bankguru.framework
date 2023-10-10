package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUI.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//Common class

/**
 * @author Admin
 */
public class BasePage {
    WebDriver driver;

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    // Nhiệm vụ mở 1 URL bất kỳ
    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
       // sleepInSecond(3);
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());

    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByID(WebDriver driver, String expectedID) {
        Set<String> allTabIDs = driver.getWindowHandles();

        for (String id : allTabIDs) {

            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {

        Set<String> allTabIDs = driver.getWindowHandles();

        for (String id : allTabIDs) {

            driver.switchTo().window(id);

            String actualTitle = driver.getTitle();

            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);

        }

    }
    private By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type is not support");
        }
        return by;
    }

    private String getDynamicXpath(String locatorType, String... dynamicValues) {
        // System.out.println("Locator Type before" + locatorType);
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValues);
        }
        // System.out.println("Value map before" + dynamicValues);
        // System.out.println("Locator Type after" + locatorType);
        return locatorType;
    }

    public WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    public void clearValueInToElementByDeleteKey(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

    }

    public String getElementText(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getText();
    }

    public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(textItem);
    }

    public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdownList(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
        // Step 1: Click vao element cho nó xổ hết ra
        getWebElement(driver, parentXpath).click();
        sleepInSecond(1);
        List<WebElement> allitems = new WebDriverWait(driver,longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childXpath)));
        for (WebElement item : allitems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
        select.selectByVisibleText(textItem);
    }

    public String getSelectItemDefautDropdown(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
        return getWebElement(driver, locatorType).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver, String locatorType) {
        return getListWebElement(driver, locatorType).size();
    }

    public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
        return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType) {
        // Ti thấy element
        // Case 1: Displayed - trả về true
        // Case 2: Undisplayed - trả về false
        try {
            return getWebElement(driver, locatorType).isDisplayed();
        } catch (NoSuchElementException e) {
            // Case 3: trả về false
            return false;
        }
    }
    // public boolean isElementUnDisplayed(WebDriver driver, String locatorType) {
    //
    // return (!getWebElement(driver, locatorType).isDisplayed());

    // }
    public boolean isElementUnDisplayed(WebDriver driver, String locatorType) {
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, locatorType);
        // Nếu như mình gán = 5 apply cho tất cả các step về sau: findElement/
        // findElements
        overrideImplicitTimeout(driver, longTimeout);

        if (elements.size() == 0) {

            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {

            return true;
        } else {

            return false;
        }
    }

    public boolean isElementUnDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        overrideImplicitTimeout(driver, shortTimeout);
        List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        overrideImplicitTimeout(driver, longTimeout);

        if (elements.size() == 0) {

            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {

            return true;
        } else {

            return false;
        }
    }

    public void overrideImplicitTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {

        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();

    }

    public boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isSelected();
    }

    public void switchToFrame(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locatorType) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locatorType)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locatorType), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }

    public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
    }

    public void scrollToElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
    }
    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
    }
    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
        return status;

    }

    public void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    /*
     * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
     */
    public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideImplicitTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(driver, longTimeout);
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, locatorType)));
    }

    public void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForElementPresense(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver,longTimeout);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorType)));
    }
    public void inputToTextboxByName(WebDriver driver, String textboxName, String valueToInput){
        waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, textboxName);
        sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, valueToInput, textboxName);
    }
    public void clickToButtonByName(WebDriver driver, String buttonName){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_NAME, buttonName);
        clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_NAME, buttonName);
    }
    public String getFieldErrorMessageById(WebDriver driver, String errorID ){
        waitForElementVisible(driver, BasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_LABEL, errorID);
      return  getElementText(driver, BasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_LABEL, errorID);
    }
    public void pressTabAtTextboxByName(WebDriver driver, String locator) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, locator);
        pressKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, Keys.TAB, locator);
    }

    private long longTimeout = GlobalConstants.getGlobalConstants().getLongTimeout();
    private long shortTimeout = GlobalConstants.getGlobalConstants().getShortTimeout();

}