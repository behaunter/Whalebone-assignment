package сom.playground.uiTests.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class TestBase {

    private Browser browser;
    protected Page page;
    private BrowserContext context;
    private Boolean isTraceEnabled = false;

    @BeforeClass
    public void  setUp(){
        //initializing browser
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));

        //browser for context
        context = browser.newContext();

        //tracing
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(false)
                .setSources(false));
        isTraceEnabled = true;

        //creating new page
        page = context.newPage();
        page.navigate("http://uitestingplayground.com/");

    }

    //closing browser after tests
    @AfterClass
    public void tearDown(){
        if (browser != null){
            browser.close();
            browser = null;
        }
    }




}
