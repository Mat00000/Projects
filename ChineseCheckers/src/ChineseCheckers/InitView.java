package ChineseCheckers;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class InitView extends OptionsSingleView
{
	
	public void initView() 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			 @Override
	            public void run() 
	            {
				 JFrame framestart = new JFrame("Options");
				 framestart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 framestart.setLayout(null);
				 framestart.setSize(340, 500);
				 framestart.setVisible(true);
				 framestart.setLocationRelativeTo(null); 
				 framestart.setResizable(false);
				 framestart.getContentPane().setBackground(Color.LIGHT_GRAY);
	            	
				 JButton singleButton = new JButton("SINGLE PLAYER");
				 singleButton.setBounds(65, 100, 200, 80);
				 JButton multiButton = new JButton("MULTI PLAYER");
				 multiButton.setBounds(65, 250, 200, 80);
				 
				 framestart.getContentPane().add(singleButton);
				 framestart.getContentPane().add(multiButton);
				 
				 singleButton.addActionListener(new ActionListener() 
				 {
					 
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						framestart.setVisible(false);
						OptionsSingleView osv = new OptionsSingleView();
						osv.initSingleView();
					}
				});
				 
				 multiButton.addActionListener(new ActionListener() 
				 {
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						framestart.setVisible(false);
						OptionsMultiView omv = new OptionsMultiView();
						omv.initMultiView();
					}
				});
				 
	            }
		});
            
	}
}
