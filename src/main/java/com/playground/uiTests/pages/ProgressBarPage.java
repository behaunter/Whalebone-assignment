package com.playground.uiTests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProgressBarPage {
    private Page page;
    public ProgressBarPage (Page page){
        this.page = page;
    }



    public Locator progressBarLink() {
        return page.getByText("Progress Bar");
    }

    public Locator startButton() {
        return page.locator("#startButton");
    }

    public Locator progressBarLocator() {
        return page.locator("#progressBar");
    }

    public Locator stopButton() {
        return page.locator("#stopButton");
    }

    public Locator result() {
        return page.locator("#result");
    }

    public String attribute() {
        return "aria-valuenow";
    }

    public String expectedResult() {
        return "Result: 0";
    }

    public void pressStopUntilPercent(String percent) {
        for (int i = 0; i < 100000; i++) {
            String progress = progressBarLocator().getAttribute(attribute());
            if (progress.equals(percent)) {
                stopButton().click();
                break;
            }
        }
    }

}
