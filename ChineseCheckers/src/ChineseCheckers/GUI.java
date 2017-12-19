package ChineseCheckers;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class GUI extends JFrame
{
	GUI() 
	{
		InitView iv = new InitView();
    	iv.initView();
	}
}

