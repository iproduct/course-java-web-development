package textchat;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

class Handler implements Runnable {
	   private ChatServer server;
	   private final Socket socket;
	   private BufferedReader in;
	   private PrintWriter out;
	   private String nickname = "Anonimous";

	   Handler(ChatServer server, Socket socket) { 
		   this.server = server;
		   System.out.println("New Client Accepted: " 
				   + socket);
		   this.socket = socket; 
		   try {    
	        in = 
	          new BufferedReader(
	            new InputStreamReader(
	              socket.getInputStream()));
	        // Enable auto-flush:
	        out = 
	          new PrintWriter(
	            new BufferedWriter(
	              new OutputStreamWriter(
	                socket.getOutputStream())), true);
	       } catch(IOException e) {
	        try {
	          socket.close();
	        } catch(IOException e2) {
	          System.err.println("Socket not closed");
	        }
	       }
	   }
	   public void run() {
		 String message = "";
		 try {
			nickname = in.readLine();
			while(!message.equalsIgnoreCase("end")){
				message = in.readLine();
				server.sendAllClients(nickname + " : " + message);
			}
	     } catch (IOException e) {
			e.printStackTrace();
	     }
	     System.out.println("Removing client " + nickname 
	    		 + " : " + socket);
	     server.removeHandler(this);
	     try {
	        socket.close();
	     } catch(IOException e2) {
	        System.err.println("Socket not closed");
	     }
	   }	   
	   public void sendMessage(String message) {
		   out.println(message);
	   }
	} // End Handler


public class ChatServer implements Runnable {
	   public static final int SERVER_PORT = 2019;
	   public static final int POOL_SIZE = 1;
   private final ServerSocket serverSocket;
   private final ExecutorService pool;
   private List<Handler> handlers = new CopyOnWriteArrayList<Handler>();

  
   public ChatServer(int port, int poolSize)
       throws IOException {
     serverSocket = new ServerSocket(port);
     pool = Executors.newCachedThreadPool();
     System.out.println("Starting Network Service on port"
    		 + serverSocket);
   }

   public void run() { // run the service
     try {
       for (;;) {
    	   Handler h = new Handler(this, serverSocket.accept());
    	   addHandler(h);
    	   pool.execute(h);
       }
     } catch (IOException ex) {
    	try {
    		serverSocket.close();
		} catch (IOException e) {
			System.err.println("Server Socket not coled.");
		}
       pool.shutdown();
     }
   }
   
   void shutdownAndAwaitTermination() {
	   pool.shutdown(); // Disable new tasks from being submitted
	   try {
	     // Wait a while for existing tasks to terminate
	     if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
	       pool.shutdownNow(); // Cancel currently executing tasks
	       // Wait a while for tasks to respond to being cancelled
	       if (!pool.awaitTermination(60, TimeUnit.SECONDS))
	           System.err.println("Pool did not terminate");
	     }
	   } catch (InterruptedException ie) {
	     // (Re-)Cancel if current thread also interrupted
	     pool.shutdownNow();
	     // Preserve interrupt status
	     Thread.currentThread().interrupt();
	   }
   }

   synchronized public void addHandler(Handler h){
	   handlers.add(h);
   }
   synchronized public void removeHandler(Handler h){
	   handlers.remove(h);
   }
   
   synchronized public void sendAllClients(String message){
	   for(Handler h : handlers)
		   h.sendMessage(message);
   }

   
   public static void main(String[] args) throws IOException{
	   ChatServer service = 
		   new ChatServer(SERVER_PORT, POOL_SIZE);
	   service.run();
//	   try {
//		   Thread.sleep(10000);
//	   } catch (InterruptedException e) {
//			e.printStackTrace();
//	   }
//	   service.shutdownAndAwaitTermination();
   }
 }

 