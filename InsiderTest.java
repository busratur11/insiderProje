package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;

import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import pages.QAJobsPage;


public class InsiderTest extends BaseTest {

    @Test
    public void testInsiderCareerFlow() {
        driver.get("https://useinsider.com/");
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageOpened(), "Home page not opened");

        homePage.goToCareersPage();


        CareersPage careersPage = new CareersPage(driver);
        Assert.assertTrue(careersPage.isLocationsVisible(), "Locations section not visible");
        Assert.assertTrue(careersPage.isTeamsVisible(), "Teams section not visible");
        Assert.assertTrue(careersPage.isLifeAtInsiderVisible(), "Life at Insider section not visible");

        careersPage.goToQAJobs();

        QAJobsPage qaJobsPage = new QAJobsPage(driver);
        qaJobsPage.filterJobs("Istanbul, Turkiye", "Quality Assurance");
        Assert.assertTrue(qaJobsPage.areJobsListed(), "No QA jobs listed");
        Assert.assertTrue(qaJobsPage.areJobDetailsCorrect(), "Job details are incorrect");

        qaJobsPage.clickViewRole();
        Assert.assertTrue(driver.getCurrentUrl().contains("lever.co"), "Not redirected to Lever application form");
        }
    }
