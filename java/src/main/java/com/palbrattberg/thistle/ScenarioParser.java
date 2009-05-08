package com.palbrattberg.thistle;

import java.util.List;

public abstract class ScenarioParser<T> {

	public abstract List<Scenario> parse(T input);

}
