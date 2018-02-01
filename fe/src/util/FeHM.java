package util;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class FeHM
{
	private static int requestNo=0;
	public static ReentrantLock executeLock;
	public static String[] executeResult;
	public static String[] execute2Result;
	public static boolean[] canuseresult;
	
	public FeHM()
	{
		executeLock=new ReentrantLock();
		executeResult=new String[3];
		execute2Result=new String[3];
		execute2Result[0]="~";
		execute2Result[1]="..";
		execute2Result[2]="..";
		canuseresult=new boolean[3];
		canuseresult[0]=false;
		canuseresult[1]=false;
		canuseresult[2]=false;
	}
	
	public static synchronized int getOwnRequestNo()
	{
		requestNo++;
		return requestNo;
	}
}
