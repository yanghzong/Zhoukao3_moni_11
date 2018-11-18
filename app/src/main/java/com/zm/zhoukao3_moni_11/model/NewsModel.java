package com.zm.zhoukao3_moni_11.model;

import com.zm.zhoukao3_moni_11.inter.ICallBack;
import com.zm.zhoukao3_moni_11.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by 择木 on 2018/11/17.
 */

public class NewsModel {
    public void getShow(String url, ICallBack callBack, Type type) {
        HttpUtils.getInstance().get(url, callBack, type);
    }
}
