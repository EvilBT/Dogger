package cn.sherlockzp.dogger

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Toast
import cn.sherlockzp.dogger.di.DaggerAppComponent
import cn.sherlockzp.dogger.di.Injectable
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * 文 件 名: DoggerApp
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/14 21:41
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
class DoggerApp : Application(), HasActivityInjector{

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        init(this)
    }
}

private fun init(doggerApp: DoggerApp) {

    toast = Toast.makeText(doggerApp,"",Toast.LENGTH_SHORT)

    DaggerAppComponent.builder().application(doggerApp)
            .build().inject(doggerApp)

    doggerApp.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityStarted(activity: Activity?) {
        }

        override fun onActivityDestroyed(activity: Activity?) {
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            handleActivity(activity)
        }
    })
}

private fun handleActivity(activity: Activity?) {
    if (activity is HasSupportFragmentInjector) {
        AndroidInjection.inject(activity)
    }

    if (activity is FragmentActivity) {
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks(){
            override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
                if (f is Injectable) {
                    AndroidSupportInjection.inject(f)
                }
            }
        }, true)
    }
}

private lateinit var toast: Toast

fun toast(msg: String) {
    toast.setText(msg)
    toast.show()
}