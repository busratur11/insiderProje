package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CareersPage extends BasePage {
    public CareersPage(WebDriver driver) {
        super(driver);
    }

    private final By locations = By.id("career-our-location");
    private final By teams = By.id("career-find-our-calling");
    private final By lifeAtInsider = By.xpath("//h2[contains(@class, 'elementor-heading-title') and text()='Life at Insider']");

    public boolean isLocationsVisible() {
        return driver.findElement(locations).isDisplayed();
    }

    public boolean isTeamsVisible() {
        return driver.findElement(teams).isDisplayed();
    }
    public boolean isLifeAtInsiderVisible() {
        return driver.findElement(lifeAtInsider).isDisplayed();
    }

    public void goToQAJobs() {
        driver.get("https://useinsider.com/careers/quality-assurance/");


        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("wt-cli-accept-all-btn")));
            cookieBtn.click();
            System.out.println("Cookie banner closed.");
        } catch (Exception e) {
            System.out.println("No cookie banner appeared.");
        }


        WebElement qaJobsBtn = driver.findElement(By.xpath("//a[contains(@class, 'btn') and contains(text(), 'QA jobs')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", qaJobsBtn);
    }

}

