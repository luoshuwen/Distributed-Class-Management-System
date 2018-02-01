package server;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import util.*;

import CenterInterface.*;
public class CenterServer extends InterfaceCenterPOA
{
	public String serverName=null;
	public static InterfaceCenter IC1;
	public static InterfaceCenter IC2;
	public static InterfaceCenter IC3;

	public CenterServer(String Sname)
	{
		this.serverName=Sname;
	}
	
	public void register(InterfaceCenter ifc, int infoNo)
	{
		if(infoNo==1)//ddo
		{
			IC1=ifc;
		}
		
		if(infoNo==2)//lvl
		{
			IC2=ifc;
		}
		
		if(infoNo==3)//mtl
		{
			IC3=ifc;
		}
	}

	
	public String createTRecord(String mi, String fn, String ln, String ad, String ph, String sp,String lo)
	{
		BackupOriginal.backup(0,mi,"");
		
		TeacherRecord newtr = TeacherRecord.createTeacherRecord(mi,fn, ln, ad, ph, sp, lo);
		// save
		HashMap hm = HM.getHm(mi);
		HashMap temp = (HashMap) hm.get(Character.toString(Integer.valueOf(ln.charAt(0)) > 96 ? Character.toUpperCase(ln.charAt(0)) : ln.charAt(0)));
		temp.put(newtr.getNo().trim(), newtr);
		hm.put(Character.toString(Integer.valueOf(ln.charAt(0)) > 96 ? Character.toUpperCase(ln.charAt(0)) : ln.charAt(0)), temp);
		// save end
		// log
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
		String time = sdf.format(d);
		Log.writeLog(time, mi.trim().substring(0, 3), mi, "Create_New_Teacher_Record", "Successfully", newtr.getNo().trim());
		// log end
		return newtr.getNo().trim();
	}


	public String createSRecord(String mi, String fn, String ln, boolean s, String[] CourseRegistered)
	{
		BackupOriginal.backup(1, mi, "");
		
		ArrayList<String> al = new ArrayList();
		for (String str : CourseRegistered)
		{
			al.add(str);
		}
		StudentRecord newsr = StudentRecord.createStudentRecord(mi,fn, ln, al, s);
		// save
		HashMap hm = HM.getHm(mi);
		HashMap temp = (HashMap) hm.get(Character.toString(Integer.valueOf(ln.charAt(0)) > 96 ? Character.toUpperCase(ln.charAt(0)) : ln.charAt(0)));
		temp.put(newsr.getNo().trim(), newsr);
		hm.put(Character.toString(Integer.valueOf(ln.charAt(0)) > 96 ? Character.toUpperCase(ln.charAt(0)) : ln.charAt(0)), temp);
		// save end
		// log
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
		String time = sdf.format(d);

		Log.writeLog(time, mi.trim().substring(0, 3), mi, "Create_New_Student_Record", "Successfully", newsr.getNo().trim());
		// log end
		return newsr.getNo().trim();
	}


