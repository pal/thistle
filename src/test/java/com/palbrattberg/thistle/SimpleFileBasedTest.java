package com.palbrattberg.thistle;

import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.palbrattberg.thistle.parsers.FileBasedScenarioParser;
import com.palbrattberg.thistle.runners.Runner;
import com.palbrattberg.thistle.runners.selenium.SeleniumRunner;

/**
 * Verify the simplest of tests.
 * 
 * @author Pål Brattberg
 */
public class SimpleFileBasedTest {

	private Runner runner;
	private FileBasedScenarioParser parser;

	@Before
	public void setUp() {
		runner = new SeleniumRunner();
		parser = new FileBasedScenarioParser();
	}

	@Test
	public void testcase_000() {
		String file = "src/test/thistle/basic-features/test1-check-google-title.thistle";
		List<Scenario> scenarioList = parser.parse(new File(file));
		assertThat(scenarioList.size(), is(1));
		Scenario scenario = scenarioList.get(0);
		assertThat(scenario.getTitle(),
				equalTo("file: test1-check-google-title.thistle, test 1"));

		List<Command> commandList = scenario.getCommands();
		assertThat(commandList.size(), is(2));
		Command cmd = commandList.get(0);
		assertThat(cmd.getType(), equalTo(Command.Type.NAVIGATE_TO));
		cmd = commandList.get(1);
		assertThat(cmd.getType(), equalTo(Command.Type.VERIFY_ELEMENT));
	}

	@Test
	public void testcase_001() {
		String file = "src/test/thistle/basic-features/test2-check-google-title-with-description.thistle";
		List<Scenario> scenarioList = parser.parse(new File(file));
		assertThat(scenarioList.size(), is(1));
		Scenario scenario = scenarioList.get(0);
		assertEquals(scenario.getTitle(),
				"Verify Google's title (Using test descriptions)");

		List<Command> commandList = scenario.getCommands();
		assertThat(commandList.size(), is(2));
		Command cmd = commandList.get(0);
		assertThat(cmd.getType(), equalTo(Command.Type.NAVIGATE_TO));
		cmd = commandList.get(1);
		assertThat(cmd.getType(), equalTo(Command.Type.VERIFY_ELEMENT));
	}

	// TestStatus status = runner.run(commandList);
	// assertTrue(status.allTestsPass());
	// assertThat(status.getNumberOfVerifications(), is(1));
	// assertThat(
	// status.getTestTitles().get(0),
	// equalTo("file: basic-features/test1-check-google-title.thistle, test 1"));
	// }

	// @Test
	// public void testcase001() {
	// TestStatus status = runner
	// .runFile("basic-features/test1-check-google-title.thistle");
	// assertTrue(status.allTestsPass());
	// assertThat(status.getNumberOfVerifications(), is(1));
	// assertThat(
	// status.getTestTitles().get(0),
	// equalTo("file: basic-features/test1-check-google-title.thistle, test 1"));
	// }
	//
	// @Test
	// public void testcase002() {
	// TestStatus status = runner
	// .runFile("basic-features/test1-check-google-title-failing.thistle");
	// assertFalse(status.allTestsPass());
	// assertThat(status.getNumberOfVerifications(), is(1));
	// assertThat(
	// status.getTestTitles().get(0),
	// equalTo("file: basic-features/test1-check-google-title.thistle, test 1"));
	// }
	//
	// @Test
	// public void testcase003() {
	// TestStatus status = runner
	// .runFile("test2-check-google-title-with-description.thistle");
	// assertTrue(status.allTestsPass());
	// assertThat(status.getNumberOfVerifications(), is(1));
	// assertThat(status.getTestTitles().get(0),
	// equalTo("Verify Google's title (Using test descriptions)"));
	//
	// }

}
