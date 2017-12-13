package ChineseCheckers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{

	private static ServerSocket server;
	
	
	public static void main(String[] args) throws Exception 
	{
        Server.acceptPlayer();
    }
	
	
	private static void acceptPlayer() throws IOException
	{
		server = new ServerSocket(1234);
		
		try 
		{
            while (true) 
            {
                Socket socket = server.accept();
                try 
                {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Welcome on the Chinese Checkers Server\n");
                } 
                finally 
                {
                	socket.close();
                }
            }
        }
        finally 
        {
        	server.close();
        }
	}
}