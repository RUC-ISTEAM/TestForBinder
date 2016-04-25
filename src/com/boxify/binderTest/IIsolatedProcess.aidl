package com.boxify.binderTest;
import com.boxify.binderTest.IBrokerProcess;

interface IIsolatedProcess {
        int sayHello(int a,int b);
        Object handBinder(int a);
        //注册回调接口
        void registerCallBack(IBrokerProcess cb);
        void unregisterCallBack(IBrokerProcess cb);
}


