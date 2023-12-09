package сom.playground.uiTests.tests;

import com.playground.uiTests.pages.ProgressBarPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProgressBarTest extends TestBase {

// from the Home page, navigate to the Progress Bar page and follow the instructions specified in the Scenario section


    @Test
    public void stopProgressBarAt75Percent()   {
        ProgressBarPage progressBarPage = new ProgressBarPage(page);

        progressBarPage.progressBarLink().click();
        progressBarPage.startButton().click();

        progressBarPage.pressStopUntilPercent("75");

        Assert.assertTrue(progressBarPage.result().innerText().contains(progressBarPage.expectedResult()));
    }



}
