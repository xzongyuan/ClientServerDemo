package View;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.NetControlClient;


public class ClientConsole {

	JFrame mJFrame;
	JPanel mJPanelMain;
	JTextArea mJTextShow;
	JScrollPane mJScrShow;
	JTextArea mJTextEnter;
	JScrollPane mJScrEnter;
	JButton mJBtnSend;

	
	public ClientConsole() {
		// TODO Auto-generated constructor stub
	} 
	
	public void initView() {
		
		mJTextShow = new JTextArea(40, 40);
		mJTextShow.setText("Show Message");
		mJTextEnter = new JTextArea(20, 40);
		mJTextEnter.setText("Enter here");
		
		mJScrShow = new JScrollPane(mJTextShow);
		mJScrShow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mJScrShow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		mJScrEnter = new JScrollPane(mJTextEnter);
		mJScrEnter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mJScrEnter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		mJBtnSend = new JButton("Send Message");
		mJBtnSend.addActionListener(new SendListener());
		
		
		mJPanelMain = new JPanel();
	 
		mJPanelMain.setLayout(new BoxLayout(mJPanelMain, BoxLayout.Y_AXIS));
		mJPanelMain.add(mJScrShow);
		mJPanelMain.add(mJBtnSend);
		mJPanelMain.add(mJScrEnter);

		mJFrame = new JFrame("Simple Chat Room");
		mJFrame.getContentPane().add(mJPanelMain);
		mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mJFrame.setSize(500, 500);	
		mJFrame.setLocation(500, 100);
		mJFrame.setVisible(true);
	}
	
	
	public class SendListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// Sent to Server
			String _MSG = "send to Server";		
			if (mNetControlClient == null) {
System.out.println("mNetControlClient == null, must call addCtrl()");					
				return;
			}
			mNetControlClient.writeMSG(_MSG);
System.out.println("send message");			
		}
	}
	

	NetControlClient mNetControlClient =null;
	public void addCtrl(NetControlClient pNetControl) {
		// TODO Auto-generated method stub
		mNetControlClient = pNetControl;
	}
	
	
}
