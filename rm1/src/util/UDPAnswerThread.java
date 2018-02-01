package util;
import java.util.*;
import java.net.*;
import java.io.*;

public class UDPAnswerThread implements Runnable {
	private int pport;

	public UDPAnswerThread(int port) {
		this.pport = port;
	}

	public void run() 
	{
		DatagramSocket serverSocket = null;
		try {
			serverSocket = new DatagramSocket(pport);
			while (true) {
				byte[] receiveData = new byte[1024];
				byte[] sendData = new byte[1024];

				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);

				String sentence = new String(receivePacket.getData());// get

				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();// get port

				String answer = null;
				if (sentence.charAt(0) == '~') {
					if(sentence.trim().charAt(1)=='m'||sentence.trim().charAt(1)=='M')
					{
						answer = "TR" + TeacherRecord.getTeacherRecordCount("MTL",true) + "SR"+ StudentRecord.getStudentRecordCount("MTL",true);
					}
					if(sentence.trim().charAt(1)=='l'||sentence.trim().charAt(1)=='L')
					{
						answer = "TR" + TeacherRecord.getTeacherRecordCount("LVL",true) + "SR"+ StudentRecord.getStudentRecordCount("LVL",true);
					}
					if(sentence.trim().charAt(1)=='d'||sentence.trim().charAt(1)=='D')
					{
						answer = "TR" + TeacherRecord.getTeacherRecordCount("DDO",true) + "SR"+ StudentRecord.getStudentRecordCount("DDO",true);
					}
				}
				if (sentence.trim().length() >5)// delete
				{
					String rid = sentence.trim().substring(0, 7);
					
					if(sentence.trim().charAt(7)=='m'||sentence.trim().charAt(7)=='M')
					{
						HM.canunlock3[Integer.parseInt(rid.substring(2, 7))] = true;
					}
					if(sentence.trim().charAt(7)=='l'||sentence.trim().charAt(7)=='L')
					{
						HM.canunlock2[Integer.parseInt(rid.substring(2, 7))] = true;
						
					}
					if(sentence.trim().charAt(7)=='d'||sentence.trim().charAt(7)=='D')
					{
						HM.canunlock1[Integer.parseInt(rid.substring(2, 7))] = true;
					}
					
					answer = rid + "false";
				}
				sendData = answer.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
	}

}
