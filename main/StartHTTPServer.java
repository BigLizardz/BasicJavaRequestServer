package main;

public class StartHTTPServer {

	public static int runtimeMinutes = 5;
	
	public static void main(String[] args) {
		MultiThreadedServer server = new MultiThreadedServer(8080);
		new Thread(server).start();

		try {
		    Thread.sleep(runtimeMinutes * (60 * 1000));
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Stopping Server");
		server.stop();
	}

}
