fun <T> Call<T>.enqueueCall(callback: CallBackKt<T>.() -> Unit) {

    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)

}


class CallBackKt<T> : Callback<T> {

    var onResponse: ((ApiResponse<T>) -> Unit)? = null
    // var onFailure: ((t: ApiResponse<Throwable>) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        var response = ApiResponse(
            status = false,
            message = t.message!!,
            data = t as T
        )
        onResponse?.invoke(response)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            var response1 = ApiResponse(
                status = response.isSuccessful,
                message = if (response.errorBody() == null) response.message() else response.errorBody()
                    ?.getError()!!,
                data = response.body()
            )
            onResponse?.invoke(response1)
        } else {


            var response1 = ApiResponse<T>(
                status = false,
                message = response.errorBody()?.string().toString(),
                data = null,
                statusCode = response.code()
            )
            onResponse?.invoke(response1)

        }
    }

}

fun ResponseBody.getError(): String? {
    return try {
        val jObjError = JSONObject(this.string())
        jObjError["Message"].toString()

    } catch (e: Exception) {
        e.message
    }.toString()
}

@SuppressLint("NewApi")
fun getHeaders(): HashMap<String, String> {
 //   val encodedString: String = Base64.getEncoder().encodeToString(string.toByteArray())
    var data = HashMap<String, String>()
    data["Authorization"] = "Basic Z2lhbmNhZ2FsbGFyZG9AZ21haWwuY29tOkF2MyR0cnV6"
    return data
}

fun getRandome(): Int {
    return (11111111..999999999).random()
}
