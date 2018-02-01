package sq;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SqUdpAsk
{
	public static void udpAsk(byte[] byy,int udpport)
	{
		DatagramSocket clientSocket=null;
		//byte[] answer=new byte[1024];
		try
		{
			clientSocket=new DatagramSocket();			
			InetAddress IPAddress=InetAddress.getByName("192.168.192.131");//InetAddress.getByName("hostname");

			while(true)
			{			
			byte[] sendData=byy;		
		
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
