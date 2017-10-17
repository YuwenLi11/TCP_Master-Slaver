/*
 * 206 Programming Project: Part 4
 *
 * Author:      Yuwen Li
 * Student ID:  011541868
 * Major:       Computer Engineering
 *              San Jose State University
 */
package socket;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MasterThread extends Thread 
{
	Socket socket=null;
public MasterThread(Socket socket)
{ this.socket=socket;
}
public void run(){
	try {        
		// send the command    
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());       
        // get command from keyboard    
        String s = new BufferedReader(new InputStreamReader(System.in)).readLine();    
        out.writeUTF(s);
        // get the input    
        DataInputStream input = new DataInputStream(socket.getInputStream());  
        String clientInputStr = input.readUTF();   
        System.out.println(clientInputStr);      
    } 
	catch (Exception e) {    
  
    } finally {    
        if (socket != null) {    
            try {    
                socket.close();    
            } catch (Exception e) {    
                socket = null;    
                System.out.println("master finally error" );    
            } }}}}   
