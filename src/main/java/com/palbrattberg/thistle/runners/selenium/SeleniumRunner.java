package com.palbrattberg.thistle.runners.selenium;

import java.util.List;

import com.palbrattberg.thistle.Command;
import com.palbrattberg.thistle.TestStatus;
import com.palbrattberg.thistle.runners.Runner;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleniumException;

public class SeleniumRunner implements Runner {

	protected DefaultSelenium selenium;
	protected boolean started = false;
	protected String host = "localhost";
	protected int port = 4444;
	protected String browser = "*firefox";

	public SeleniumRunner() {

	}

	public void close() {
		selenium.stop();
		started = false;
	}

	@Override
	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}

	public void initialize(String url) {
		if (!started) {
			selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/");
			selenium.start();
			started = true;
		}
	}
//
//	public void testHelloWorld() throws Exception {
//		try {
//			selenium.open("http://localhost:8080/mywebapp/index.jsp");
//			assertEquals("Hello, World", selenium.getText("//h1"));
//		} catch (SeleniumException ex) {
//			fail(ex.getMessage());
//			throw ex;
//		}
//	}

	public TestStatus run(final List<Command> commandList) {
		return new TestStatus();
	}

}
