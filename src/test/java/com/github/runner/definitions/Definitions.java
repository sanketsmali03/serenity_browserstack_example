package com.github.runner.definitions;

import java.net.URL;
import java.util.Iterator;

import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import net.serenitybdd.cucumber.suiteslicing.SerenityTags;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class Definitions {

	 public static ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<AppiumDriver>();

		@Before
		public void before() {
			System.out.println("Calling Driver 1");
			EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
			

			//String username = System.getenv("BROWSERSTACK_USERNAME");

			//String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
			String username ="sanketmali4";
			String accessKey="XaqpcHttuyFzXSzC3uNM";
			String environment = System.getProperty("environment");
			System.out.println("environment is " + environment);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			

			Iterator it = environmentVariables.getKeys().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();

				if (key.equals("browserstack.user") || key.equals("browserstack.key")
						|| key.equals("browserstack.server")) {
					continue;
				} else if (key.startsWith("bstack_")) {
					capabilities.setCapability(key.replace("bstack_", ""), environmentVariables.getProperty(key));
					if (key.equals("bstack_browserstack.local")
							&& environmentVariables.getProperty(key).equalsIgnoreCase("true")) {
						System.setProperty("browserstack.local", "true");
					}
				} else if (environment != null && key.startsWith("environment." + "parallel_1")) {

					capabilities.setCapability(key.replace("environment." + "parallel_1" + ".", ""),
							environmentVariables.getProperty(key));
					if (key.equals("environment." + environment + ".browserstack.local")
							&& environmentVariables.getProperty(key).equalsIgnoreCase("true")) {
						System.setProperty("browserstackgr.local", "true");
					}
				}
			}
			capabilities.setCapability("os_version", "9.0");
			capabilities.setCapability("device", "Google Pixel 3");
			capabilities.setCapability("app", "bs://e324e0ffe83b2936cd072d75e565c4666767157c");
			try {
				driverThread.set(new AndroidDriver(new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"),capabilities));

			} catch (Exception e) {
				System.out.println(e);

			}
			SerenityTags.create().tagScenarioWithBatchingInfo();
		}

    @Given("there {int} cucumber(s) in my belly")
    public void putCucumbersInBelly(Integer num) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(num + " cucumbers have been eaten");
    }
    
    @After
	public void teardown()
	{
		driverThread.get().quit();
	}
}
