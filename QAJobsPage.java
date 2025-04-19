package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class QAJobsPage extends BasePage {
    public QAJobsPage(WebDriver driver) {
        super(driver);
    }

    private final By locationFilter = By.xpath("//span[@id='select2-filter-by-location-container']");
    private final By departmentFilter = By.xpath("//span[@id='select2-filter-by-department-container']");
    private final By jobList = By.cssSelector("div.position-list-item");
    private final By viewRoleButton = By.xpath("//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5' and contains(text(), 'View Role')]");
    private final By cookieAcceptButton = By.id("wt-cli-accept-all-btn");


    public void filterJobs(String location, String department) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie banner closed.\nNo cookie bar");
        }


        WebElement locationDropdown = wait.until(ExpectedConditions.elementToBeClickable(locationFilter));
        js.executeScript("arguments[0].scrollIntoView(true);", locationDropdown);
        locationDropdown.click();

        By istanbulOption = By.xpath("//li[contains(@class,'select2-results__option') and text()='Istanbul, Turkiye']");
        WebElement istanbulElement = wait.until(ExpectedConditions.visibilityOfElementLocated(istanbulOption));
        istanbulElement.click();

        WebElement departmentDropdown = wait.until(ExpectedConditions.elementToBeClickable(departmentFilter));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", departmentDropdown);
        departmentDropdown.click();



        By searchInputLocator = By.xpath("//input[contains(@class,'select2-search__field')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInputLocator));
        WebElement departmentInput = wait.until(ExpectedConditions.elementToBeClickable(searchInputLocator));
        departmentInput.sendKeys(department);

        By qaOption = By.xpath("//li[contains(@class, 'select2-results__option') and text()='Quality Assurance']");
        WebElement qaElement = wait.until(ExpectedConditions.elementToBeClickable(qaOption));
        qaElement.click();


        try {
            Thread.sleep(1500);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    public boolean areJobsListed() {
        return driver.findElements(jobList).size() > 0;
    }

    public boolean areJobDetailsCorrect() {
        List<WebElement> jobs = driver.findElements(jobList);
        for (WebElement job : jobs) {
            String position = job.findElement(By.className("position-title")).getText();
            String dept = job.findElement(By.className("position-department")).getText();
            String loc = job.findElement(By.className("position-location")).getText();
            if (!position.contains("Quality Assurance") || !dept.contains("Quality Assurance") || !loc.contains("Istanbul, Turkiye")) {
                return false;
            }
        }
        return true;
    }

    public void clickViewRole() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewRole = wait.until(ExpectedConditions.elementToBeClickable(viewRoleButton));
        viewRole.click();
    }

}
