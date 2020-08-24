package com.release.mvvm2;

import com.release.base.base.BaseApplication;
import com.release.base.config.ModuleLifecycleConfig;
import com.release.mvvm2.dao.DaoMaster;
import com.release.mvvm2.dao.DaoSession;
import com.release.mvvm2.dao.NewsTypeDao;

import org.greenrobot.greendao.database.Database;

/**
 * @author Mr.release
 * @create 2019/3/22
 * @Describe
 */
public class App extends BaseApplication {

    public DaoSession mDaoSession;
    private static App mContext;
    public static App getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
        init();
    }

    private void init() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "dao_db");
        Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        NewsTypeDao.updateLocalData(this, mDaoSession);
    }
}
