package com.boxify.binderTest;

import android.app.ActivityThread;
import android.app.Application;
import android.app.ApplicationThreadNative;
import android.os.Binder;
import com.boxify.reflect.*;




/**
 * @author Lody
 * @version 1.0
 */
public final class DelegateActivityThread {

    private static DelegateActivityThread SINGLETON = new DelegateActivityThread();

    private Reflect activityThreadReflect;

    public DelegateActivityThread() {
        activityThreadReflect = Reflect.on(ActivityThread.currentActivityThread());
    }

    public static DelegateActivityThread getSingleton() {
        return SINGLETON;
    }

    public Application getInitialApplication() {
        return activityThreadReflect.get("mInitialApplication");
    }

   /* public Instrumentation getInstrumentation() {
        return activityThreadReflect.get("mInstrumentation");
    }

    public void setInstrumentation(Instrumentation newInstrumentation) {
        activityThreadReflect.set("mInstrumentation", newInstrumentation);
    }
    */

}
