package ChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class OldClient
{
	
	public OldPlayer ourPlayer;
	
    private static int PORT = 2222;
    private BufferedReader in ;
    private PrintWriter out;
    private Socket socket;
    
	public static void main(String[] args) throws Exception 
    {
		while (true) {
            String serverAddress = "localhost";
    		OldClient client = new OldClient(serverAddress);
            new GUI();
            client.play();
		}

    }
    
	
	private OldClient(String serverAddress) throws IOException 
    {
    	/*// IPAdress Universal "127.0.0.1"
        //String serverAddress = JOptionPane.showInputDialog("Enter IPAdress to connect to Server");
		socket = new Socket(serverAddress, 2222);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        System.out.println("CLIENT: Connected to server");
        
        String answer = in.readLine();
        //JOptionPane.showMessageDialog(null, answer);*/
		
		   socket = new Socket(serverAddress, PORT);
	       in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		   System.out.println("CLIENT: Connected to server");
	       out = new PrintWriter(socket.getOutputStream(), true);
	        
	       // Create our player
	       ourPlayer = new OldPlayer(socket, 0);
	       ourPlayer.setClient(this);
	       
//	        ourPlayer.start();
    }
    
	
	public void play() {
	try {
        // The thread is only started after everyone connects.
        ourPlayer.output.println("MESSAGE All players connected");

        // Repeatedly get commands from the client and process them.
        while (true) {
            String command = ourPlayer.input.readLine();
            System.out.println("CLIENT: Received message: " + command);
            
            // "YOUR_ID_IS,2"
            if (command.startsWith("YOUR_ID_IS") && ourPlayer.getPlayerId() == -1) {
            	String playerIdString = extractFromMessage(command, "YOUR_ID_IS");
            	int ourPlayerId = Integer.parseInt(playerIdString);
            	ourPlayer.setPlayerId(ourPlayerId);
            	System.out.println("CLIENT: New player ID assigned: #" + ourPlayer.getPlayerId());
            }
            else {
            	System.out.println("UNKNOWN SERVER RESPONSE: " + command);
            }
        }
    } catch (IOException e) {
        System.out.println("Player died: " + e);
    } finally {
        try {socket.close();} catch (IOException e) {}
    }
}
	
	
    public static void sendToServer(String message) {
//    	out.write(message);
    }

    private static String extractFromMessage(String message, String prefix) {
    	return message.substring(prefix.length());
    }

}