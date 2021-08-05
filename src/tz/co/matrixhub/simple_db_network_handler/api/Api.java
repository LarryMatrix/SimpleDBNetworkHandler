package tz.co.matrixhub.simple_db_network_handler.api;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.PUT;
import retrofit2.http.POST;
import retrofit2.http.PATCH;
import retrofit2.http.Path;



public interface Api {

    @GET("getting-endpoint/")
    Call<List<ModelName>> getEndpoint(@HeaderMap Map<String, String> headers);

    @POST("posting-endpoint/")
    Call<ModelName> postEndpoint(@HeaderMap Map<String, String> headers, @Body ModelName modelName);

    @PATCH("patching-endpoint/{id}/")
    Call<ModelName> patchEndpoint(@HeaderMap Map<String, String> headers, @Path("id") int id, @Body ModelName modelName);

    @PUT("putting-endpoint/{id}/")
    Call<ModelName> putEndpoint(@HeaderMap Map<String, String> headers, @Path("id") int id, @Body ModelName modelName);

}
