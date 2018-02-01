import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import CenterInterfaceFE.*;

public class TestThread1 implements Runnable
{
	private int managerID;
	private int operationID;
	private int sucNo;
	private int pport;
	private int location;
	private int loop;
	private static int cc;
	
	private String fn;
	private String ln;
	private String add;
	private String pho;
	private String spe;
	private String loca;
	
	private boolean stat;
	private String[] cR;
	
	private String reID;
	private String fieldN;
	private String[] newV;
	
	private String remoteName;
	
	public static void setCc(int c)
	{
		cc=c;
	}
	
	public TestThread1(int suc,int id,int oid,int p,int lo,int loo)//5
	{
		this.managerID=id;
		this.operationID=oid;
		this.pport=p;
		this.location=lo;
		this.loop=loo;
	}
	
	public TestThread1(int suc,int id,int oid,int p,int lo,int loo,      String fn,String ln,String ad,String ph,String sp,String loc)//Trecord//11
	{
		this.sucNo=suc;
		this.managerID=id;
		this.operationID=oid;
		this.pport=p;
		this.location=lo;
		this.loop=loo;
		
		this.fn=fn;
		this.ln=ln;
		this.add=ad;
		this.pho=ph;
		this.spe=sp;
		this.loca=loc;
	}
	
	public TestThread1(int suc,int id,int oid,int p,int lo,int loo,      String fn,String ln,boolean sta,String... courR)//Srecord //8+...
	{
		this.sucNo=suc;
		this.managerID=id;
		this.operationID=oid;
		this.pport=p;
		this.location=lo;
		this.loop=loo;
		
		this.fn=fn;
		this.ln=ln;
		this.stat=sta;
		this.cR=courR;
	}
	
	public TestThread1(int suc,  int id,int oid,int p,int lo,int loo,      String rID,String fieldn,String... newVa)//edit //7+
	{
		this.sucNo=suc;
		this.managerID=id;
		this.operationID=oid;
		this.pport=p;
		this.location=lo;
		this.loop=loo;
		
		this.reID=rID;
		this.fieldN=fieldn;
		this.newV=newVa;
	}
	
	public TestThread1(int suc,int id,int oid,int p,int lo,int loo,      String rID,String remoteN)//transfer,7
	{
		this.sucNo=suc;
		this.managerID=id;
		this.operationID=oid;
		this.pport=p;
		this.location=lo;
		this.loop=loo;
		
		this.reID=rID;
		this.remoteName=remoteN;
	}
	
