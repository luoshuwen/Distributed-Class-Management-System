package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
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
					if(sentence.trim().charAt(5)=='m'||sentence.trim().charAt(5)=='M')
					{
						HM.error[0]++;
						if(HM.error[0]==3)
						{
							answer=Recovery.recover();
							HM.error[0]=0;
						}
						else
						{
							answer="NotYet";
						}
					}
					if(sentence.trim().charAt(5)=='l'||sentence.trim().charAt(5)=='L')
					{
						HM.error[1]++;
						if(HM.error[1]==3)
						{
							answer=Recovery.recover();
							HM.error[1]=0;
						}
						else
						{
							answer="NotYet";
						}
					}
					if(sentence.trim().charAt(5)=='d'||sentence.trim().charAt(5)=='D')
					{
						HM.error[2]++;
						if(HM.error[2]==3)
						{
							answer=Recovery.recover();
							HM.error[2]=0;
						}
						else
						{
							answer="NotYet";
						}
					}
					
				}
				
				if (sentence.trim().charAt(0)=='r')// rebootXXX
				{
					if(sentence.trim().charAt(6)=='m'||sentence.trim().charAt(6)=='M')
					{
						String okk=BackupDatabase.backupDB("MTL");
						
						RM.mtlorbth.orb.destroy();
						RM.mtlorbth.orb=null;
						RM.mtlorbth=new orbThread("MTL~");
						new Thread(RM.mtlorbth).start();
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
						Log.writeLog(time, "MTL", UdpTaskThread.getThisTask().getManagerID().trim(), "Reboot_Server_MTL", "Successfully", "");
						
						Thread.sleep(2000);
						
						InterfaceCenter iclvl=RM.lvlorbth.getICRef();
						iclvl.register(RM.mtlorbth.getICRef(), 3);
						System.out.println("mtl register itself to lvl ~");
						
						InterfaceCenter icddo=RM.ddoorbth.getICRef();
						icddo.register(RM.mtlorbth.getICRef(), 3);
						System.out.println("mtl register itself to ddo ~");
						
						InterfaceCenter icmtl=RM.mtlorbth.getICRef();
						icmtl.register(iclvl, 2);
						icmtl.register(icddo, 1);
						
						//answer=Recovery.recover();
						
						answer="reboot MTL OK~"+Recovery.recover();
					}
					if(sentence.trim().charAt(6)=='l'||sentence.trim().charAt(6)=='L')
					{
						String okk=BackupDatabase.backupDB("LVL");
						
						RM.lvlorbth.orb.destroy();
						RM.lvlorbth.orb=null;
						RM.lvlorbth=new orbThread("LVL~");
						new Thread(RM.lvlorbth).start();
						
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
						Log.writeLog(time, "LVL", UdpTaskThread.getThisTask().getManagerID().trim(), "Reboot_Server_LVL", "Successfully", "");
						
						Thread.sleep(2000);
						
						
						InterfaceCenter icmtl=RM.mtlorbth.getICRef();
						icmtl.register(RM.lvlorbth.getICRef(), 2);
						System.out.println("lvl register itself to mtl ~");
						
						
						
						InterfaceCenter icddo=RM.ddoorbth.getICRef();
						icddo.register(RM.lvlorbth.getICRef(), 2);
						System.out.println("lvl register itself to ddo ~");
						
						InterfaceCenter iclvl=RM.lvlorbth.getICRef();
						iclvl.register(icmtl, 3);
						iclvl.register(icddo, 1);
						
						//answer=Recovery.recover();
						
						answer="reboot LVL OK~"+Recovery.recover();
					}
					if(sentence.trim().charAt(6)=='d'||sentence.trim().charAt(6)=='D')
					{
						String okk=BackupDatabase.backupDB("DDO");
						
						RM.ddoorbth.orb.destroy();
						RM.ddoorbth=new orbThread("DDO~");
						new Thread(RM.ddoorbth).start();
						
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
						Log.writeLog(time, "DDO", UdpTaskThread.getThisTask().getManagerID().trim(), "Reboot_Server_DDO", "Successfully", "");
						
						Thread.sleep(2000);
						
						InterfaceCenter icmtl=RM.mtlorbth.getICRef();
						icmtl.register(RM.ddoorbth.getICRef(), 1);
						System.out.println("ddo register itself to mtl ~");
						
						InterfaceCenter iclvl=RM.lvlorbth.getICRef();
						iclvl.register(RM.ddoorbth.getICRef(), 1);
						System.out.println("ddo register itself to lvl ~");
						
						InterfaceCenter icddo=RM.ddoorbth.getICRef();
						icddo.register(icmtl, 3);
						icddo.register(iclvl, 2);
						
						//answer=Recovery.recover();
						
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
