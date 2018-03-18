package cn.sherlockzp.dogger.repository

import android.arch.lifecycle.LiveData
import cn.sherlockzp.dogger.api.GangService
import cn.sherlockzp.dogger.db.DoggerDb
import cn.sherlockzp.dogger.vo.Gank
import cn.sherlockzp.dogger.vo.Result
import cn.sherlockzp.livedata.retrofit.ApiResponse
import cn.sherlockzp.repository.NetworkBoundResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GankRepository @Inject constructor(
        private val gankService: GangService,
        private val db: DoggerDb) {

    fun load(type: String, size: Int, page: Int)
        = object : NetworkBoundResource<List<Result>,Gank>(){
        override fun saveCallResult(item: Gank?) {
            item?.let {
                db.resultDao().insert(item.results)
            }
        }

        override fun shouldFetch(data: List<Result>?) = data == null || data.isEmpty()

        override fun loadFromDb() = db.resultDao().loadAllResult()

        override fun createCall(): LiveData<ApiResponse<Gank>> = gankService.getGank(type, size, page)
    }.asLiveData()
}