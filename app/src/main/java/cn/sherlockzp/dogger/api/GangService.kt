package cn.sherlockzp.dogger.api

import android.arch.lifecycle.LiveData
import cn.sherlockzp.dogger.vo.Gank
import retrofit2.http.GET
import retrofit2.http.Path


interface GangService {

    @GET("api/data/{type}/{size}/{page}")
    fun getGank(@Path("type") type: String,
                @Path("size") size: Int,
                @Path("page") page: Int): LiveData<ApiResponse<Gank>>
}