	public String getRecordCounts(String mi)
	{
		int result = TeacherRecord.getTeacherRecordCount(mi,true) + StudentRecord.getStudentRecordCount(mi,true);// zijide
		String resultt = mi.trim().substring(0, 3)+" " + Integer.toString(result);
		// System.out.println(resultt);
		// ask
		String ans1,ans2,resulttt="";
		if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
		{
			ans1 = UDPAskThread.udpAsk("~LVL", Meta.udpAnswerPortNoLVL);
			ans2 = UDPAskThread.udpAsk("~DDO", Meta.udpAnswerPortNoDDO);
			int ans1i = ans1.indexOf('S');
			int ans2i = ans2.indexOf('S');

			int result2 = Integer.parseInt(ans1.substring(2, ans1i).trim())
					+ Integer.parseInt(ans1.substring(ans1i + 2, ans1.length()).trim());
			String resultt2 = "LVL " + Integer.toString(result2);
			int result3 = Integer.parseInt(ans2.substring(2, ans2i).trim())
					+ Integer.parseInt(ans2.substring(ans2i + 2, ans2.length()).trim());
			String resultt3 = "DDO " + Integer.toString(result3);
			resulttt = resultt + "," + resultt2 + "," + resultt3;
		}
		if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
		{
			ans1 = UDPAskThread.udpAsk("~MTL", Meta.udpAnswerPortNoMTL);
			ans2 = UDPAskThread.udpAsk("~DDO", Meta.udpAnswerPortNoDDO);

			int ans1i = ans1.indexOf('S');
			int ans2i = ans2.indexOf('S');

			int result2 = Integer.parseInt(ans1.substring(2, ans1i).trim())
					+ Integer.parseInt(ans1.substring(ans1i + 2, ans1.length()).trim());
			String resultt2 = "MTL " + Integer.toString(result2);
			int result3 = Integer.parseInt(ans2.substring(2, ans2i).trim())
					+ Integer.parseInt(ans2.substring(ans2i + 2, ans2.length()).trim());
			String resultt3 = "DDO " + Integer.toString(result3);
			resulttt = resultt + "," + resultt2 + "," + resultt3;
		}
		if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
		{
			ans1 = UDPAskThread.udpAsk("~LVL", Meta.udpAnswerPortNoLVL);
			ans2 = UDPAskThread.udpAsk("~MTL", Meta.udpAnswerPortNoMTL);

			int ans1i = ans1.indexOf('S');
			int ans2i = ans2.indexOf('S');

			int result2 = Integer.parseInt(ans1.substring(2, ans1i).trim())
					+ Integer.parseInt(ans1.substring(ans1i + 2, ans1.length()).trim());
			String resultt2 = "LVL " + Integer.toString(result2);
			int result3 = Integer.parseInt(ans2.substring(2, ans2i).trim())
					+ Integer.parseInt(ans2.substring(ans2i + 2, ans2.length()).trim());
			String resultt3 = "MTL " + Integer.toString(result3);
			resulttt = resultt + "," + resultt2 + "," + resultt3;
		}		
		// ask end
		// log
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
		String time = sdf.format(d);
		Log.writeLog(time, mi.trim().substring(0, 3), mi, "Query_Records_Count", "Successfully", resulttt);
		// log end
		return resulttt;
	}


