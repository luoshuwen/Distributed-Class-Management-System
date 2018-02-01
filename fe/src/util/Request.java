package util;

import java.io.Serializable;

public class Request implements Serializable
{
	private int ReqNo;
	private int SucNo;
	private UdpCommand ucd;

	public Request(int suc,int reqno,UdpCommand udp)
	{
		this.ReqNo=reqno;
		this.SucNo=suc;
		this.ucd=udp;
	}
	
	public void setReqNo(int rn)
	{
		this.ReqNo=rn;
	}
	
	public int getReqNo()
	{
		return this.ReqNo;
	}
	
	public void setSucNo(int sn)
	{
		this.SucNo=sn;
	}
	
	public int getSucNo()
	{
		return this.SucNo;
	}
	
	public void setUdpCommand(UdpCommand u)
	{
		this.ucd=u;
	}
	
	public UdpCommand getUdpCommand()
	{
		return this.ucd;
	}
}
