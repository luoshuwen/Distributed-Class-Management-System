package sq;

import util.ReqHM;

public class SqRun
{
	public static void main(String[] args)
	{
		ReqHM reqHM=new ReqHM();
		
		new Thread(new SqRequestThread(35000)).start();
		new Thread(new SqRequestThread(35001)).start();
		new Thread(new SqRequestThread(35002)).start();
		
		new Thread(new SqMsgThread(36000)).start();
		new Thread(new SqMsgThread(36001)).start();
		new Thread(new SqMsgThread(36002)).start();
		
		System.out.println("Sequencer is running...");
	}
}
