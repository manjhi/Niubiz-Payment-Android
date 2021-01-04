
class NetworkClient {
    internal fun getRequestHeader(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .build()
            chain.proceed(request)
        }
            .connectTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .readTimeout(300, java.util.concurrent.TimeUnit.SECONDS)
        return httpClient.build()

    }

    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apisandbox.vnforappstest.com/")
//            .baseUrl("https://apiprod.vnforapps.com/")
            .client(getRequestHeader())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    internal fun provideRetrofitCertificate(): Retrofit {
        return Retrofit.Builder()
//            .baseUrl("https://apisandbox.vnforappstest.com/")
            .baseUrl("https://apitestenv.vnforapps.com/")
//            .baseUrl("https://jobs.vnforapps.com/")
            .client(getRequestHeader())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
