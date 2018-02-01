package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import CenterInterface.*;
import util.*;

public class RmUdpControl implements Runnable
{
	private int pport;
	private boolean flag=false;//true for bytes

	public RmUdpControl(int port)
	{
		this.pport = port;
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

				byte[] answerbyte=new byte[1024*50];//50kb
				
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);//block

				String sentence = new String(receivePacket.getData());// get

				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();// get port

				String answer = null;
				
				
				
				if(sentence.trim().charAt(0)=='m'||sentence.trim().charAt(0)=='M')//ask state
				{
					int oid=Integer.parseInt(sentence.trim().substring(3, 4));
					String rid=sentence.trim().substring(4, sentence.trim().length());
					
					if(oid==0)
					{
						answer=Integer.toString(TeacherRecord.countNo3-1);
					}
					
					if(oid==1)
					{
						answer=Integer.toString(StudentRecord.countNo3-1);
					}
					
					if(oid==2)
					{
						this.flag=true;//return byte[]
						ByteHelper bh=new ByteHelper();
						HashMap temps=new HashMap();
						temps.put("MTL", HM.getHm("MTL"));
						temps.put("LVL", HM.getHm("LVL"));
						temps.put("DDO", HM.getHm("DDO"));
						answerbyte=bh.toByteArray(temps);
					}
					
					if(oid==3||oid==4)
					{
						this.flag=true;//return byte[]
						ByteHelper bh=new ByteHelper();
						if(rid.trim().charAt(0)=='T')//teacher
						{
							answerbyte=bh.toByteArray(BackupOriginal.btr);
						}
						if(rid.trim().charAt(0)=='S')//stu
						{
							answerbyte=bh.toByteArray(BackupOriginal.bsr);
						}						
					}

				}
				if(sentence.trim().charAt(0)=='l'||sentence.trim().charAt(0)=='L')//ask state
				{
					int oid=Integer.parseInt(sentence.trim().substring(3, 4));
					String rid=sentence.trim().substring(4, sentence.trim().length());
					
					if(oid==0)
					{
						answer=Integer.toString(TeacherRecord.countNo2-1);
					}
					
					if(oid==1)
					{
						answer=Integer.toString(StudentRecord.countNo2-1);
					}
					
					if(oid==2)
					{
						this.flag=true;//return byte[]
						ByteHelper bh=new ByteHelper();
						HashMap temps=new HashMap();
						temps.put("MTL", HM.getHm("MTL"));
						temps.put("LVL", HM.getHm("LVL"));
						temps.put("DDO", HM.getHm("DDO"));
						answerbyte=bh.toByteArray(temps);
					}
					
					if(oid==3||oid==4)
					{
						this.flag=true;//return byte[]
						ByteHelper bh=new ByteHelper();
						if(rid.trim().charAt(0)=='T')//teacher
						{
							answerbyte=bh.toByteArray(BackupOriginal.btr);
						}
						if(rid.trim().charAt(0)=='S')//stu
						{
							answerbyte=bh.toByteArray(BackupOriginal.bsr);
						}						
					}
				}
				if(sentence.trim().charAt(0)=='d'||sentence.trim().charAt(0)=='D')//ask state
				{
					int oid=Integer.parseInt(sentence.trim().substring(3, 4));
					String rid=sentence.trim().substring(4, sentence.trim().length());
					
					if(oid==0)
					{
						answer=Integer.toString(TeacherRecord.countNo1-1);
					}
					
					if(oid==1)
					{
						answer=Integer.toString(StudentRecord.countNo1-1);
					}
					
					if(oid==2)
					{
						
						this.flag=true;//return byte[]
						ByteHelper bh=new ByteHelper();
						HashMap temps=new HashMap();
						temps.put("MTL", HM.getHm("MTL"));
						temps.put("LVL", HM.getHm("LVL"));
						temps.put("DDO", HM.getHm("DDO"));
						//System.out.println(HM.getHm("MTL").size()+"~"+HM.getHm("LVL").size()+"~"+HM.getHm("DDO").size()+"~"+temps.size()+"~");
						answerbyte=bh.toByteArray(temps);
						
					}
					
					if(oid==3||oid==4)
					{
						this.flag=true;//return byte[]
						ByteHelper bh=new ByteHelper();
						if(rid.trim().charAt(0)=='T')//teacher
						{
							answerbyte=bh.toByteArray(BackupOriginal.btr);
						}
						if(rid.trim().charAt(0)=='S')//stu
						{
							answerbyte=bh.toByteArray(BackupOriginal.bsr);
						}						
					}
				}
				
				
				
				if (sentence.trim().charAt(0)=='e') //errorXXX
				{
					answer=Recovery.recover();
				}
				
				if (sentence.trim().charAt(0)=='r')// rebootXXX
				{
					if(sentence.trim().charAt(6)=='m'||sentence.trim().charAt(6)=='M')
					{
						String okk=BackupDatabase.backupDB("MTL");
						
						RM.mtlorbth.orb.destroy();
						RM.mtlorbth=new orbThread("MTL~");
						new Thread(RM.mtlorbth).start();
						
						Thread.sleep(2000);
						
						InterfaceCenter iclvl=RM.lvlorbth.getICRef();
						iclvl.register(RM.mtlorbth.getICRef(), 3);
						System.out.println("mtl register itself to lvl ~");
						
						InterfaceCenter icddo=RM.ddoorbth.getICRef();
						icddo.register(RM.mtlorbth.getICRef(), 3);
						System.out.println("mtl register itself to ddo ~");
						
						answer="reboot MTL OK~"+Recovery.recover();
					}
					if(sentence.trim().charAt(6)=='l'||sentence.trim().charAt(6)=='L')
					{
						String okk=BackupDatabase.backupDB("LVL");
						
						RM.lvlorbth.orb.destroy();
						RM.lvlorbth=new orbThread("LVL~");
						new Thread(RM.lvlorbth).start();
						
						Thread.sleep(2000);
						
						InterfaceCenter icmtl=RM.mtlorbth.getICRef();
						icmtl.register(RM.lvlorbth.getICRef(), 2);
						System.out.println("lvl register itself to mtl ~");
						
						InterfaceCenter icddo=RM.ddoorbth.getICRef();
						icddo.register(RM.lvlorbth.getICRef(), 2);
						System.out.println("lvl register itself to ddo ~");
						
						answer="reboot LVL OK~"+Recovery.recover();
					}
					if(sentence.trim().charAt(6)=='d'||sentence.trim().charAt(6)=='D')
					{
						String okk=BackupDatabase.backupDB("DDO");
						
						RM.ddoorbth.orb.destroy();
						RM.ddoorbth=new orbThread("DDO~");
						new Thread(RM.ddoorbth).start();
						
						Thread.sleep(2000);
						
						InterfaceCenter icmtl=RM.mtlorbth.getICRef();
						icmtl.register(RM.ddoorbth.getICRef(), 1);
						System.out.println("ddo register itself to mtl ~");
						
						InterfaceCenter iclvl=RM.lvlorbth.getICRef();
						iclvl.register(RM.ddoorbth.getICRef(), 1);
						System.out.println("ddo register itself to lvl ~");
						
						answer="reboot DDO OK~"+Recovery.recover();
					}
				}
				
				DatagramPacket sendPacket=null;
				if(flag==false)
				{
					sendData = answer.getBytes();
					sendPacket= new DatagramPacket(sendData, sendData.length, IPAddress, port);
				}
				else
				{
					sendPacket= new DatagramPacket(answerbyte, answerbyte.length, IPAddress, port);
					this.flag=false;
				}
								 
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
