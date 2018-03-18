package cn.sherlockzp.dogger.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import cn.sherlockzp.dogger.vo.Result

/**
 * 文 件 名: DoggerDb
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/18 14:54
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
@Database(entities = [Result::class], version = 1)
@TypeConverters(Converters::class)
abstract class DoggerDb : RoomDatabase() {

    abstract fun resultDao(): ResultDao
}