package fe;

import CenterInterfaceFE.InterfaceCenterPOA;
import util.ByteHelper;
import util.FeHM;
import util.FeUDP2Ask;
import util.FeUdp2Ask;
import util.FeUdp3Ask;
import util.FeUdpAsk;
import util.Request;
import util.UdpCommand;

public class FeServer2 extends  InterfaceCenterPOA
{
	
	public String createTRecord(String suc, String mi, String fn, String ln, String ad, String ph, String sp, String lo)
	{
		String answer=null;
		
		int reqID=FeHM.getOwnRequestNo();
		int sucno=Integer.parseInt(suc.trim());
		
		UdpCommand uc=new UdpCommand();
		uc.setOperationID(0);
		uc.setManagerID(mi);
		uc.setFirstName(fn);
		uc.setLastName(ln);
		uc.setAddress(ad);
		uc.setPhoneNo(ph);
		uc.setSpecialization(sp);
		uc.setLocation(lo);
		
		Request req=new Request(sucno, reqID, uc);
		
		//FeHM.requestPool.put(reqID, req);
		
		ByteHelper bh=new ByteHelper();
		byte[] byy=bh.toByteArray(req);
		
		FeUdpAsk.udpAsk(byy, 35000);
		FeUdpAsk.udpAsk(byy, 35001);
		
		FeHM.executeLock.lock();
		
		FeHM.executeResult[0]="";
		FeHM.executeResult[1]="";
		FeHM.executeResult[2]="";
		FeHM.canuseresult[0]=false;
		FeHM.canuseresult[1]=false;
		FeHM.canuseresult[2]=false;
		
		FeUdp2Ask.udpAsk("next"+reqID, 36000);
		FeUdp2Ask.udpAsk("next"+reqID, 36001);
		
		
		
		while(true)
		{
			System.out.println(FeHM.canuseresult[0]+"~~~~~~~~"+FeHM.canuseresult[1]);
			if(FeHM.canuseresult[0]==true&&FeHM.canuseresult[1]==true)// hou xu chu li
			{
				answer=FeHM.executeResult[1].trim();
				
				if(sucno==0)
				{
					
				}
				if(sucno==1)
				{
					System.out.println(FeUdp3Ask.udpAsk("error"+mi.trim().substring(0, 3), 30011));
				}
				if(sucno==2)
				{
					System.out.println(FeUdp3Ask.udpAsk("reboot"+mi.trim().substring(0, 3), 30011));
				}
				
				FeHM.canuseresult[0]=false;
				FeHM.canuseresult[1]=false;
				FeHM.canuseresult[2]=false;
				break;
			}
		}
		
		FeHM.executeLock.unlock();
		
		return answer;
	}

	
	public String createSRecord(String suc, String mi, String fn, String ln, boolean s, String[] CourseRegistered)
	{
		String answer=null;
		
		int reqID=FeHM.getOwnRequestNo();
		int sucno=Integer.parseInt(suc.trim());
		
		UdpCommand uc=new UdpCommand();
		uc.setOperationID(1);
		uc.setManagerID(mi);
		uc.setFirstName(fn);
		uc.setLastName(ln);
		uc.setActiveStatus(s);
		uc.setCourseRegistered(CourseRegistered);
		
		Request req=new Request(sucno, reqID, uc);
		
		//FeHM.requestPool.put(reqID, req);
		
		ByteHelper bh=new ByteHelper();
		byte[] byy=bh.toByteArray(req);
		
		FeUdpAsk.udpAsk(byy, 35000);
		FeUdpAsk.udpAsk(byy, 35001);
		
		FeHM.executeLock.lock();
		
		FeHM.executeResult[0]="";
		FeHM.executeResult[1]="";
		FeHM.executeResult[2]="";
		FeHM.canuseresult[0]=false;
		FeHM.canuseresult[1]=false;
		FeHM.canuseresult[2]=false;
		
		FeUdp2Ask.udpAsk("next"+reqID, 36000);
		FeUdp2Ask.udpAsk("next"+reqID, 36001);
		
		while(true)
		{
			System.out.println(FeHM.canuseresult[0]+"~~~~~~~~"+FeHM.canuseresult[1]);
			if(FeHM.canuseresult[0]==true&&FeHM.canuseresult[1]==true)// hou xu chu li
			{
				answer=FeHM.executeResult[1].trim();
				
				if(sucno==0)
				{
					
				}
				if(sucno==1)
				{
					System.out.println(FeUdp3Ask.udpAsk("error"+mi.trim().substring(0, 3), 30011));
				}
				if(sucno==2)
				{
					System.out.println(FeUdp3Ask.udpAsk("reboot"+mi.trim().substring(0, 3), 30011));
				}
				
				FeHM.canuseresult[0]=false;
				FeHM.canuseresult[1]=false;
				FeHM.canuseresult[2]=false;
				break;
			}
		}
		
		FeHM.executeLock.unlock();
		
		return answer;
	}

	
	public String getRecordCounts(String suc, String mi)
	{
		String answer=null;
		
		int reqID=FeHM.getOwnRequestNo();
		int sucno=Integer.parseInt(suc.trim());
		
		UdpCommand uc=new UdpCommand();
		uc.setOperationID(2);
		uc.setManagerID(mi);
		
		Request req=new Request(sucno, reqID, uc);
		
		
		ByteHelper bh=new ByteHelper();
		byte[] byy=bh.toByteArray(req);
		
		
		
		FeUdpAsk.udpAsk(byy, 35000);
		FeUdpAsk.udpAsk(byy, 35001);
		
		FeHM.executeLock.lock();
		
		FeHM.executeResult[0]="";
		FeHM.executeResult[1]="";
		FeHM.executeResult[2]="";
		FeHM.canuseresult[0]=false;
		FeHM.canuseresult[1]=false;
		FeHM.canuseresult[2]=false;
		
		FeUdp2Ask.udpAsk("next"+reqID, 36000);
		FeUdp2Ask.udpAsk("next"+reqID, 36001);
		
		while(true)
		{
			
			System.out.println(FeHM.canuseresult[0]+"~~~~~~~~"+FeHM.canuseresult[1]);
			
			
			if(FeHM.canuseresult[0]==true&&FeHM.canuseresult[1]==true)// hou xu chu li
			{
				//System.out.println("fehere!!!!!!!!!!!!!");
				answer=FeHM.executeResult[1].trim();
				
				if(sucno==0)
				{
					
				}
				if(sucno==1)
				{
					System.out.println(FeUdp3Ask.udpAsk("error"+mi.trim().substring(0, 3), 30011));
				}
				if(sucno==2)
				{
					System.out.println(FeUdp3Ask.udpAsk("reboot"+mi.trim().substring(0, 3), 30011));
				}
				
				FeHM.canuseresult[0]=false;
				FeHM.canuseresult[1]=false;
				FeHM.canuseresult[2]=false;
				break;
			}
		}
		
		FeHM.executeLock.unlock();
		
		return answer;
	}

	
	public String editRecord(String suc, String mi, String recordID, String fieldname, String[] newvalue)
	{
		String answer=null;
		
		int reqID=FeHM.getOwnRequestNo();
		int sucno=Integer.parseInt(suc.trim());
		
		UdpCommand uc=new UdpCommand();
		uc.setOperationID(3);
		uc.setManagerID(mi);
        uc.setRecordID(recordID);
        uc.setFieldName(fieldname);
        uc.setNewValue(newvalue);
		
		Request req=new Request(sucno, reqID, uc);
		
		//FeHM.requestPool.put(reqID, req);
		
		ByteHelper bh=new ByteHelper();
		byte[] byy=bh.toByteArray(req);
		
		FeUdpAsk.udpAsk(byy, 35000);
		FeUdpAsk.udpAsk(byy, 35001);
		
		FeHM.executeLock.lock();
		
		FeHM.executeResult[0]="";
		FeHM.executeResult[1]="";
		FeHM.executeResult[2]="";
		FeHM.canuseresult[0]=false;
		FeHM.canuseresult[1]=false;
		FeHM.canuseresult[2]=false;
		
		FeUdp2Ask.udpAsk("next"+reqID, 36000);
		FeUdp2Ask.udpAsk("next"+reqID, 36001);
		
		while(true)
		{
			System.out.println(FeHM.canuseresult[0]+"~~~~~~~~"+FeHM.canuseresult[1]);
			if(FeHM.canuseresult[0]==true&&FeHM.canuseresult[1]==true)// hou xu chu li
			{
				answer=FeHM.executeResult[1].trim();
				
				if(sucno==0)
				{
					
				}
				if(sucno==1)
				{
					System.out.println(FeUdp3Ask.udpAsk("error"+mi.trim().substring(0, 3), 30011));
				}
				if(sucno==2)
				{
					System.out.println(FeUdp3Ask.udpAsk("reboot"+mi.trim().substring(0, 3), 30011));
				}
				
				FeHM.canuseresult[0]=false;
				FeHM.canuseresult[1]=false;
				FeHM.canuseresult[2]=false;
				break;
			}
		}
		
		FeHM.executeLock.unlock();
		
		return answer;
	}

	
	public String MtransferRecord(String suc, String mid, String rid, String remoteName)
	{
		String answer=null;
		
		int reqID=FeHM.getOwnRequestNo();
		int sucno=Integer.parseInt(suc.trim());
		
		UdpCommand uc=new UdpCommand();
		uc.setOperationID(4);
		uc.setManagerID(mid);
		uc.setRecordID(rid);
		uc.setRemoteName(remoteName);
		
		Request req=new Request(sucno, reqID, uc);
		
		//FeHM.requestPool.put(reqID, req);
		
		ByteHelper bh=new ByteHelper();
		byte[] byy=bh.toByteArray(req);
		
		FeUdpAsk.udpAsk(byy, 35000);
		FeUdpAsk.udpAsk(byy, 35001);
		
		FeHM.executeLock.lock();
		
		FeHM.executeResult[0]="";
		FeHM.executeResult[1]="";
		FeHM.executeResult[2]="";
		FeHM.canuseresult[0]=false;
		FeHM.canuseresult[1]=false;
		FeHM.canuseresult[2]=false;
		
		FeUdp2Ask.udpAsk("next"+reqID, 36000);
		FeUdp2Ask.udpAsk("next"+reqID, 36001);
		
		while(true)
		{
			System.out.println(FeHM.canuseresult[0]+"~~~~~~~~"+FeHM.canuseresult[1]);
			if(FeHM.canuseresult[0]==true&&FeHM.canuseresult[1]==true)// hou xu chu li
			{
				answer=FeHM.executeResult[1].trim();
				
				if(sucno==0)
				{
					
				}
				if(sucno==1)
				{
					System.out.println(FeUdp3Ask.udpAsk("error"+mid.trim().substring(0, 3), 30011));
				}
				if(sucno==2)
				{
					System.out.println(FeUdp3Ask.udpAsk("reboot"+mid.trim().substring(0, 3), 30011));
				}
				
				FeHM.canuseresult[0]=false;
				FeHM.canuseresult[1]=false;
				FeHM.canuseresult[2]=false;
				break;
			}
		}
		
		FeHM.executeLock.unlock();
		
		return answer;
	}

	
	public void print(String mi)
	{
				
	}

	
}
