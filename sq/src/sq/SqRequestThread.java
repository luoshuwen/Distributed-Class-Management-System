package sq;

import util.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SqRequestThread implements Runnable
{

	private int pport;

	public SqRequestThread(int port)
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
				byte[] receiveData = new byte[8192];
				byte[] sendData=new byte[4096];
				
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				
				byte[] byy=receivePacket.getData();
				
                //new Thread(new ReceiveThread(byy,this.pport)).start();
				ByteHelper bh=new ByteHelper();
				Request req=(Request)bh.toObject(byy);
				
		        if(this.pport==35000)//rm1
		        {
		        	ReqHM.reqPool1.put(req.getReqNo(), req);
		        	//System.out.println(req.getReqNo()+"~~~~"+ReqHM.reqPool1.size());
		        }
		        
		        if(this.pport==35001)//rm2
		        {
		        	ReqHM.reqPool2.put(req.getReqNo(), req);
		        }
		        
		        if(this.pport==35002)//rm3
		        {
		        	ReqHM.reqPool3.put(req.getReqNo(), req);
		        }
		        
		        
             
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();// get port

				String answer = "ok";
				
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
