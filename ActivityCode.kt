 AuthenticationRepo.requests().getToken(listener = {
            if (it.status) {
                var auth = it.data?.string().toString()
                var header = HashMap<String, String>()
                header["Authorization"] = auth
                header["Content-Type"] = "application/json"
                AuthenticationRepo.requests().certificate("522591303", header, listener = {
                    if (it.status) {
                        var json = JSONObject(it.data?.string().toString())
                        Log.e("DATA", json.getString("pinHash"))
                        var certi = json.getString("pinHash")
                        var data = HashMap<String, Any>()
                        data[VISANET_SECURITY_TOKEN] = auth
                        data[VISANET_CHANNEL] = "MOBILE"
                        data[VISANET_COUNTABLE] = true
                        data[VISANET_MERCHANT] = "522591303"
                        data[VISANET_PURCHASE_NUMBER] = getRandome().toString()
                        data[VISANET_AMOUNT] = 100.2
                       /* val MDDdata = HashMap<String, String>()
                        MDDdata["19"] = "LIM"
                        MDDdata["20"] = "AQP"
                        MDDdata["21"] = "AFKI345"
                        MDDdata["94"] = "ABC123DEF"

                        data[VISANET_MDD] = MDDdata*/
                        data[VISANET_ENDPOINT_URL] = "https://apisandbox.vnforappstest.com"

                        data[VISANET_REGISTER_NAME] = "John"
                        data[VISANET_REGISTER_LASTNAME] = "Smith"
                        data[VISANET_REGISTER_EMAIL] = "john@mailinator.com"
                        data[VISANET_CERTIFICATE_HOST] = "apitestenv.vnforapps.com"
                        data[VISANET_CERTIFICATE_PIN] = certi
                        val custom = VisaNetViewAuthorizationCustom()
                        custom.logoImage=R.drawable.mask_group_68
                        custom.buttonColorMerchant=resources.getColor(R.color.purple_200)
                        data[VISANET_USER_TOKEN]="manji@mailinator.com"

                        VisaNet.authorization(this, data,/*custom*/)
                    }
                })
//

            } else {
                Log.e("DATA", it.message)
            }
        })
        
        
        
        
        
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VISANET_AUTHORIZATION) {
            if (data != null) {
                if (resultCode == RESULT_OK) {
                    val JSONString = data.extras!!.getString("keySuccess")
                    val toast1 = Toast.makeText(applicationContext, JSONString, Toast.LENGTH_LONG)
                    Log.e("DATA", JSONString.toString())
                    toast1.show()
                } else {
                    var JSONString = data.extras!!.getString("keyError")
                    JSONString = JSONString ?: ""
                    val toast1 = Toast.makeText(applicationContext, JSONString, Toast.LENGTH_LONG)
                    Log.e("DATA", JSONString.toString())
                    toast1.show()
                }
            } else {
                val toast1 = Toast.makeText(applicationContext, "Cancel...", Toast.LENGTH_LONG)
                toast1.show()
            }
        }
    }
        
