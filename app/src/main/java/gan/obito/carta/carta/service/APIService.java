package gan.obito.carta.carta.service;

import com.squareup.okhttp.RequestBody;

import gan.obito.carta.carta.model.Pemeriksaan;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Gasik Sambada on 10/26/2016.
 */
public interface APIService {
    @Headers( "Content-Type: application/json" )
    @POST("carta")
    Call<Pemeriksaan> calculateCarta(@Body RequestBody requestBody);
}
