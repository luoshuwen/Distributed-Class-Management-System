package util;

public class ReceiveThread implements Runnable
{

	private byte[] byy;
	private int pport;
	
	public ReceiveThread(byte[] by,int port)
	{
		this.byy=by;
		this.pport=port;
	}

	public void run()
	{
		//byte[] by=receivePacket.getData();
		ByteHelper bh=new ByteHelper();
		Request req=(Request)bh.toObject(this.byy);
		
        if(this.pport==35000)//rm1
        {
        	ReqHM.reqPool1.put(req.getReqNo(), req);
        	System.out.println(req.getReqNo()+"~~~~"+ReqHM.reqPool1.size());
        }
        
        if(this.pport==35001)//rm2
        {
        	ReqHM.reqPool2.put(req.getReqNo(), req);
        }
        
        if(this.pport==35002)//rm3
        {
        	ReqHM.reqPool3.put(req.getReqNo(), req);
        }
	}

}
