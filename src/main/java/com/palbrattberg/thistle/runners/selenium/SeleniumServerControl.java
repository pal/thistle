package com.palbrattberg.thistle.runners.selenium;

import java.util.List;

import org.mortbay.util.MultiException;
import org.openqa.selenium.server.SeleniumServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Code from: http://stackoverflow.com/questions/
 * 321112/running-selenium-from-a-java-process
 */
public class SeleniumServerControl {
	private static final SeleniumServerControl instance = new SeleniumServerControl();
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static SeleniumServerControl getInstance() {
		return instance;
	}

	private SeleniumServer server = null;

	protected SeleniumServerControl() {
	}
	
	public void startSeleniumServer() {
		if (server == null) {
			try {
				server = new SeleniumServer(SeleniumServer.DEFAULT_PORT);
				logger.debug("Created selenium server: {}", server.toString());
			} catch (Exception e) {
				logger.error("Could not create Selenium Server", e);
			}
		}
		try {
			server.start();
		} catch (Exception e) {
			logger.error("Could not start Selenium Server", e);
			if (e instanceof MultiException) {
				MultiException multiEx = (MultiException) e;
				for (Exception ex : ((List<Exception>) multiEx.getExceptions())) {
					logger.warn("MultiException", ex);
				}
			}
		}
	}

	public void stopSeleniumServer() {
		if (server != null) {
			try {
				server.stop();
				server = null;
			} catch (Exception e) {
				logger.error("Could not stop Selenium Server", e);
			}
		}
	}
}