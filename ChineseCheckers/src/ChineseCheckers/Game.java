package ChineseCheckers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

@SuppressWarnings({ "unused", "serial" })
public class Game
{
	
	public static int 	MODE_TWO_PLAYERS=2, MODE_THREE_PLAYERS=3, 
						MODE_FOUR_PLAYERS=4, MODE_SIX_PLAYERS=6;
	
	
	public void initBoard() 
	{
    	
		EventQueue.invokeLater(new Runnable() 
		{
            @Override
            public void run() 
            {
                JFrame frame = new JFrame("Chinese Checkers");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new Board());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);	
                frame.setResizable(false);
            }
        });
	}
	
	
	
  	public class Board extends JPanel 
  	{	
	   	
	   	private int BOARD_SIZE = 620;
    	private JLabel gb [] = new JLabel[121];
    	private JLabel gameStatus1;
    	private JLabel gameStatus2;
    	public JButton endTurn;
    	
    	private SpringLayout boardLayout;
    	
		private ArrayList<Integer> turnOrder;
		private HashMap<Integer, Integer> player;
    	
    	public int currPlayer;
    	private int gameMode;

    	public boolean gameStarted;
    	public boolean gameOver;

		private ImageIcon o_grn, o_blk, o_wht, o_yel, o_blu, o_red;
		public ImageIcon o_blank;
		private ImageIcon s_grn, s_blk, s_wht, s_yel, s_blu, s_red;
		private ImageIcon h_grn, h_blk, h_wht, h_yel, h_blu, h_red;
		private ImageIcon d_grn, d_blk, d_wht, d_yel, d_blu, d_red;
		private ImageIcon board;

		private BoardMouseHandler boardMouseHandler = new BoardMouseHandler();

    	private int winner;
    	

    	public void initImageIcons() 
    	{

    		o_grn = new ImageIcon(getClass().getResource("/images/o_green_34.png"));
    		o_blk = new ImageIcon(getClass().getResource("/images/o_black_34.png"));
    		o_wht = new ImageIcon(getClass().getResource("/images/o_white_34.png"));
    		o_yel = new ImageIcon(getClass().getResource("/images/o_yellow_34.png"));
    		o_blu = new ImageIcon(getClass().getResource("/images/o_blue_34.png"));
    		o_red = new ImageIcon(getClass().getResource("/images/o_red_34.png"));
    		o_blank = new ImageIcon(getClass().getResource("/images/o_blank_34.png"));

			s_grn = new ImageIcon(getClass().getResource("/images/s_green_34.png"));
    		s_blk = new ImageIcon(getClass().getResource("/images/s_black_34.png"));
    		s_wht = new ImageIcon(getClass().getResource("/images/s_white_34.png"));
    		s_yel = new ImageIcon(getClass().getResource("/images/s_yellow_34.png"));
    		s_blu = new ImageIcon(getClass().getResource("/images/s_blue_34.png"));
    		s_red = new ImageIcon(getClass().getResource("/images/s_red_34.png"));

			h_grn = new ImageIcon(getClass().getResource("/images/h_green_34.png"));
    		h_blk = new ImageIcon(getClass().getResource("/images/h_black_34.png"));
    		h_wht = new ImageIcon(getClass().getResource("/images/h_white_34.png"));
    		h_yel = new ImageIcon(getClass().getResource("/images/h_yellow_34.png"));
    		h_blu = new ImageIcon(getClass().getResource("/images/h_blue_34.png"));
    		h_red = new ImageIcon(getClass().getResource("/images/h_red_34.png"));

			d_grn = new ImageIcon(getClass().getResource("/images/d_green_34.png"));
    		d_blk = new ImageIcon(getClass().getResource("/images/d_black_34.png"));
    		d_wht = new ImageIcon(getClass().getResource("/images/d_white_34.png"));
    		d_yel = new ImageIcon(getClass().getResource("/images/d_yellow_34.png"));
    		d_blu = new ImageIcon(getClass().getResource("/images/d_blue_34.png"));
    		d_red = new ImageIcon(getClass().getResource("/images/d_red_34.png"));

    		board = new ImageIcon(getClass().getResource("/images/board.jpg"));
    		
    	} 
    	
    	
        public void setTurnOrder()
    	{
        	
    		switch(OptionsSingleView.mode)
    		{
    		
   			case 2:
   				int tab2[] = {0, 3};
   				int curr2 = (int) Math.floor(Math.random()*2);
   				
   				currPlayer = tab2[curr2];
   				gameMode = MODE_TWO_PLAYERS;
    				
   				player.put(0,3);
    			player.put(3,0);

    			turnOrder.add(0);
    			turnOrder.add(3);
    			break;
    				
   			case 3:
   				int tab3[] = {0, 2, 4};
   				int curr3 = (int) Math.floor(Math.random()*3);
   				
   				currPlayer = tab3[curr3];
   				gameMode = MODE_THREE_PLAYERS;
    				
    			player.put(0,2);
    			player.put(2,4);
    			player.put(4,0);

    			turnOrder.add(0);
    			turnOrder.add(2);
    			turnOrder.add(4);
    			break;
    				
    		case 4:
    			int tab4[] = {0, 2, 3, 5};
   				int curr4 = (int) Math.floor(Math.random()*4);
    			
    			currPlayer = tab4[curr4];
    			gameMode = MODE_FOUR_PLAYERS;
   				
   				player.put(0,3);
   				player.put(2,5);
   				player.put(3,0);
   				player.put(5,2);

    			turnOrder.add(0);
    			turnOrder.add(2);
    			turnOrder.add(3);
   				turnOrder.add(5);
    			break;
    				
    		case 6:
    			int tab6[] = {0, 1, 2, 3, 4, 5};
   				int curr6 = (int) Math.floor(Math.random()*6);
    			
    			currPlayer = tab6[curr6];
    			gameMode = MODE_SIX_PLAYERS;
    			
    			player.put(0,3);
    			player.put(1,4);
    			player.put(2,5);
    			player.put(3,0);
    			player.put(4,1);
    			player.put(5,2);   

   				turnOrder.add(0);
   				turnOrder.add(1);
   				turnOrder.add(2);
   				turnOrder.add(3);
   				turnOrder.add(4);
   				turnOrder.add(5);
    				
   				break;
    				
   			default:
   				break;
    		}
    	}

	    
	    
	    // CONSTRUCTOR
	    public Board() 
	    {
	    	
	    	initImageIcons();
        	boardLayout = new SpringLayout();
	        setLayout(boardLayout);
	        initGame();

	    } 

	    
	    
	    // INITS
	    public void initGame() 
	    {
	    	
	    	gameOver = false;
	    	gameStarted = true;
	    	winner = -1;
	    	turnOrder = new ArrayList<Integer>();
	    	player = new HashMap<Integer, Integer>();
	    	
	    	setTurnOrder();
	    	buildTurnInfo();
	    	buildBoard();
	    }
	    
	    
	    public void updatePlayer(int i) {
	    	switch(i) {
				case 0:	
					gameStatus1.setText("GREEN"); 
					gameStatus1.setForeground(Color.GREEN);
					break;
				case 1:	
					gameStatus1.setText("WHITE"); 
					gameStatus1.setForeground(Color.GRAY);
					break;
				case 2:	
					gameStatus1.setText("BLUE"); 
					gameStatus1.setForeground(Color.BLUE);
					break;
				case 3:	
					gameStatus1.setText("RED");
					gameStatus1.setForeground(Color.RED);
					break;
				case 4:	
					gameStatus1.setText("YELLOW");
					gameStatus1.setForeground(Color.YELLOW); 
					break;
				case 5:	
					gameStatus1.setText("BLACK"); 
					gameStatus1.setForeground(Color.BLACK);
					break;
				default: break;
			}
	    }

	    
	    public void buildTurnInfo() {
	    	
	    	gameStatus1 = new JLabel();
	    	gameStatus2 = new JLabel();
	    	
        	gameStatus1.setFont(new Font("Tahoma", Font.BOLD, 30));
        	gameStatus2.setFont(new Font("Tahoma", Font.BOLD, 30));

			updatePlayer(currPlayer);
	    		
	    	gameStatus2.setText("TURN"); 

	    	add(gameStatus1);
	    	add(gameStatus2);

			boardLayout.putConstraint(SpringLayout.NORTH, gameStatus1, 
				10, SpringLayout.NORTH, this);
	        boardLayout.putConstraint(SpringLayout.WEST, gameStatus1, 
	        	10, SpringLayout.WEST, this);

			boardLayout.putConstraint(SpringLayout.NORTH, gameStatus2, 
				0, SpringLayout.SOUTH, gameStatus1);
	        boardLayout.putConstraint(SpringLayout.WEST, gameStatus2, 
	        	0, SpringLayout.WEST, gameStatus1);

	        endTurn = new JButton("END TURN");
	    	
	    	endTurn.setPreferredSize(new Dimension(155, 35));
        	endTurn.setFocusPainted(false);
        	endTurn.setMargin(new Insets(0,0,0,0));
        	endTurn.setFont(new Font("Tahoma", Font.BOLD, 25));
	    	endTurn.addMouseListener(boardMouseHandler);
	    	endTurn.setBackground(new Color(59, 89, 182));
    		endTurn.setForeground(Color.WHITE);

			boardLayout.putConstraint(SpringLayout.SOUTH, endTurn, 
				-10, SpringLayout.SOUTH, this);
	        boardLayout.putConstraint(SpringLayout.EAST, endTurn, 
	        	-10, SpringLayout.EAST, this);
	        
    		add(endTurn);

    		endTurn.setVisible(true);
	    	gameStatus1.setVisible(true);
	    	gameStatus2.setVisible(true);

	    } 


	    public void buildBoard() {

        	for (int i = 0; i < gb.length; i++) {
        		gb[i] = new JLabel(pieceType(i));
            	gb[i].addMouseListener(boardMouseHandler);
        		add(gb[i]);
        	}

	        int SIZE = 34;
	        int BELOW = 0;
	        int RIGHT = 5;
	        int OFFSET = 20;

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[0],
	         20, SpringLayout.WEST, this);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[0],
	         295, SpringLayout.WEST, this);
	        
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[1],
	         BELOW, SpringLayout.SOUTH, gb[0]);
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[2],
	         BELOW, SpringLayout.SOUTH, gb[0]);

	        boardLayout.putConstraint(SpringLayout.WEST, gb[1],
	         -OFFSET, SpringLayout.WEST, gb[0]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[2],
	         RIGHT, SpringLayout.EAST, gb[1]);

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[3],
	         BELOW, SpringLayout.SOUTH, gb[1]);
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[4],
	         BELOW, SpringLayout.SOUTH, gb[1]);
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[5],
	         BELOW, SpringLayout.SOUTH, gb[1]);

	        boardLayout.putConstraint(SpringLayout.WEST, gb[3],
	         -OFFSET, SpringLayout.WEST, gb[1]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[4],
	         RIGHT, SpringLayout.EAST, gb[3]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[5],
	         RIGHT, SpringLayout.EAST, gb[4]);
	        
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[6],
	         BELOW, SpringLayout.SOUTH, gb[3]);
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[7],
	         BELOW, SpringLayout.SOUTH, gb[3]);
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[8],
	         BELOW, SpringLayout.SOUTH, gb[3]);
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[9],
	         BELOW, SpringLayout.SOUTH, gb[3]);

	        boardLayout.putConstraint(SpringLayout.WEST, gb[6],
	         -OFFSET, SpringLayout.WEST, gb[3]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[7],
	         RIGHT, SpringLayout.EAST, gb[6]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[8],
	         RIGHT, SpringLayout.EAST, gb[7]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[9],
	         RIGHT, SpringLayout.EAST, gb[8]);

	        for (int i = 10; i < 23; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[6]);    
	        }

	        boardLayout.putConstraint(SpringLayout.EAST, gb[10],
	         -RIGHT, SpringLayout.WEST, gb[11]);
	        boardLayout.putConstraint(SpringLayout.EAST, gb[11],
	         -RIGHT, SpringLayout.WEST, gb[12]);
	        boardLayout.putConstraint(SpringLayout.EAST, gb[12],
	         -RIGHT, SpringLayout.WEST, gb[13]);
	        boardLayout.putConstraint(SpringLayout.EAST, gb[13],
	         -RIGHT, SpringLayout.WEST, gb[14]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[14],
	         -OFFSET, SpringLayout.WEST, gb[6]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[15],
	         RIGHT, SpringLayout.EAST, gb[14]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[16],
	         RIGHT, SpringLayout.EAST, gb[15]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[17],
	         RIGHT, SpringLayout.EAST, gb[16]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[18],
	         RIGHT, SpringLayout.EAST, gb[17]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[19],
	         RIGHT, SpringLayout.EAST, gb[18]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[20],
	         RIGHT, SpringLayout.EAST, gb[19]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[21],
	         RIGHT, SpringLayout.EAST, gb[20]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[22],
	         RIGHT, SpringLayout.EAST, gb[21]);

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[23],
	         BELOW, SpringLayout.SOUTH, gb[10]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[23],
	         OFFSET, SpringLayout.WEST, gb[10]);

	        for (int i = 24; i < 35; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[10]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }
	        
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[35],
	         BELOW, SpringLayout.SOUTH, gb[23]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[35],
	         OFFSET, SpringLayout.WEST, gb[23]);

	        for (int i = 36; i < 46; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[23]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }
	        
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[46],
	         BELOW, SpringLayout.SOUTH, gb[35]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[46],
	         OFFSET, SpringLayout.WEST, gb[35]);

	        for (int i = 47; i < 56; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[35]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[56],
	         BELOW, SpringLayout.SOUTH, gb[46]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[56],
	         OFFSET, SpringLayout.WEST, gb[46]);

	        for (int i = 57; i < 65; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[46]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[65],
	         BELOW, SpringLayout.SOUTH, gb[56]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[65],
	         -OFFSET, SpringLayout.WEST, gb[56]);

	        for (int i = 66; i < 75; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[56]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[75],
	         BELOW, SpringLayout.SOUTH, gb[65]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[75],
	         -OFFSET, SpringLayout.WEST, gb[65]);

	        for (int i = 76; i < 86; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[65]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[86],
	         BELOW, SpringLayout.SOUTH, gb[75]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[86],
	         -OFFSET, SpringLayout.WEST, gb[75]);

	        for (int i = 87; i < 98; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[75]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[98],
	         BELOW, SpringLayout.SOUTH, gb[86]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[98],
	         -OFFSET, SpringLayout.WEST, gb[86]);

	        for (int i = 99; i < 111; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[86]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[111],
	         BELOW, SpringLayout.SOUTH, gb[102]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[111],
	         OFFSET, SpringLayout.WEST, gb[102]);

	        for (int i = 112; i < 115; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[102]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[115],
	         BELOW, SpringLayout.SOUTH, gb[111]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[115],
	         OFFSET, SpringLayout.WEST, gb[111]);

	        for (int i = 116; i < 118; i++) {
	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
	             BELOW, SpringLayout.SOUTH, gb[111]);    
	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
	             RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[118],
	         BELOW, SpringLayout.SOUTH, gb[115]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[118],
	         OFFSET, SpringLayout.WEST, gb[115]);

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[119],
	         BELOW, SpringLayout.SOUTH, gb[115]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[119],
	         RIGHT, SpringLayout.EAST, gb[118]);

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[120],
	         BELOW, SpringLayout.SOUTH, gb[118]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[120],
	         OFFSET, SpringLayout.WEST, gb[118]);
	    } 
	    
	    
	    
	    // SHOWING MOVEMENTS
	    public void showMoves(JLabel p) 
	    {
	    	for (int i = 0; i < gb.length; i++) 
	    	{
	    		if((inRange(p, gb[i]) || canJump(p, gb[i])) && p != gb[i] && !isMarble(gb[i])) 
	    		{
	    			gb[i].setIcon(getImageIcon(getColorInt(p), 'h'));
	    		}
	    	}
	    }

	    
	    public void hideMoves(JLabel p) 
	    {
	    	for (int i = 0; i < gb.length; i++) 
	    	{
	    		if(isHighlight(gb[i])) {
	    			gb[i].setIcon(o_blank);
	    		}
	    	}
	    }

	    
	    
	    // RULES
	    public boolean isBlank(JLabel p) { 
	    	return 6 == getColorInt(p); 
	    } 

	   
	    public boolean isMarble(JLabel p) 
	    { 
	    	return 0 <= getColorInt(p) && 5 >= getColorInt(p); 
	    } 
	   

	    public boolean isHighlight(JLabel p) 
	    {
    		if(p.getIcon() == h_grn) return true;
    		if(p.getIcon() == h_wht) return true;
    		if(p.getIcon() == h_blu) return true;
    		if(p.getIcon() == h_red) return true;
    		if(p.getIcon() == h_yel) return true;
    		if(p.getIcon() == h_blk) return true;
	    	return false;
	    }
	    
	    
	    public boolean isDot(JLabel p) 
	    {
    		if(p.getIcon() == d_grn) return true;
    		if(p.getIcon() == d_wht) return true;
    		if(p.getIcon() == d_blu) return true;
    		if(p.getIcon() == d_red) return true;
    		if(p.getIcon() == d_yel) return true;
    		if(p.getIcon() == d_blk) return true;
	    	return false;
	    }

	    
	    public boolean inRange(JLabel s, JLabel e) 
	    {
	    	return (Math.abs(s.getX() - e.getX()) 
	    		  + Math.abs(s.getY() - e.getY())) < 55;
	    }

	    
	    public boolean isTurn(JLabel s) 
	    {
	    	if (getColorInt(s) == currPlayer)
	    		return true;
	    	return false;
	    }

	    
	    public boolean compIsMarble(Component c) 
	    {
			JLabel tmp;

			try { 
				tmp = ((JLabel) c); 
			} catch (ClassCastException e) { 
				return false; 
			}

			if (isMarble(tmp))
				return true;

			return false;			
	    }

	    
	    public boolean isGameOver(int a, int b)
	    {
	    	int count = 0;

	    	for (int i = 0; i < gb.length; i++) 
	    	{
	    		if (pieceType(i) == getImageIcon(b,'o'))
	    			if (gb[i].getIcon() == getImageIcon(a,'o'))
	    				count++;
	    	}

	    	if (count == 10) 
	    		return true;
	    	return false;	    		
	    }

	    
	   	public boolean canJump(JLabel s, JLabel e) 
	   	{
	      	int total = Math.abs(s.getX() - e.getX()) 
	      			  + Math.abs(s.getY() - e.getY());

	    	int x = s.getX() - e.getX();
	    	int y = s.getY() - e.getY();

	    	int direction = 0;

	    	if (x > 0 && y > 0) direction = 1; 			// + +
	    	else if (x < 0 && y > 0) direction = 2;  	// - +
	    	else if (x < 0 && y < 0) direction = 3;  	// - -
			else if (x > 0 && y < 0) direction = 4;  	// + -
	    	else if (x > 0 && y == 0) direction = 5; 	// <--
	    	else if (x < 0 && y == 0) direction = 6; 	// -->
	    	
	    	if(total == 78 || total == 106 || total == 107 || total == 108) 
	    	{
	    		switch (direction) 
	    		{
	    			case 1: // + +
	    				if(compIsMarble(getComponentAt(s.getX() - 20,
	    				 s.getY() - 34))
	    				|| compIsMarble(getComponentAt(s.getX() - 19,
	    				 s.getY() - 34)))
	    					if (isBlank(e))
	    						return true;
	    				break;
	    			case 2: // - +
	    				if(compIsMarble(getComponentAt(s.getX() + 20, 
	    					s.getY() - 34))
	    				|| compIsMarble(getComponentAt(s.getX() + 19, 
	    					s.getY() - 34)))
	    					if (isBlank(e))
	    						return true;
	    				break;
	    			case 3: // - -
	    				if(compIsMarble(getComponentAt(s.getX() + 20, 
	    					s.getY() + 34))
	    				|| compIsMarble(getComponentAt(s.getX() + 19, 
	    					s.getY() + 34)))
	    					if (isBlank(e))
	    						return true;
	    				break;
	    			case 4: // + -
	    				if(compIsMarble(getComponentAt(s.getX() - 20, 
	    					s.getY() + 34))
	    				|| compIsMarble(getComponentAt(s.getX() - 19, 
	    					s.getY() + 34)))
	    					if (isBlank(e))
	    						return true;
	    				break;
	    			case 5: // <--
	    				if(compIsMarble(getComponentAt(s.getX() - 39, 
	    					s.getY())))
	    					if (isBlank(e))
	    						return true;
	    				break;
	    			case 6: // -->
	    				if(compIsMarble(getComponentAt(s.getX() + 39, 
	    					s.getY())))
	    					if (isBlank(e))
	    						return true;
	    				break;
	    			default:
	    				return false;
	    		}
	    	}
	    	
	    	return false;
	    } 

	   	
	   	public boolean canJumpAgain(JLabel p) {
	    	for (int i = 0; i < gb.length; i++)
	    		if(canJump(p, gb[i]))
	    			return true;
	    	return false;
	    } 

	   	
	   	public void showJumps(JLabel p) {
	    	for (int i = 0; i < gb.length; i++) {
	    		if(canJump(p, gb[i]) && p != gb[i] && !isMarble(gb[i]))
	    			gb[i].setIcon(getImageIcon(getColorInt(p), 'h'));
	    	}
	    } 

	   	
	    public int getColorInt(JLabel p) {
	    	if (p.getIcon() == o_grn || p.getIcon() == s_grn) return 0;
	    	if (p.getIcon() == o_wht || p.getIcon() == s_wht) return 1;
	    	if (p.getIcon() == o_blu || p.getIcon() == s_blu) return 2;
	    	if (p.getIcon() == o_red || p.getIcon() == s_red) return 3;
	    	if (p.getIcon() == o_yel || p.getIcon() == s_yel) return 4;
	    	if (p.getIcon() == o_blk || p.getIcon() == s_blk) return 5;
	    	if (p.getIcon() == o_blank) return 6;
	    	
	    	return 7;
	    } 

	    
	    
	    // IMAGES VOIDS
	    public ImageIcon pieceType(int index) 
	    {

		    int grn_arr [] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	        int blk_arr [] = {10, 11, 12, 13, 23, 24, 25, 35, 36, 46};
		    int wht_arr [] = {19, 20, 21, 22, 32, 33, 34, 44, 45, 55};
		    int yel_arr [] = {65, 75, 76, 86, 87, 88, 98, 99, 100, 101};
		    int blu_arr [] = {74, 84, 85, 95, 96, 97, 107, 108, 109, 110};
		    int red_arr [] = {111, 112, 113, 114, 115, 116, 117, 118, 119, 120};
		    
		    if(OptionsSingleView.mode==MODE_TWO_PLAYERS)
		    {
			    for (int i = 0; i < 10; i++) 
			    {
			    	if(grn_arr[i] == index) 
			    		return o_grn;
			    	else if (red_arr[i] == index)
			    		return o_red;
			    }
		    }
		    
		    if(OptionsSingleView.mode==MODE_THREE_PLAYERS)
		    {
		    	for (int i = 0; i < 10; i++) 
			    {
			    	if(grn_arr[i] == index) 
			    		return o_grn;
			    	else if (blu_arr[i] == index)
			    		return o_blu;
			    	else if (yel_arr[i] == index)
			    		return o_yel;
			    }
		    }
		    
		    if(OptionsSingleView.mode==MODE_FOUR_PLAYERS)
		    {
		    	for (int i = 0; i < 10; i++) 
			    {
			    	if(grn_arr[i] == index) 
			    		return o_grn;
			    	else if (blk_arr[i] == index)
			    		return o_blk;
			    	else if (blu_arr[i] == index)
			    		return o_blu;
			    	else if (red_arr[i] == index)
			    		return o_red;
			    }
		    }
		    
		    if(OptionsSingleView.mode==MODE_SIX_PLAYERS)
		    {
		    	for (int i = 0; i < 10; i++) 
			    {
			    	if(grn_arr[i] == index) 
			    		return o_grn;
			    	else if (blk_arr[i] == index)
			    		return o_blk;
			    	else if (wht_arr[i] == index)
			    		return o_wht;
			    	else if (yel_arr[i] == index)
			    		return o_yel;
			    	else if (blu_arr[i] == index)
			    		return o_blu;
			    	else if (red_arr[i] == index)
			    		return o_red;
			    }
		    }
		    
		    return o_blank;
	    }
	    
	    
	    public ImageIcon getImageIcon(int i, char c) 
	    {
	    	
	    	// Highlight
	    	if (c == 'h') 
	    	{
	    		if (0 == i) return h_grn;
		    	else if (1 == i) return h_wht;
		    	else if (2 == i) return h_blu;
		    	else if (3 == i) return h_red;
		    	else if (4 == i) return h_yel;
		    	else if (5 == i) return h_blk;
		    }

		    // Dot
			if (c == 'd')
			{
				if (0 == i) return d_grn;
		    	else if (1 == i) return d_wht;
		    	else if (2 == i) return d_blu;
		    	else if (3 == i) return d_red;
		    	else if (4 == i) return d_yel;
		    	else if (5 == i) return d_blk;
			}

			// Select
			if (c == 's') 
			{
				if (0 == i) return s_grn;
		    	else if (1 == i) return s_wht;
		    	else if (2 == i) return s_blu;
		    	else if (3 == i) return s_red;
		    	else if (4 == i) return s_yel;
		    	else if (5 == i) return s_blk;
			}

			// Original
			if (c == 'o') 
			{
				if (0 == i) return o_grn;
		    	else if (1 == i) return o_wht;
		    	else if (2 == i) return o_blu;
		    	else if (3 == i) return o_red;
		    	else if (4 == i) return o_yel;
		    	else if (5 == i) return o_blk;
		    	else if (6 == i) return o_blank;
			}

			return null;
	    } 

	    
	    public ImageIcon getMarble(JLabel p) 
	    {
	    	if (p.getIcon() == s_grn) return o_grn;
	    	if (p.getIcon() == s_wht) return o_wht;
	    	if (p.getIcon() == s_blu) return o_blu;
	    	if (p.getIcon() == s_red) return o_red;
	    	if (p.getIcon() == s_yel) return o_yel;
	    	if (p.getIcon() == s_blk) return o_blk;
	    	return null;
	    } 
	    
	    
	  	public void turnOver() 
	  	{

	  		int currIndex = 0;

	  		for (int i = 0; i < turnOrder.size(); i++)
	  			if (turnOrder.get(i) == currPlayer)
	  				currIndex = i;

	  		if (gameMode > 3)
		  		if (currIndex == turnOrder.size()-1)
		  			currPlayer = turnOrder.get(0);
		  		else 
		  			currPlayer = turnOrder.get(currIndex+1);
	  		else
	  			currPlayer = player.get(currPlayer);

	  		updatePlayer(currPlayer);

        	if(isGameOver(currPlayer, player.get(currPlayer))) 
        	{
        		endGame();
        	}	  		
	  	} 
	  	
	  	
	  	public void endGame()
	  	{
	  		winner = currPlayer;
    		gameOver = true;
    		String colorPlayer;
    		
    		if(winner == 0) colorPlayer = "GREEN";
    		else if(winner == 1) colorPlayer = "WHITE";
    		else if(winner == 2) colorPlayer = "BLUE";
    		else if(winner == 3) colorPlayer = "RED";
    		else if(winner == 4) colorPlayer = "YELLOW";
    		else if(winner == 5) colorPlayer = "BLACK";
    		else colorPlayer = "";
    		
  			System.out.printf("Player %d Wins!!!\n", winner);
    		String message = ("Player" + colorPlayer + "wins! EXIT?");
    		
    		int x =JOptionPane.showConfirmDialog(null, message, "Do you want to go?", JOptionPane.YES_NO_OPTION);
    		
    		if(JOptionPane.YES_OPTION == x)
    			System.exit(-1);
	  	}
	  	
	  	
	  	
	  	// OVERRIDE
	    @Override
		protected void paintComponent(Graphics g) 
	    {
    		super.paintComponent(g);
        	g.drawImage(board.getImage(), 0, 0, null);
		}

	    
	    @Override
        public Dimension getPreferredSize() 
	    {
            return new Dimension(BOARD_SIZE, BOARD_SIZE);
        }

	    
	    
	    // MOUSE
		class BoardMouseHandler implements MouseListener 
		{

		 	private JLabel start;
		 	private JLabel end;
		 	private JLabel jumping;
		 	private JLabel firstMoved;

		 	private boolean hasJumped = false;

	        public void mousePressed( MouseEvent event ) 
	        {
	        	if (event.getSource() != endTurn) 
	        	{
		        	start = (JLabel) event.getSource();
		        	
		        	if (!gameOver && gameStarted && isTurn(start) 
		        		&& !isBlank(start) 
		        		&& isMarble((JLabel) event.getSource())
		        		&& firstMoved == null) 
		        	{
	        			start.setIcon(getImageIcon(getColorInt(start), 's'));
	        			showMoves(start);
		        	}
		        	
		        	if (!gameOver && gameStarted && isTurn(start) 
		        		&& !isBlank(start) 
		        		&& isMarble((JLabel) event.getSource())
		        		&& firstMoved == start) 
		        	{
		        		start.setIcon(getImageIcon(getColorInt(start), 's'));
			        	showJumps(start);
		        	}
				}

	        	if (event.getSource() == endTurn) 
	        	{
					hasJumped = false;
        			jumping = null;
        			firstMoved = null;
	        		turnOver();	
				}

	        } 

	        
	        public void mouseReleased( MouseEvent event ) 
	        {
	        	
	        	boolean moveMade = false;

	        	try
	        	{
		        	//MOVES 
		        	if (isDot(end)) 
		        	{
		        		end.setIcon(getMarble(start));
		        		moveMade = true;
		        		if (!inRange(start,end)) 
		        		{
		        			hasJumped = true;
		        		}
		        		firstMoved = end;
		        	}
		        } catch (NullPointerException e) { moveMade = false; }

	        	hideMoves(start);	        	

	        	if (!moveMade) 
	        	{
	        		start.setIcon(getImageIcon(getColorInt(start), 'o'));
	        	} 
	        	else 
	        	{ 
	        		if (canJumpAgain(end) && hasJumped) 
	        		{
	        			hasJumped = true;

	        			start.setIcon(o_blank); 

	        			start = end;
	        			jumping = start;
	        			end = null;

	        		} 
	        		else 
	        		{
	        			hasJumped = false;
	        			jumping = null;
	        			firstMoved = null;
		        		start.setIcon(o_blank); 
		        		turnOver();
	        		}
	        	}
	        }

	        
	        public void mouseEntered( MouseEvent event )
	        {
	        	if (event.getSource() != endTurn) 
	        	{
	        		end = (JLabel) event.getSource();

		        	if (isHighlight((JLabel) event.getSource())) 
		        	{
		        		end = (JLabel) event.getSource();
		        		end.setIcon(getImageIcon(currPlayer, 'd'));
		        	}
		        }		
	        } 

	        
	        public void mouseExited( MouseEvent event ) 
	        {
	        	if (event.getSource() != endTurn) 
	        	{
		        	JLabel tmp = (JLabel) event.getSource();
		        	if (isDot(tmp))
		        		end.setIcon(getImageIcon(currPlayer, 'h'));
		        }
	        } 
	        
	        
	        public void mouseClicked( MouseEvent event ) 
	        { 
	        	
	        }

	    } 

	
	} 
}
