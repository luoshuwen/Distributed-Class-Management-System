package fe;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import util.ByteHelper;
import util.FeUDP2Ask;
import util.UdpCommand;

public class C6
{
	public static void main(String[] args)
	{
		UdpCommand u0=new UdpCommand();
		u0.setOperationID(0);//createT
		u0.setManagerID("MTL0102");
		u0.setFirstName("lll");
		u0.setLastName("sw");
		u0.setAddress("sjkfhdjszhdjshf");
		u0.setPhoneNo("564552454");
		u0.setSpecialization("cs");
		u0.setLocation("mtl");
		
		ByteHelper bh0=new ByteHelper();
		byte[] by0=bh0.toByteArray(u0);
		//System.out.println(by.length);		
		
		byte[] ans10=FeUDP2Ask.udpAsk(by0, 20011);
		byte[] ans20=FeUDP2Ask.udpAsk(by0, 20012);
		
		System.out.println(new String(ans10));
		System.out.println(new String(ans20));
		
				
		
		
		String receivedAnswer=null;
		DatagramSocket clientSocket=null;
		try
		{
			clientSocket=new DatagramSocket();			
			InetAddress IPAddress=InetAddress.getByName("192.168.192.131");//InetAddress.getByName("hostname");

			while(true)
			{			 
			byte[] sendData=new byte[1024];
			byte[] receiveData=new byte[1024];			
			String sentence ="rebootMTL";		
			sendData=sentence.getBytes();			
			DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,IPAddress,30011);	

			clientSocket.send(sendPacket);			
			
			System.out.println("e~ has sent out!");
			
			DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length);

			clientSocket.receive(receivePacket);
			
			receivedAnswer=new String(receivePacket.getData());	
			System.out.println(receivedAnswer);
			clientSocket.close();
			System.out.println("From Server:"+receivedAnswer);
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
