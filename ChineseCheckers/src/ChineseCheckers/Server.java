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
		System.out.println("SERVER: Server is running");
		 ServerSocket server = new ServerSocket(2222);
		
        Game game = new Game();
//        Game.Player playerX = game.new Player(listener.accept(), 'X');
//        Game.Player playerO = game.new Player(listener.accept(), 'O');
        
        Player player1 = Server.acceptPlayer(server, 1);
        System.out.println("Player1 = " + player1);
//        Player player2 = Server.acceptPlayer(listener, 2);
//        System.out.println("Player2 = " + player2);
        
//        player1.setOpponent(player2);
//        player2.setOpponent(player1);

        game.currPlayer = player1.getPlayerId();
        
        // ====
        
		try 
		{
            while (true) 
            {	
            	
            	System.out.println("SERVER: Now listening...");
                Socket socket = server.accept();
                try 
                {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Welcome on the Chinese Checkers Server\n");
                    System.out.println("SERVER: Running for client");
                } 
                catch (Exception e) 
                {
                	e.setStackTrace(null);
                	//socket.close();
                	//System.out.println("SERVER: Socket closed");
                }
            }
        }
        finally 
        {
        	server.close();
        }
    }
	
	
	private static Player acceptPlayer(ServerSocket listener, int playerId) throws IOException
	{
		System.out.println("SERVER: Waiting for client...");
		Player player = new Player(listener.accept(), playerId);
		System.out.println("SERVER: Client #" + player.getPlayerId() 
		+ " found, broadcast ID to him...");

        player.sendToClient("YOUR_ID_IS," + playerId);
		
		return player;
		
//		server = new ServerSocket(2222);
//		
//		try 
//		{
//            while (true) 
//            {	
//            	
//            	System.out.println("SERVER: Listening");
//                Socket socket = server.accept();
//                System.out.println("SERVER: Client found");
//                try 
//                {
//                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//                    out.println("Welcome on the Chinese Checkers Server\n");
//                    System.out.println("SERVER: Running for client");
//                } 
//                catch (Exception e) 
//                {
//                	e.setStackTrace(null);
//                	//socket.close();
//                	//System.out.println("SERVER: Socket closed");
//                }
//            }
//        }
//        finally 
//        {
//        	server.close();
//        }
	}
	
	
}