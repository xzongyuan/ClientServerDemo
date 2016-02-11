package Controller;

import View.ServerConsole;

public class TestServer {

	public static void main(String[] args) {
		
		ServerConsole _Console = new ServerConsole();
		_Console.initView();
		
		NetControlServer _Ctrl = new NetControlServer();

		//add view for update
		_Ctrl.addView(_Console);
		
		_Ctrl.listen();
		
	}
}
