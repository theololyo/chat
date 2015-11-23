/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * @author Markovic
 */
public class FileLogger implements Logger {

    private String mPath;
    public FileLogger(){
        mPath = "/logg.txt";
    }
    
    public FileLogger(String pathToLogg){
        mPath = pathToLogg;
    }

    @Override
    public void writeLogg(String stringToLogg) {
        try {

            File file = new File(System.getProperty("user.dir") + mPath);

            if (!file.exists()) {
                System.out.println("fire");
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[" + LocalDateTime.now() + "]" + " " + stringToLogg);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

}
