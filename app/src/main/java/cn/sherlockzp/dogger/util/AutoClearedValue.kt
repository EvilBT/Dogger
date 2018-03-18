package cn.sherlockzp.dogger.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * 文 件 名: AutoClearedValue
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/18 18:02
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
class AutoClearedValue<T>(fragment: Fragment,private var value: T?) {

    init {
        fragment.fragmentManager?.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks(){
                    override fun onFragmentViewDestroyed(fm: FragmentManager?, f: Fragment?) {
                        if (f == fragment){
                            value = null
                            fragment.fragmentManager?.unregisterFragmentLifecycleCallbacks(this)
                        }
                    }
                }
        ,false)
    }

    fun get() = value
}