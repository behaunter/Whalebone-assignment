package сom.playground.uiTests.tests;


import com.playground.uiTests.pages.LoadDelayPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoadDelayTest extends TestBase{


// on the Home page, click on the Load Delay and verify the page will get loaded in reasonable time

    @Test
    public void LoadDelayTest(){
        LoadDelayPage loadDelayPage = new LoadDelayPage(page);

        loadDelayPage.linkElement().click();
        loadDelayPage.waitForSelectorToBeVisible(loadDelayPage.StringButton());

        assertTrue(loadDelayPage.expectedButton().isVisible());
        assertTrue(loadDelayPage.expectedTextOnPage().isVisible());

    }
}
