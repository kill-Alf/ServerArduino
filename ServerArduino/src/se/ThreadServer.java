package se;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadServer extends Thread{
	
	private static ServerSocket ss;
	private ExecutorService service;
	private boolean flag;
	private ArrayList<ThreadArduino> threads;
	
	public ThreadServer() throws IOException {
		ss = new ServerSocket(9080);
		flag = true;
		threads = new ArrayList<ThreadArduino>();
	}
	
	public void shut(){
		flag = false;
	}

	
	@Override
	public void run() {
		Socket x;
		int core = Runtime.getRuntime().availableProcessors();
		// TODO Auto-generated method stub
		while(flag) {
			try {
				x = ss.accept();
				System.out.println("Connection ok");
				ThreadArduino thread = new ThreadArduino(x);
				service=Executors.newScheduledThreadPool(core);
				service.execute(thread);
				threads.add(thread);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println();
		System.out.println("Esco dal while");
		System.out.println();
		
		for(int i = 0;i<threads.size();i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
