package cn.sherlockzp.vo

/**
 * 文 件 名: Resource
 * 创 建 人: 陈志鹏
 * 创建日期: 2018/3/18 16:02
 * 邮   箱: ch_zh_p@qq.com
 * 修改时间:
 * 修改备注:
 */
data class Resource<T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null)
        fun <T> error(msg: String?, data: T?) = Resource(Status.ERROR, data, msg)
        fun <T> loading(data: T?) = Resource(Status.LOADING, data, null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}