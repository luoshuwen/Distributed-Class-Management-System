package sq;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

import util.ByteHelper;

import util.ReceiveThread;
import util.ReqHM;
import util.Request;
import util.UdpCommand;

public class SqMsgThread implements Runnable
{
	int pport;
	
	public SqMsgThread(int port)
	{
		this.pport=port;
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
				
				String msg=new String(receiveData);
				int reqID=Integer.parseInt(msg.trim().substring(4, msg.trim().length()));
				
				if(this.pport==36000)
				{
					Request reqnow=(Request)ReqHM.reqPool1.get(reqID);
					
					//System.out.println(reqID+"~qqqqq~"+ReqHM.reqPool1.size());
					
					ReqHM.reqPool1.remove(reqID);
					
					UdpCommand udpc=reqnow.getUdpCommand();
					
					ByteHelper bh0=new ByteHelper();
					byte[] by0=bh0.toByteArray(udpc);

					SqUdpAsk.udpAsk(by0, 20011);
				}
				if(this.pport==36001)
				{
					Request reqnow=(Request)ReqHM.reqPool2.get(reqID);
					ReqHM.reqPool2.remove(reqID);
					
					UdpCommand udpc=reqnow.getUdpCommand();
					
					ByteHelper bh0=new ByteHelper();
					byte[] by0=bh0.toByteArray(udpc);

					SqUdpAsk.udpAsk(by0, 20012);
				}
				if(this.pport==36002)
				{
					Request reqnow=(Request)ReqHM.reqPool3.get(reqID);
					ReqHM.reqPool3.remove(reqID);
					
					UdpCommand udpc=reqnow.getUdpCommand();
					
					ByteHelper bh0=new ByteHelper();
					byte[] by0=bh0.toByteArray(udpc);

					SqUdpAsk.udpAsk(by0, 20013);
				}
				
//				int min=65535;
//				if(this.pport==36000)
//				{
//					//currentPool=ReqHM.reqPool1;
//					for(Object obj:ReqHM.reqPool1.keySet())
//					{
//						if( Integer.parseInt(obj.toString().trim())<min  )
//						{
//							min=Integer.parseInt(obj.toString().trim());
//						}
//					}
//					if(min<65535)
//					{
//						Request reqmin=(Request)ReqHM.reqPool1.get(min);
//						ReqHM.reqPool1.remove(min);
//						
//						UdpCommand udpc=reqmin.getUdpCommand();
//						
//						ByteHelper bh0=new ByteHelper();
//						byte[] by0=bh0.toByteArray(udpc);
//
//						SqUdpAsk.udpAsk(by0, 20011);
//					}
//				}
//				if(this.pport==36001)
//				{
//					for(Object obj:ReqHM.reqPool2.keySet())
//					{
//						if( Integer.parseInt(obj.toString().trim())<min  )
//						{
//							min=Integer.parseInt(obj.toString().trim());
//						}
//					}
//					if(min<65535)
//					{
//						Request reqmin=(Request)ReqHM.reqPool2.get(min);
//						ReqHM.reqPool2.remove(min);
//						
//						UdpCommand udpc=reqmin.getUdpCommand();
//						
//						ByteHelper bh0=new ByteHelper();
//						byte[] by0=bh0.toByteArray(udpc);
//
//						SqUdpAsk.udpAsk(by0, 20012);
//					}
//				}
//				if(this.pport==36002)
//				{
//
//				}
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
