package View;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.NetControlServer;
 
public class ServerConsole {

	private JFrame mJFrame;
	private JPanel mJPanelMain;
	private JTextArea mJTextShow;
	private JScrollPane mJScrShow;
	//private JTextArea mJTextEnter;
	//private JScrollPane mJScrEnter;
	//private JButton mJBtnSend;
	
	public void initView() {
		// TODO Auto-generated method stub
		mJTextShow = new JTextArea(40, 40);
		mJTextShow.setText("Show Message");
		
		mJScrShow = new JScrollPane(mJTextShow);
		mJScrShow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mJScrShow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	//  mJTextEnter = new JTextArea(20, 40);
	//  mJTextEnter.setText("Enter here");
	//	mJScrEnter = new JScrollPane(mJTextEnter);
	//	mJScrEnter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	//	mJScrEnter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);		
	//	mJBtnSend = new JButton("Send Message");
	//	mJBtnSend.addActionListener(new SendListener());
		
		
		mJPanelMain = new JPanel();
	 
		mJPanelMain.setLayout(new BoxLayout(mJPanelMain, BoxLayout.Y_AXIS));
		mJPanelMain.add(mJScrShow);
	//	mJPanelMain.add(mJBtnSend);
	//	mJPanelMain.add(mJScrEnter);

		mJFrame = new JFrame("Simple Chat Room");
		mJFrame.getContentPane().add(mJPanelMain);
		mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mJFrame.setSize(500, 500);		
		mJFrame.setVisible(true);
	}
	
	public void appendText(String _RecString) {
		// TODO Auto-generated method stub
		mJTextShow.append(_RecString+"\n");
System.out.println("appendText = " +_RecString);

	}

 
}
