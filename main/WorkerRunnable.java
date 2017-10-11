package main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
        	System.out.println("..sending");
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String httpRequest = "";
            for (String line; (line = reader.readLine()) != null;) {
            	if(line.contains("GET")) {
            		httpRequest = line;
            		break;
            	}
                System.out.println(line);	
//                httpRequest += line;
            }
            
            System.out.println(httpRequest);
        	String page = PageDirectory.getPath(httpRequest);
			try {
				page = new String(Files.readAllBytes(Paths.get(page)));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        	String httpResponse = "HTTP/1.1 200 OK\r\n\r\n " + page; 
            
            long time = System.currentTimeMillis();
            output.write(httpResponse.getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}