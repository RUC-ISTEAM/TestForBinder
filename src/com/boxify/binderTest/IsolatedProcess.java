package com.boxify.binderTest;

import android.annotation.SuppressLint;
import android.app.ActivityThread;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.boxify.reflect.*;
public class IsolatedProcess extends Service{
	private static final String TAG = "IsolatedProcess";
	
	private RemoteCallbackList<IBrokerProcess> mCallBacks = new RemoteCallbackList<IBrokerProcess>();
    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
	private final IIsolatedProcess.Stub mBinder = new IIsolatedProcess.Stub() {
		
	        @Override
	        public int sayHello(int a,int b) throws RemoteException {
	            Log.d(TAG, "called IsolatedService sayHello()");
	            final int len = mCallBacks.beginBroadcast();
	            for (int i = 0; i < len; i++) {
	                try {
	                     // 通知回调
	                          mCallBacks.getBroadcastItem(i).getHello(a+b);
	                    } catch (RemoteException e) {
	                             e.printStackTrace();
	                    }
	           }
	           mCallBacks.finishBroadcast();
	           
	           return a+b;
	        }
	        @Override
	        public Object handBinder(int a) throws RemoteException{
	        	if (!isMainThread()) {
		            throw new IllegalThreadStateException("Warning:not main thread！");
		        }
	        	Reflect getMainThread=Reflect.on(ActivityThread.currentActivityThread());
	   		    Object appThread = getMainThread.get("mAppThread");
	    	    return appThread;
	      }
	        @Override
	        public void registerCallBack(IBrokerProcess cb) throws RemoteException {
	            mCallBacks.register(cb);
	        } 
	        @Override
	        public void unregisterCallBack(IBrokerProcess cb) throws RemoteException {
	            mCallBacks.unregister(cb);
            }
	 };



	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
    public void helloWorld(){
    	Toast.makeText(this.getApplicationContext(), "Hello World! -from isolated process", Toast.LENGTH_SHORT).show();
    }
    public IsolatedProcess(){}
    

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mCallBacks.kill();
		super.onDestroy();
	};
}
