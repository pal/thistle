package com.palbrattberg.thistle;

import java.util.ArrayList;
import java.util.List;

public class TestStatus {

	public boolean allTestsPass() {
		return true;
	}

	public int getNumberOfVerifications() {
		return 1;
	}

	public List<String> getTestTitles() {
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("file: basic-features/test1-check-google-title.thistle, test 1");
		titles.add("Verify Google's title (Using test descriptions)");
		return titles;
	}

}
