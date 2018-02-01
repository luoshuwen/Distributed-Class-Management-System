package util;
import server.*;
import CenterInterface.*;

public class BackupOriginal
{
	public static int Tcount;
	public static int Scount;
	public static TeacherRecord btr;
	public static StudentRecord bsr;
	
	public static void backup(int OperationID, String mi,String rid)
	{
		if(OperationID==0)//create T record
		{
			Tcount=TeacherRecord.getCount(mi);
		}
		
		if(OperationID==1)//create S record
		{
			Scount=StudentRecord.getCount(mi);
		}
		
		if(OperationID==3)//editRecord
		{
			if(rid.trim().charAt(0)=='T')//teacher
			{
				btr=TeacherRecord.getRecord(mi, rid.trim(), true);
			}
			else
			{
				bsr=StudentRecord.getRecord(mi, rid, true);
			}
		}
		
		if(OperationID==4)//transfer record
		{
			if(rid.trim().charAt(0)=='T')//teacher
			{
				btr=TeacherRecord.getRecord(mi, rid.trim(), true);
				
			}
			else
			{
				bsr=StudentRecord.getRecord(mi, rid, true);
			}
		}
	}
}
