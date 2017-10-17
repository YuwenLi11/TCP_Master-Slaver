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
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;


class portThread extends Thread{
	Socket socket=null;
	String ret=null;
	String add=null;
	public portThread(Socket socket,String x,String add)
	{ this.socket=socket;
	  this.ret=x;
	  this.add=add;
	}
	  public void run(){
try {         
        StringBuffer x=new StringBuffer();    
       	int xg=ret.lastIndexOf("-");
       	int kg=ret.lastIndexOf(" ");
       	String s1=ret.substring(kg+1,xg);
       	String s2=ret.substring(xg+1);
       	 int min = Integer.parseInt(s1);
       	 int max = Integer.parseInt(s2);
       	 int timeout=3000;
       	 for(int bo=min;bo<=max;bo++)
       {
               try{ 
               	//System.out.println(add);
                Socket s = new Socket();
                s.connect( new InetSocketAddress(add, bo), timeout );
                s.close();
                x.append(bo);
                x.append(",");
               }
               catch (IOException e) {
               //	System.out.println("close");
               }
       }     String oup1=x.toString();
             //String oup2="no port alive";
       DataOutputStream ap = new DataOutputStream(socket.getOutputStream()); 
    	   
       if("".equals(oup1))
       ap.writeUTF(oup1);
       else
    	   oup1=oup1.substring(0,oup1.length()-1);
       ap.writeUTF(oup1);
       
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 
	} 
	}
public class ipThread extends Thread 
{  public static boolean ping(String ipAddress) throws Exception {
    int  timeOut =  5000 ;      
  boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
    return status;}

/*	  public static boolean ping(String command) {
		  boolean res = false;
			try {
				Process p = Runtime.getRuntime().exec( command);
				BufferedReader inputStream = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				
				String s = "";
	            String x="";
				while ((s = inputStream.readLine()) != null) {
					x+=s;
				}
				
					String un="timeout";
					if(x.indexOf(un)>=0)
					{
					res =false;
					}
					else 
					{res=true;}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
*/
		
Socket socket=null;
	String ret=null;
public ipThread(Socket socket,String x)
{ this.socket=socket;
  this.ret=x;
}

public void run(){
	 try { 
		
		StringBuffer x=new StringBuffer();
 		int q=ret.lastIndexOf(".");
 		int w=ret.lastIndexOf("-");
 		int r=ret.lastIndexOf(" ");
 		String sc1=ret.substring(0,w);
 		int e=sc1.lastIndexOf(".");
 		String b=sc1.substring(r+1,e+1);
 		String ra1=sc1.substring(e+1);
 		String ra2=ret.substring(q+1);
 		
 		 int min = Integer.parseInt(ra1);
 		 int max = Integer.parseInt(ra2);
 		 for(int xu=min;xu<=max;xu++)
 		 {  String ip=b+xu;
 	     //System.out.println(ip);
 		 try {
 			//String adp = "-c 2 -W 5000 "+ip;
			//boolean tf=ping("ping " + adp);
 			 boolean tf=ping(ip);
 			if(tf==true)
 			{
 				x.append(ip);
 				x.append(",");
 			}
 		} catch (Exception e1) {
 			//System.out.println("no respond");
 		
 		}
 		 }   
 		  String oup1=x.toString();

           DataOutputStream ap = new DataOutputStream(socket.getOutputStream()); 

           if("".equals(oup1))
               ap.writeUTF(oup1);
               else
            	   oup1=oup1.substring(0,oup1.length()-1);
               ap.writeUTF(oup1);
               
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}

class geoThread extends Thread 
{    public static boolean ping(String ipAddress) throws Exception {
    int  timeOut =  5000 ;          
    boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
    return status;
}
	/*  public static boolean ping(String command) {
		  boolean res = false;
			try {
				Process p = Runtime.getRuntime().exec( command);
				BufferedReader inputStream = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				
				String s = "";
	            String x="";
				while ((s = inputStream.readLine()) != null) {
					x+=s;
				}
			
					String un="timeout";
					if(x.indexOf(un)>=0)
					{
					res =false;
					}
					else 
					{res=true;}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
*/
	Socket socket=null;
	String ret=null;
public geoThread(Socket socket,String x)
{ this.socket=socket;
  this.ret=x;
}

public void run(){
	 try { 
		 
		StringBuffer x=new StringBuffer();
 		int q=ret.lastIndexOf(".");
 		int w=ret.lastIndexOf("-");
 		int r=ret.lastIndexOf(" ");
 		String sc1=ret.substring(0,w);
 		int e=sc1.lastIndexOf(".");
 		String b=sc1.substring(r+1,e+1);
 		String ra1=sc1.substring(e+1);
 		String ra2=ret.substring(q+1);
 		
 		 int min = Integer.parseInt(ra1);
 		 int max = Integer.parseInt(ra2);
 		 for(int xu=min;xu<=max;xu++)
 		 {  String ip=b+xu;

 		 try {
 			//String adp = "-c 2 -W 5000 "+ip;
			//boolean tf=ping("ping " + adp);
			 boolean tf=ping(ip);
 			if(tf==true)
 			{      
                    URL url = new URL("https://ipapi.co/"+ip+"/json/");
                    
 		            URLConnection conn = url.openConnection();
 		            conn.setRequestProperty("User-Agent", "java-ipapi-client");
 		            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
 		            String line = null;
 		            while ((line = reader.readLine()) != null)
 		            x.append(line );
 		           x.append("\n");
 		            reader.close();

 			}
 			else 
 			{
 				x.append("");
 			}
 		} catch (Exception e1) {
 			//System.out.println("no respond");
 		
 		}
 		 }   

           DataOutputStream ap = new DataOutputStream(socket.getOutputStream()); 
           String res=x.toString();
		       res=res.replace("{","");
		       res=res.replace("}","");
		       res=res.replace(" ","");
		       res=res.replace("\"","");
               ap.writeUTF(res);
               
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
