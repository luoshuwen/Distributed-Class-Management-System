package util;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class TeacherRecord implements Serializable// extends Record
{
	public String number;
	public static int countNo1 = 0;
	public static int countNo2 = 0;
	public static int countNo3 = 0;
	
	private final static ReentrantLock queryLock1=new ReentrantLock();
	private final static ReentrantLock queryLock2=new ReentrantLock();
	private final static ReentrantLock queryLock3=new ReentrantLock();

	private String firstname;
	private String lastname;
	private String address;
	private String phone;
	private String specialization;
	private String location;

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
	
	public TeacherRecord()
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
	
 	public static TeacherRecord getRecord(String mi,String rid,boolean flag)
	{
 		TeacherRecord tr=null;
 		
		//flag = false;// bucunzai
		HashMap hm = HM.getHm(mi);
		for (Object obj : hm.keySet())
		{
			HashMap temp = (HashMap) hm.get(obj.toString().trim());
			for (Object objj : temp.keySet())
			{
				if (objj.toString().trim().equals(rid.trim()))
				{
					tr=(TeacherRecord)temp.get(objj);
				}
			}
		}	
 		return tr;
	}
	
	public static synchronized TeacherRecord createTeacherRecord(String mi,String fn, String ln, String ad, String ph, String sp, String lo)
	{
		String rid=null;
		if(mi.trim().charAt(0)=='M'||mi.trim().charAt(0)=='m')//mtl
		{
			rid= "TR" + String.format("%05d", countNo3+1).trim();
		}
		if(mi.trim().charAt(0)=='L'||mi.trim().charAt(0)=='l')//lvl
		{
			rid= "TR" + String.format("%05d", countNo2+1+30000).trim();
		}
		if(mi.trim().charAt(0)=='D'||mi.trim().charAt(0)=='d')//ddo
		{
			rid= "TR" + String.format("%05d", countNo1+1+60000).trim();
		}		
		TeacherRecord tr=new TeacherRecord(mi,rid,fn,ln,ad,ph,sp,lo);		
		return tr;
	}
	
	public static int getTeacherRecordCount(String mi,boolean flag)//true= query false=lock
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
				if(objt.toString().charAt(0)=='T')
				{
					sum++;
				}
			}
		}
		return sum;
	}
	
	public TeacherRecord(String mi,String no,String fn, String ln, String ad, String ph, String sp, String lo)
	{
		this.firstname = fn;
		this.lastname = ln;
		this.address = ad;
		this.phone = ph;
		this.specialization = sp;
		this.location = lo;
		this.number = no;
		
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

	public String getNo()
	{
		return this.number;
	}

	public String getFirstName()
	{
		return this.firstname;
	}

	public String getLastname()
	{
		return this.lastname;
	}

	public String getAddress()
	{
		return this.address;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public String getSpecialization()
	{
		return this.specialization;
	}

	public String getLocation()
	{
		return this.location;
	}
	
	public synchronized void setAddress(String newAddress)
	{
		this.address=newAddress;
	}
	
	public synchronized void setPhone(String newPhone)
	{
		this.phone=newPhone;
	}
	
	public synchronized void setLocation(String newLocation)
	{
		this.location=newLocation;
	}
	
}

