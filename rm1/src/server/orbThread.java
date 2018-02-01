package server;
import java.util.HashMap;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import CenterInterface.*;
import util.*;

public class orbThread implements Runnable
{

	private String[] args=new String[4];
	private String name;
	private InterfaceCenter ICRef;
	public ORB orb;
	
	public InterfaceCenter getICRef()
	{
		return this.ICRef;
	}
	public orbThread(String name)
	{
		this.name=name;
		this.args[0] = "-ORBInitialPort";
		this.args[2] = "-ORBInitialHost";
		this.args[3] = "localhost";
		
		if(name.trim().charAt(0)=='m'||name.trim().charAt(0)=='M')
		{
			this.args[1] = Integer.toString(Meta.registerPortNoMTL).trim();
		}
		if(name.trim().charAt(0)=='l'||name.trim().charAt(0)=='L')
		{
			this.args[1] = Integer.toString(Meta.registerPortNoLVL).trim();
		}
		if(name.trim().charAt(0)=='d'||name.trim().charAt(0)=='D')
		{
			this.args[1] = Integer.toString(Meta.registerPortNoDDO).trim();
		}
		this.orb=ORB.init(this.args, null);
		
		try
		{
			//ORB orb1 = ORB.init(this.args, null);
			POA rootpoa1 = POAHelper.narrow(this.orb.resolve_initial_references("RootPOA"));
			CenterServer cs=new CenterServer(name.trim().substring(0, 3));
			rootpoa1.activate_object(cs);
			InterfaceCenter acs =InterfaceCenterHelper.narrow(rootpoa1.servant_to_reference(cs));
			
			this.ICRef=acs;
            //System.out.println(name+":ICRef has been initialized.");
	        //Bind reference with NameService
	        NamingContext namingContext = NamingContextHelper.narrow(this.orb.resolve_initial_references("NameService"));
	        
	        NameComponent[] nc = { new NameComponent("CenterServer"+this.name.substring(0, 3).trim(), "") };
	        
	        namingContext.rebind(nc, acs);
	        rootpoa1.the_POAManager().activate();
		}
		catch (Exception e) 
		{
			System.out.println(name+" error");
		}
	}

	public void run()
	{
		try
		{
			//initialize the database
			HM u=new HM();
			HashMap hm=null;
			if(this.name.trim().length()==3)
			{			
				hm=InitialEnd.initialing(this.name,true);
			}
			if(this.name.trim().length()==4)//reboot
			{
				hm=InitialEnd.initialing(this.name,false);
			}
			HM.setHm(this.name,hm);
			
			int sum=0;
			for(Object obj:hm.keySet())
			{
				HashMap temp=(HashMap)hm.get(obj.toString());
				sum+=temp.size();
			}
			
			ByteHelper bh=new ByteHelper();
			
			
			System.out.println(this.name.trim()+"~"+hm.size()+"~"+sum+"~~"+bh.toByteArray(hm).length);
			
	        System.out.println("Server"+this.name.trim()+" ready and running ....");
	        this.orb.run();
	        
	        
		}
        catch (Exception e) {
		}

	}

}
