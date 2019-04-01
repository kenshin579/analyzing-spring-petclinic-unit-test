package org.springframework.samples;


/**
 *
 */
public class TestSetupException extends Exception {

	private static final long serialVersionUID = 1L;

	public TestSetupException(String internalMessage, Throwable t) {
		super(internalMessage, t);
	}

	public TestSetupException(String internalMessage) {
		super(internalMessage);
	}

	public TestSetupException(Exception e2) {
		super(e2);
	}

}
