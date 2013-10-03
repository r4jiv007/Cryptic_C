/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outputserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rajiv
 */
public class MultiClient extends Thread {
        Socket socket=null;
        public MultiClient(Socket socket) {
	super("MultiClient");
	this.socket = socket;
    }
    
   
	 public void run() {
      
            try {
                String clientSentence;
                //Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient =
                   new BufferedReader(new InputStreamReader(socket.getInputStream()));
               // DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                while ((clientSentence = inFromClient.readLine())!=null){
                ServerOP.op.append(clientSentence+"\n");  
                //}
               
        }   } catch (IOException ex) {
                Logger.getLogger(ServerOP.class.getName()).log(Level.SEVERE, null, ex);
            }
   
    
    
    
}
}
