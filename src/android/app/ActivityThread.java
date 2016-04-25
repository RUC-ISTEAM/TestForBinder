package android.app;

import android.os.IBinder;

/**
 * NOTICE:不要混淆本类
 *
 * @author Lody
 * @version 1.0
 */
public final class ActivityThread {

    /**
     * NOTICE: 必须在UI线程调用本方法,否则返回NULL
     *
     * @return
     */
    public static ActivityThread currentActivityThread() {
        return null;
    }
    public class ApplicationThread extends ApplicationThreadNative {

		@Override
		public IBinder asBinder() {
			// TODO Auto-generated method stub
			return null;
		}
    }
    public static ApplicationThread getApplicationThread(){
    	return  null;
    }

}
