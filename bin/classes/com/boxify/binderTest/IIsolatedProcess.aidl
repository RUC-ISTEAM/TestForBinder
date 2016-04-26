package com.boxify.binderTest;
import com.boxify.binderTest.IBrokerProcess;
import com.boxify.binderTest.AppBinder;
interface IIsolatedProcess {
        int sayHello(int a,int b);
        AppBinder handBinder();
        //注册回调接口
        void registerCallBack(IBrokerProcess cb);
        void unregisterCallBack(IBrokerProcess cb);
}


