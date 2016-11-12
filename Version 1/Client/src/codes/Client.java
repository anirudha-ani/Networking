package codes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Client {
	public static Socket socket = null;
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        String serverIP = null;
        int serverPort = 0;
        String clientName = null;
        String serverName = null;
        
		try 
		{
			System.out.print("Server Location: ");
            serverIP = scanner.nextLine();

            System.out.print("Server Port: ");
            serverPort = Integer.parseInt(scanner.nextLine());
            socket = new Socket(serverIP, serverPort);
            
			
//			System.out.println("Connected to Server\n"
//					+ "Socket: " + socket.getInetAddress() + ":" +
//					socket.getPort() + "\n" );
		} catch (IOException e) {
			System.out.print("Connection to network can not be stablished");
		}
		BufferedReader in = null;
		PrintWriter out = null;
		Scanner consoleInput = new Scanner(System.in);
		try {
			in = new BufferedReader( new InputStreamReader(
					socket.getInputStream() ));
			out = new PrintWriter(socket.getOutputStream(), true);
			serverName = in.readLine();
			System.out.println("Welcome to " + serverName + " server\n");

			System.out.print("Your Name_ ");
			clientName = consoleInput.nextLine();
			out.println(clientName);
			System.out.println("\n\nYour Session with " + serverName + " has started\n\n");
			while(true) {
				
				System.out.print(serverName + " >> ");
				System.out.println(in.readLine());

				System.out.print(clientName + " >> ");
				out.println(consoleInput.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
