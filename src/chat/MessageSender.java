/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Markovic
 */
public class MessageSender {

    private final String MULTICAST_ADDRESS = "228.28.28.28";
    private final int PORT = 6789;

    /**
     * Sends the chat message to all clients
     *
     * @param sender Name of the sender
     * @param message Text message to send
     * @throws IOException If there is an IO error
     */
    public void broadcast(String sender, String message) throws IOException {

        DatagramSocket socket = new DatagramSocket();
        String toSend = sender + ": " + message;
        byte[] b = toSend.getBytes();

        DatagramPacket datagram = new DatagramPacket(b, b.length,
                InetAddress.getByName(MULTICAST_ADDRESS), PORT);

        socket.send(datagram);
        socket.close();
    }
}
