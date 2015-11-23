/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * @author Markovic
 */
public class FileLoggerSubscriber implements Subscriber {

    @Override
    public void update(MessagePacket messagePacket) {
        try {

            File file = new File(System.getProperty("user.dir") + "/logg.txt");

            if (!file.exists()) {
                System.out.println("fire");
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[" + LocalDateTime.now() + "]" + " " + messagePacket.getMessage());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
