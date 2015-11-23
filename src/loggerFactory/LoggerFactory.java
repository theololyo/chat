/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggerFactory;

/**
 *
 * @author Theo
 */
public class LoggerFactory {

    public Logger getLogger(String loggerType) {
        if (loggerType.equalsIgnoreCase("ConsoleLoggerSubscriber")) {
            return new ConsoleLoggerSubscriber();
        }

        return null;

    }

    public Logger getFileLogger(String loggerType, String loggerPath) {
        if (loggerType.equalsIgnoreCase("FileLoggerSubscriber")) {
            return new FileLoggerSubscriber(loggerPath);
        } else if (loggerType.equalsIgnoreCase("FileLogger")) {
            return new FileLogger(loggerPath);
        }

        return null;
    }

}
