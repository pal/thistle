package com.palbrattberg.thistle.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.palbrattberg.thistle.Command;
import com.palbrattberg.thistle.Scenario;
import com.palbrattberg.thistle.ScenarioParser;
import com.palbrattberg.thistle.runners.selenium.SeleniumRunner;

public class FileBasedScenarioParser extends ScenarioParser<File> {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<Scenario> parse(File input) {
		ArrayList<Scenario> scenarioList = new ArrayList<Scenario>();
		int scenarioCounter = 1;
		Scenario scenario = new Scenario();
		scenario.setTitle(String.format("file: %s, test %s", input.getName(),
				scenarioCounter));
		scenarioList.add(scenario);

		try {
			Scanner scanner = new Scanner(input);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (isTestDelimeter(line)) {
					if (scenarioCounter != 1) {
						scenario = new Scenario();
						scenarioList.add(scenario);
					}
					scenarioCounter++;
					scenario.setTitle(extractTitle(line));
					continue;
				}
				Command cmd = parseCommand(line);
				scenario.addCommand(cmd);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}

		return scenarioList;
	}

	private String extractTitle(String prospect) {
		Pattern pattern = Pattern.compile("Test:\\s+(.*)");
		Matcher matcher = pattern.matcher(prospect);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			throw new IllegalArgumentException(String.format(
					"String '%s' did not contain a Scenario title", prospect));
		}
	}

	private boolean isTestDelimeter(String prospect) {
		Pattern pattern = Pattern.compile("Test:(.*)");
		Matcher matcher = pattern.matcher(prospect);

		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	private Command parseCommand(String nextLine) {
		Command cmd = null;

		Pattern cmdNavigateTo = Pattern.compile("Navigate to\\s+(.*)");
		Pattern cmdVerify = Pattern
				.compile("Verify\\s+(?:that)?\\s?+(?:the)?\\s?+(title)\\s?+is\\s?+(not)?\\s?+\"(.*)\"");
		Matcher cmdNavigateToMatcher = cmdNavigateTo.matcher(nextLine);

		if (cmdNavigateToMatcher.find()) {
			cmd = new Command(Command.Type.NAVIGATE_TO);
			cmd.setProperty("url", cmdNavigateToMatcher.group(1));
		} else {
			Matcher cmdVerifyMatcher = cmdVerify.matcher(nextLine);
			if (cmdVerifyMatcher.find()) {
				cmd = new Command(Command.Type.VERIFY_ELEMENT);
				cmd.setProperty("locator", cmdVerifyMatcher.group(1));
				cmd.setProperty("should_match", !"not"
						.equalsIgnoreCase(cmdVerifyMatcher.group(2)));
				cmd.setProperty("expected_value", cmdVerifyMatcher.group(3));
			} else {
				String err = String.format("Couldn't parse input: '%s'",
						nextLine);
				logger.error(err);
				throw new IllegalArgumentException(err);
			}
		}

		return cmd;
	}
}
