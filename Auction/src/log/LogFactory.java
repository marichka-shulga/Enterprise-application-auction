package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class LogFactory {
	
	public static Logger getLogger(Class<?> classForLogging){
		return LogManager.getLogger(classForLogging.getName());
	}

}
