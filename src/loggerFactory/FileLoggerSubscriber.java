/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggerFactory;

import chat.MessagePacket;
import chat.Subscriber;

/**
 *
 * @author Theo
 */
public class FileLoggerSubscriber implements Logger, Subscriber {

    private FileLogger mFileLogger;

    public FileLoggerSubscriber(String pathToLog) {
        mFileLogger = (FileLogger) new LoggerFactory().getFileLogger("FileLogger",pathToLog);
    }

    @Override
    public void update(MessagePacket messagepacket) {
        writeLogg(messagepacket.getMessage());
    }

    @Override
    public void writeLogg(String stringToLogg) {
        mFileLogger.writeLogg(stringToLogg);
    }

}
