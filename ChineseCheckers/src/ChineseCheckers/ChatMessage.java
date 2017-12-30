package ChineseCheckers;

import java.io.*;

/*
 * This class defines the different type of messages that will be exchanged between the
 * Clients and the Server. 
 * When talking from a Java Client to a Java Server a lot easier to pass Java objects, no 
 * need to count bytes or to wait for a line feed at the end of the frame
 */
public class ChatMessage implements Serializable {

    protected static final long serialVersionUID = 1112122200L;

    // The different types of message sent by the Client
    // WHOISIN to receive the list of the users connected
    // MESSAGE an ordinary message
    // LOGOUT to disconnect from the Server
    public static final String WHO_IS_IN = "WHO_IS_IN";
    public static final String MESSAGE = "MESSAGE";
    public static final String MOVE = "MOVE";
    public static final String LOGOUT = "LOG_OUT";
    
    // Message type
    private String type;
    
    // Message content
    private String message;
    
    // -------------------------------------------------------------------- 
    // CONSTRUCTORS

    public ChatMessage(String type, String message) {
        this.type = type;
        this.message = message;
    }

    // -------------------------------------------------------------------- 
    
    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
