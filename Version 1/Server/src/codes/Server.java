package codes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Server {
	private static ServerSocket server = null;
	private static Socket socket = null;
	private static final int port = 8080;
	public static void main(String[] args) {
		//Create IO Objects
		BufferedReader in = null;
		PrintWriter out = null;
		Scanner consoleInput = new Scanner(System.in);
		String serverName = null;
		String clientName = null;
		//Start Server
		try {
			System.out.println("Server Loading ...");
			server = new ServerSocket(port);
			System.out.println("Server Loaded");
			System.out.print("\nServer Name_");
			serverName = consoleInput.nextLine();
		} catch (IOException e) {
			System.out.println("Can not listen to port: " + port);
			System.exit(-1);
		}
		while(true) {
			//Create Socket
			try {
				socket = server.accept();
				System.out.println("\nA Client has joined the server\n");
			} catch (IOException e) {
				System.out.println("Communication Error with client");
				System.exit(-1);
			}
			try {
				in = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream()
								)
						);
				
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println(serverName);
				clientName =  in.readLine();
				System.out.println("Name: " + clientName);

				while(socket.isConnected()) {
					System.out.print(serverName + " >> ");
					out.println(consoleInput.nextLine());
					System.out.print(clientName + " >> ");
					System.out.println(in.readLine());
				}
			} catch (IOException e) {
				System.out.println("Client Left");
				//consoleInput.close();
			}
		}
	}
}
