package util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpAskThreadBy
{
	public static byte[] udpAsk(String s,int udpport)
	{
		//System.out.println(s+"A"+udpport);
		//int udpport=30008;
		byte[] receivedAnswer=null;
		DatagramSocket clientSocket=null;
		try
		{
			clientSocket=new DatagramSocket();			
			InetAddress IPAddress=InetAddress.getByName("192.168.192.131");//InetAddress.getByName("hostname");

			while(true)
			{			
			byte[] sendData=new byte[1024*50];
			byte[] receiveData=new byte[1024*50];			
			String sentence =s;		
			sendData=sentence.getBytes();			
			DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,udpport);	
			//System.out.println("mmp");
			clientSocket.send(sendPacket);			
			
			DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);

			clientSocket.receive(receivePacket);
			
			receivedAnswer=receivePacket.getData();	
			
			clientSocket.close();
			//System.out.println("From Server:"+receivedAnswer);
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
		return receivedAnswer;
	}
}
