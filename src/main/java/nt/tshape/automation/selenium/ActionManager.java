package nt.tshape.automation.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ActionManager {
    public WebDriver driver;
    public WebDriverWait wait;
    public TestContext testContext;
    StringBuilder printOutInfo = null;

    public ActionManager(WebDriver driver, WebDriverWait wait, TestContext testContext) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constant.SHORT_TIME);
        this.testContext = new TestContext();
    }

    public WebElement highlightElement(WebElement elementToHighlight) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].style.borders='2px solid red'", elementToHighlight);
        return elementToHighlight;
    }

    public List<WebElement> highlightElement(List<WebElement> elementsToHighlight) {
        for (WebElement webElement : elementsToHighlight) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("arguments[0].style.borders='2px solid red'", webElement);
        }
        return elementsToHighlight;
    }

    public WebElement findElement(String elementByTypeAndPath) {
        WebElement workingElement = null;
        String[] extractedString = elementByTypeAndPath.split("=", 2);
        String byType = extractedString[0];
        String path = extractedString[1];
        try {
            switch (byType) {
                case "id" -> workingElement = driver.findElement(By.id(path));
                case "xpath" -> workingElement = driver.findElement(By.xpath(path));
                case "class" -> workingElement = driver.findElement(By.className(path));
                case "css" -> workingElement = driver.findElement(By.cssSelector(path));
                case "linkText" -> workingElement = driver.findElement(By.linkText(path));
                case "name" -> workingElement = driver.findElement(By.name(path));
                case "partialLinkText" -> workingElement = driver.findElement(By.partialLinkText(path));
                case "tag" -> workingElement = driver.findElement(By.tagName(path));
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("The element located by : [" + elementByTypeAndPath + "] cannot be found!");
            throw new NoSuchElementException("The element located by : [" + elementByTypeAndPath + "] cannot be found!");
        } catch (StaleElementReferenceException staleElementReferenceException) {
            return findElement(elementByTypeAndPath);
        } catch (Exception e) {
            System.out.println("There is an error when finding the element : [" + elementByTypeAndPath + "]");
            throw e;
        }
        return workingElement;
    }

    public void sendKeys(String elementToSendKey, CharSequence keysToSend) {
        try {
            WebElement workingElement = findElement(elementToSendKey);
            workingElement.sendKeys(keysToSend);
            printOutInfo.append("Sent [").append(keysToSend).append("] into element [").append(elementToSendKey.toString()).append("]");
        } catch (StaleElementReferenceException staleElementReferenceException) {
            sendKeys(elementToSendKey, keysToSend);
        } catch (Exception e) {
            printOutInfo.append("Cannot send [").append(keysToSend).append("] into element [").append(elementToSendKey.toString()).append("]");
            throw e;
        }
        System.out.println(printOutInfo);
    }

    public String getText(String elementToGetText) {
        try {
            WebElement workingElement = findElement(elementToGetText);
            String resultText = workingElement.getTagName().equalsIgnoreCase("input") ? workingElement.getAttribute("value") : workingElement.getText();
            printOutInfo.append("Got text [").append(resultText).append("] from element [").append(elementToGetText.toString()).append("]");
            System.out.println(printOutInfo);
            return resultText;
        } catch (StaleElementReferenceException staleElementReferenceException) {
            return getText(elementToGetText);
        } catch (Exception e) {
            printOutInfo.append("Cannot get text from element [").append(elementToGetText.toString()).append("]");
            System.out.println(printOutInfo);
            throw e;
        }
    }

    public void click(String elementToClick) {
        try {
            findElement(elementToClick).click();
            printOutInfo.append("Clicked on the element [").append(elementToClick.toString()).append("]");
            System.out.println(printOutInfo);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            click(elementToClick);
        } catch (Exception e) {
            printOutInfo.append("Cannot click on element [").append(elementToClick.toString()).append("]");
            System.out.println(printOutInfo);
            throw e;
        }
    }
}
