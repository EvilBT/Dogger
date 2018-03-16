package cn.sherlockzp.dogger.api

import retrofit2.Response
import java.io.IOException


class ApiResponse<T> {
    val code: Int
    val body: T?
    val msg: String?

    constructor(error: Throwable) {
        code = 500
        body = null
        msg = error.message
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            msg = null
        } else {
            var message : String? = null
            response.errorBody()?.let {
                try {
                    message = it.string()
                } catch (e: IOException) {
                    // Ignored
                }
            }
            if (message.isNullOrEmpty()) {
                message = response.message()
            }
            msg = message
            body = null
        }
    }

    fun isSuccessful() = code in 200 until 300
}