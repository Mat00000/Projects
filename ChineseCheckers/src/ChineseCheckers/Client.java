package ChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client 
{
	
    private static Socket socket;

    
	public static void main(String[] args) throws Exception 
    {
        Client.connectToServer();
        new GUI();
    }
    
	
	private static void connectToServer() throws IOException 
    {
    	// IPAdress Universal "127.0.0.1"
        String serverAddress = JOptionPane.showInputDialog("Enter IPAdress to connect to Server");
		socket = new Socket(serverAddress, 1234);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = in.readLine();
        JOptionPane.showMessageDialog(null, answer);
    }
    
}