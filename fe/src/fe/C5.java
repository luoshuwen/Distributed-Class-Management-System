package fe;
import util.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class C5
{
	//execute an operation
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
		
				
		
			
		
		
		
		UdpCommand u1=new UdpCommand();
		u1.setOperationID(1);//createT
		u1.setManagerID("LVL0102");
		u1.setFirstName("lll");
		u1.setLastName("sw");
		u1.setActiveStatus(false);
		String[] cr= {"mmp","cnm"};
		u1.setCourseRegistered(cr);		
		
		ByteHelper bh1=new ByteHelper();
		byte[] by1=bh1.toByteArray(u1);
		//System.out.println(by.length);		
		
		byte[] ans11=FeUDP2Ask.udpAsk(by1, 20011);
		byte[] ans21=FeUDP2Ask.udpAsk(by1, 20012);
		
		System.out.println(new String(ans11));
		System.out.println(new String(ans21));
		
		
		
		

		
		
		
		UdpCommand u2=new UdpCommand();
		u2.setOperationID(2);//get
		u2.setManagerID("DDO0102");		
		
		ByteHelper bh2=new ByteHelper();
		byte[] by2=bh2.toByteArray(u2);
		//System.out.println(by.length);		
		
		byte[] ans12=FeUDP2Ask.udpAsk(by2, 20011);
		byte[] ans22=FeUDP2Ask.udpAsk(by2, 20012);
		
		System.out.println(new String(ans12));
		System.out.println(new String(ans22));
		
		
			
		
		

		
		
		
		
		
		
		UdpCommand u3=new UdpCommand();
		u3.setOperationID(3);//get
		u3.setManagerID("MTL1234");	
		u3.setRecordID("TR00009");
		u3.setFieldName("address");
		String[] nv= {"dgkglsgdfdgh"};
		u3.setNewValue(nv);
		
		ByteHelper bh3=new ByteHelper();
		byte[] by3=bh3.toByteArray(u3);
		//System.out.println(by.length);
		
		
		byte[] ans13=FeUDP2Ask.udpAsk(by3, 20011);
		byte[] ans23=FeUDP2Ask.udpAsk(by3, 20012);
		
		System.out.println(new String(ans13));
		System.out.println(new String(ans23));
		
		
		
		
		

		
		
		
		
		
		
		
		UdpCommand u4=new UdpCommand();
		u4.setOperationID(4);//get
		u4.setManagerID("LVL1234");	
		u4.setRecordID("TR30002");
		u4.setRemoteName("DDO");
		
		ByteHelper bh4=new ByteHelper();
		byte[] by4=bh4.toByteArray(u4);
		//System.out.println(by.length);
		
		
		byte[] ans14=FeUDP2Ask.udpAsk(by4, 20011);
		byte[] ans24=FeUDP2Ask.udpAsk(by4, 20012);
		
		System.out.println(new String(ans14));
		System.out.println(new String(ans24));
		
		
		
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
			String sentence ="rebootLVL";		
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