	public void run()
	{
		try
		{			
			if(location==0)//mtl
			{
				//llokup
				String[] args1=new String[4];
				args1[0] = "-ORBInitialPort";
				args1[1] = Integer.toString(25000).trim();
				args1[2] = "-ORBInitialHost";
				args1[3] = "localhost";
				ORB orb1 = ORB.init(args1, null);
				org.omg.CORBA.Object objRef1 = orb1.resolve_initial_references("NameService");
				NamingContextExt ncRef1 = NamingContextExtHelper.narrow(objRef1);
				InterfaceCenter icM = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRef1.resolve_str("CenterServerFE"));
				//
				if(loop==1)
				{
					if(this.operationID==0)
					{
						//System.out.println("miao~~~~~~");
						String mid="MTL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icM.createTRecord(sucNum,manageridstr, this.fn,this.ln, this.add,this.pho,this.spe, this.loca);
						System.out.println("The result of createTRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "MTL", manageridstr, "Create_New_Teacher_Record", "Successfully",result);
						
					}
					if(this.operationID==1)
					{
						String mid="MTL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icM.createSRecord(sucNum,manageridstr, this.fn,this.ln, this.stat, cR);
						System.out.println("The result of createSRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "MTL", manageridstr, "Create_New_Student_Record", "Successfully",result);
					}
					if(this.operationID==2)
					{
						String mid="MTL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icM.getRecordCounts(sucNum,manageridstr);
						System.out.println("The result asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "MTL", manageridstr, "Query_Records_Count", "Successfully",result);
					}
					if(this.operationID==3)
					{
						String mid="MTL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icM.editRecord(sucNum,manageridstr, this.reID,this.fieldN, this.newV);
						System.out.println("The result of editRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "MTL", manageridstr, "Edit_Record", result,"");
					}
					if(this.operationID==4)
					{
						String mid="MTL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icM.MtransferRecord(sucNum,manageridstr, this.reID, this.remoteName);
						System.out.println("The result of transferRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "MTL", manageridstr, "Transfer_Record_"+this.reID.trim()+"_to_"+this.remoteName.trim(), result,"");
					}
				}
				else
				{
					if(this.operationID==0)
					{
						int count=0;
						while(count<loop)
						{
							String mid="MTL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icM.createTRecord(sucNum,manageridstr, this.fn,this.ln, this.add,this.pho,this.spe, this.loca);
							System.out.println(cc+"The result of createTRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "MTL", manageridstr, "Create_New_Teacher_Record", "Successfully",result);							
							count++;
						}
					}
					if(this.operationID==1)
					{
						int count=0;
						while(count<loop)
						{
							String mid="MTL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icM.createSRecord(sucNum,manageridstr, this.fn,this.ln, this.stat, cR);
							System.out.println(cc+"The result of createSRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "MTL", manageridstr, "Create_New_Student_Record", "Successfully",result);						
							count++;
						}
					}
					if(this.operationID==2)
					{
						int count=0;
						while(count<loop)
						{
							String mid="MTL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icM.getRecordCounts(sucNum,manageridstr);
							System.out.println(cc+"The result asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "MTL", manageridstr, "Query_Records_Count", "Successfully",result);
							count++;
						}
					}
					if(this.operationID==3)
					{
						int count=0;
						while(count<loop)
						{
							String mid="MTL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icM.editRecord(sucNum,manageridstr, this.reID,this.fieldN, this.newV);
							System.out.println(cc+"The result of editRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "MTL", manageridstr, "Edit_Record", result,"");
							count++;
						}
					}
					if(this.operationID==4)
					{
						int count=0;
						while(count<loop)
						{
							String mid="MTL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icM.MtransferRecord(sucNum,manageridstr, this.reID, this.remoteName);
							System.out.println(cc+"The result of transferRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "MTL", manageridstr, "Transfer_Record_"+this.reID.trim()+"_to_"+this.remoteName.trim(), result,"");
							count++;
						}
					}
				}
			}
			if(location==1)//LVL
			{
				String[] args2=new String[4];
				args2[0] = "-ORBInitialPort";
				args2[1] = Integer.toString(25000).trim();
				args2[2] = "-ORBInitialHost";
				args2[3] = "localhost";
				ORB orb2 = ORB.init(args2, null);
				org.omg.CORBA.Object objRef2 = orb2.resolve_initial_references("NameService");
				NamingContextExt ncRef2 = NamingContextExtHelper.narrow(objRef2);
				InterfaceCenter icL = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRef2.resolve_str("CenterServerFE"));
				
				if(loop==1)
				{
					if(this.operationID==0)
					{
						String mid="LVL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icL.createTRecord(sucNum,manageridstr, this.fn,this.ln, this.add,this.pho,this.spe, this.loca);
						System.out.println("The result of createTRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "LVL", manageridstr, "Create_New_Teacher_Record", "Successfully",result);
					}
					if(this.operationID==1)
					{
						String mid="LVL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icL.createSRecord(sucNum,manageridstr, this.fn,this.ln, this.stat, cR);
						System.out.println("The result of createSRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "LVL", manageridstr, "Create_New_Student_Record", "Successfully",result);
						
					}
					if(this.operationID==2)
					{
						String mid="LVL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icL.getRecordCounts(sucNum,manageridstr);
						System.out.println("The result asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "LVL", manageridstr, "Query_Records_Count", "Successfully",result);
					}
					if(this.operationID==3)
					{
						String mid="LVL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icL.editRecord(sucNum,manageridstr, this.reID,this.fieldN, this.newV);
						System.out.println("The result of editRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "LVL", manageridstr, "Edit_Record", result,"");
					}
					if(this.operationID==4)
					{
						String mid="LVL";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icL.MtransferRecord(sucNum,manageridstr, this.reID, this.remoteName);
						System.out.println("The result of transferRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "LVL", manageridstr, "Transfer_Record_"+this.reID.trim()+"_to_"+this.remoteName.trim(), result,"");
					}
				}
				else
				{
					if(this.operationID==0)
					{
						int count=0;
						while(count<loop)
						{
							String mid="LVL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icL.createTRecord(sucNum,manageridstr, this.fn,this.ln, this.add,this.pho,this.spe, this.loca);
							System.out.println(cc+"The result of createTRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "LVL", manageridstr, "Create_New_Teacher_Record", "Successfully",result);							
							count++;
						}
					}
					if(this.operationID==1)
					{
						int count=0;
						while(count<loop)
						{
							String mid="LVL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icL.createSRecord(sucNum,manageridstr, this.fn,this.ln, this.stat, cR);
							System.out.println(cc+"The result of createSRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "LVL", manageridstr, "Create_New_Student_Record", "Successfully",result);							
							count++;
						}
					}
					if(this.operationID==2)
					{
						int count=0;
						while(count<loop)
						{
							String mid="LVL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icL.getRecordCounts(sucNum,manageridstr);
							System.out.println(cc+"The result asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "LVL", manageridstr, "Query_Records_Count", "Successfully",result);
							count++;
						}
					}
					if(this.operationID==3)
					{
						int count=0;
						while(count<loop)
						{
							String mid="LVL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icL.editRecord(sucNum,manageridstr, this.reID,this.fieldN, this.newV);
							System.out.println(cc+"The result of editRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "LVL", manageridstr, "Edit_Record", result,"");
							count++;
						}
					}
					if(this.operationID==4)
					{
						int count=0;
						while(count<loop)
						{
							String mid="LVL";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icL.MtransferRecord(sucNum,manageridstr, this.reID, this.remoteName);
							System.out.println(cc+"The result of transferRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "LVL", manageridstr, "Transfer_Record_"+this.reID.trim()+"_to_"+this.remoteName.trim(), result,"");
							count++;
						}
					}
				}
			}
			if(location==2)//DDO
			{
				String[] args3=new String[4];
				args3[0] = "-ORBInitialPort";
				args3[1] = Integer.toString(25000).trim();
				args3[2] = "-ORBInitialHost";
				args3[3] = "localhost";
				ORB orb3 = ORB.init(args3, null);
				org.omg.CORBA.Object objRef3 = orb3.resolve_initial_references("NameService");
				NamingContextExt ncRef3 = NamingContextExtHelper.narrow(objRef3);
				InterfaceCenter icD = (InterfaceCenter) InterfaceCenterHelper.narrow(ncRef3.resolve_str("CenterServerFE"));
				
				if(loop==1)
				{
					if(this.operationID==0)
					{
						String mid="DDO";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icD.createTRecord(sucNum,manageridstr, this.fn,this.ln, this.add,this.pho,this.spe, this.loca);
						System.out.println("The result of createTRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "DDO", manageridstr, "Create_New_Teacher_Record", "Successfully",result);
					}
					if(this.operationID==1)
					{
						String mid="DDO";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icD.createSRecord(sucNum,manageridstr, this.fn,this.ln, this.stat, cR);
						System.out.println("The result of createSRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "DDO", manageridstr, "Create_New_Student_Record", "Successfully",result);
					}
					if(this.operationID==2)
					{
						String mid="DDO";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icD.getRecordCounts(sucNum,manageridstr);
						System.out.println("The result asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "DDO", manageridstr, "Query_Records_Count", "Successfully",result);
					}
					if(this.operationID==3)
					{
						String mid="DDO";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icD.editRecord(sucNum,manageridstr, this.reID,this.fieldN, this.newV);
						System.out.println("The result of editRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "DDO", manageridstr, "Edit_Record", result,"");
					}
					if(this.operationID==4)
					{
						//System.out.println("mmpddo");
						String mid="DDO";								
						String manageridstr = mid+String.format("%04d", managerID);
						String sucNum=Integer.toString(this.sucNo);
						String result = icD.MtransferRecord(sucNum,manageridstr, this.reID, this.remoteName);
						System.out.println("The result of transferRecord asked by "+manageridstr+" is:"+result);
						
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
						String time = sdf.format(d);
				        Log.writeLog(time, "DDO", manageridstr, "Transfer_Record_"+this.reID.trim()+"_to_"+this.remoteName.trim(), result,"");
					}
				}
				else
				{
					if(this.operationID==0)
					{
						int count=0;
						while(count<loop)
						{
							String mid="DDO";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icD.createTRecord(sucNum,manageridstr, this.fn,this.ln, this.add,this.pho,this.spe, this.loca);
							System.out.println(cc+"The result of createTRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "DDO", manageridstr, "Create_New_Teacher_Record", "Successfully",result);						
							count++;
						}
					}
					if(this.operationID==1)
					{
						int count=0;
						while(count<loop)
						{
							String mid="DDO";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icD.createSRecord(sucNum,manageridstr, this.fn,this.ln, this.stat, cR);
							System.out.println(cc+"The result of createSRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "DDO", manageridstr, "Create_New_Student_Record", "Successfully",result);						
							count++;
						}
					}
					if(this.operationID==2)
					{
						int count=0;
						while(count<loop)
						{
							String mid="DDO";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icD.getRecordCounts(sucNum,manageridstr);
							System.out.println(cc+"The result asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "DDO", manageridstr, "Query_Records_Count", "Successfully",result);
							count++;
						}
					}
					if(this.operationID==3)
					{
						int count=0;
						while(count<loop)
						{
							String mid="DDO";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icD.editRecord(sucNum,manageridstr, this.reID,this.fieldN, this.newV);
							System.out.println(cc+"The result of editRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "DDO", manageridstr, "Edit_Record", result,"");
							count++;
						}
					}
					if(this.operationID==4)
					{
						int count=0;
						while(count<loop)
						{
							String mid="DDO";								
							String manageridstr = mid+String.format("%04d", managerID);
							String sucNum=Integer.toString(this.sucNo);
							String result = icD.MtransferRecord(sucNum,manageridstr, this.reID, this.remoteName);
							System.out.println(cc+"The result of transferRecord asked by "+manageridstr+" is:"+result);
							cc++;
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH:mm:ss");
							String time = sdf.format(d);
					        Log.writeLog(time, "DDO", manageridstr, "Transfer_Record_"+this.reID.trim()+"_to_"+this.remoteName.trim(), result,"");
							count++;
						}
					}
				}
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
