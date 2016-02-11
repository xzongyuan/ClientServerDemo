package Controller;
import Controller.NetControlClient;
import View.ClientConsole;


public class TestClient {
	
	public static void main(String[] args) {
		
		ClientConsole _window = new ClientConsole();
		_window.initView();
		
		NetControlClient _NetControl = new NetControlClient();
		
		_window.addCtrl(_NetControl);
		
		
		//String _ServerMsg= _NetControl.readLine();
		//System.out.println(_ServerMsg);
	}

}
