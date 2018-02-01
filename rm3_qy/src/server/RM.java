package server;

import java.util.HashMap;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import CenterInterface.*;
import util.*;

public class RM
{
	public static orbThread mtlorbth;
	public static orbThread lvlorbth;
	public static orbThread ddoorbth;
	
	public static void main(String[] args)
	{
		Meta.setUdpAnswerPortNoMTL(10204);
		Meta.setUdpAnswerPortNoLVL(10404);
		Meta.setUdpAnswerPortNoDDO(10604);
		
		Meta.setUdpAnswerPortNo2MTL(10205);
		Meta.setUdpAnswerPortNo2LVL(10405);
		Meta.setUdpAnswerPortNo2DDO(10605);
		
		Meta.setRegisterPortNoMTL(30004);
		Meta.setRegisterPortNoLVL(20004);
		Meta.setRegisterPortNoDDO(10004);
		
		Meta.rm1TaskUdp=20011;
		Meta.rm1ReErUdp=30011;
		Meta.rm1ReturnStatusUdp=40011;
		
		Meta.rm2TaskUdp=20012;
		Meta.rm2ReErUdp=30012;
		Meta.rm2ReturnStatusUdp=40012;
		
		Meta.rm2TaskUdp=20013;
		Meta.rm2ReErUdp=30013;
		Meta.rm2ReturnStatusUdp=40013;
		
		HM hmm=new HM();
		
		new Thread(new RmUdpControl(30013)).start();//reboot/error
		
		new Thread(new RmUdpControl(40013)).start();//return status
		
		new Thread(new UdpTaskThread(20013)).start();//receive task
		
		//new Thread(new PrintThread(50012)).start();
		try 
		{
			//set 2 udp ports for each server
			
			//open 2 threads for each server
			int udpport=Meta.udpAnswerPortNoMTL;
			new Thread(new UDPAnswerThread(udpport)).start();
			udpport=Meta.udpAnswerPortNo2MTL;
			new Thread(new UDPAnswerThread(udpport)).start();
			
			udpport=Meta.udpAnswerPortNoLVL;
			new Thread(new UDPAnswerThread(udpport)).start();
			udpport=Meta.udpAnswerPortNo2LVL;
			new Thread(new UDPAnswerThread(udpport)).start();
			
			udpport=Meta.udpAnswerPortNoDDO;
			new Thread(new UDPAnswerThread(udpport)).start();
			udpport=Meta.udpAnswerPortNo2DDO;
			new Thread(new UDPAnswerThread(udpport)).start();
			
            //open servers
			mtlorbth=new orbThread("MTL");
			new Thread(mtlorbth).start();
			
			lvlorbth=new orbThread("LVL");
			new Thread(lvlorbth).start();
			
			ddoorbth=new orbThread("DDO");
			new Thread(ddoorbth).start();
			
			Thread.sleep(1000);
			//initial register
			//destination mtl
			InterfaceCenter icmtl=mtlorbth.getICRef();			
			icmtl.register(lvlorbth.getICRef(), 2);
			System.out.println("lvl register itself to mtl");
			icmtl.register(ddoorbth.getICRef(), 1);
			System.out.println("ddo register itself to mtl");
			
			//destination lvl
			InterfaceCenter iclvl=lvlorbth.getICRef();
			iclvl.register(mtlorbth.getICRef(), 3);
			System.out.println("mtl register itself to lvl");
			iclvl.register(ddoorbth.getICRef(), 1);
			System.out.println("ddo register itself to lvl");
			
			//destination ddo
			InterfaceCenter icddo=ddoorbth.getICRef();
			icddo.register(mtlorbth.getICRef(), 3);
			System.out.println("mtl register itself to ddo");
			icddo.register(lvlorbth.getICRef(), 2);
			System.out.println("lvl register itself to ddo");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
