package cn.sherlockzp.dogger.vo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import cn.sherlockzp.dogger.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import xyz.zpayh.adapter.BaseViewHolder
import xyz.zpayh.adapter.DefaultMultiItem
import java.util.*

data class Gank(
		var error: Boolean,
		var results: List<Result>
)

@Entity
data class Result(
		@PrimaryKey
		var _id: String = "",
		var createdAt: Date = Date(),
		var desc: String = "",
		@Ignore
		var images: List<String> = arrayListOf(),
		var publishedAt: String = "",
		var source: String= "",
		var type: String= "",
		var url: String= "",
		var used: Boolean = false,
		var who: String= ""
)

class Girl(result: Result) : DefaultMultiItem<Result>(R.layout.app_girl_item, result)  {
	/**
	 * 进行数据处理，显示文本，图片等内容
	 * @param holder Holder Helper
	 */
	override fun convert(holder: BaseViewHolder) {
		holder.setImage(R.id.iv_girl) {
			Glide.with(it.context).load(data.url)
					.apply(RequestOptions.errorOf(R.mipmap.ic_launcher))
					.apply(RequestOptions.centerCropTransform())
					.into(it)
		}
	}
}