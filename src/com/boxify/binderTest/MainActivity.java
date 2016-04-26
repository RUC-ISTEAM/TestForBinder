package com.boxify.binderTest;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.kainny.testforplugin.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.content.pm.*;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MainActivity extends Activity implements OnClickListener {
    private IIsolatedProcess mService;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TARGET = "com.kainny.boxify";
    private boolean mIsBound = false;
    private TextView tv;
    private IBinder appThread=null;
    private Bundle coreSettings =new Bundle();
    private Bundle testArgs = new Bundle();
    
    
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_SHORT).show();
            mService = IIsolatedProcess.Stub.asInterface(service);
            try{
            	mService.registerCallBack(mBrokerProcess);
            }catch(RemoteException e){
            	e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "Service disconnected", Toast.LENGTH_SHORT).show();
            
            try{
            	mService.unregisterCallBack(mBrokerProcess);
            }catch(RemoteException e){
            	e.printStackTrace();
            }
            mService = null;
        }
    };
    private IBrokerProcess mBrokerProcess = new IBrokerProcess.Stub() {
    	  
    	 @Override
    	 public void getHello(int result) throws RemoteException {
    	     String toastStr = "BrokerProcess get the result: ";
    	     toastStr+=result;
          //   tv.setText(toastStr);
            }
        };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.bind).setOnClickListener(this);
        findViewById(R.id.unbind).setOnClickListener(this);
        findViewById(R.id.call).setOnClickListener(this);
        findViewById(R.id.bindApp).setOnClickListener(this);
        tv = (TextView) this.findViewById(R.id.tv);
        
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
        case R.id.bind:
        	Intent intent = new Intent("com.boxify.binderTest.IIsolatedProcess");
            bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
            mIsBound = true;
            Toast.makeText(this, "Try to binding...", Toast.LENGTH_SHORT);
            break;
        case R.id.unbind:
        	if (mIsBound) {
                unbindService(mServiceConnection);
                Toast.makeText(this, "Try to unbinding...", Toast.LENGTH_SHORT);
                mIsBound = false;
            }
            break;
        case R.id.call:
            callRemote();
            break;
        case R.id.bindApp:
        	bindApp();
        	break;
        	
        
    }
		
	}
    private void bindApp(){
    	if(appThread != null){
            Parcel data =Parcel.obtain();
//           
   		    data.writeInterfaceToken("android.app.IApplicationThread");
   		    data.writeString(TARGET);
   		    ApplicationInfo appInfo = null;
			try {
				appInfo = getPackageManager().getApplicationInfo(TARGET, 0);
			} catch (NameNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
   		    appInfo.writeToParcel(data,0);
   		    List<ProviderInfo> providers = null;
   		    data.writeTypedList(providers);//providers
   		    data.writeInt(0);//testname ==NULL
   		    String profileName =null;
   		    data.writeString(profileName);
   		    data.writeInt(0);//profileFd
   		    data.writeInt(0);//autoStopProfiler
   		    
   		    //testArgs.putInt("args", 0x4C444E42);
   		    
   		    //testArgs.putString(profileName, profileName);
   		    data.writeBundle(testArgs);//testArgs
            
   		    data.writeStrongInterface(null);//testWatcher
   		    data.writeInt(0);//debugMode;
   		    data.writeInt(0);//openGLtrace
   		    data.writeInt(0);//restricted backup mode
   		    data.writeInt(0);//persistent   		    
   		    Configuration config = new Configuration();
   		    config.setToDefaults();   		   
   		    config.locale=new Locale("en_US");
   		    //Toast.makeText(this, config.toString(), Toast.LENGTH_SHORT).show();
   		    config.writeToParcel(data, 0); 			
   	 	    data.writeInt(1);//
            data.writeInt(DisplayMetrics.DENSITY_DEFAULT);
            data.writeFloat(1.0f);
            data.writeFloat(1.0f);
            HashMap<String, IBinder> services = null;
   		    data.writeMap(services);//data.writeMap(services);
   		   
   		    data.writeBundle(coreSettings);//data.writeBundle(coreSettings);          
            try {
				appThread.transact(13, data, null, IBinder.FLAG_ONEWAY);
				Toast.makeText(this, "GoodNews", Toast.LENGTH_SHORT).show();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            data.recycle();
            
    	}
    	else{
    		Toast.makeText(this, "Cannot get Appthread", Toast.LENGTH_SHORT).show();
    	}
    }
	private void callRemote() {
		// TODO Auto-generated method stub
		if (mService != null) {
            try {
                //int result = mService.sayHello(1,2);
                //Toast.makeText(this, "The result is "+result, Toast.LENGTH_SHORT).show();
                AppBinder appBinder = mService.handBinder();
                appThread = appBinder.getAppThread();
                Toast.makeText(this,appThread.toString(),Toast.LENGTH_SHORT).show();
                
            } catch (RemoteException e) {
                e.printStackTrace();
                Toast.makeText(this, "Remote call error!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Service is not available yet!", Toast.LENGTH_SHORT).show();
        }
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mIsBound) {
            unbindService(mServiceConnection);
        }
		
	}
	
}
