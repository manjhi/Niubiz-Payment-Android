
class AuthenticationRepo {

    companion object {
        val retrofitService: ApiInterface by lazy {
            NetworkClient().provideRetrofit().create(ApiInterface::class.java)
        }
        val retrofitServiceCertificate: ApiInterface by lazy {
            NetworkClient().provideRetrofitCertificate().create(ApiInterface::class.java)
        }

        fun requests(): AuthenticationRepo {
            return AuthenticationRepo()

        }
    }

    fun getToken(listener: ResponseListner<ResponseBody>) {
        retrofitService.getToken(getHeaders()).enqueueCall {
            onResponse = {
                listener.onResponse(it)
            }
        }
    }

    fun certificate(
        id: String,
        header: HashMap<String, String>,
        listener: ResponseListner<ResponseBody>
    ) {
        retrofitServiceCertificate.certificate(header, id).enqueueCall {
            onResponse = {
                listener.onResponse(it)
            }
        }
    }
}
