package сom.playground.uiTests.tests;

import com.playground.uiTests.pages.SampleAppPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SampleAppTest extends TestBase {
    //    - from the Home page, navigate to the Sample App page and cover all the functionalities of that feature by tests
    SampleAppPage sampleAppPage;

    @BeforeClass
    public void prepare() {
        sampleAppPage = new SampleAppPage(page);
    }

    @Test
    public void successfulLoginAndLogout()  {
        sampleAppPage.loginInSampleApp(sampleAppPage.actualUsername,sampleAppPage.correctPassword);
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.successfulLoginStatus);
    }

    @Test
    public void incorrectPassword() {
        sampleAppPage.loginInSampleApp(sampleAppPage.actualUsername,sampleAppPage.RAND_PASS);
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);
    }

    @Test
    public void emptyValuesInBothFields() {
        sampleAppPage.loginInSampleApp("","");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);

    }

    @Test
    public void emptyValuesInLoginOnly() {
        sampleAppPage.loginInSampleApp("","pwd");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);
    }

    @Test
    public void emptyValuesInPasswordOnly() {
        sampleAppPage.loginInSampleApp(sampleAppPage.RAND_NAME,"");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);
    }

    @Test
    public void specialSymbolsInBothFields() {
        sampleAppPage.loginInSampleApp("!@#$%","^&*()");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);

    }
    @Test
    public void spacesInTheBeginningAndEnd() {
        sampleAppPage.loginInSampleApp(" user "," pwd ");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);
    }

    @Test
    public void spacesInTheMiddle() {
        sampleAppPage.loginInSampleApp("u s e r","p w d");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);
    }

    @Test
    public void minimalValuesInFields() {
        sampleAppPage.loginInSampleApp("a","a");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);
    }

    @Test
    public void maximalValuesInFields() {
        sampleAppPage.loginInSampleApp(sampleAppPage.RAND_MAX_NAME,sampleAppPage.RAND_MAX_PASS);
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);
    }

    @Test
    public void checkXssInjection() {
        sampleAppPage.loginInSampleApp("<script> alert(\"XSS\");</script>","<script> alert(\"XSS\");</script>");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);

    }

    @Test
    public void checkSQLInjections() {
        sampleAppPage.loginInSampleApp(" SELECT * FROM Users WHERE UserId = 1;"," SELECT * FROM Users WHERE UserId = 1;");
        String actualLoginStatus = sampleAppPage.loginStatus().innerText();
        assertEquals(actualLoginStatus, sampleAppPage.invalidLoginStatus);
    }


}
