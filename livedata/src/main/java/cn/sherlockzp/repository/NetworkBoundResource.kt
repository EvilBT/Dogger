package cn.sherlockzp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import cn.sherlockzp.livedata.retrofit.ApiResponse
import cn.sherlockzp.vo.Resource

/**
 * 文 件 名: NetworkBoundResource
 * 创建日期: 2018/3/18 16:11
 * 修改时间:
 * 修改备注: 一个泛型类，是谷歌示例中的 从网络及sqlite数据库中提供资源
 */
abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor(){
    private val result : MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        result.value = Resource.loading(null)
        val dbSource = this.loadFromDb()
        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch(it)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource){
                    setValue(Resource.success(it))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (newValue != result.value) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        // 这里我们重新连接DbSource作为新数据来源，它将快速发送其最新值
        result.addSource(dbSource){
            setValue(Resource.loading(it))
        }

        result.addSource(apiResponse) {
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            it?.let { response ->
                if (response.isSuccessful()) {
                    disk {
                        saveCallResult(processResponse(response))
                        main {
                            // 上面保存了最新的数据到数据库中，我们这里需要重新
                            // 请求最新的本地数据库中的数据，否则我们可能无法使用从网络收到的最新结果进行更新
                            result.addSource(loadFromDb()){
                                setValue(Resource.success(it))
                            }
                        }
                    }
                } else {
                    onFetchFailed()
                    result.addSource(dbSource){
                        setValue(Resource.error(response.msg,it))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {
    }

    fun asLiveData() = result

    @WorkerThread
    protected open fun processResponse(response: ApiResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)

    /**
     * 是否刷新数据
     * @return true 重新从网络上获取最新数据
     *         false 直接使用数据库的数据
     */
    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}