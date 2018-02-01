package server;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import CenterInterface.InterfaceCenter;
import CenterInterface.InterfaceCenterHelper;
import util.BackupDatabase;
import util.ByteHelper;
import util.Meta;
import util.UdpCommand;

public class UdpTaskThread implements Runnable
{

	private int pport;
	
	private static UdpCommand thisudptask;
	
	public static UdpCommand getThisTask()
	{
		return thisudptask;
	}
	
	public UdpTaskThread(int port)
	{
		this.pport=port;
	}
	
	public void run()
	{
		DatagramSocket serverSocket = null;
		
		try {
			serverSocket = new DatagramSocket(pport);
			
			while (true) 
			{
				byte[] receiveData = new byte[4096];
				byte[] sendData = new byte[4096];
				byte[] taskby=null;

				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				
				taskby=receivePacket.getData();// get
				//System.out.println(taskby.length);

				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();// get port

				String answer = null;

				//do task ,fill in answer
				ByteHelper bh=new ByteHelper();
				UdpCommand udptask=(UdpCommand)bh.toObject(taskby);
				
                this.thisudptask=udptask;
				
				if(udptask.getOperationID()==0)//createT
				{
					String[] argsc=new String[4];
					argsc[0] = "-ORBInitialPort";
					if(udptask.getManagerID().trim().charAt(0)=='m'||udptask.getManagerID().trim().charAt(0)=='M')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoMTL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='l'||udptask.getManagerID().trim().charAt(0)=='L')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoLVL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='d'||udptask.getManagerID().trim().charAt(0)=='D')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoDDO).trim();
					}					
					argsc[2] = "-ORBInitialHost";
					argsc[3] = "localhost";
					ORB orbc = ORB.init(argsc, null); 
					org.omg.CORBA.Object objRefc = orbc.resolve_initial_references("NameService");
					NamingContextExt ncRefc = NamingContextExtHelper.narrow(objRefc);
					InterfaceCenter cs = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRefc.resolve_str("CenterServer"+udptask.getManagerID().trim().substring(0, 3).toUpperCase().trim()));
					String result = cs.createTRecord(udptask.getManagerID().trim(), udptask.getFirstName().trim(), udptask.getLastName().trim(), udptask.getAdress().trim(), udptask.getPhoneNo().trim(), udptask.getSpecialization().trim(), udptask.getLocation().trim());
					
					answer=result;
				}
				
				if(udptask.getOperationID()==1)//createS
				{
					String[] argsc=new String[4];
					argsc[0] = "-ORBInitialPort";
					if(udptask.getManagerID().trim().charAt(0)=='m'||udptask.getManagerID().trim().charAt(0)=='M')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoMTL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='l'||udptask.getManagerID().trim().charAt(0)=='L')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoLVL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='d'||udptask.getManagerID().trim().charAt(0)=='D')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoDDO).trim();
					}					
					argsc[2] = "-ORBInitialHost";
					argsc[3] = "localhost";
					ORB orbc = ORB.init(argsc, null); 
					org.omg.CORBA.Object objRefc = orbc.resolve_initial_references("NameService");
					NamingContextExt ncRefc = NamingContextExtHelper.narrow(objRefc);
					InterfaceCenter cs = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRefc.resolve_str("CenterServer"+udptask.getManagerID().trim().substring(0, 3).toUpperCase().trim()));
					String result = cs.createSRecord(udptask.getManagerID().trim(), udptask.getFirstName().trim(), udptask.getLastName().trim(), udptask.getActiveStatus(), udptask.getCourseRegistered());
					
					answer=result;
				}
				
				if(udptask.getOperationID()==2)//record Counts
				{
					String[] argsc=new String[4];
					argsc[0] = "-ORBInitialPort";
					if(udptask.getManagerID().trim().charAt(0)=='m'||udptask.getManagerID().trim().charAt(0)=='M')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoMTL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='l'||udptask.getManagerID().trim().charAt(0)=='L')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoLVL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='d'||udptask.getManagerID().trim().charAt(0)=='D')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoDDO).trim();
					}					
					argsc[2] = "-ORBInitialHost";
					argsc[3] = "localhost";
					ORB orbc = ORB.init(argsc, null); 
					org.omg.CORBA.Object objRefc = orbc.resolve_initial_references("NameService");
					NamingContextExt ncRefc = NamingContextExtHelper.narrow(objRefc);
					InterfaceCenter cs = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRefc.resolve_str("CenterServer"+udptask.getManagerID().trim().substring(0, 3).toUpperCase().trim()));
					String result = cs.getRecordCounts(udptask.getManagerID().trim());
					
					answer=result;
				}
				
				if(udptask.getOperationID()==3)//editR
				{
					String[] argsc=new String[4];
					argsc[0] = "-ORBInitialPort";
					if(udptask.getManagerID().trim().charAt(0)=='m'||udptask.getManagerID().trim().charAt(0)=='M')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoMTL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='l'||udptask.getManagerID().trim().charAt(0)=='L')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoLVL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='d'||udptask.getManagerID().trim().charAt(0)=='D')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoDDO).trim();
					}					
					argsc[2] = "-ORBInitialHost";
					argsc[3] = "localhost";
					ORB orbc = ORB.init(argsc, null); 
					org.omg.CORBA.Object objRefc = orbc.resolve_initial_references("NameService");
					NamingContextExt ncRefc = NamingContextExtHelper.narrow(objRefc);
					InterfaceCenter cs = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRefc.resolve_str("CenterServer"+udptask.getManagerID().trim().substring(0, 3).toUpperCase().trim()));
					String result = cs.editRecord(udptask.getManagerID().trim(), udptask.getRecordID().trim(), udptask.getFieldName().trim(), udptask.getNewValue());
					
					answer=result;
				}
				
				if(udptask.getOperationID()==4)//Mtransfer
				{
					String[] argsc=new String[4];
					argsc[0] = "-ORBInitialPort";
					if(udptask.getManagerID().trim().charAt(0)=='m'||udptask.getManagerID().trim().charAt(0)=='M')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoMTL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='l'||udptask.getManagerID().trim().charAt(0)=='L')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoLVL).trim();
					}
					if(udptask.getManagerID().trim().charAt(0)=='d'||udptask.getManagerID().trim().charAt(0)=='D')
					{
						argsc[1] = Integer.toString(Meta.registerPortNoDDO).trim();
					}					
					argsc[2] = "-ORBInitialHost";
					argsc[3] = "localhost";
					ORB orbc = ORB.init(argsc, null); 
					org.omg.CORBA.Object objRefc = orbc.resolve_initial_references("NameService");
					NamingContextExt ncRefc = NamingContextExtHelper.narrow(objRefc);
					InterfaceCenter cs = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRefc.resolve_str("CenterServer"+udptask.getManagerID().trim().substring(0, 3).toUpperCase().trim()));
					String result = cs.MtransferRecord(udptask.getManagerID().trim(), udptask.getRecordID().trim(), udptask.getRemoteName().trim());
					
					answer=result;
				}
				//task end

				sendData = answer.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 25003);
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
