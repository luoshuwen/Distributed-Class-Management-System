package util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FeUdp2Ask
{
	public static void udpAsk(String msg,int udpport)
	{
		DatagramSocket clientSocket=null;
		try
		{
			clientSocket=new DatagramSocket();			
			InetAddress IPAddress=InetAddress.getByName("192.168.192.131");//InetAddress.getByName("hostname");

			while(true)
			{			
			byte[] sendData=msg.trim().getBytes();		
		
			DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,udpport);	

			clientSocket.send(sendPacket);			
			
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
