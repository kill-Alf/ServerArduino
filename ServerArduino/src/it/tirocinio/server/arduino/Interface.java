package it.tirocinio.server.arduino;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.*;
import javax.swing.JFrame;

public class Interface extends JFrame{

	private JButton buttonStart;
	private JButton buttonStop;
	private JPanel panel;
	private static JTextArea areaTesto;
	private ThreadServer t;
	
	public static void changeText(String text) {
		areaTesto.append(text);
	}
	
	
	public Interface() throws IOException {
		JFrame frame=new JFrame();
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		createPanel();
		frame.add(panel);
		frame.setVisible(true);
		

	}

	public void createPanel() {
		buttonStart = new JButton("Start");

		buttonStart.addActionListener(y -> {
			try {
				t = new ThreadServer();
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});

		buttonStop = new JButton("Stop");
		
		buttonStop.addActionListener(c->{
			t.shutDown();
		});
		
		areaTesto = new JTextArea(20,50);
		areaTesto.setEditable(false);
		panel = new JPanel();
		panel.add(buttonStart);
		panel.add(buttonStop);
		panel.add(areaTesto);
	}

}
