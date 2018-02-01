
public class TestProgram
{
	public static void main(String[] args)
	{
		
		Meta.setUdpAnswerPortNoMTL(10200);
		Meta.setUdpAnswerPortNoLVL(10400);
		Meta.setUdpAnswerPortNoDDO(10600);
		TestThread1.setCc(0);
	
		//new Thread(new TestThread1(3,3,30000,0,20      , "TR00005", "phone", "54818444514")).start();
		//new Thread(new TestThread1(1,0,30000,0,20      ,"abc","bba","add123","12356456454","cs","lvl")).start();//createT
		//new Thread(new TestThread1(3,3,30000,0,1      , "TR00005", "phone", "54818444514")).start();//edut
		//new Thread(new TestThread1(4,2,30000,0,20)).start();//managerID ,operationID,portNO,locationID,loop
		//new Thread(new TestThread1(2,1,30000,0,20      ,"bca","fef",false,"cs","se","is")).start();//createS
		//edut
		new Thread(new TestThread1(1,1,0,Meta.registerPortNoDDO,2,20      ,"abc","bba","add123","12356456454","cs","lvl")).start();//createT
		new Thread(new TestThread1(2,2,1,Meta.registerPortNoMTL,0,20      ,"bca","fef",false,"cs","se","is")).start();//createS
		new Thread(new TestThread1(0,3,3,Meta.registerPortNoMTL,0,20      , "TR60002", "phone", "54818444514")).start();//edut
		//new Thread(new TestThread1(100,4,Meta.registerPortNoMTL,0,20)).start();//managerID ,operationID,portNO,locationID,loop
		
		new Thread(new TestThread1(0,100,4,Meta.registerPortNoMTL,0,20             ,"TR60002","DDO")).start();
		new Thread(new TestThread1(0,100,4,Meta.registerPortNoDDO,2,20             ,"TR60002","MTL")).start();
		new Thread(new TestThread1(0,4,2,Meta.registerPortNoLVL,1,20)).start();//managerID ,operationID,portNO,locationID,loop
		
//		new Thread(new TestThread1(2,1,Meta.registerPortNoMTL,0,50      ,"bca","fef",false,"cs","se","is")).start();//createS
//		new Thread(new TestThread1(3,3,Meta.registerPortNoDDO,2,50      , "TR00050", "phone", "54818444514")).start();//edut
//		
//		new Thread(new TestThread1(100,4,Meta.registerPortNoMTL,0,1             ,"TR60002","DDO")).start();
//		new Thread(new TestThread1(5,0,Meta.registerPortNoLVL,1,50      ,"abc","cba","add123","123546524","cs","mtl")).start();//createT
//		new Thread(new TestThread1(6,1,Meta.registerPortNoDDO,2,50      ,"bca","def",false,"cs","se","is")).start();//createS
//		new Thread(new TestThread1(7,3,Meta.registerPortNoMTL,0,50      , "TR00085", "address", "newAddressssssss")).start();//edut
//		new Thread(new TestThread1(8,3,Meta.registerPortNoLVL,1,50      , "SR00083", "courseRegistered", "1","2","3","4","5","6")).start();//edut

	}
}
