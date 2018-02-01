package util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FeUdpAsk
{
	public static void udpAsk(byte[] byy,int udpport)
	{
		DatagramSocket clientSocket=null;
		String receivedAnswer=null;
		try
		{
			clientSocket=new DatagramSocket();	
			byte[] receiveData=new byte[4096];
			InetAddress IPAddress=InetAddress.getByName("192.168.192.131");//InetAddress.getByName("hostname");

			while(true)
			{			
			byte[] sendData=byy;		
		
			DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,udpport);	

			clientSocket.send(sendPacket);			
			
			DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);

			clientSocket.receive(receivePacket);
			
			clientSocket.close();

			break;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			clientSocket.close();
		}

	}
}
