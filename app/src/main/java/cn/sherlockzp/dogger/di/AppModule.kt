package cn.sherlockzp.dogger.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * 文 件 名: AppModule
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/14 22:43
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton @Provides
    fun provideSherlockName() = "Sherlock Holmes"
}