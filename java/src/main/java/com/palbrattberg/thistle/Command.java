package com.palbrattberg.thistle;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.palbrattberg.thistle.runners.selenium.SeleniumRunner;

public class Command {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	public enum Type {
		NAVIGATE_TO, VERIFY_ELEMENT
	}

	private Type type = Type.NAVIGATE_TO;
	private HashMap<String, Object> properties;

	public Command(final Type aType) {
		this.type = aType;
		this.properties = new HashMap<String, Object>();
	}

	public Type getType() {
		return type;
	}

	public Object getProperty(String key) {
		return properties.get(key);
	}

	public void setProperty(String key, Object value) {
		properties.put(key, value);
		logger.debug("Set key '{}' to '{}'", key, value);
	}

}
