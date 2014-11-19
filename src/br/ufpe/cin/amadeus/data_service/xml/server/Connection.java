package br.ufpe.cin.amadeus.data_service.xml.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Connection {

	private int id;

	private String name = null;
	private Socket socket;
	private BufferedReader in = null;
	private PrintWriter out = null;

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(socket.getOutputStream(), true);
		this.out.flush();
	}

	public synchronized boolean isReady() throws IOException {
		return in.ready();
	}

	public synchronized  String receiveFirstMessage() throws IOException{
		String message = "";

		String temp = "";
		do{
			temp =  in.readLine();
			message = message.concat(temp);
		}while (isReady()); 
		
		return message;
	}

	public synchronized String receiveMessage() throws IOException{
		String message = "";

		String temp = "";
		if(isReady()){
			do{
				temp =  in.readLine();
				message = message.concat(temp);
			}while (isReady()); 
		}
		
		return message;
	}
	
	public synchronized void sendMessage(String string) {
		out.print(string);
		out.flush();
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized void setId(int id) {
		this.id = id;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}
	
	public synchronized boolean isClosed(){
		return getSocket().isClosed();
	}
	
	public synchronized Socket getSocket(){
		return this.socket;
	}
	
	public synchronized boolean isConnected(){
		return getSocket().isConnected();
	}
	
	public synchronized void close(){
		try {
			this.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
