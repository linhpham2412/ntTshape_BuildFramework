package nt.tshape.automation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionManager {
    StringBuilder printOutInfo = null;
    private WebDriver driver;

    public void sendKeys(By elementToSendKey, CharSequence keysToSend) {
        try {
            WebElement workingElement = driver.findElement(elementToSendKey);
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

    public String getText(By elementToGetText) {
        try {
            WebElement workingElement = driver.findElement(elementToGetText);
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

    public void click(By elementToClick) {
        try {
            driver.findElement(elementToClick).click();
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
