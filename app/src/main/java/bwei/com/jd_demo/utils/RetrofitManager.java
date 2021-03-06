package bwei.com.jd_demo.utils;

import android.arch.lifecycle.ViewModelProvider;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static String BASE_URL=Apis.LUN_URL;
    private final Retrofit retrofit;
    private static class SingleHoder{

        private static final RetrofitManager MANAGER=new RetrofitManager(BASE_URL);
    }

    public static RetrofitManager getDefault(){
        return SingleHoder.MANAGER;
    }
    private RetrofitManager(String baseUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkhttpClinet())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    private OkHttpClient buildOkhttpClinet() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .readTimeout(2000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
    public <T> T create(Class<T> Clazz) {
        return retrofit.create(Clazz);
    }
}
