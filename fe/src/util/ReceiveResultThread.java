package util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveResultThread implements Runnable
{

	private int pport;

	public ReceiveResultThread(int port)
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

				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);

				//System.out.println((new String(receiveData)).trim()+"ok~~~"+this.pport);
				
				String sentence = new String(receivePacket.getData());// get
				
				
				//System.out.println(sentence.trim()+"~~~"+this.pport);

				if(this.pport==25001)//rm1
				{
					FeHM.executeResult[0]=sentence.trim();
					FeHM.canuseresult[0]=true;
					//System.out.println(25001+"trueeeeeeeee"+FeHM.canuseresult[0]+"~~~"+FeHM.canuseresult[1]);
				}
				
				if(this.pport==25002)//rm2
				{
					FeHM.executeResult[1]=sentence.trim();
					FeHM.canuseresult[1]=true;
					//System.out.println(25002+"trueeeeeeeee"+FeHM.canuseresult[0]+"~~~"+FeHM.canuseresult[1]);
				}
				
				if(this.pport==25003)//rm3
				{
					FeHM.executeResult[2]=sentence.trim();
					FeHM.canuseresult[2]=true;
				}
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
