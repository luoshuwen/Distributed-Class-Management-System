package util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


public class StudentRecord implements Serializable
{	
	private String number;
	public static int countNo1=0;
	public static int countNo2=0;
	public static int countNo3=0;
	
	private final static ReentrantLock queryLock1=new ReentrantLock();
	private final static ReentrantLock queryLock2=new ReentrantLock();
	private final static ReentrantLock queryLock3=new ReentrantLock();
	
	private String firstname;
	private String lastname;
	private ArrayList courseRegistered;
	private boolean activeStatus;
	private String statusDate;
	
	public static int getCount(String mi)
	{
		if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
		{
			return countNo3;
		}
		if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
		{
			return countNo2;
		}
		if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
		{
			return countNo1;
		}
		return 0;
	}
	
	public StudentRecord()
	{
		
	}
	
	public static void controlQuery(String mi,boolean flag)
	{
		if(flag==false)
		{
			//queryLock.lock();
			if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
			{
				queryLock3.lock();
			}
			if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
			{
				queryLock2.lock();	
			}
			if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
			{
				queryLock1.lock();	
			}
		}
	}
	
	public static void uncontrolQuery(String mi,boolean flag)
	{
		if(flag==false)
		{
			//queryLock.unlock();
			if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
			{
				queryLock3.unlock();
			}
			if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
			{
				queryLock2.unlock();	
			}
			if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
			{
				queryLock1.unlock();	
			}
		}
	}
	
	public static synchronized StudentRecord createStudentRecord(String mi,String fn,String ln,ArrayList cr,boolean as)
	{
		String rid=null;
		if(mi.trim().charAt(0)=='M'||mi.trim().charAt(0)=='m')//mtl
		{
			rid= "SR" + String.format("%05d", countNo3+1).trim();
		}
		if(mi.trim().charAt(0)=='L'||mi.trim().charAt(0)=='l')//lvl
		{
			rid= "SR" + String.format("%05d", countNo2+1+30000).trim();
		}
		if(mi.trim().charAt(0)=='D'||mi.trim().charAt(0)=='d')//ddo
		{
			rid= "SR" + String.format("%05d", countNo1+1+60000).trim();
		}
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
		String time = sdf.format(d);
		
		StudentRecord sr=new StudentRecord(mi,rid,fn,ln,cr,as,time);
				
		return sr;
	}
	
	public static int getStudentRecordCount(String mi,boolean flag)//true= query false=lock
	{
		if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
		{
			queryLock3.lock();	
			queryLock3.unlock();	
		}
		if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
		{
			queryLock2.lock();	
			queryLock2.unlock();	
		}
		if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
		{
			queryLock1.lock();	
			queryLock1.unlock();	
		}
		
		int sum=0;
		HashMap hm=HM.getHm(mi);
		for(Object obj:hm.keySet())
		{
			HashMap temp=(HashMap)hm.get(obj.toString().trim());
			for(Object objt:temp.keySet())
			{
				if(objt.toString().charAt(0)=='S')
				{
					sum++;
				}
			}
		}
		return sum;
	}
	
	public static boolean hasRecord(String mi,String rid)
	{
		boolean flag=false;
		HashMap hm=HM.getHm(mi);
		for(Object obj:hm.keySet())
		{
			HashMap temp=(HashMap)hm.get(obj.toString());
			if(temp.get(rid.trim())!=null)
			{
				flag=true;
			}
		}
		return flag;
	}
	
	public static StudentRecord getRecord(String mi,String rid,boolean flag)
	{
 		StudentRecord sr=null;
 		HashMap hm=HM.getHm(mi);
 		for(Object obj:hm.keySet())
 		{
 			HashMap temp=(HashMap)hm.get(obj.toString().trim());
 			if(temp.get(rid.trim())!=null)
 			{
 				sr=(StudentRecord)temp.get(rid.trim());
 			}
 		}
 		return sr;
	}
	
	public StudentRecord(String mi,String no,String fn,String ln,ArrayList cr,boolean as,String sd)
	{
		this.firstname=fn;
		this.lastname=ln;
		this.courseRegistered=cr;
		this.activeStatus=as;
		this.statusDate=sd;
		this.number=no;
		if(mi.trim().charAt(0)=='M'||mi.trim().charAt(0)=='m')//mtl
		{
			countNo3++;
		}
		if(mi.trim().charAt(0)=='L'||mi.trim().charAt(0)=='l')//lvl
		{
			countNo2++;
		}
		if(mi.trim().charAt(0)=='D'||mi.trim().charAt(0)=='d')//ddo
		{
			countNo1++;
		}
	}

	public String getFirstName()
	{
		return this.firstname;
	}
	
	public String getLastName()
	{
		return this.lastname;
	}
	
	public boolean getActiveStatus()
	{
		return this.activeStatus;
	}
	
	public String getActiveStatusString()
	{
		if(this.activeStatus==true)
		{
			return "true";
		}
		else
		{
			return "false";
		}
	}
	
	public String getStatusDate()
	{
		return this.statusDate;
	}
	
	public String getNo()
	{
		return this.number;
	}
	
	public synchronized void setActiveStatus(boolean f)
	{
		if(this.activeStatus!=f)
		{
			this.activeStatus=f;
			
			Date d = new Date();   
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");  
	        String time = sdf.format(d);
			this.statusDate=time;
		}
	}
	
	public synchronized void setCourseRegistered(ArrayList<String> newAl)
	{
		this.courseRegistered=newAl;
	}
	
	public ArrayList getCourseRegistered()
	{
		return this.courseRegistered;
	}
}


