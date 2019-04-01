package org.springframework.samples.petclinic.common;


/**
 *
 */
public class PetClinicCoreException extends Exception {

	private static final long serialVersionUID = 101L;

	public PetClinicCoreException(String internalMessage, Throwable t) {
		super(internalMessage, t);
	}

	public PetClinicCoreException(String internalMessage) {
		super(internalMessage);
	}

	public PetClinicCoreException(Exception e2) {
		super(e2);
	}

}
