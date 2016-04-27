/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/Kainny/Documents/workspace/TestForBinder/src/com/boxify/binderTest/IBrokerProcess.aidl
 */
package com.boxify.binderTest;
public interface IBrokerProcess extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.boxify.binderTest.IBrokerProcess
{
private static final java.lang.String DESCRIPTOR = "com.boxify.binderTest.IBrokerProcess";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.boxify.binderTest.IBrokerProcess interface,
 * generating a proxy if needed.
 */
public static com.boxify.binderTest.IBrokerProcess asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.boxify.binderTest.IBrokerProcess))) {
return ((com.boxify.binderTest.IBrokerProcess)iin);
}
return new com.boxify.binderTest.IBrokerProcess.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getHello:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.getHello(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.boxify.binderTest.IBrokerProcess
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void getHello(int result) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(result);
mRemote.transact(Stub.TRANSACTION_getHello, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getHello = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void getHello(int result) throws android.os.RemoteException;
}
