package com.zm.zhoukao3_moni_11.presenter;

import com.google.gson.reflect.TypeToken;
import com.zm.zhoukao3_moni_11.bean.NewsBean;
import com.zm.zhoukao3_moni_11.inter.ICallBack;
import com.zm.zhoukao3_moni_11.model.NewsModel;
import com.zm.zhoukao3_moni_11.utils.HttpUtils;
import com.zm.zhoukao3_moni_11.view.Iview;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 择木 on 2018/11/17.
 */

public class NewsPresenter {
    private Iview iv;
    private NewsModel model;

    public void attach(Iview iv) {
        this.iv = iv;
        model = new NewsModel();
    }

    public void getShow() {
        String url = "http://www.xieast.com/api/news/news.php";

        Type type = new TypeToken<NewsBean>() {
        }.getType();

        model.getShow(url, new ICallBack() {

            @Override
            public void onSuccess(Object obj) {
                iv.getShow(obj);

            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }




        }, type);
    }



    public void detach() {
        if (iv != null) {
            iv = null;
        }
    }


}
