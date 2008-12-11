package com.palbrattberg.thistle.runners;

import java.util.List;

import com.palbrattberg.thistle.Command;
import com.palbrattberg.thistle.TestStatus;

public interface Runner {
	TestStatus run(List<Command> commandList);
}
