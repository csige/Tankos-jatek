package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.util.XML11Char;

public class Server extends Thread{
	private ServerSocket serverSocket;
	Socket newClient;
	ArrayList<Socket> clientSockets;
	private static WriteXML xmlCreator;
	boolean interrupted = false;
	String[] message = {"1", "2", "3", "4", "N", "1", "2", "3", "S"};
	   
	   public Server(int port) throws IOException
	   {
	      serverSocket = new ServerSocket(port);
//	      serverSocket.setSoTimeout(20000);
	   }

	   public void run()
	   {
	      while(!interrupted)
	      {
	         try
	         {
	        	
	            System.out.println("Waiting for client on port " +
	            serverSocket.getLocalPort() + "...");
	            newClient = serverSocket.accept();
	            clientSockets.add(newClient);
	            System.out.println("Just connected to "
	                  + newClient.getRemoteSocketAddress());
	            new ClientThread(newClient).start();
//	            DataInputStream in =
//	                  new DataInputStream(newClient.getInputStream());
//	            System.out.println(in.readUTF());
//	            DataOutputStream out =
//	                 new DataOutputStream(newClient.getOutputStream());
//	            out.writeUTF("Thank you for connecting to "
//	              + server.getLocalSocketAddress());
	            
	         }catch(SocketTimeoutException s)
	         {
	            System.out.println("Socket timed out!");
	            break;
	         }catch(IOException e)
	         {
	            e.printStackTrace();
	            break;
	         }finally{
	        	 try {
					for(int i = 0;i<clientSockets.size();i++){
						clientSockets.get(i).close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	         }
	      }
	      
	      
	   }
	   public static void main(String [] args)
	   {
	      int port = 6600;
	      xmlCreator = new WriteXML();
	      try
	      {
	         Thread t = new Server(port);
	         t.start();
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	   }
	   
	   
	   public class ClientThread extends Thread{
		   protected Socket socket;
		   boolean interrupted = false;

		    public ClientThread(Socket clientSocket) {
		        this.socket = clientSocket;
		    }

		    public void run() {
		        InputStream inp = null;
		        BufferedReader brinp = null;
		        DataOutputStream out = null;
		        try {
		            inp = socket.getInputStream();
		            brinp = new BufferedReader(new InputStreamReader(inp));
		            out = new DataOutputStream(socket.getOutputStream());
		        } catch (IOException e) {
		            return;
		        }
		        
		        while (!interrupted) {
		            try {
		            	
		            	
		            	StringBuilder builder = new StringBuilder();
		            	String aux = "";

		            	while ((aux = brinp.readLine()) != null) {
		            	    builder.append(aux);
		            	}

		            	String text = builder.toString();
		            	
		                if ((text == null) || text.equalsIgnoreCase("QUIT")) {
		                    socket.close();
		                    return;
		                } else {
		                	System.out.println(text);
		                }
		                
		                out.writeUTF(xmlCreator.createXMLString(message));
		                out.flush();
		                
		            } catch (IOException e) {
		                e.printStackTrace();
		                return;
		            }
		        }
		    }
	   }

}
