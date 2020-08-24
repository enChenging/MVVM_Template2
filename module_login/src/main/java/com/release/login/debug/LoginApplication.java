package com.release.login.debug;

import com.release.base.base.BaseApplication;
import com.release.base.config.ModuleLifecycleConfig;

/**
 * @author Mr.release
 * @create 2019/5/15
 * @Describe
 */
public class LoginApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }

}
