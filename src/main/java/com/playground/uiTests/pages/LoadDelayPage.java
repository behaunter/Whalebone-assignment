package com.playground.uiTests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoadDelayPage {

    private Page page;

    public LoadDelayPage(Page page) {
        this.page = page;
    }

    public Locator linkElement() {
        return page.locator("text=Load Delay");
    }

    public Locator expectedButton() {
        return page.locator("text=Button Appearing After Delay");
    }

    public Locator expectedTextOnPage() {
        return page.locator("text=Scenario");
    }

    public String StringButton() {
        return "text=Button Appearing After Delay";
    }
    public void waitForSelectorToBeVisible(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

}
