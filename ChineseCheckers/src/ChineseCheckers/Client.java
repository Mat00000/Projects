package ChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Client
{	
		public static void main(String[] args) throws IOException
		{
			Client.connectToServer();											//IPAdress in Mati's home "192.168.1.9" or universal "127.0.0.1"
			new GUI();
		}
		
		private static void connectToServer() throws IOException
		{
	        String serverAddress = JOptionPane.showInputDialog(
	        		"Enter IP Address of a machine that is\n" +
	        		"running the date service on port 2133:");
	        @SuppressWarnings("resource")
			Socket socket = new Socket(serverAddress, 2133);
	        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        String answer = input.readLine();
	        JOptionPane.showMessageDialog(null, answer);
		}
}
