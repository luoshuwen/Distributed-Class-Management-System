package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BackupDatabase
{
	public static String backupDB(String name)
	{
		File file = new File("./backup");
		if (!file.exists())
		{
			file.mkdir();
		}
		
		ArrayList all1=new ArrayList();
		ArrayList all2=new ArrayList();
		
		HashMap uu=HM.getHm(name);
		
		for(Object obj:uu.keySet())
		{
			HashMap tempuu=(HashMap)uu.get(obj.toString().trim());
			for(Object objj:tempuu.keySet())
			{
				if(objj.toString().trim().charAt(0)=='T')//teacher record
				{
					TeacherRecord ttr=(TeacherRecord)tempuu.get(objj.toString().trim());
					String tempcontent = objj.toString().trim()+"\t"+ttr.getFirstName().trim()+"\t"+ttr.getLastname().trim()+"\t"+ ttr.getAddress().trim()+"\t"+ ttr.getPhone().trim()+"\t"+ttr.getSpecialization().trim()+"\t"+ttr.getLocation().trim()+"\n";
					all1.add(tempcontent);
				}
				if(objj.toString().trim().charAt(0)=='S') //student record
				{
					StudentRecord ssr=(StudentRecord)tempuu.get(objj.toString().trim());
					ArrayList alt=ssr.getCourseRegistered();
					for(Object strtemp:alt)
					{
						String tempcontent=objj.toString().trim()+"\t"+strtemp.toString().trim()+"\n";
						
						all2.add(tempcontent);
					}
					String tempcontent=objj.toString().trim()+"\t"+ssr.getFirstName().trim()+"\t"+ssr.getLastName().trim()+"\t"+ssr.getActiveStatusString()+"\t"+ssr.getStatusDate().trim()+"\n";
					all1.add(tempcontent);
				}
			}
		}
		
		//System.out.println(all1.size());
		//System.out.println(all2.size());		
		
		File file2 = new File("./backup/" + name.trim()+"_record.txt");
		if (file2.exists())
		{		
			try
			{
				file2.delete();            
				file2.createNewFile();
			}
			catch(Exception e)
			{
				
			}
            BufferedWriter out = null ;  
            try  
            {              	
                out = new  BufferedWriter( new  OutputStreamWriter(    new  FileOutputStream("./backup/"+ name.trim()+"_record.txt",  true )));  
                for(Object objtemp:all1)
                {
                	out.write(objtemp.toString());
                }
                  
            } 
            catch  (Exception e) 
            {  
                e.printStackTrace();
            } 
            finally  
            {  
                try  
                {  
                    out.close();  
                } 
                catch  (IOException e) 
                {  
                    e.printStackTrace();  
                }  
            }
		} 
		else
		{
			try
			{
				file2.createNewFile();//create
				
	            BufferedWriter out = null ;  
	            try  
	            {  
	                out = new  BufferedWriter( new  OutputStreamWriter(    new  FileOutputStream("./backup/" + name.trim()+"_record.txt",  true )));  
	                for(Object objtemp:all1)
	                {
	                	out.write(objtemp.toString());
	                } 
	            } 
	            catch  (Exception e) 
	            {  
	                e.printStackTrace();
	            } 
	            finally  
	            {  
	                try  
	                {  
	                    out.close();  
	                } 
	                catch  (IOException e) 
	                {  
	                    e.printStackTrace();  
	                }  
	            }
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		file2 = new File("./backup/" + name.trim()+"_course.txt");
		if (file2.exists())
		{		
			try
			{
				file2.delete();            
				file2.createNewFile();
			}
			catch(Exception e)
			{
				
			}
            BufferedWriter out = null ;  
            try  
            {              	
                out = new  BufferedWriter( new  OutputStreamWriter(    new  FileOutputStream("./backup/"+ name.trim()+"_course.txt",  true )));  
                for(Object objtemp:all2)
                {
                	out.write(objtemp.toString());
                }
                  
            } 
            catch  (Exception e) 
            {  
                e.printStackTrace();
            } 
            finally  
            {  
                try  
                {  
                    out.close();  
                } 
                catch  (IOException e) 
                {  
                    e.printStackTrace();  
                }  
            }
		} 
		else
		{
			try
			{
				file2.createNewFile();//create
				
	            BufferedWriter out = null ;  
	            try  
	            {  
	                out = new  BufferedWriter( new  OutputStreamWriter(    new  FileOutputStream("./backup/" + name.trim()+"_course.txt",  true )));  
	                for(Object objtemp:all2)
	                {
	                	out.write(objtemp.toString());
	                } 
	            } 
	            catch  (Exception e) 
	            {  
	                e.printStackTrace();
	            } 
	            finally  
	            {  
	                try  
	                {  
	                    out.close();  
	                } 
	                catch  (IOException e) 
	                {  
	                    e.printStackTrace();  
	                }  
	            }
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return "OK";
	}
}
