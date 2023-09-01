package cl.cat2814.tdcertificacion.model.remoteData

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperheroClient {

    companion object {
        private const val URL_BASE = "https://y-mariocanedo.vercel.app/"

        fun getRetrofitSuperhero(): SuperheroApi {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(SuperheroApi::class.java)
        }
    }
}
