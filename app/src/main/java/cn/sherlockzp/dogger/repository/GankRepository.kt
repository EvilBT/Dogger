package cn.sherlockzp.dogger.repository

import android.arch.lifecycle.LiveData
import cn.sherlockzp.dogger.api.GangService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GankRepository @Inject constructor(private val gankService: GangService) {

    fun loadGank(type: String, size: Int, page: Int)
            = gankService.getGank(type,size,page)
}