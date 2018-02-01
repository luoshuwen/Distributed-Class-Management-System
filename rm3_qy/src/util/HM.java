package util;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
public class HM
{
	public static HashMap hm1;
	public static HashMap hm2;
	public static HashMap hm3;//mtl
	
	public static ReentrantLock[] hmLocks01=new ReentrantLock[100000];//0 send
	public static ReentrantLock[] hmLocks02=new ReentrantLock[100000];
	public static ReentrantLock[] hmLocks03=new ReentrantLock[100000];
	
	public static ReentrantLock[] hmLocks11=new ReentrantLock[100000];//1 receive
	public static ReentrantLock[] hmLocks12=new ReentrantLock[100000];
	public static ReentrantLock[] hmLocks13=new ReentrantLock[100000];
	
	public static boolean[] canunlock1=new boolean[100000];
	public static boolean[] canunlock2=new boolean[100000];
	public static boolean[] canunlock3=new boolean[100000];
	
	public HM()
	{
		for(int i=0;i<100000;i++)
		{
			hmLocks01[i]=new ReentrantLock();
			hmLocks02[i]=new ReentrantLock();
			hmLocks03[i]=new ReentrantLock();
			
			hmLocks11[i]=new ReentrantLock();
			hmLocks12[i]=new ReentrantLock();
			hmLocks13[i]=new ReentrantLock();
			
			canunlock1[i]=false;
			canunlock2[i]=false;
			canunlock3[i]=false;
		}
	}

	public static HashMap getHm(String mi)
	{
		//return hm;
		if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')
		{
			return hm3;
		}
		if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')
		{
			return hm2;
		}
		if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')
		{
			return hm1;
		}
		
		return null;
	}
	
	public static void setHm(String mi,HashMap hashmap)
	{
		if(mi.trim().charAt(0)=='m'||mi.trim().charAt(0)=='M')//MTL
		{
			hm3=hashmap;
		}
		if(mi.trim().charAt(0)=='l'||mi.trim().charAt(0)=='L')//lvl
		{
			hm2=hashmap;
		}
		if(mi.trim().charAt(0)=='d'||mi.trim().charAt(0)=='D')//ddo
		{
			hm1=hashmap;
		}
		//hm=hashmap;
	}
	
	public static int getHMNo(String name)
	{
		if(name.trim().charAt(0)=='m'||name.trim().charAt(0)=='M')
		{
			return hm3.size();
		}
		if(name.trim().charAt(0)=='l'||name.trim().charAt(0)=='L')
		{
			return hm2.size();
		}
		if(name.trim().charAt(0)=='d'||name.trim().charAt(0)=='D')
		{
			return hm1.size();
		}
		return 0;		
	}
	
}
