package com.boxify.binderTest;


import com.kainny.testforplugin.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    private IIsolatedProcess mService;
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean mIsBound = false;
    private TextView tv;
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
            mService = null;
            try{
            	mService.unregisterCallBack(mBrokerProcess);
            }catch(RemoteException e){
            	e.printStackTrace();
            }
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
    }
		
	}

	private void callRemote() {
		// TODO Auto-generated method stub
		if (mService != null) {
            try {
                int result = mService.sayHello(1,2);
                Toast.makeText(this, "The result is "+result, Toast.LENGTH_SHORT).show();
                Binder appBind = mService.handBinder(result);
                Toast.makeText(this,appBind.toString(),Toast.LENGTH_SHORT);
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
