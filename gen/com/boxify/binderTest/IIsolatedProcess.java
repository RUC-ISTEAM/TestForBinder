/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/Kainny/Documents/workspace/TestForBinder/src/com/boxify/binderTest/IIsolatedProcess.aidl
 */
package com.boxify.binderTest;
public interface IIsolatedProcess extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.boxify.binderTest.IIsolatedProcess
{
private static final java.lang.String DESCRIPTOR = "com.boxify.binderTest.IIsolatedProcess";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.boxify.binderTest.IIsolatedProcess interface,
 * generating a proxy if needed.
 */
public static com.boxify.binderTest.IIsolatedProcess asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.boxify.binderTest.IIsolatedProcess))) {
return ((com.boxify.binderTest.IIsolatedProcess)iin);
}
return new com.boxify.binderTest.IIsolatedProcess.Stub.Proxy(obj);
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
case TRANSACTION_sayHello:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
int _result = this.sayHello(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_handBinder:
{
data.enforceInterface(DESCRIPTOR);
com.boxify.binderTest.AppBinder _result = this.handBinder();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_registerCallBack:
{
data.enforceInterface(DESCRIPTOR);
com.boxify.binderTest.IBrokerProcess _arg0;
_arg0 = com.boxify.binderTest.IBrokerProcess.Stub.asInterface(data.readStrongBinder());
this.registerCallBack(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallBack:
{
data.enforceInterface(DESCRIPTOR);
com.boxify.binderTest.IBrokerProcess _arg0;
_arg0 = com.boxify.binderTest.IBrokerProcess.Stub.asInterface(data.readStrongBinder());
this.unregisterCallBack(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.boxify.binderTest.IIsolatedProcess
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
@Override public int sayHello(int a, int b) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(a);
_data.writeInt(b);
mRemote.transact(Stub.TRANSACTION_sayHello, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public com.boxify.binderTest.AppBinder handBinder() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.boxify.binderTest.AppBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_handBinder, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.boxify.binderTest.AppBinder.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//注册回调接口

@Override public void registerCallBack(com.boxify.binderTest.IBrokerProcess cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallBack(com.boxify.binderTest.IBrokerProcess cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_sayHello = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_handBinder = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregisterCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public int sayHello(int a, int b) throws android.os.RemoteException;
public com.boxify.binderTest.AppBinder handBinder() throws android.os.RemoteException;
//注册回调接口

public void registerCallBack(com.boxify.binderTest.IBrokerProcess cb) throws android.os.RemoteException;
public void unregisterCallBack(com.boxify.binderTest.IBrokerProcess cb) throws android.os.RemoteException;
}