	public String editRecord(String mi, String recordID, String fieldname, String[] newvalue)
	{
		BackupOriginal.backup(3, mi, recordID);
		// receive
		ArrayList<String> al = new ArrayList();
		for (String str : newvalue)
		{
			if (str != null)
				al.add(str);
		}
		// judge content
		boolean flag = true;
		// phone
		if (al.size() != 1 && fieldname.trim().charAt(0) != 'c')
		{
			flag = false;
		}
		if (fieldname.trim().charAt(0) == 'l')// location
		{
			if (al.get(0).trim().length() == 3 && al.get(0).trim().equals("MTL"))
			{
			} else
			{
				if (al.get(0).trim().length() == 3 && al.get(0).trim().equals("LVL"))
				{
				} else
				{
					if (al.get(0).trim().length() == 3 && al.get(0).trim().equals("DDO"))
					{
					} else
					{
						flag = false;
					}
				}
			}

		}
		if (fieldname.trim().charAt(1) == 'c')//
		{
			if (al.get(0).trim().length() == 4 && al.get(0).trim().equals("true"))
			{
			} else
			{
				if (al.get(0).trim().length() == 5 && al.get(0).trim().equals("false"))
				{
				} else
				{
					flag = false;
				}
			}

		}
		// judge end

		if (flag == false)
		{
			// log
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
			String time = sdf.format(d);
			Log.writeLog(time, mi.trim().substring(0, 3), mi, "Edit_Record", "Editing failed, invalid message.", "");
			// log end
			return "Editing failed, invalid message.";
		}

		// get lock
		if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
		{
			HM.hmLocks03[Integer.parseInt(recordID.trim().substring(2, 7))].lock();
		}
		if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
		{
			HM.hmLocks02[Integer.parseInt(recordID.trim().substring(2, 7))].lock();
		}
		if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
		{
			HM.hmLocks01[Integer.parseInt(recordID.trim().substring(2, 7))].lock();
		}
		

		// content no problem, hasRecord?
		flag = false;// bucunzai
		HashMap hm = HM.getHm(mi);
		for (Object obj : hm.keySet())
		{
			HashMap temp = (HashMap) hm.get(obj.toString().trim());
			for (Object objj : temp.keySet())
			{
				if (objj.toString().trim().equals(recordID.trim()))
				{
					flag = true;
				}
			}
		}

		if (flag == false)
		{
			if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
			{
				HM.hmLocks03[Integer.parseInt(recordID.trim().substring(2, 7))].unlock();
			}
			if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
			{
				HM.hmLocks02[Integer.parseInt(recordID.trim().substring(2, 7))].unlock();
			}
			if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
			{
				HM.hmLocks01[Integer.parseInt(recordID.trim().substring(2, 7))].unlock();
			}
			
			// log
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
			String time = sdf.format(d);
			Log.writeLog(time, mi.trim().substring(0, 3), mi, "Edit_Record", "Editing failed, record not found.", "");
			// log end

			return "Editing failed, record not found.";
		} 
		else// record in local
		{
			if (recordID.charAt(0) == 'T')// teacher
			{
				TeacherRecord tr = TeacherRecord.getRecord(mi,recordID, true);

				if (fieldname.trim().equals("address"))
				{
					tr.setAddress(al.get(0).trim());
				}
				if (fieldname.trim().equals("phone"))
				{
					tr.setPhone(al.get(0).trim());
				}
				if (fieldname.trim().equals("location"))
				{
					tr.setLocation(al.get(0).trim());
				}
				HashMap hmm = HM.getHm(mi);
				HashMap temp = (HashMap) hmm.get(Character.toString(Integer.valueOf(tr.getLastname().charAt(0)) > 96
						? Character.toUpperCase(tr.getLastname().charAt(0))
						: tr.getLastname().charAt(0)));
				temp.put(tr.getNo().trim(), tr);
				hmm.put(Character.toString(Integer.valueOf(tr.getLastname().charAt(0)) > 96
						? Character.toUpperCase(tr.getLastname().charAt(0))
						: tr.getLastname().charAt(0)), temp);

				if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
				{
					HM.hmLocks03[Integer.parseInt(recordID.trim().substring(2, recordID.length()))].unlock();
				}
				if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
				{
					HM.hmLocks02[Integer.parseInt(recordID.trim().substring(2, recordID.length()))].unlock();
				}
				if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
				{
					HM.hmLocks01[Integer.parseInt(recordID.trim().substring(2, recordID.length()))].unlock();
				}
				// log
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
				String time = sdf.format(d);
				
				Log.writeLog(time, mi.trim().substring(0, 3), mi, "Edit_Record", "Edit successfully.", "The_new_value_of_"+fieldname.trim()+"_is_"+al.get(0).trim());
				// log end
				return "Edit successfully.";
			} 
			else// student
			{
				StudentRecord sr = StudentRecord.getRecord(mi,recordID, true);

				if (sr == null)
				{
					System.out.println("empty");
				}
				if (fieldname.trim().equals("activeStatus"))
				{
					if (al.get(0).trim() .equals("false"))
						sr.setActiveStatus(false);
					else
						sr.setActiveStatus(true);
				}
				if (fieldname.trim().equals("courseRegistered"))
				{
					sr.setCourseRegistered(al);
				}
				HashMap hmm = HM.getHm(mi);
				HashMap temp = (HashMap) hmm.get(Character.toString(Integer.valueOf(sr.getLastName().charAt(0)) > 96
						? Character.toUpperCase(sr.getLastName().charAt(0))
						: sr.getLastName().charAt(0)));
				temp.put(sr.getNo().trim(), sr);
				hmm.put(Character.toString(Integer.valueOf(sr.getLastName().charAt(0)) > 96
						? Character.toUpperCase(sr.getLastName().charAt(0))
						: sr.getLastName().charAt(0)), temp);

				if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
				{
					HM.hmLocks03[Integer.parseInt(recordID.trim().substring(2, recordID.length()))].unlock();
				}
				if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
				{
					HM.hmLocks02[Integer.parseInt(recordID.trim().substring(2, recordID.length()))].unlock();
				}
				if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
				{
					HM.hmLocks01[Integer.parseInt(recordID.trim().substring(2, recordID.length()))].unlock();
				}
				//HM.hmLocks[Integer.parseInt(recordID.trim().substring(2, recordID.length()))].unlock();
				// log
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
				String time = sdf.format(d);
				
				String remark="";
				for(String str:al)
				{
					remark=remark+"_"+str;
				}
				
				Log.writeLog(time,mi.trim().substring(0, 3), mi, "Edit_Record", "Edit successfully.", "The_new_value_of_"+fieldname.trim()+"_is"+remark.trim());
				// log end
				return "Edit successfully.";
			}
		}
	}


