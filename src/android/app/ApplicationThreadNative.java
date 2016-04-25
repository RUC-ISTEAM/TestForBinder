package android.app;

import android.os.Binder;
import android.os.IBinder;

public abstract class ApplicationThreadNative extends Binder
implements IApplicationThread {	
	

static public IApplicationThread asInterface(IBinder obj) {
	return null;
}
}