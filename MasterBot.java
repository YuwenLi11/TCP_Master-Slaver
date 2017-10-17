/*
 * 206 Programming Project: Part 4
 *
 * Author:      Yuwen Li
 * Student ID:  011541868
 * Major:       Computer Engineering
 *              San Jose State University
 */
package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MasterBot
{
	public static void main(String[] args)
{     try {
	
	int to=0;
	System.out.println("Master start"); 
	  System.out.println("list\n"+"connect\n"+"disconnect\n"+"ipscan\n"+"tcpportscan\n"+"geoipscan\n"+"please enter you command");
	//create master's socket,set the port and listen the port
	ServerSocket MasterBotSocket=new ServerSocket(2222);
    Socket socket=null;
    while(true){	
	socket=MasterBotSocket.accept();// use accept()to listen and wait the connection
	//create new thread
	MasterThread serverThread=new MasterThread(socket);
	//start thread
	serverThread.start();
	to++;
	//System.out.println(to);
     }
} catch (IOException e) {
	e.printStackTrace();
}
}
}