	public void StransferRecord(String site, byte[] record, String rid, String mi, String fromN)
	{
		if(site.trim().charAt(0)=='m'||site.trim().charAt(0)=='M')
		{
			HM.hmLocks13[Integer.parseInt(rid.substring(2, 7))].lock();
		}
		if(site.trim().charAt(0)=='l'||site.trim().charAt(0)=='L')
		{
			HM.hmLocks12[Integer.parseInt(rid.substring(2, 7))].lock();
		}
		if(site.trim().charAt(0)=='d'||site.trim().charAt(0)=='D')
		{
			HM.hmLocks11[Integer.parseInt(rid.substring(2, 7))].lock();
		}
		
		ByteHelper bth = new ByteHelper();
		if (rid.charAt(0) == 'T')// teacher
		{
			TeacherRecord tr = (TeacherRecord) bth.toObject(record);
			// TeacherRecord.controlQuery(false);
			// save
			HashMap hm = HM.getHm(site);
			HashMap temp = (HashMap) hm.get(Character.toString(Integer.valueOf(tr.getLastname().trim().charAt(0)) > 96
					? Character.toUpperCase(tr.getLastname().trim().charAt(0))
					: tr.getLastname().trim().charAt(0)));
			temp.put(tr.getNo(), tr);
			hm.put(Character.toString(Integer.valueOf(tr.getLastname().trim().charAt(0)) > 96
					? Character.toUpperCase(tr.getLastname().trim().charAt(0))
					: tr.getLastname().trim().charAt(0)), temp);
			HM.setHm(site,hm);
			
			if(site.trim().charAt(0)=='m'||site.trim().charAt(0)=='M')
			{
				HM.hmLocks13[Integer.parseInt(rid.substring(2, 7))].unlock();
			}
			if(site.trim().charAt(0)=='l'||site.trim().charAt(0)=='L')
			{
				HM.hmLocks12[Integer.parseInt(rid.substring(2, 7))].unlock();
			}
			if(site.trim().charAt(0)=='d'||site.trim().charAt(0)=='D')
			{
				HM.hmLocks11[Integer.parseInt(rid.substring(2, 7))].unlock();
			}
			
			// close query
			if (fromN.charAt(0) == 'M' || fromN.charAt(0) == 'm')
			{
				String ans = UDPAskThread.udpAsk(tr.getNo().trim() + "MTL", Meta.udpAnswerPortNo2MTL);
			}
			if (fromN.charAt(0) == 'L' || fromN.charAt(0) == 'l')
			{
				String ans = UDPAskThread.udpAsk(tr.getNo().trim() + "LVL", Meta.udpAnswerPortNo2LVL);
			}
			if (fromN.charAt(0) == 'D' || fromN.charAt(0) == 'd')
			{
				String ans = UDPAskThread.udpAsk(tr.getNo().trim() + "DDO", Meta.udpAnswerPortNo2DDO);
			}
			// log
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
			String time = sdf.format(d);
			Log.writeLog(time, site.trim().substring(0, 3), site.trim().substring(0, 3), "Receive_Remote_Record_From_"+mi.trim(), "Successfully", tr.getNo());
		} 
		else
		{
			StudentRecord sr = (StudentRecord) bth.toObject(record);
			// StudentRecord.controlQuery(false);
			// save
			HashMap hm = HM.getHm(site);
			HashMap temp = (HashMap) hm.get(Character.toString(Integer.valueOf(sr.getLastName().trim().charAt(0)) > 96
					? Character.toUpperCase(sr.getLastName().trim().charAt(0))
					: sr.getLastName().trim().charAt(0)));
			temp.put(sr.getNo(), sr);
			hm.put(Character.toString(Integer.valueOf(sr.getLastName().trim().charAt(0)) > 96
					? Character.toUpperCase(sr.getLastName().trim().charAt(0))
					: sr.getLastName().trim().charAt(0)), temp);
			HM.setHm(site,hm);

			if(site.trim().charAt(0)=='m'||site.trim().charAt(0)=='M')
			{
				HM.hmLocks13[Integer.parseInt(rid.substring(2, 7))].unlock();
			}
			if(site.trim().charAt(0)=='l'||site.trim().charAt(0)=='L')
			{
				HM.hmLocks12[Integer.parseInt(rid.substring(2, 7))].unlock();
			}
			if(site.trim().charAt(0)=='d'||site.trim().charAt(0)=='D')
			{
				HM.hmLocks11[Integer.parseInt(rid.substring(2, 7))].unlock();
			}
			
			// close query
			if (fromN.charAt(0) == 'M' || fromN.charAt(0) == 'm')
			{
				String ans = UDPAskThread.udpAsk(sr.getNo().trim() + "MTL", Meta.udpAnswerPortNo2MTL);
			}
			if (fromN.charAt(0) == 'L' || fromN.charAt(0) == 'l')
			{
				String ans = UDPAskThread.udpAsk(sr.getNo().trim() + "LVL", Meta.udpAnswerPortNo2LVL);
			}
			if (fromN.charAt(0) == 'D' || fromN.charAt(0) == 'd')
			{
				String ans = UDPAskThread.udpAsk(sr.getNo().trim() + "DDO", Meta.udpAnswerPortNo2DDO);
			}
			// log
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
			String time = sdf.format(d);
			Log.writeLog(time, site.trim().substring(0, 3), site.trim().substring(0, 3), "Receive_Remote_Record_From_"+mi.trim(), "Successfully", sr.getNo());
			// System.out.println(sr.getFirstName());
		}		
	}


