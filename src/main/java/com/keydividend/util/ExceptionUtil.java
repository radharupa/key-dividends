package com.keydividend.util;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

/**
 * 
 * @author rupau
 *
 */
public class ExceptionUtil {
	
	
	public static String getDetails(Throwable exception) {
		
		if (exception == null) {
			return null;
		}				
		
		StringBuilder details = new StringBuilder();
		boolean isTopLevelException = Boolean.TRUE;
		
		do {
			
			if (!isTopLevelException) {
				details.append(" Caused by: ");	
			}
			
			CharArrayWriter cWriter = new CharArrayWriter();
			PrintWriter pWriter = new PrintWriter(cWriter);
			exception.printStackTrace(pWriter);
			
			// exception.printStackTrace() API returns the fullstack trace of the top level exception
			// and initial few lines of the stack trace for all the parent exceptions.
			// We don't want to just few lines of the parent level exceptions, rather we want complete
			// stack trace. Through the while looping we get all the parent exception and
			// we will be printing the complete stack trace.
			// Thus we want to chop off initial few lines of the parent exception, returned by the current
			// exception.
			String fullStackTrace = cWriter.toString();
			int endOfCurrentStackTrace = fullStackTrace.indexOf("Caused by: ");
			if (endOfCurrentStackTrace > 0) {
			
				fullStackTrace = fullStackTrace.substring(0, endOfCurrentStackTrace);
			}			
			details.append(fullStackTrace);
			
			isTopLevelException = Boolean.FALSE;
			exception = exception.getCause();
		} while (exception != null);
				
		return details.toString();
	}

}
