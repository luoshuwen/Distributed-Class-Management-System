import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

public class Meta
{
	public static int registerPortNoMTL;
	public static int udpAnswerPortNoMTL;
	public static int registerPortNoLVL;
	public static int udpAnswerPortNoLVL;
	public static int registerPortNoDDO;
	public static int udpAnswerPortNoDDO;
	
	public static int udpAnswerPortNo2MTL;
	public static int udpAnswerPortNo2LVL;
	public static int udpAnswerPortNo2DDO;
	
	public static ORB ServerOrb=null;
	public static POA ServerRootPOA=null;

	
	public static void setRootPOA(POA pOa)
	{
		ServerRootPOA=pOa;
	}
	
	public static POA getRootPOA()
	{
		return ServerRootPOA;
	}
	
	public static void setOrb(ORB oRb)
	{
		ServerOrb=oRb;
	}
	
	public static ORB getOrb()
	{
		return ServerOrb;
	}
	
	public Meta()
	{
		
	}
	
	public static void setRegisterPortNoMTL(int RegisterPortNoMTL)
	{
		registerPortNoMTL=RegisterPortNoMTL;
	}
	
	public static void setUdpAnswerPortNoMTL(int UdpAnswerPortNoMTL)
	{
		udpAnswerPortNoMTL=UdpAnswerPortNoMTL;
	}
	
	public static void setRegisterPortNoLVL(int RegisterPortNoLVL)
	{
		registerPortNoLVL=RegisterPortNoLVL;
	}
	
	public static void setUdpAnswerPortNoLVL(int UdpAnswerPortNoLVL)
	{
		udpAnswerPortNoLVL=UdpAnswerPortNoLVL;
	}
	
	public static void setRegisterPortNoDDO(int RegisterPortNoDDO)
	{
		registerPortNoDDO=RegisterPortNoDDO;
	}
	
	public static void setUdpAnswerPortNoDDO(int UdpAnswerPortNoDDO)
	{
		udpAnswerPortNoDDO=UdpAnswerPortNoDDO;
	}
	
	
	public static void setUdpAnswerPortNo2MTL(int UdpAnswerPortNo2MTL)
	{
		udpAnswerPortNo2MTL=UdpAnswerPortNo2MTL;
	}
	public static void setUdpAnswerPortNo2LVL(int UdpAnswerPortNo2LVL)
	{
		udpAnswerPortNo2LVL=UdpAnswerPortNo2LVL;
	}
	public static void setUdpAnswerPortNo2DDO(int UdpAnswerPortNo2DDO)
	{
		udpAnswerPortNo2DDO=UdpAnswerPortNo2DDO;
	}
}
