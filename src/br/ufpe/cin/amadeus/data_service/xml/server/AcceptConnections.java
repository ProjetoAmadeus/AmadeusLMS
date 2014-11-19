package br.ufpe.cin.amadeus.data_service.xml.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.ufpe.cin.amadeus.data_service.controllers.CtrlResquest;

public class AcceptConnections extends Thread {
	
	private static ServerSocket serverSocket;
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			int cTosPortNumber = 3300;
		    String str;
		    serverSocket = new ServerSocket(cTosPortNumber);
			
			while (true) {
				
			    System.out.println("Waiting for a connection on " + cTosPortNumber);

			    Socket fromClientSocket = serverSocket.accept();
			    PrintWriter pw = new PrintWriter(fromClientSocket.getOutputStream(), true);

			    BufferedReader br = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));
			    
			    String xml = "";
				while ((str = br.readLine()) != null) {
					if (str.equals("bye")) {
						//pw.println("bye");
						break;
					} else {
						xml += str.trim();
					}
				}
			    String dataMessage = CtrlResquest.getInstance().execute(xml.trim());
			    
			    pw.write(dataMessage);
			    pw.println("bye");
			    
			    pw.close();
			    br.close();

			    fromClientSocket.close();
				
			}
		} catch (IOException e) {
			System.out.println("Erro ao criar o ServerSocket");
			e.printStackTrace();
		}
	}

	public static void main(String [] args){
		AcceptConnections acceptConnections = new AcceptConnections();
		acceptConnections.start();
		
	}

}
