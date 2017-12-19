package ChineseCheckers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

@SuppressWarnings({ "unused", "serial" })
public class Game extends JFrame 
{
	
	public static int 	MODE_TWO_PLAYERS=2, MODE_THREE_PLAYERS=3, 
						MODE_FOUR_PLAYERS=4, MODE_SIX_PLAYERS=6;
	
	private JLabel gb [] = new JLabel[121];
	
	private SpringLayout boardLayout;
	
	private ImageIcon original_blk, original_wht, original_yel, original_blu, original_red, original_blank, original_grn;
	private ImageIcon current_grn, current_blk, current_wht, current_yel, current_blu, current_red;
	private ImageIcon possible_grn, possible_blk, possible_wht, possible_yel, possible_blu, possible_red;
	private ImageIcon point;
	private ImageIcon boardImage;
	
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
		
		private JLabel gameStatus_1, gameStatus_2;
		
		private JButton endTurn;
		
		private ArrayList<Integer> turnOrder;
		private HashMap<Integer, Integer> player;
		
		private int currPlayer;
		private int gameMode;
		private int winner;

		private boolean gameStarted;
		private boolean gameOver;
   

		public Board()
		{
			initImages();
			boardLayout = new SpringLayout();
			setLayout(boardLayout);
			initGame();
		}
		
		
		private void initImages()
		{		

			original_grn = new ImageIcon(getClass().getResource("/images/green.png"));
    		original_blk = new ImageIcon(getClass().getResource("/images/black.png"));
    		original_wht = new ImageIcon(getClass().getResource("/images/white.png"));
    		original_yel = new ImageIcon(getClass().getResource("/images/yellow.png"));
    		original_blu = new ImageIcon(getClass().getResource("/images/blue.png"));
    		original_red = new ImageIcon(getClass().getResource("/images/red.png"));
    		original_blank = new ImageIcon(getClass().getResource("/images/blank.png"));
    		
			current_blk = new ImageIcon(getClass().getResource("/images/cur_black.png"));
			current_grn = new ImageIcon(getClass().getResource("/images/cur_green.png"));
			current_wht = new ImageIcon(getClass().getResource("/images/cur_white.png"));
			current_yel = new ImageIcon(getClass().getResource("/images/cur_yellow.png"));
			current_blu = new ImageIcon(getClass().getResource("/images/cur_blue.png"));
			current_red = new ImageIcon(getClass().getResource("/images/cur_red.png"));

			possible_grn = new ImageIcon(getClass().getResource("/images/poss_green.png"));
    		possible_blk = new ImageIcon(getClass().getResource("/images/poss_black.png"));
    		possible_wht = new ImageIcon(getClass().getResource("/images/poss_white.png"));
    		possible_yel = new ImageIcon(getClass().getResource("/images/poss_yellow.png"));
    		possible_blu = new ImageIcon(getClass().getResource("/images/poss_blue.png"));
    		possible_red = new ImageIcon(getClass().getResource("/images/poss_red.png"));

			point = new ImageIcon(getClass().getResource("/images/points.png"));
    		
			boardImage = new ImageIcon(getClass().getResource("/images/board.jpg"));
		}
		
		
		public void initGame()
		{
			gameStarted = false;
			gameOver = false;
			winner = -1;
			
	    	turnOrder = new ArrayList<Integer>();
	    	player = new HashMap<Integer, Integer>();
			
			setTurnOrder();
			buildBoard();
			buildInfo();
		}
		
	    @Override
		protected void paintComponent(Graphics g) 
	    {
    		super.paintComponent(g);
        	g.drawImage(boardImage.getImage(), 0, 0, null);
		}
	    
