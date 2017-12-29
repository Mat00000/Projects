package ChineseCheckers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class OptionsMultiView 
{

	public void initMultiView()
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
            	s2Button.setBounds(65, 30, 200, 80);
            	JButton s3Button = new JButton("3 PLAYERS");
            	s3Button.setBounds(65, 150, 200, 80);
            	JButton s4Button = new JButton("4 PLAYERS");
            	s4Button.setBounds(65, 270, 200, 80);
            	JButton s6Button = new JButton("6 PLAYERS");
            	s6Button.setBounds(65, 380, 200, 80);
            	
            	frameS.getContentPane().add(s2Button);
            	frameS.getContentPane().add(s3Button);
            	frameS.getContentPane().add(s4Button);
            	frameS.getContentPane().add(s6Button);
            	
            	s2Button.addActionListener(new ActionListener() 
            	{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						
					}
				});
            	
            	s3Button.addActionListener(new ActionListener() 
            	{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						
					}
				});
            	
            	s4Button.addActionListener(new ActionListener() 
            	{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						
					}
				});
            	
            	s6Button.addActionListener(new ActionListener() 
            	{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						
					}
				});
            	
			}
		});
	}
}
