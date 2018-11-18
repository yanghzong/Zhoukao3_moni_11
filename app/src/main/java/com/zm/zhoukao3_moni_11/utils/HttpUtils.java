package com.zm.zhoukao3_moni_11.utils;

import android.os.Handler;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zm.zhoukao3_moni_11.inter.ICallBack;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by 择木 on 2018/11/16.
 */

public class HttpUtils {

    private static  volatile HttpUtils instance;

    private OkHttpClient client;

    private Handler handler=new Handler();

    public HttpUtils(){
        client=new OkHttpClient();
    }


    public static  HttpUtils getInstance(){
        if (instance==null){
            synchronized (HttpUtils.class){
                if (instance==null){
                    instance=new HttpUtils();
                }
            }
        }
        return instance;
    }

    public void get(String url, final ICallBack callBack, final Type type){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();

        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    final Object o = gson.fromJson(result, type);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(o);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
