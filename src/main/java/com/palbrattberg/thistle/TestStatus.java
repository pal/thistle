package com.palbrattberg.thistle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStatus {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HashMap<Command, Boolean> verifications;

	public TestStatus() {
		verifications = new HashMap<Command, Boolean>();
	}

	public boolean allTestsPass() {
		boolean allOk = true;
		for (Boolean ok : verifications.values()) {
			if (!ok) {
				allOk = false;
				break;
			}
		}
		return allOk;
	}

	public int getNumberOfVerifications() {
		return verifications.size();
	}

	public List<String> getTestTitles() {
		ArrayList<String> titles = new ArrayList<String>();
		titles
				.add("file: basic-features/test1-check-google-title.thistle, test 1");
		titles.add("Verify Google's title (Using test descriptions)");
		return titles;
	}

	public void addVerification(Command key, boolean value) {
		verifications.put(key, value);
	}

}
