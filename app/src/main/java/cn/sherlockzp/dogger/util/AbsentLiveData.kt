package cn.sherlockzp.dogger.util

import android.arch.lifecycle.LiveData

/**
 * 文 件 名: AbsentLiveData
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/18 23:26
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
class AbsentLiveData<T> private constructor(): LiveData<T>(){
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}