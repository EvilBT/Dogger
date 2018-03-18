package cn.sherlockzp.dogger.ui.girl

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import cn.sherlockzp.dogger.repository.GankRepository
import cn.sherlockzp.dogger.util.AbsentLiveData
import cn.sherlockzp.dogger.vo.Girl
import cn.sherlockzp.vo.Resource
import cn.sherlockzp.vo.Status
import javax.inject.Inject

/**
 * 文 件 名: MainViewModel
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/14 23:20
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
class GirlViewModel @Inject constructor(repository: GankRepository) : ViewModel() {
    private val girls : LiveData<Resource<List<Girl>>>
    val page = MutableLiveData<Int>()
    init {
        girls = Transformations.switchMap(page){
            if (it == null) {
                return@switchMap AbsentLiveData.create<Resource<List<Girl>>>()
            } else {
                return@switchMap Transformations.map(repository.load("福利",10, it)){
                    val data = it.data?.map { Girl(it) }
                    return@map when(it.status) {
                        Status.SUCCESS -> Resource.success(data)
                        Status.LOADING -> Resource.loading(data)
                        Status.ERROR -> Resource.error(it.message, data)
                    }
                }
            }
        }
    }

    fun setPage(page: Int) {
        this.page.value = page
    }

    fun getGirls() = girls
}