	public String MtransferRecord(String mid, String rid, String remoteName)
	{
		
		BackupOriginal.backup(4, mid, rid);
		// judge
		if (!StudentRecord.hasRecord(mid,rid.trim()) || !TeacherRecord.hasRecord(mid,rid.trim()))
		{
			// log
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
			String time = sdf.format(d);

			Log.writeLog(time, mid.trim().substring(0, 3), mid.trim(), "Transfer_Record_:" + rid.trim(),
					"Failed,can_not_find_the_record_on_"+mid.trim().substring(0, 3), "");
			// log end
			return "Failed,can_not_find_the_record_on_"+mid.trim().substring(0, 3);
		}
		
		// judge end
		if(mid.trim().charAt(0)=='m'||mid.trim().charAt(0)=='M')
		{
			HM.hmLocks03[Integer.parseInt(rid.trim().substring(2, rid.length()))].lock();
		}
		if(mid.trim().charAt(0)=='l'||mid.trim().charAt(0)=='L')
		{
			HM.hmLocks02[Integer.parseInt(rid.trim().substring(2, rid.length()))].lock();
			
		}
		if(mid.trim().charAt(0)=='d'||mid.trim().charAt(0)=='D')
		{
			HM.hmLocks01[Integer.parseInt(rid.trim().substring(2, rid.length()))].lock();
		}
		
		// get record
		if (rid.charAt(0) == 'T')// teacher
		{
			TeacherRecord tr = TeacherRecord.getRecord(mid,rid, false);
			ByteHelper bth = new ByteHelper();
			byte[] by = bth.toByteArray(tr);

			TeacherRecord.controlQuery(mid,false);

			
			if (remoteName.charAt(0) == 'L' || remoteName.charAt(0) == 'l')// lvl
			{
				// System.out.println("mmp");
				
				IC2.StransferRecord("lvl",by, tr.getNo().trim(), mid, mid.trim().substring(0, 3));
			}
			if (remoteName.charAt(0) == 'D' || remoteName.charAt(0) == 'd')// ddo
			{
				// System.out.println("mmp");
				
				IC1.StransferRecord("ddo",by, tr.getNo().trim(), mid, mid.trim().substring(0, 3));
			}
			if (remoteName.charAt(0) == 'M' || remoteName.charAt(0) == 'm')// mtl
			{
				// System.out.println("mmp");
				IC3.StransferRecord("mtl",by, tr.getNo().trim(), mid, mid.trim().substring(0, 3));
			}

		} 
		else// student
		{
			StudentRecord sr = StudentRecord.getRecord(mid,rid, false);
			ByteHelper bth = new ByteHelper();
			byte[] by = bth.toByteArray(sr);

			StudentRecord.controlQuery(mid,false);

			if (remoteName.charAt(0) == 'L' || remoteName.charAt(0) == 'l')// lvl
			{
				// System.out.println("mmp");
				IC2.StransferRecord("lvl",by, sr.getNo().trim(), mid, mid.trim().substring(0, 3));
			}
			if (remoteName.charAt(0) == 'D' || remoteName.charAt(0) == 'd')// ddo
			{
				// System.out.println("mmp");
				IC1.StransferRecord("ddo",by, sr.getNo().trim(), mid, mid.trim().substring(0, 3));
			}
			if (remoteName.charAt(0) == 'M' || remoteName.charAt(0) == 'm')// mtl
			{
				// System.out.println("mmp");
				IC3.StransferRecord("mtl",by, sr.getNo().trim(), mid, mid.trim().substring(0, 3));
			}

		}
		// delete record
		HashMap hm = HM.getHm(mid);
		for (Object obj : hm.keySet())
		{
			HashMap temp = (HashMap) hm.get(obj.toString().trim());
			if (temp.remove(rid) != null)
			{
				hm.put(obj.toString().trim(), temp);
				break;
			}
		}
		HM.setHm(mid,hm);

		if(mid.trim().charAt(0)=='m'||mid.trim().charAt(0)=='M')
		{
			HM.hmLocks03[Integer.parseInt(rid.trim().substring(2, rid.length()))].unlock();
		}
		if(mid.trim().charAt(0)=='l'||mid.trim().charAt(0)=='L')
		{
			HM.hmLocks02[Integer.parseInt(rid.trim().substring(2, rid.length()))].unlock();
		}
		if(mid.trim().charAt(0)=='d'||mid.trim().charAt(0)=='D')
		{
			HM.hmLocks01[Integer.parseInt(rid.trim().substring(2, rid.length()))].unlock();
		}
		

		// lock query
		if (rid.charAt(0) == 'T')// teacher
		{
			// TeacherRecord.controlQuery(false);//lock query
			if(mid.trim().charAt(0)=='m'||mid.trim().charAt(0)=='M')
			{
				while (true)
				{
					if (HM.canunlock3[Integer.parseInt(rid.trim().substring(2, rid.length()))] == true)
					{
						TeacherRecord.uncontrolQuery(mid,false);
						HM.canunlock3[Integer.parseInt(rid.trim().substring(2, rid.length()))] = false;
						break;
					}
				}
			}
			if(mid.trim().charAt(0)=='l'||mid.trim().charAt(0)=='L')
			{
				while (true)
				{
					if (HM.canunlock2[Integer.parseInt(rid.trim().substring(2, rid.length()))] == true)
					{
						TeacherRecord.uncontrolQuery(mid,false);
						HM.canunlock2[Integer.parseInt(rid.trim().substring(2, rid.length()))] = false;
						break;
					}
				}
			}
			if(mid.trim().charAt(0)=='d'||mid.trim().charAt(0)=='D')
			{
				while (true)
				{
					if (HM.canunlock1[Integer.parseInt(rid.trim().substring(2, rid.length()))] == true)
					{
						TeacherRecord.uncontrolQuery(mid,false);
						HM.canunlock1[Integer.parseInt(rid.trim().substring(2, rid.length()))] = false;
						break;
					}
				}
			}
		} 
		else
		{
			if(mid.trim().charAt(0)=='m'||mid.trim().charAt(0)=='M')
			{
				while (true)
				{
					if (HM.canunlock3[Integer.parseInt(rid.trim().substring(2, rid.length()))] == true)
					{
						StudentRecord.uncontrolQuery(mid,false);
						HM.canunlock3[Integer.parseInt(rid.trim().substring(2, rid.length()))] = false;
						break;
					}
				}
			}
			if(mid.trim().charAt(0)=='l'||mid.trim().charAt(0)=='L')
			{
				while (true)
				{
					if (HM.canunlock2[Integer.parseInt(rid.trim().substring(2, rid.length()))] == true)
					{
						StudentRecord.uncontrolQuery(mid,false);
						HM.canunlock2[Integer.parseInt(rid.trim().substring(2, rid.length()))] = false;
						break;
					}
				}
			}
			if(mid.trim().charAt(0)=='d'||mid.trim().charAt(0)=='D')
			{
				while (true)
				{
					if (HM.canunlock1[Integer.parseInt(rid.trim().substring(2, rid.length()))] == true)
					{
						StudentRecord.uncontrolQuery(mid,false);
						HM.canunlock1[Integer.parseInt(rid.trim().substring(2, rid.length()))] = false;
						break;
					}
				}
			}
			// StudentRecord.controlQuery(false);//lock query
		}
		// lock end
		// get end
		// log
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
		String time = sdf.format(d);

		Log.writeLog(time, mid.trim().substring(0, 3), mid.trim(), "Transfer_Record_:" + rid.trim()+"_To_"+remoteName.trim(), "Successfully", "");
		// log end
		// HM.hmLocks[Integer.parseInt(rid.trim().substring(2, rid.length()))].unlock();
		return "Successfully";
	}

	

}
