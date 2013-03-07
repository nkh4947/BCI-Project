package org.tizen.common.bci.exception.generator;

import org.tizen.common.bci.exception.tracker.ExceptionTracker;


public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ExceptionGenerator.transform("java.lang.Exception", "Exception");
	}

}
