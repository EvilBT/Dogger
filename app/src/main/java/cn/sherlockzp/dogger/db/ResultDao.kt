package cn.sherlockzp.dogger.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import cn.sherlockzp.dogger.vo.Result

/**
 * 文 件 名: ResultDao
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/18 14:52
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(results: List<Result>): List<Long>

    @Query("SELECT * FROM Result")
    fun loadAllResult(): LiveData<List<Result>>

    @Query("SELECT * FROM Result WHERE type = :type")
    fun loadResultWithType(type: String): LiveData<List<Result>>
}