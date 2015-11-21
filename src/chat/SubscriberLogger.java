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
public class SubscriberLogger implements Subscriber {

    @Override
    public void update(String message) {
        System.out.println("Logg: " + message);
    }
    
}
