package it.tirocinio.server.arduino;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ThreadArduino extends Thread{

	private Socket socket;
	private Scanner input;
	private MongoDatabase database;


	public ThreadArduino(Socket s) throws IOException {
		socket = s;	
		//database = ConnectionPool.connect("mydb");
	}


	public void run() {

		try {
			input = new Scanner(socket.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String stringId = input.nextLine();
		if(stringId.equals("non ci sono messaggi")) {
			
		} else {
			int id = Integer.parseInt(stringId);
			switch(id) {
			case 1: pulseSensor();
			break;
			case 2: 
				try {
					dhtSensor();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3: 
				try {
					gyroscopeSensor();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4: voiceSensor();
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			} 

		}


	}

	private void pulseSensor() {
		String pulseSensor = input.nextLine();
	}

	private void dhtSensor() throws IOException {
		String dhtSensorTemp = input.nextLine();
		String dhtSensorUmidity = input.nextLine();
		//MongoCollection<Document> collection = database.getCollection("dhtSensor");
		//Document document = new Document("Temperatura",Integer.parseInt(dhtSensorTemp)).append("Umidità", Integer.parseInt(dhtSensorUmidity));
		//collection.insertOne(document);
		System.out.println(dhtSensorUmidity);
		System.out.println(dhtSensorTemp);
		System.out.println();
	}

	private void gyroscopeSensor() {
		String x = input.nextLine();
		String y = input.nextLine();
		String z = input.nextLine();
		//MongoCollection<Document> collection = database.getCollection("gyroscopeSensor");
		//Document document = new Document("x",Integer.parseInt(x)).append("y", Integer.parseInt(y)).append("z", Integer.parseInt(z));
		//collection.insertOne(document);
		System.out.println(x);
		System.out.println(y);
		System.out.println(z);
		System.out.println();
	}

	private void voiceSensor() {
		String voiceSensor = input.nextLine();
		//MongoCollection<Document> collection = database.getCollection("voiceSensor");
		//Document document = new Document("suono",Integer.parseInt(voiceSensor));
		//collection.insertOne(document);
		System.out.println(voiceSensor);
		System.out.println();
	}


}
