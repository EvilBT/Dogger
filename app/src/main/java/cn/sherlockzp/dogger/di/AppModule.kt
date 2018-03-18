package cn.sherlockzp.dogger.di

import android.app.Application
import android.arch.persistence.room.Room
import cn.sherlockzp.dogger.api.GangService
import cn.sherlockzp.dogger.db.DoggerDb
import cn.sherlockzp.livedata.retrofit.calladapter.LiveDataCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
            .create()


    @Singleton
    @Provides
    fun provideGankService(gson: Gson) = Retrofit.Builder()
            .baseUrl("http://gank.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .build()
            .create(GangService::class.java)

    @Singleton
    @Provides
    fun provideDb(app: Application) = Room.databaseBuilder(
            app, DoggerDb::class.java, "dogger.db"
    ).build()
}