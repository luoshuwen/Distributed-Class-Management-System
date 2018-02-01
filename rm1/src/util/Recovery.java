package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import CenterInterface.InterfaceCenter;
import CenterInterface.InterfaceCenterHelper;
import server.UdpTaskThread;

public class Recovery
{
	public static String recover()
	{
		String result="not initialized";
		//get operationID
		int oid=UdpTaskThread.getThisTask().getOperationID();
		String mid=UdpTaskThread.getThisTask().getManagerID().trim();
		UdpTaskThread.getThisTask().setSucno(0);
		String rid="";
		if(oid==3||oid==4)
		{
			rid=UdpTaskThread.getThisTask().getRecordID().trim();
		}
		
		
		if(rid==null||rid.trim().length()==0)
		{
			rid="0";
		}
		
		
		
		if(oid==0)//createT
		{
			String s=mid.trim().substring(0, 3)+oid+rid;
			byte[] by=UdpAskThreadBy.udpAsk(s.trim(), Meta.rm2ReturnStatusUdp);//rm2
			
			if(mid.trim().charAt(0)=='m'||mid.trim().charAt(0)=='M')
			{
				TeacherRecord.countNo3= Integer.parseInt((new String(by)).trim());
			}
			if(mid.trim().charAt(0)=='l'||mid.trim().charAt(0)=='L')
			{
				TeacherRecord.countNo2= Integer.parseInt((new String(by)).trim());
			}
			if(mid.trim().charAt(0)=='d'||mid.trim().charAt(0)=='D')
			{
				TeacherRecord.countNo1= Integer.parseInt((new String(by)).trim());
			}
			
		}
		
		if(oid==1)//createS
		{
			String s=mid.trim().substring(0, 3)+oid+rid;
			byte[] by=UdpAskThreadBy.udpAsk(s.trim(), Meta.rm2ReturnStatusUdp);//rm2
			
			if(mid.trim().charAt(0)=='m'||mid.trim().charAt(0)=='M')
			{
				StudentRecord.countNo3= Integer.parseInt((new String(by)).trim());
			}
			if(mid.trim().charAt(0)=='l'||mid.trim().charAt(0)=='L')
			{
				StudentRecord.countNo2= Integer.parseInt((new String(by)).trim());
			}
			if(mid.trim().charAt(0)=='d'||mid.trim().charAt(0)=='D')
			{
				StudentRecord.countNo1= Integer.parseInt((new String(by)).trim());
			}
		}
		
		if(oid==2)//getRecordC
		{
			String s=mid.trim().substring(0, 3)+oid+rid;
			byte[] by=UdpAskThreadBy.udpAsk(s.trim(), Meta.rm2ReturnStatusUdp);//rm2
			//System.out.println(by.length+"~~~~~~~~~~");
			ByteHelper bHelper=new ByteHelper();
			HashMap hmother = (HashMap)bHelper.toObject(by);
			
			HM.setHm("MTL", (HashMap)hmother.get("MTL"));
			HM.setHm("LVL", (HashMap)hmother.get("LVL"));
			HM.setHm("DDO", (HashMap)hmother.get("DDO"));
		}
		
		if(oid==3||oid==4)//editRecord
		{
			String s=mid.trim().substring(0, 3)+oid+rid;
			byte[] by=UdpAskThreadBy.udpAsk(s.trim(), Meta.rm2ReturnStatusUdp);//rm2
			
			ByteHelper bHelper=new ByteHelper();
			if(rid.trim().charAt(0)=='T')
			{
				TeacherRecord otr=(TeacherRecord)bHelper.toObject(by);
				HashMap hm = HM.getHm(mid);
				HashMap temp = (HashMap) hm.get(Character.toString(Integer.valueOf(otr.getLastname().charAt(0)) > 96 ? Character.toUpperCase(otr.getLastname().charAt(0)) : otr.getLastname().charAt(0)));
				temp.put(otr.getNo().trim(), otr);
				hm.put(Character.toString(Integer.valueOf(otr.getLastname().charAt(0)) > 96 ? Character.toUpperCase(otr.getLastname().charAt(0)) : otr.getLastname().charAt(0)), temp);
			}
			else
			{
				StudentRecord osr=(StudentRecord)bHelper.toObject(by);
				HashMap hm = HM.getHm(mid);
				HashMap temp = (HashMap) hm.get(Character.toString(Integer.valueOf(osr.getLastName().charAt(0)) > 96 ? Character.toUpperCase(osr.getLastName().charAt(0)) : osr.getLastName().charAt(0)));
				temp.put(osr.getNo().trim(), osr);
				hm.put(Character.toString(Integer.valueOf(osr.getLastName().charAt(0)) > 96 ? Character.toUpperCase(osr.getLastName().charAt(0)) : osr.getLastName().charAt(0)), temp);
			}
		}

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
		String time = sdf.format(d);
		String opera="",ridd="";
		if(oid==0)
		{
			opera="Create_Teacher_Record";
		}
		if(oid==1)
		{
			opera="Create_Student_Record";
		}
		if(oid==2)
		{
			opera="Get_Record_Counts";
		}
		if(oid==3)
		{
			opera="Edit_Record";
		}
		if(oid==4)
		{
			opera="Transfer_Record";
		}
		if(oid==3||oid==4)
		{
			ridd=rid;
		}
		Log.writeLog(time, mid.trim().substring(0, 3), mid, "Recover_To_The_State_Before_Current_Operation", opera, ridd);
		
		
		try
		{
			String[] argsc=new String[4];
			argsc[0] = "-ORBInitialPort";
			if(mid.trim().charAt(0)=='m'||mid.trim().charAt(0)=='M')
			{
				argsc[1] = Integer.toString(Meta.registerPortNoMTL).trim();
			}
			if(mid.trim().charAt(0)=='l'||mid.trim().charAt(0)=='L')
			{
				argsc[1] = Integer.toString(Meta.registerPortNoLVL).trim();
			}
			if(mid.trim().charAt(0)=='d'||mid.trim().charAt(0)=='D')
			{
				argsc[1] = Integer.toString(Meta.registerPortNoDDO).trim();
			}					
			argsc[2] = "-ORBInitialHost";
			argsc[3] = "localhost";
			ORB orbc = ORB.init(argsc, null); 
			org.omg.CORBA.Object objRefc = orbc.resolve_initial_references("NameService");
			NamingContextExt ncRefc = NamingContextExtHelper.narrow(objRefc);
			InterfaceCenter cs = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRefc.resolve_str("CenterServer"+mid.trim().substring(0, 3).toUpperCase().trim()));
			
			
			UdpCommand udptask=UdpTaskThread.getThisTask();
			if(oid==0)
			{
				result = cs.createTRecord(udptask.getManagerID().trim(), udptask.getFirstName().trim(), udptask.getLastName().trim(), udptask.getAdress().trim(), udptask.getPhoneNo().trim(), udptask.getSpecialization().trim(), udptask.getLocation().trim());	
				System.out.println("OKKKKK!");
			}
			if(oid==1)
			{
				result = cs.createSRecord(udptask.getManagerID().trim(), udptask.getFirstName().trim(), udptask.getLastName().trim(), udptask.getActiveStatus(), udptask.getCourseRegistered());				
			}
			if(oid==2)
			{
				result = cs.getRecordCounts(udptask.getManagerID().trim());				
			}
			if(oid==3)
			{
				result = cs.editRecord(udptask.getManagerID().trim(), udptask.getRecordID().trim(), udptask.getFieldName().trim(), udptask.getNewValue());				
			}
			if(oid==4)
			{
				result = cs.MtransferRecord(udptask.getManagerID().trim(), udptask.getRecordID().trim(), udptask.getRemoteName().trim());				
			}
		}
		catch(Exception e)
		{
			
		}	
		return result;
	}
}
