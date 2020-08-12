package axian.upax.colaboradores.respository.remote.services

import retrofit2.Call
import retrofit2.http.GET

interface ContactApiService {
    @GET
    fun getContanctService(): Call<ResponseContactService>
}