package dk.eaaa.bm.optimization.util;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 * TimerUtil is used to time the duration of an operation. When the operation starts, the name and start time is logged. When the operation finishes, the finish time and name is logged along with the
 * run time of the operation.
 */
public class TimerUtil implements AutoCloseable {

    private long startTime;
    private long endTime;
    private Level loggingLevel;
    private String operationName;
    private Logger logger;

    /*
     * Default logger if none is specified by the user (calling method).
     */
    private Logger dftLogger = LoggerFactory.getLogger(this.getClass());

    /*
     * Construct new TimerUtil instance.
     * 
     * @param loggingLevel Output logging messages using this logging level.
     * @param operationName The logical name of the operation being performed.
     */
    private TimerUtil(Level loggingLevel, String operationName) {
        this.loggingLevel = loggingLevel;
        this.operationName = operationName;
        this.startTime = getTime();
        this.logger = dftLogger;
        logMsg(String.format("Started operation %s.", operationName));
    }

    /*
     * Construct new TimerUtil instance.
     * 
     * @param logger Output messages using this logger instead of the internal default.
     * @param loggingLevel Output logging messages using this logging level.
     * @param operationName The logical name of the operation being performed. 
     */
    private TimerUtil(Logger logger, Level loggingLevel, String operationName) {
        this.loggingLevel = loggingLevel;
        this.operationName = operationName;
        this.startTime = getTime();
        this.logger = logger == null ? dftLogger : logger;
        logMsg(String.format("Started operation %s.", operationName));
    }

    /**
     * Returns a new initialized TimerUtil object.
     * 
     * @param loggingLevel Output logging messages using this logging level.
     * @param operationName The logical name of the operation being performed.
     * @return TimerUtil object
     */
    public static TimerUtil start(Level loggingLevel, String operationName) {
        return new TimerUtil(loggingLevel, operationName);
    }

    /**
     * Returns a new initialized TimerUtil object with logging level set to debug.
     * 
     * @param operationName The logical name of the operation being performed.
     * @return TimerUtil object
     */
    public static TimerUtil start(String operationName) {
        return new TimerUtil(Level.DEBUG, operationName);
    }

    /**
     * Returns a new initialized TimerUtil object.
     * 
     * @param logger Output messages using this logger instead of the internal default.
     * @param loggingLevel Output logging messages using this logging level.
     * @param operationName The logical name of the operation being performed.
     * @return TimerUtil object
     */
    public static TimerUtil start(Logger logger, Level loggingLevel, String operationName) {
        return new TimerUtil(logger, loggingLevel, operationName);
    }

    /**
     * Returns a new initialized TimerUtil object with logging level set to debug.
     * 
     * @param logger Output messages using this logger instead of the internal default.
     * @param operationName The logical name of the operation being performed.
     * @return TimerUtil object
     */
    public static TimerUtil start(Logger logger, String operationName) {
        return new TimerUtil(logger, Level.DEBUG, operationName);
    }

    /**
     * Overridden close method implemented from AutoClosable.
     */
    @Override
    public void close() throws Exception {
        this.stop();
    }

    /**
     * Stops the timer and logs information including the run time of the operations.
     */
    public void stop() {
        try {
            if (this.endTime == 0L) {
                this.endTime = getTime();
                Duration duration = Duration.ofMillis(this.endTime - this.startTime);
                String msg = String.format("Finished operation %s - Time spent %s", this.operationName, duration);
                logMsg(msg);
            }
        } catch (Exception e) {
            logger.error("Unexpected error occurred in TimerUtil.", e);
        }
    }

    /*
     * Logs the specified message using the current logger and and logging level.
     */
    private void logMsg(String msg) {

        switch (loggingLevel) {

        case TRACE:
            logger.trace(msg);
            break;
        case DEBUG:
            logger.debug(msg);
            break;
        case INFO:
            logger.info(msg);
            break;
        case WARN:
            logger.warn(msg);
            break;
        case ERROR:
            logger.error(msg);
            break;
        }

    }

    /*
     * Returns the current system time in millis.
     */
    private long getTime() {
        return System.currentTimeMillis();
    }

}
