package com.palbrattberg.thistle;

public class Command {

	public enum Type{
		NAVIGATE_TO, VERIFY_ELEMENT
	}

	private Type type = Type.NAVIGATE_TO;

	public Command(final Type aType) {
		this.type = aType;
	}

	public Type getType() {
		return type;
	}

}
