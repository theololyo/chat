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
public interface Notifier {
    
    public void attach(Subscriber s);
    public void detach(Subscriber s);
    public void notifySubscribers(String message);
    
}
