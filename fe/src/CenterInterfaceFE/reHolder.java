package CenterInterfaceFE;


/**
* CenterInterfaceFE/reHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from InterfaceMTL.idl
* Wednesday, August 2, 2017 11:12:54 PM EDT
*/

public final class reHolder implements org.omg.CORBA.portable.Streamable
{
  public byte value[] = null;

  public reHolder ()
  {
  }

  public reHolder (byte[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CenterInterfaceFE.reHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CenterInterfaceFE.reHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CenterInterfaceFE.reHelper.type ();
  }

}