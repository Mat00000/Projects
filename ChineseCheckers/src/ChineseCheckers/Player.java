package ChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Player extends Thread {
	private int playerId = -1;
	private Client client;
	Player opponent;
	Socket socket;
	BufferedReader input;
	PrintWriter output;

	/**
	 * Constructs a handler thread for a given socket and mark initializes the
	 * stream fields, displays the first two welcoming messages.
	 */
	public Player(Socket socket, int playerId) {
		this.socket = socket;
		this.setPlayerId(playerId);

		if (socket != null) {
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream(), true);
				output.println("SERVER: (Client Code) WELCOME " + playerId);
				output.println("SERVER: (Client Code) Waiting for opponent to connect");
			} catch (IOException e) {
				System.out.println("SERVER: (Client Code) Player died: " + e);
			}
		}
	}
		
	
	/**
	 * Accepts notification of who the opponent is.
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	/**
	 * Handles the otherPlayerMoved message.
	 */
	public void otherPlayerMoved(String move) {
		System.out.println("Other player moved " + move);
	}

	/**
	 * The run method of this thread.
	 */
	public void run() {
		client.play();
	}

	public void sendToClient(String message) {
		System.out.println("SERVER sending message `" + message + "` to CLIENT #" + this.getPlayerId());
		output.write(message);
	}
	
	// ==========

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return this.client;
	}

}
