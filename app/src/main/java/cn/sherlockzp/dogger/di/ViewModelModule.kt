package cn.sherlockzp.dogger.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import cn.sherlockzp.dogger.ui.girl.GirlViewModel
import cn.sherlockzp.dogger.viewmodel.DoggerViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * 文 件 名: ViewModelModule
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/14 23:24
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GirlViewModel::class)
    abstract fun bindGirlViewModel(girlViewModel: GirlViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(doggerViewModelFactory: DoggerViewModelFactory): ViewModelProvider.Factory
}