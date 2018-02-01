package util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PrintThread implements Runnable
{
	private int pport;

	public PrintThread(int port)
	{
		this.pport = port;
	}

	public void run()
	{

		DatagramSocket serverSocket = null;
		try
		{
			serverSocket = new DatagramSocket(pport);
			while (true)
			{
				byte[] receiveData = new byte[4096];
				byte[] sendData = new byte[4096];

				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);

				String sentence = new String(receivePacket.getData());// get

				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();// get port

				String answer = "OK~~~";

				PrintDatabase.backupDB("MTL");
				PrintDatabase.backupDB("LVL");
				PrintDatabase.backupDB("DDO");

				sendData = answer.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);

			}
		} 
		catch (Exception e)
		{

			e.printStackTrace();
		} 
		finally
		{
			serverSocket.close();
		}
	}
}
