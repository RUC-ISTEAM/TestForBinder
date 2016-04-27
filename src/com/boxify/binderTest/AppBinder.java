package com.boxify.binderTest;

import android.content.pm.ActivityInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class AppBinder extends Binder implements Parcelable {
    private IBinder mAppBinder;;
	public AppBinder(Parcel source) {
		// TODO Auto-generated constructor stub
		super();
		try {
			this.setAppThread(source.readStrongBinder());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		Log.i("!", "writeToParcel"); 
		dest.writeStrongBinder(mAppBinder);
		
	}

	public void setAppThread(IBinder iBinder) {  
        this.mAppBinder = iBinder;  
    }  
 
    public IBinder getAppThread() {  
    	Log.i("!", "getAppThread");
        return mAppBinder;  
    }
	public static final Parcelable.Creator<AppBinder> CREATOR = new Creator<AppBinder>(){
		@Override
		         public AppBinder createFromParcel(Parcel source) {
			          Log.i("!", "createFromParcel");  
		              return new AppBinder(source);
		          }

		@Override
		public AppBinder[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}
	};

}
