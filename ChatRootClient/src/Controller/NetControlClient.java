package Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class NetControlClient {
	
	private SocketAddress mSockServer;
	private Socket mSockService;
	private InputStream mInput;  //Byte
	private InputStreamReader mInputReader; //Char
	private BufferedReader mInputBuffer; // Char Buffer

	//out
	private OutputStream mOutPut;  //Byte
	private PrintWriter mPrintWriter; //Char 
	
 
	public NetControlClient() {
		// TODO Auto-generated constructor stub

		mSockServer = new InetSocketAddress("127.0.0.1", 4242);  //this constructor will connect the IP/port
		
	    mSockService = new Socket(); //get the service port
		
		try {
			mSockService.connect(mSockServer, 6000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getSocketStream(mSockService);
	} 

	//1.get stream
	private void getSocketStream(Socket pSocket){			
 System.out.println("SoccketServcie = " + pSocket);
			try {
				mInput = pSocket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (mInput == null) {				
				return;
			}
			mInputReader = new InputStreamReader(mInput);
			
			if (mInputBuffer == null) {
				return;
			}
			mInputBuffer = new BufferedReader(mInputReader);
		
	}
	
	//2.read stream
	public String readLine(){
		
		String _Temp = null;
		try {
			_Temp = mInputBuffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return _Temp;		
	}
	
	//3.get out put stream
	public void writeMSG(String pString){
		try {
			mOutPut = mSockService.getOutputStream();
			mPrintWriter = new PrintWriter(mSockService.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

System.out.println("client msg = "+pString);
		mPrintWriter.write(pString+"\n");
		mPrintWriter.flush();
System.out.println("flush out to server");
		
	} 
	
	//destroy it
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		
		IOUtil.closeNetInStream(mSockService,mInput,mInputReader,mInputBuffer); 		
		IOUtil.closeNetOutStream(mOutPut,mPrintWriter);
		
		
	}

	

}
