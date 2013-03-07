package org.tizen.common.bci.exception.tracker;

import java.util.ArrayList;
import org.tizen.common.bci.exception.appender.IExceptionAppender;


public class ExceptionTracker {
	
	public static final String FQCN;
	public static final String NAME_METHOD_TRACK = "trackException";
	public static final String TYPE_METHOD_TRACK = "(Ljava/lang/Exception;)V";
	
	private static final ArrayList<IExceptionAppender> appenderList = new ArrayList<IExceptionAppender>();
	
	static {
		FQCN = ExceptionTracker.class.getCanonicalName().replace('.', '/');
	}
	
	//Removing synchronized may reduce consistency but avoid deadlock, and increase reliability
	public static void trackException(Exception e) {
//		synchronized (appenderList) {
			for(IExceptionAppender appender: appenderList) {
				appender.appendException(e);
			}			
//		}
	}
	
	public static void addApender(IExceptionAppender appender) {
		synchronized (appenderList) {
			ExceptionTracker.appenderList.add(appender);			
		}
	}
	
	public static void removeAppender(IExceptionAppender appender) {
		synchronized (appenderList) {
			ExceptionTracker.appenderList.remove(appender);	
		}
	}
}
