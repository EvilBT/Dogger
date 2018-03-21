package cn.sherlockzp.dogger.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bind:imageUrl")
fun bindImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
            .load(url)
            //.apply(RequestOptions.centerCropTransform())
            .into(imageView)
}

private val FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault())

@BindingAdapter("bind:date")
fun bindDate(textView: TextView, date: Date) {
    textView.text = FORMAT.format(date)
}
