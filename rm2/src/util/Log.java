package util;
import java.io.*;

public class Log
{
	public static void writeLog(String time,String site,String people,String operation,String result,String remark)
	{
		File file = new File("./log");
		if (!file.exists())
		{
			file.mkdir();
		}
		
		File file2 = new File("./log/" + people + ".txt");
		if (file2.exists())
		{
			String log=time.trim()+"\t"+site.trim()+"\t"+people.trim()+"\t"+operation.trim()+"\t"+result.trim()+"\t"+remark.trim()+"\n";
			
            BufferedWriter out = null ;  
            try  
            {  
                out = new  BufferedWriter( new  OutputStreamWriter(    new  FileOutputStream("./log/" + people + ".txt",  true )));  
                out.write(log);  
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

				String log=time.trim()+"\t"+site.trim()+"\t"+people.trim()+"\t"+operation.trim()+"\t"+result.trim()+"\t"+remark.trim()+"\n";
				
	            BufferedWriter out = null ;  
	            try  
	            {  
	                out = new  BufferedWriter( new  OutputStreamWriter(    new  FileOutputStream("./log/" + people + ".txt",  true )));  
	                out.write(log);  
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
	}
}
