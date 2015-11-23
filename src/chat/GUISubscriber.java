package chat;

import loggerFactory.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import loggerFactory.Logger;
import loggerFactory.LoggerFactory;

/**
 * The main GUI for a simple chat application.
 *
 * @author Thomas Ejnefjall
 */
public class GUISubscriber extends JFrame implements Subscriber {

    private static final long serialVersionUID = -6901406569465760897L;
    private JTextArea mChatArea, mMessageArea;
    private JButton mSendButton;
    private MessageSender mDs;
    private String mUser;
    private boolean mDisplayErrors;
    private final Logger mLocalLogger;

    /**
     * Creates a ChatGUI
     *
     * @param userName the name to use in the chat
     */
    public GUISubscriber(String userName) {
        mLocalLogger = new LoggerFactory().getFileLogger("FileLogger","Default");
        this.setTitle("Simple Chat - " + userName);
        mDs = new MessageSender();
        mUser = userName;
        this.initializeGUI();
        this.addGUIListeners();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initializes the GUI
     */
    private void initializeGUI() {
        mChatArea = new JTextArea(25, 1);
        mMessageArea = new JTextArea(3, 10);
        mSendButton = new JButton("Send");

        mMessageArea.setLineWrap(true);
        mMessageArea.setBorder(BorderFactory.createLineBorder(Color.black));
        mChatArea.setEnabled(false);
        mChatArea.setLineWrap(true);

        Container contentPane = this.getContentPane();

        contentPane.add(mChatArea, BorderLayout.NORTH);
        contentPane.add(mMessageArea, BorderLayout.WEST);
        contentPane.add(mSendButton, BorderLayout.CENTER);

        this.setSize(200, 500);
        this.setResizable(false);

    }

    /**
     * Adds GUI related listeners
     */
    private void addGUIListeners() {
        mSendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        mMessageArea.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (mMessageArea.getText().length() > 0) {
                        mMessageArea.setText(mMessageArea.getText().substring(0, mMessageArea.getText().length() - 1));
                        sendMessage();
                    }
                }
            }
        });
    }

    /**
     * Send current message to all users
     */
    private void sendMessage() {
        try {
            mDs.broadcast(mUser, mMessageArea.getText());
            mMessageArea.setText("");
            mMessageArea.grabFocus();
            throw new IOException();

        } catch (IOException e) {
            mLocalLogger.writeLogg("Error: " + e.getMessage());
            this.error();
        }
    }

    /**
     * Receives message from all users
     *
     * @param message The received message
     */
    public void displayMessage(String message) {
        mChatArea.append(message + "\n");
    }

    /**
     * Informs the user that an error has occurred and exits the application
     */
    public void error() {
        JOptionPane.showMessageDialog(this, "An error has occured and the application will close", "Error", JOptionPane.WARNING_MESSAGE);
        this.setVisible(false);
        this.dispose();
        System.exit(ERROR);
    }

    @Override
    public void update(MessagePacket messagePacket) {
        if (!messagePacket.isError()) {
            displayMessage(messagePacket.getMessage());
        }
    }

    private void displayErrors(boolean displayErrors) {
        mDisplayErrors = displayErrors;
    }
}
