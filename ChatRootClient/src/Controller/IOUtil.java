package Controller;

import java.io.BufferedReader;
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
import java.net.Socket;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class IOUtil {
	
	public static void closeSocket(Socket pSocket){
		
		if(pSocket!=null){
			try {
				pSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pSocket = null;
		}
	}

	public static void closeInputStream(InputStream pInputStream){
		
		if(pInputStream!=null){
			try {
				pInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pInputStream = null;
		}
	}

	public static void closeOutputStream(OutputStream pOutputStream){
		
		if(pOutputStream!=null){
			try {
				pOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pOutputStream = null;
		}
	}
	
	public static void closePrintWriter(PrintWriter pPrintWriter){
		
		if(pPrintWriter!=null){
			pPrintWriter.close();
			pPrintWriter = null;
		}
	}
	
	public static void closeInputStreamReader(InputStreamReader pInputStreamReader){
		
		if(pInputStreamReader!=null){
			try {
				pInputStreamReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pInputStreamReader = null;
		}
	}
	
	public static void closeBufferReader(BufferedReader pBufferedReader){
		
		if(pBufferedReader!=null){
			try {
				pBufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pBufferedReader = null;
		}
	}
	
	public static void closeNetInStream(Socket pSockServer,InputStream pInput,InputStreamReader pInputReader,BufferedReader pInputBuffer) {
		IOUtil.closeSocket(pSockServer);
		IOUtil.closeInputStream(pInput);
		IOUtil.closeInputStreamReader(pInputReader);
		IOUtil.closeBufferReader(pInputBuffer);
	}
	public static void closeNetOutStream(OutputStream pOutPut,PrintWriter pPrintWriter) {
		IOUtil.closeOutputStream(pOutPut); 
		IOUtil.closePrintWriter(pPrintWriter);
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
			closeOutputStream(_ObjOut);
			closeOutputStream(_OutputStream);
	 
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
		closeInputStream(pObjIn);
		closeInputStream(pInputStream);
		return pCheckBoxArrayState;
	}


}
