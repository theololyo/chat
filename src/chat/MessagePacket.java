/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

/**
 *
 * @author Markovic
 */
public class MessagePacket {

    private final boolean mIsError;
    private final String mMessage;

    public MessagePacket(String message, boolean isError) {
        mMessage = message;
        mIsError = isError;
    }

    public boolean isError() {
        return mIsError;
    }

    public String getMessage() {
        return mMessage;
    }
    
    @Override
    public String toString(){
        return mMessage;
    }

}
