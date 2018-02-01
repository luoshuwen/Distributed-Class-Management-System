package fe;

import java.util.HashMap;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import CenterInterfaceFE.*;
import util.FeHM;
import util.ReceiveResultThread;



public class FeRun
{
	public static void main(String[] args)
	{
		new Thread(new ReceiveResultThread(25001)).start();
		new Thread(new ReceiveResultThread(25002)).start();
		new Thread(new ReceiveResultThread(25003)).start();
		
		FeHM feHM=new FeHM();
		
		ORB orb=null;
		try
		{
			//System.out.println(HM.hmLocks[0].isLocked());
            args=new String[4];
            args[0] = "-ORBInitialPort";
			args[1] = Integer.toString(25000).trim();
			args[2] = "-ORBInitialHost";
			args[3] = "localhost";
			//System.out.println("hereing!!!");
			orb = ORB.init(args, null);
			//Instantiate Servant and create reference
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            
            FeServer fs = new FeServer();
            
            rootPOA.activate_object(fs);
            
            InterfaceCenter icf =InterfaceCenterHelper.narrow(rootPOA.servant_to_reference(fs));
            //Bind reference with NameService
            NamingContext namingContext = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
            
            NameComponent[] nc = { new NameComponent("CenterServerFE", "") };
            
            namingContext.rebind(nc, icf);

            //Activate rootpoa
            rootPOA.the_POAManager().activate();

            //Start readthread and wait for incoming requests
            System.out.println("ServerFE ready and running ....");
		}
		catch(Exception e)
		{
			
		}
	}
}
