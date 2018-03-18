package cn.sherlockzp.dogger.db

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * 文 件 名: Converters
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/19 01:47
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
open class Converters {
    companion object {
        @JvmStatic
        @TypeConverter
        fun fromTimestamp(value: Long) = Date(value)

        @JvmStatic
        @TypeConverter
        fun dateToTimestamp(date: Date) = date.time
    }
}