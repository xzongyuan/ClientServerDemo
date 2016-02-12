package Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream; 
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import View.ServerConsole;


public class NetControlServer {
	
	private ServerSocket mSocketListen;
	private Socket mSocketConnected;
	private InputStream mInput;  //Byte
	private InputStreamReader mInputReader; //Char
	private BufferedReader mBufferedReader; // Char Buffer
	private ServerConsole mServerConsole;
	//out
	private OutputStream mOutPut;  //Byte
	private PrintWriter mPrintWriter; //Char 
	
 
	public NetControlServer() {
		// TODO Auto-generated constructor stub
		
	}

	public void listen() {
		try {
			mSocketListen = new ServerSocket(4242);  //this constructor will connect the IP/port
			
			while (true) {

				mSocketConnected = mSocketListen.accept(); 
				
				Thread _Thread = new Thread(new RunReader());	
				_Thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	//1.get stream
	private void getSocketStream(Socket pSocket){			
 System.out.println("Socket = "+mSocketConnected.getPort());
			try {
				mInput = mSocketConnected.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (mInput == null) {				
				return;
			}
			mInputReader = new InputStreamReader(mInput);
System.out.println("mInputReader = "+((mInputReader) == null ? "null" : mInputReader));
			
			if (mInputReader == null) {
				return;
			}
			mBufferedReader = new BufferedReader(mInputReader);

System.out.println("mBufferedReader = "+((mBufferedReader) == null ? "null" : mBufferedReader));
	}
	
	//2.read stream
	public String readLine(){
		
		String _Temp = null;
		try {
System.out.println("before read");			
			_Temp = mBufferedReader.readLine();
System.out.println("after read");			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return _Temp;		
	}
	
	//3.get out put stream
	public void writeMSG(String pString){
		try {
			mOutPut = mSocketConnected.getOutputStream();
			mPrintWriter = new PrintWriter(mSocketConnected.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		mPrintWriter.write(pString);
		mPrintWriter.flush();
		
	} 
	
	//destroy it
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		
		IOUtil.closeIO(mSocketListen);
		IOUtil.closeNetInStream(mSocketConnected,mInput,mInputReader,mBufferedReader); 		
		IOUtil.closeNetOutStream(mOutPut,mPrintWriter);
	}

	public class RunReader implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("In Thread");
			getSocketStream(mSocketConnected);

			while (true) {
				String _RecString= readLine();
				if (_RecString == null) {
System.out.println("In while:buffer is null");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				} 
				
				
				if (mServerConsole == null) {
System.out.println("mServerConsole = null");					
					continue;
				}
				mServerConsole.appendText(_RecString);
			
			}
		
				
		}
		
	}

	
	public void addView(ServerConsole pConsole) {
		// TODO Auto-generated method stub
System.out.println("pConsole = "+pConsole);		
		mServerConsole = pConsole;
	}

}
