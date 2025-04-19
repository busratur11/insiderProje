package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToCompanyMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement companyMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("x")));
        companyMenu.click();
    }
    private final By companyMenu = By.xpath("//a[contains(@class, 'dropdown-toggle') and contains(normalize-space(text()), 'Company')]");
    private final By careersOption = By.xpath("//a[@class='dropdown-sub' and contains(text(), 'Careers')]");
    public boolean isHomePageOpened() {
        return driver.getTitle().contains("Insider");
    }



    public void goToCareersPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'dropdown-toggle') and contains(normalize-space(text()), 'Company')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='dropdown-sub' and contains(text(), 'Careers')]"))).click();
    }

}
