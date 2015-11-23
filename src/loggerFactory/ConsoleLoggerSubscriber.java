/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggerFactory;

import chat.MessagePacket;
import chat.Subscriber;
import java.time.LocalDateTime;

/**
 *
 * @author Markovic
 */
public class ConsoleLoggerSubscriber implements Subscriber, Logger {

    @Override
    public void update(MessagePacket messagePacket) {
        writeLogg(messagePacket.getMessage());
    }

    @Override
    public void writeLogg(String stringToLogg) {
        System.out.println("[" + LocalDateTime.now() + "]" + " " + stringToLogg);
    }

}
