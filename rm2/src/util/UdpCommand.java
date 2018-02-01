package util;


import java.io.Serializable;
import java.util.Set;

public class UdpCommand implements Serializable
{
	public int operationID;
	public int sucno;
	public String managerID;
	public String fiestName;
	public String lastName;
	public String address;
	public String phoneNo;
	public String specialization;
	public String location;
	public boolean activeStatus;
	public String[] courseRegistered;
	public String recordID;
	public String fieldName;
	public String[] newValue;
	public String remoteName;
	
	public void setSucno(int suc)
	{
		this.sucno=suc;
	}
	
	public int getSucno()
	{
		return this.sucno;
	}
	
	public void setOperationID(int oid)
	{
		this.operationID=oid;
	}
	
	public int getOperationID()
	{
		return this.operationID;
	}
	
	
	public void setManagerID(String mid)
	{
		this.managerID=mid;
	}
	
	public String getManagerID()
	{
		return this.managerID;
	}
	
	
	public void setFirstName(String fn)
	{
		this.fiestName=fn;
	}
	
	public String getFirstName()
	{
		return this.fiestName;
	}
	
	
	public void setLastName(String ln)
	{
		this.lastName=ln;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	

	public void setAddress(String ad)
	{
		this.address=ad;
	}
	
	public String getAdress()
	{
		return this.address;
	}
	
	
	public void setPhoneNo(String ph)
	{
		this.phoneNo=ph;
	}
	
	public String getPhoneNo()
	{
		return this.phoneNo;
	}
	
	
	public void setSpecialization(String sp)
	{
		this.specialization=sp;
	}
	
	public String getSpecialization()
	{
		return this.specialization;
	}
	
	
	public void setLocation(String lo)
	{
		this.location=lo;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	
	public void setActiveStatus(boolean flag)
	{
		this.activeStatus=flag;
	}
	
	public boolean getActiveStatus()
	{
		return this.activeStatus;
	}
	
	
	public void setCourseRegistered(String[] cr)
	{
		this.courseRegistered=cr;
	}
	
	public String[] getCourseRegistered()
	{
		return this.courseRegistered;
	}
	
	
	public void setRecordID(String rid)
	{
		this.recordID=rid;
	}
	
	public String getRecordID()
	{
		return this.recordID;
	}
	
	
	public void setFieldName(String fin)
	{
		this.fieldName=fin;
	}
	
	public String getFieldName()
	{
		return this.fieldName;
	}
	
	
	public void setNewValue(String[] nv)
	{
		this.newValue=nv;
	}
	
	public String[] getNewValue()
	{
		return this.newValue;
	}
	
	
	public void setRemoteName(String rn)
	{
		this.remoteName=rn;
	}
	
	public String getRemoteName()
	{
		return this.remoteName;
	}
	
}
