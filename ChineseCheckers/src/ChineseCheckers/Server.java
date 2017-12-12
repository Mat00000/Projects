package ChineseCheckers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server 
{
	public static void main(String[] args) throws IOException 
	{
        Server.sendWelcome();
    }
	
	private static void sendWelcome() throws IOException
	{
		ServerSocket listener = new ServerSocket(2133);
        try 
        {
            while (true) 
            {
                Socket socket = listener.accept();
                try 
                {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Welcome on the Chinese Checkers Server");
                } 
                finally 
                {
                    socket.close();
                }
            }
        }
        finally 
        {
            listener.close();
        }
	}
}
