package ChineseCheckers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class OptionsSingleView
{	
	
	public static int mode;
	
	public void initSingleView()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				JFrame frameS = new JFrame("Options Single Player");
            	frameS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	frameS.setLayout(null);
            	frameS.setSize(340, 530);
            	frameS.setVisible(true);
            	frameS.setResizable(false);
            	frameS.setLocationRelativeTo(null);
            	frameS.getContentPane().setBackground(Color.LIGHT_GRAY);
            	
            	JButton s2Button = new JButton("2 PLAYERS");
            	s2Button.setBounds(65, 10, 200, 80);
            	JButton s3Button = new JButton("3 PLAYERS");
            	s3Button.setBounds(65, 110, 200, 80);
            	JButton s4Button = new JButton("4 PLAYERS");
            	s4Button.setBounds(65, 210, 200, 80);
            	JButton s6Button = new JButton("6 PLAYERS");
            	s6Button.setBounds(65, 310, 200, 80);
            	JButton sBotButton = new JButton("PLAYER VS BOT");
            	sBotButton.setBounds(65, 410, 200, 80);
            	
            	frameS.getContentPane().add(s2Button);
            	frameS.getContentPane().add(s3Button);
            	frameS.getContentPane().add(s4Button);
            	frameS.getContentPane().add(s6Button);
            	frameS.getContentPane().add(sBotButton);
            	
            	s2Button.addActionListener(new ActionListener() 
            	{
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frameS.setVisible(false);
						mode = 2;
						Game game = new Game();
						game.initBoard();
						
					}
				});
            	
            	s3Button.addActionListener(new ActionListener() 
            	{
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frameS.setVisible(false);
						mode = 3;
						Game game = new Game();
						game.initBoard();
						
					}
				});
            	
            	s4Button.addActionListener(new ActionListener() 
            	{
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frameS.setVisible(false);
						mode = 4;
						Game game = new Game();
						game.initBoard();
						
					}
				});
            	
            	s6Button.addActionListener(new ActionListener() 
            	{
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frameS.setVisible(false);
						mode = 6;
						Game game = new Game();
						game.initBoard();
						
					}
				});
            	
            	sBotButton.addActionListener(new ActionListener()
            	{
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frameS.setVisible(false);
						mode = 2;
						Game game = new Game();
						game.initBoard();
						
					}
				});
            	
			}
		});
	
	}
	
}
