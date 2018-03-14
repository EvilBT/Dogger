package cn.sherlockzp.dogger.di

import android.app.Application
import cn.sherlockzp.dogger.DoggerApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * 文 件 名: AppComponent
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/14 22:36
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(doggerApp: DoggerApp)
}