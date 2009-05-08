package com.palbrattberg.thistle;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scenario {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<Command> commandList = new ArrayList<Command>();
	private String title = "file: basic-features/test1-check-google-title.thistle, test 1";

	public List<Command> getCommands() {
		return commandList;
	}

	public void addCommand(final Command cmd) {
		commandList.add(cmd);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String aTitle) {
		this.title = aTitle;
	}
}
