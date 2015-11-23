package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * The communicator handles the network traffic between all chat clients.
 * Messages are sent and received via the UDP protocol which may lead to
 * messages being lost.
 *
 * @author Thomas Ejnefjall
 */
public class UDPChatNotifier implements Runnable, Notifier {

    private final int DATAGRAM_LENGTH = 100;
    private final int PORT = 6789;
    private final String MULTICAST_ADDRESS = "228.28.28.28";
    private List<Subscriber> mSubscribers;

    /**
     * Creates a ChatCommunicator
     *
     */
    public UDPChatNotifier() {
        mSubscribers = new ArrayList<Subscriber>();
    }

    /**
     * Starts to listen for messages from other clients
     */
    public void startListen() {
        new Thread(this).start();
    }

    /**
     * Listens for messages from other clients
     *
     * @throws IOException If there is an IO error
     */
    private void listenForMessages() throws IOException {
        byte[] b = new byte[DATAGRAM_LENGTH];
        DatagramPacket datagram = new DatagramPacket(b, b.length);

        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            socket.joinGroup(InetAddress.getByName(MULTICAST_ADDRESS));
            while (true) {
                socket.receive(datagram);
                String message = new String(datagram.getData());
                message = message.substring(0, datagram.getLength());
                datagram.setLength(b.length);
                this.notifySubscribers(new MessagePacket(message, false));
            }
        }
    }

    @Override
    public void run() {
        try {
            this.listenForMessages();
        } catch (IOException e) {
            notifySubscribers(new MessagePacket("Error " + e.toString(), true));
        }
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        mSubscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        mSubscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(MessagePacket messagepacket) {
        for (Subscriber mSubscriber : mSubscribers) {
            mSubscriber.update(messagepacket);
        }
    }
}
