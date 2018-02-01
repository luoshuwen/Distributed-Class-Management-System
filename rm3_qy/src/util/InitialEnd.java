package util;
import java.io.*;
import java.util.*;

public class InitialEnd
{
	public  static HashMap initialing(String name,boolean flag)//true for initial
	{
		//
		HashMap hm=new HashMap();
		for(int i=1;i<=26;i++)
		{
			HashMap nhm=new HashMap();
			hm.put(Character.toString((char)(64+i)),nhm);
		}
		
		String currentP="";
		if(flag==true)
		{
			currentP="record";
		}
		else
		{
			currentP="backup";
		}
		
		String filePath=null;
		if(name.trim().charAt(0)=='m'||name.trim().charAt(0)=='M')
		{
			filePath= "./"+currentP+"/MTL_record.txt";
		}
		if(name.trim().charAt(0)=='l'||name.trim().charAt(0)=='L')
		{
			filePath= "./"+currentP+"/LVL_record.txt";
		}
		if(name.trim().charAt(0)=='d'||name.trim().charAt(0)=='D')
		{
			filePath= "./"+currentP+"/DDO_record.txt";
		}
		 
		try
		{
			File file = new File(filePath);
			if (file.isFile() && file.exists())
			{
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));// 
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null)
				{
					String record = lineTxt;
					String[] rrecord = new String[7];
					StringTokenizer tokenizer = new StringTokenizer(record);
					int ii = 0;
					while (tokenizer.hasMoreTokens())
					{							
						rrecord[ii] = tokenizer.nextToken();
						ii++;
					}
					if (ii == 7)//teacher
					{
						 TeacherRecord rcd=new TeacherRecord(name.trim(),rrecord[0].trim(),rrecord[1].trim(),rrecord[2].trim(),rrecord[3].trim(),rrecord[4].trim(),rrecord[5].trim(),rrecord[6].trim());
						 HashMap temp=(HashMap)hm.get(Character.toString(Integer.valueOf(rcd.getLastname().trim().charAt(0))>96?    Character.toUpperCase(rcd.getLastname().trim().charAt(0)) :rcd.getLastname().trim().charAt(0)));
						 temp.put(rcd.getNo(), rcd);
						 hm.put(Character.toString(Integer.valueOf(rcd.getLastname().trim().charAt(0))>96?    Character.toUpperCase(rcd.getLastname().trim().charAt(0)) :rcd.getLastname().trim().charAt(0)), temp);
						 
					}
					if (ii == 5)//studentRecord
					{
						ArrayList al=new ArrayList();
						
						File ff=null;
						if(name.trim().charAt(0)=='m'||name.trim().charAt(0)=='M')
						{
							ff=new File("./"+currentP+"/MTL_course.txt");
						}
						if(name.trim().charAt(0)=='l'||name.trim().charAt(0)=='L')
						{
							ff=new File("./"+currentP+"/LVL_course.txt");
						}
						if(name.trim().charAt(0)=='d'||name.trim().charAt(0)=='D')
						{
							ff=new File("./"+currentP+"/DDO_course.txt");
						}
                                                 
                        if (ff.isFile() && ff.exists())
             			{
             				InputStreamReader read2 = new InputStreamReader(new FileInputStream(ff));// 
             				BufferedReader bufferedReader2 = new BufferedReader(read2);
             				String lineTxt2 = null;
             				while ((lineTxt2 = bufferedReader2.readLine()) != null)
             				{
             					String record2 = lineTxt2;
             					String[] rrecord2 = new String[2];
             					StringTokenizer tokenizer2 = new StringTokenizer(record2);
             					int ii2 = 0;			//
             					while (tokenizer2.hasMoreTokens())
             					{							
             						rrecord2[ii2] = tokenizer2.nextToken();
             						ii2++;
             					}
             					if(ii2==2)
             					{
             						if(rrecord2[0].trim().equals(rrecord[0].trim()))
             						{
             							al.add(rrecord2[1].trim());
             						}
             					}
             				}
             				read2.close();
             			}                         
						StudentRecord rcd=new StudentRecord(name.trim(),rrecord[0].trim(),rrecord[1].trim(),rrecord[2].trim(),al,Boolean.parseBoolean(rrecord[3].trim()),rrecord[4].trim());
						HashMap temp=(HashMap)hm.get(Character.toString(Integer.valueOf(rcd.getLastName().trim().charAt(0))>96?Character.toUpperCase(rcd.getLastName().trim().charAt(0)):rcd.getLastName().trim().charAt(0)));
						temp.put(rcd.getNo(), rcd);
						hm.put(Character.toString(Integer.valueOf(rcd.getLastName().trim().charAt(0))>96?Character.toUpperCase(rcd.getLastName().trim().charAt(0)):rcd.getLastName().trim().charAt(0)),temp);
					}
				}
				read.close();
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	    return hm;
	}
}
