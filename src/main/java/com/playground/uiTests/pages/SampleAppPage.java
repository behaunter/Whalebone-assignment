package com.playground.uiTests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.apache.commons.lang3.RandomStringUtils;


public class SampleAppPage {

    private Page page;

    public SampleAppPage(Page page) {
        this.page = page;
    }

    public Locator sampleAppLink() {
        return page.getByText("Sample App");
    }

    public Locator userNameInput() {
        return page.getByPlaceholder("User Name");
    }

    public Locator passwordInput() {return page.locator("//input[@name='Password']");}
    public Locator loginBtn() {return page.locator("#login");}
    public Locator loginStatus() {return page.locator("#loginstatus");}

    public static final String actualUsername = "sadf";
    public static final String successfulLoginStatus = "Welcome, " + actualUsername + "!";
    public static final String invalidLoginStatus = "Invalid username/password";
    public static final String correctPassword = "pwd";
    public static final String RAND_MAX_NAME = RandomStringUtils.randomAlphanumeric(100,1000);
    public static final String RAND_MAX_PASS = RandomStringUtils.randomAlphanumeric(100,1000);
    public static final String RAND_PASS = RandomStringUtils.randomAlphanumeric(1,20);
    public static final String RAND_NAME = RandomStringUtils.randomAlphanumeric(1,20);

    public void loginInSampleApp(String username,String password) {
        sampleAppLink().click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        userNameInput().fill(username);
        passwordInput().fill(password);
        loginBtn().click();
    }





}
