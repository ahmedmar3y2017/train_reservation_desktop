/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class multipleClients {

    ServerSocket serverSocket;
    java.net.Socket socket;

    public multipleClients() throws IOException {

        serverSocket = new ServerSocket(8000);

//        int clients = 1;
        while (true) {
//            System.out.println("Server wait client number " + clients);
            socket = serverSocket.accept();
            InetAddress inetAddress = socket.getInetAddress();

            System.out.println("The hos name is : " + inetAddress.getHostName());
            System.out.println("The host Address is : " + inetAddress.getHostAddress());

            Thread t = new Thread(new clientsthread(socket));
            t.start();

//            clients++;

        }

    }
//    public static void main(String[] args) throws IOException {
//        new multipleClients();
//    }
}

class clientsthread implements Runnable {

    private java.net.Socket socket;

    public clientsthread(java.net.Socket socket) {

        this.socket = socket;

    }

    @Override
    public void run() {

        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            

//                double d = inputStream.read();
            
                
                
                
            

        } catch (IOException ex) {
            Logger.getLogger(clientsthread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
