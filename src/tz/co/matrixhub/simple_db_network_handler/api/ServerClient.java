package tz.co.matrixhub.simple_db_network_handler.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerClient {

    private static final String API_URL = "http://your_base_url/base_path";

    private Retrofit retrofit;
    private static ServerClient serverClient;

    private ServerClient() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request newRequest = originalRequest.newBuilder()
                                .header("Accept", "application/json")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request newRequest = originalRequest.newBuilder()
                                .header("Content-Type", "application/json")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        retrofit = new Retrofit
                .Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized ServerClient getInstance() {
        if (serverClient == null) {
            serverClient = new ServerClient();
        }
        return serverClient;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
