package com.palbrattberg.thistle.runners.selenium;

import java.util.List;

import org.openqa.selenium.server.SeleniumServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.palbrattberg.thistle.Command;
import com.palbrattberg.thistle.TestStatus;
import com.palbrattberg.thistle.runners.Runner;
import com.thoughtworks.selenium.DefaultSelenium;

public class SeleniumRunner implements Runner {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected DefaultSelenium selenium;
	protected boolean started = false;
	protected String host = "localhost";
	protected int port = SeleniumServer.DEFAULT_PORT;
	protected String browser = "*iehta";
	protected SeleniumServerControl seleniumServer;

	public SeleniumRunner() {
		seleniumServer = SeleniumServerControl.getInstance();
		try {
			seleniumServer.startSeleniumServer();
		} catch (Exception e) {
			throw new RuntimeException("Couldn't start selenium server!", e);
		}

	}

	public void close() {
		try {
			selenium.stop();
		} catch (Exception e) {
			logger.warn("Got error when stopping selenium browser handle", e);
		}

		started = false;
	}

	@Override
	protected void finalize() throws Throwable {
		close();
		seleniumServer.stopSeleniumServer();
		super.finalize();
	}

	public void initialize(String url) {
		if (!started) {
			logger.debug("Starting Selenium runner at {}", url);
			selenium = new DefaultSelenium(host, port, browser, url);
			selenium.start();
			started = true;
		}
	}

	//
	// public void testHelloWorld() throws Exception {
	// try {
	// selenium.open("http://localhost:8080/mywebapp/index.jsp");
	// ;
	// } catch (SeleniumException ex) {
	// fail(ex.getMessage());
	// throw ex;
	// }
	// }

	public TestStatus run(final List<Command> commandList) {
		TestStatus status = new TestStatus();
		for (Command cmd : commandList) {
			if (cmd.getType().equals(Command.Type.NAVIGATE_TO)) {
				boolean ok = doOpen((String) cmd.getProperty("url"));
				// status.addVerification(cmd, ok);
			} else if (cmd.getType().equals(Command.Type.VERIFY_ELEMENT)) {
				String locator = (String) cmd.getProperty("locator");
				boolean should_match = (Boolean) cmd
						.getProperty("should_match");
				String expected_value = (String) cmd
						.getProperty("expected_value");

				boolean ok = doVerify(locator, should_match, expected_value);
				status.addVerification(cmd, ok);
			}
		}
		return status;
	}

	private boolean doVerify(String locator, boolean should_match,
			String expected_value) {
		// assertEquals("hello world - Google Search", browser.getTitle());
		String actual_value = selenium.getText("//" + locator) + "";
		boolean testOk = actual_value.equals(expected_value) == should_match;
		logger
				.debug(
						"Verifying that '{}' equals '{}' is {}. Actual value: '{}' Test pass: {}",
						new Object[] { locator, expected_value, should_match,
								actual_value, testOk });
		return testOk;
	}

	private boolean doOpen(String url) {
		logger.debug("Navigating to {}", url);
		initialize(url);
		selenium.open(url);
		return true;
	}

}
