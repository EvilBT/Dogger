package cn.sherlockzp.repository

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

/**
 * 文 件 名: AppExecutors
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/18 16:12
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
private val diskIo = Executors.newSingleThreadExecutor()
private val networkIo = Executors.newFixedThreadPool(3)
private val mainThreadHandler = Handler(Looper.getMainLooper())

fun disk(runnable: ()->Unit) {
    diskIo.execute(runnable)
}

fun network(runnable: ()->Unit) {
    networkIo.execute(runnable)
}

fun main(runnable: ()->Unit) {
    mainThreadHandler.post(runnable)
}