	    @Override
        public Dimension getPreferredSize() 
	    {
            return new Dimension(BOARD_SIZE, BOARD_SIZE);
        }
	    
	    
	    public void setTurnOrder()
		{
			switch(OptionsSingleView.mode)
			{
			
			case 2:
				currPlayer = 0;

				player.put(0,3);
				player.put(3,0);

				turnOrder.add(0);
				turnOrder.add(3);
				break;
				
			case 3:
				currPlayer = 0;

				player.put(0,2);
				player.put(2,4);
				player.put(4,0);

				turnOrder.add(0);
				turnOrder.add(2);
				turnOrder.add(4);
				break;
				
			case 4:
				currPlayer = 0;

				player.put(0,3);
				player.put(1,4);
				player.put(3,0);
				player.put(4,1);

				turnOrder.add(0);
				turnOrder.add(1);
				turnOrder.add(3);
				turnOrder.add(4);

				break;
				
			case 6:
				currPlayer = 0;
				
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
				
			}
		}
	    

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
			    		return original_grn;
			    	else if (red_arr[i] == index)
			    		return original_red;
			    }
		    }
		    
		    if(OptionsSingleView.mode==MODE_THREE_PLAYERS)
		    {
		    	for (int i = 0; i < 10; i++) 
			    {
			    	if(grn_arr[i] == index) 
			    		return original_grn;
			    	else if (blu_arr[i] == index)
			    		return original_blu;
			    	else if (yel_arr[i] == index)
			    		return original_yel;
			    }
		    }
		    
		    if(OptionsSingleView.mode==MODE_FOUR_PLAYERS)
		    {
		    	for (int i = 0; i < 10; i++) 
			    {
			    	if(grn_arr[i] == index) 
			    		return original_grn;
			    	else if (blk_arr[i] == index)
			    		return original_blk;
			    	else if (blu_arr[i] == index)
			    		return original_blu;
			    	else if (red_arr[i] == index)
			    		return original_red;
			    }
		    }
		    
		    if(OptionsSingleView.mode==MODE_SIX_PLAYERS)
		    {
		    	for (int i = 0; i < 10; i++) 
			    {
			    	if(grn_arr[i] == index) 
			    		return original_grn;
			    	else if (blk_arr[i] == index)
			    		return original_blk;
			    	else if (wht_arr[i] == index)
			    		return original_wht;
			    	else if (yel_arr[i] == index)
			    		return original_yel;
			    	else if (blu_arr[i] == index)
			    		return original_blu;
			    	else if (red_arr[i] == index)
			    		return original_red;
			    }
		    }
		    
		    return original_blank;
	    }
	    
	    
		public void buildBoard() 
		{
			int SIZE = 34;
	        int BELOW = 0;
	        int RIGHT = 5;
	        int OFFSET = 20;
	        
	    	for (int i = 0; i < gb.length; i++) {
	    		gb[i] = new JLabel(pieceType(i));
	        	//gb[i].addMouseListener(boardMouseHandler);
	    		add(gb[i]);
	    	}
	        
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[0], 20, SpringLayout.WEST, this);
	      	boardLayout.putConstraint(SpringLayout.WEST, gb[0], 295, SpringLayout.WEST, this);
	      	boardLayout.putConstraint(SpringLayout.NORTH, gb[1], BELOW, SpringLayout.SOUTH, gb[0]);
	      	boardLayout.putConstraint(SpringLayout.NORTH, gb[2], BELOW, SpringLayout.SOUTH, gb[0]);
	      	boardLayout.putConstraint(SpringLayout.WEST, gb[1], -OFFSET, SpringLayout.WEST, gb[0]);
	      	boardLayout.putConstraint(SpringLayout.WEST, gb[2], RIGHT, SpringLayout.EAST, gb[1]);
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[3], BELOW, SpringLayout.SOUTH, gb[1]); 
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[4], BELOW, SpringLayout.SOUTH, gb[1]);
	      	boardLayout.putConstraint(SpringLayout.NORTH, gb[5], BELOW, SpringLayout.SOUTH, gb[1]);
	      	boardLayout.putConstraint(SpringLayout.WEST, gb[3], -OFFSET, SpringLayout.WEST, gb[1]);
	      	boardLayout.putConstraint(SpringLayout.WEST, gb[4], RIGHT, SpringLayout.EAST, gb[3]);
	      	boardLayout.putConstraint(SpringLayout.WEST, gb[5], RIGHT, SpringLayout.EAST, gb[4]);
	      	boardLayout.putConstraint(SpringLayout.NORTH, gb[6], BELOW, SpringLayout.SOUTH, gb[3]);
	      	boardLayout.putConstraint(SpringLayout.NORTH, gb[7], BELOW, SpringLayout.SOUTH, gb[3]);
	      	boardLayout.putConstraint(SpringLayout.NORTH, gb[8], BELOW, SpringLayout.SOUTH, gb[3]);
	      	boardLayout.putConstraint(SpringLayout.NORTH, gb[9], BELOW, SpringLayout.SOUTH, gb[3]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[6], -OFFSET, SpringLayout.WEST, gb[3]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[7], RIGHT, SpringLayout.EAST, gb[6]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[8], RIGHT, SpringLayout.EAST, gb[7]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[9], RIGHT, SpringLayout.EAST, gb[8]);
	        
	        
	        for (int i = 10; i < 23; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[6]);    
	      	}
	        
	        boardLayout.putConstraint(SpringLayout.EAST, gb[10], -RIGHT, SpringLayout.WEST, gb[11]);
	        boardLayout.putConstraint(SpringLayout.EAST, gb[11], -RIGHT, SpringLayout.WEST, gb[12]);
	        boardLayout.putConstraint(SpringLayout.EAST, gb[12], -RIGHT, SpringLayout.WEST, gb[13]);
	        boardLayout.putConstraint(SpringLayout.EAST, gb[13], -RIGHT, SpringLayout.WEST, gb[14]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[14], -OFFSET, SpringLayout.WEST, gb[6]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[15], RIGHT, SpringLayout.EAST, gb[14]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[16], RIGHT, SpringLayout.EAST, gb[15]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[17], RIGHT, SpringLayout.EAST, gb[16]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[18], RIGHT, SpringLayout.EAST, gb[17]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[19], RIGHT, SpringLayout.EAST, gb[18]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[20], RIGHT, SpringLayout.EAST, gb[19]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[21], RIGHT, SpringLayout.EAST, gb[20]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[22], RIGHT, SpringLayout.EAST, gb[21]);
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[23], BELOW, SpringLayout.SOUTH, gb[10]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[23], OFFSET, SpringLayout.WEST, gb[10]);        

	      	        
	        for (int i = 24; i < 35; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[10]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }
	      	        
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[35], BELOW, SpringLayout.SOUTH, gb[23]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[35], OFFSET, SpringLayout.WEST, gb[23]);

	        for (int i = 36; i < 46; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[23]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }
	      	        
	        boardLayout.putConstraint(SpringLayout.NORTH, gb[46], BELOW, SpringLayout.SOUTH, gb[35]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[46], OFFSET, SpringLayout.WEST, gb[35]);

	        for (int i = 47; i < 56; i++)
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[35]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[56], BELOW, SpringLayout.SOUTH, gb[46]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[56], OFFSET, SpringLayout.WEST, gb[46]);

	        for (int i = 57; i < 65; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[46]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[65], BELOW, SpringLayout.SOUTH, gb[56]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[65], -OFFSET, SpringLayout.WEST, gb[56]);

	        for (int i = 66; i < 75; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[56]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[75], BELOW, SpringLayout.SOUTH, gb[65]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[75], -OFFSET, SpringLayout.WEST, gb[65]);

	        for (int i = 76; i < 86; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[65]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[86], BELOW, SpringLayout.SOUTH, gb[75]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[86], -OFFSET, SpringLayout.WEST, gb[75]);

	        for (int i = 87; i < 98; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[75]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[98], BELOW, SpringLayout.SOUTH, gb[86]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[98], -OFFSET, SpringLayout.WEST, gb[86]);

	        for (int i = 99; i < 111; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[86]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[111], BELOW, SpringLayout.SOUTH, gb[102]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[111], OFFSET, SpringLayout.WEST, gb[102]);

	        for (int i = 112; i < 115; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[102]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[115], BELOW, SpringLayout.SOUTH, gb[111]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[115], OFFSET, SpringLayout.WEST, gb[111]);

	        for (int i = 116; i < 118; i++) 
	        {
	        	boardLayout.putConstraint(SpringLayout.NORTH, gb[i], BELOW, SpringLayout.SOUTH, gb[111]);    
	        	boardLayout.putConstraint(SpringLayout.WEST, gb[i], RIGHT, SpringLayout.EAST, gb[i-1]);
	        }

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[118], BELOW, SpringLayout.SOUTH, gb[115]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[118], OFFSET, SpringLayout.WEST, gb[115]);

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[119], BELOW, SpringLayout.SOUTH, gb[115]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[119], RIGHT, SpringLayout.EAST, gb[118]);

	        boardLayout.putConstraint(SpringLayout.NORTH, gb[120], BELOW, SpringLayout.SOUTH, gb[118]);
	        boardLayout.putConstraint(SpringLayout.WEST, gb[120],OFFSET, SpringLayout.WEST, gb[118]);
		}
		
	    
		public void updatePlayer(int i) 	
		{
			switch(i) 
			{
			
			case 0:	
				gameStatus_1.setText("GREEN "); 
				gameStatus_1.setForeground(Color.GREEN);
				
				break;
			
			case 1:	
				gameStatus_1.setText("WHITE "); 
				gameStatus_1.setForeground(Color.WHITE);
				
				break;
			
			case 2:	
				gameStatus_1.setText("BLUE "); 
				gameStatus_1.setForeground(Color.BLUE);
				
				break;
				
			case 3:	
				gameStatus_1.setText("RED ");
				gameStatus_1.setForeground(Color.RED);
				
				break;
				
			case 4:	
				gameStatus_1.setText("YELLOW ");
				gameStatus_1.setForeground(Color.YELLOW); 
				
				break;
				
			case 5:	
				gameStatus_1.setText("BLACK "); 
				gameStatus_1.setForeground(Color.BLACK);
				
				break;
			}
		}
		
		
		public void buildInfo()
		{
			gameStatus_1 = new JLabel();
	    	gameStatus_2 = new JLabel();
	    	
        	gameStatus_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        	gameStatus_2.setFont(new Font("Tahoma", Font.BOLD, 30));

			//updatePlayer(currPlayer);
	    		
	    	gameStatus_2.setText("TURN"); 

	    	add(gameStatus_1);
	    	add(gameStatus_2);
	    	
			boardLayout.putConstraint(SpringLayout.NORTH, gameStatus_1, 10, SpringLayout.NORTH, this);
		    boardLayout.putConstraint(SpringLayout.WEST, gameStatus_1, 10, SpringLayout.WEST, this);

			boardLayout.putConstraint(SpringLayout.NORTH, gameStatus_2, 0, SpringLayout.SOUTH, gameStatus_1);
			boardLayout.putConstraint(SpringLayout.WEST, gameStatus_2, 0, SpringLayout.WEST, gameStatus_1);

			endTurn = new JButton("END TURN");
		    	
		    endTurn.setPreferredSize(new Dimension(150, 35));
	       	endTurn.setFocusPainted(false);
	       	endTurn.setMargin(new Insets(0,0,0,0));
	       	endTurn.setFont(new Font("Tahoma", Font.BOLD, 25));
			//endTurn.addMouseListener(boardMouseHandler);
	    	endTurn.setBackground(new Color(55, 75, 143));	    		
	    	endTurn.setForeground(Color.WHITE);

	    	boardLayout.putConstraint(SpringLayout.SOUTH, endTurn, -10, SpringLayout.SOUTH, this);
	    	boardLayout.putConstraint(SpringLayout.EAST, endTurn, -20, SpringLayout.EAST, this);
		        
	    	add(endTurn);
		}
				
		
	    public int getColorInt(JLabel p) 
	    {
	    	if (p.getIcon() == original_grn || p.getIcon() == current_grn) return 0;
	    	if (p.getIcon() == original_wht || p.getIcon() == current_wht) return 1;
	    	if (p.getIcon() == original_blu || p.getIcon() == current_blu) return 2;
	    	if (p.getIcon() == original_red || p.getIcon() == current_red) return 3;
	    	if (p.getIcon() == original_yel || p.getIcon() == current_yel) return 4;
	    	if (p.getIcon() == original_blk || p.getIcon() == current_blk) return 5;
	    	if (p.getIcon() == original_blank) return 6;
	    	
	    	return 7;
	    }
		
	    
	    
		
	}
    
}
