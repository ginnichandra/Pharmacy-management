package com.example.team;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;

    // SCHOOL IP ADDRESS
//        private static final String BASE_URL = "http://192.168.3.83:8080/";

    // HOME IP ADDRESS
//    private static final String BASE_URL = "http://192.168.0.169:8080/";

    //ABHINAV
    private static final String BASE_URL = "http://192.168.2.35:8080/";

//    private static final String BASE_URL = "http://192.168.2.39:8080/";

    public static Retrofit getRetrofitInstance(Boolean header, final String key) {

        if (header) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                            Request originalRequest = chain.request();

                            Request newRequest = originalRequest.newBuilder()
                                    .header("Authorization", " Bearer " + key)
                                    .build();

                            return chain.proceed(newRequest);
                        }
                    })
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            return retrofit;
        } else {
            if (retrofit == null) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .build();

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
            }

            return retrofit;
        }

    }
}
