interface ApiInterface {

    @GET("api.security/v1/security")
    fun getToken(@HeaderMap header: HashMap<String, String>): Call<ResponseBody>

    @POST("api.certificate/v1/query/{merchantId}"/*"api.confirmation/v1/confirmation/ecommerce/{merchantId}"*/)
    fun certificate(
        @HeaderMap header: HashMap<String, String>,
        @Path("merchantId") merchantId: String
    ): Call<ResponseBody>


}
