package ChineseCheckers;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class GUI extends JFrame
{
	private JLabel gb [] = new JLabel[121];
	private SpringLayout boardLayout;
	
	GUI() 
	{
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
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
		
	}
	
	public void buildBoard() 
	{
		@SuppressWarnings("unused")
		int SIZE = 34;
        int BELOW = 0;
        int RIGHT = 5;
        int OFFSET = 20;
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

      	        for (int i = 24; i < 35; i++) 
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[10]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }
      	        
      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[35],
      	         BELOW, SpringLayout.SOUTH, gb[23]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[35],
      	         OFFSET, SpringLayout.WEST, gb[23]);

      	        for (int i = 36; i < 46; i++) 
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[23]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }
      	        
      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[46],
      	         BELOW, SpringLayout.SOUTH, gb[35]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[46],
      	         OFFSET, SpringLayout.WEST, gb[35]);

      	        for (int i = 47; i < 56; i++)
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[35]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }

      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[56],
      	         BELOW, SpringLayout.SOUTH, gb[46]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[56],
      	         OFFSET, SpringLayout.WEST, gb[46]);

      	        for (int i = 57; i < 65; i++) 
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[46]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }

      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[65],
      	         BELOW, SpringLayout.SOUTH, gb[56]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[65],
      	         -OFFSET, SpringLayout.WEST, gb[56]);

      	        for (int i = 66; i < 75; i++) 
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[56]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }

      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[75],
      	         BELOW, SpringLayout.SOUTH, gb[65]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[75],
      	         -OFFSET, SpringLayout.WEST, gb[65]);

      	        for (int i = 76; i < 86; i++) 
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[65]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }

      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[86],
      	         BELOW, SpringLayout.SOUTH, gb[75]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[86],
      	         -OFFSET, SpringLayout.WEST, gb[75]);

      	        for (int i = 87; i < 98; i++) 
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[75]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }

      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[98],
      	         BELOW, SpringLayout.SOUTH, gb[86]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[98],
      	         -OFFSET, SpringLayout.WEST, gb[86]);

      	        for (int i = 99; i < 111; i++) 
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[86]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }

      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[111],
      	         BELOW, SpringLayout.SOUTH, gb[102]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[111],
      	         OFFSET, SpringLayout.WEST, gb[102]);

      	        for (int i = 112; i < 115; i++) 
      	        {
      	            boardLayout.putConstraint(SpringLayout.NORTH, gb[i],
      	             BELOW, SpringLayout.SOUTH, gb[102]);    
      	            boardLayout.putConstraint(SpringLayout.WEST, gb[i],
      	             RIGHT, SpringLayout.EAST, gb[i-1]);
      	        }

      	        boardLayout.putConstraint(SpringLayout.NORTH, gb[115],
      	         BELOW, SpringLayout.SOUTH, gb[111]);
      	        boardLayout.putConstraint(SpringLayout.WEST, gb[115],
      	         OFFSET, SpringLayout.WEST, gb[111]);

      	        for (int i = 116; i < 118; i++) 
      	        {
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
	
}

