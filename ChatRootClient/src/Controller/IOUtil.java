package Controller;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class IOUtil {
	

	public static void closeIO(Closeable pClosable){
		
		if(pClosable!=null){
			try {
				pClosable.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pClosable = null;
		}
	}
	 
	public static void closeNetInStream(Socket pSockServer,InputStream pInput,InputStreamReader pInputReader,BufferedReader pBufferReader) {
		
		closeIO(pSockServer);
		closeIO(pInput);
		closeIO(pInputReader);
		closeIO(pBufferReader);  
	}
	public static void closeNetOutStream(OutputStream pOutPut,PrintWriter pPrintWriter) {
		closeIO(pOutPut);
		closeIO(pPrintWriter); 
	}
	
	public static <T> void writeObject(T _CheckBoxArrayState,
			JFileChooser _FileChooser, ObjectOutputStream _ObjOut,
			FileOutputStream _OutputStream) {
		try {
			_OutputStream = new FileOutputStream(_FileChooser.getSelectedFile());				
			_ObjOut = new ObjectOutputStream(_OutputStream);
			_ObjOut.writeObject(_CheckBoxArrayState);
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			closeIO(_ObjOut);
			closeIO(_OutputStream);
	 
		}
	}

	@SuppressWarnings("unchecked")
	public static<T> T readObject(T pCheckBoxArrayState,
			JFileChooser pFileChooser, ObjectInputStream pObjIn,
			FileInputStream pInputStream) {
		try {
			pInputStream = new FileInputStream(pFileChooser.getSelectedFile());
			pObjIn = new ObjectInputStream(pInputStream); 
			pCheckBoxArrayState = ((T)(pObjIn.readObject()));			
		 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		closeIO(pObjIn);
		closeIO(pInputStream);
		return pCheckBoxArrayState;
	}


}
