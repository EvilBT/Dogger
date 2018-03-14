package cn.sherlockzp.dogger.di

import cn.sherlockzp.dogger.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 文 件 名: MainActivityModule
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/14 23:28
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}