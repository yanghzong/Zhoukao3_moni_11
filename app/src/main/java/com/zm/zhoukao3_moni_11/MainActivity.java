package com.zm.zhoukao3_moni_11;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zm.zhoukao3_moni_11.adapter.NewsAdapter;
import com.zm.zhoukao3_moni_11.bean.NewsBean;
import com.zm.zhoukao3_moni_11.presenter.NewsPresenter;
import com.zm.zhoukao3_moni_11.view.Iview;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Iview <NewsBean>{

    private ImageView imgBack;
    private ImageView imgStar;
    private RecyclerView rvShow;
    private List<NewsBean.DataBean> list;
    private NewsAdapter newsAdapter;
    private TextView tvTou;
    private NewsPresenter presenter;
    private ImageView imgYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        presenter = new NewsPresenter();
        presenter.attach( this);
        presenter.getShow();



        list = new ArrayList<>();

        newsAdapter = new NewsAdapter(this,list);
        rvShow.setAdapter(newsAdapter);

        rvShow.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


    }

    private void initView() {
        imgBack = findViewById(R.id.img_back);
        imgStar = findViewById(R.id.img_star);
        rvShow = findViewById(R.id.rv_show);
        imgYes = findViewById(R.id.img_yes);
       /* tvTou = findViewById(R.id.tv_tou);*/

        imgBack.setOnClickListener(this);
        imgStar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();

                break;
          case R.id.img_star:
             /* ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(imgStar,"alpha",0f,0.3f,0.6f,0.9f,1f);
              objectAnimator1.setDuration(5000);
              Glide.with(this).load(R.mipmap.yes).into(imgStar);
              objectAnimator1.start();*/
             /* ObjectAnimator XAnimator = ObjectAnimator.ofFloat(imgStar, "translationX", 0f, -670f);
              ObjectAnimator YAnimator = ObjectAnimator.ofFloat(imgStar, "translationY", 0f, 1110f);
              ObjectAnimator alpha = ObjectAnimator.ofFloat(imgStar, "alpha", 0.2f,0.4f,0.6f,0.8f,1f);
              AnimatorSet set = new AnimatorSet();
              set.play(XAnimator).with(YAnimator).with(alpha);
              set.setDuration(3000);
              set.start();
              set.addListener(new AnimatorListenerAdapter() {
                  public void onAnimationEnd(Animator animation) {
                      super.onAnimationEnd(animation);
                      //动画执行结束后变为选中
                      imgStar.setImageDrawable(getResources().getDrawable(R.mipmap.yes));

                  }

              });*/


              ObjectAnimator move = ObjectAnimator.ofFloat(imgStar, "translationY", 0f, 1700f);
              ObjectAnimator scale2 = ObjectAnimator.ofFloat(imgStar, "translationX",0f,-1000f);

              ObjectAnimator alpha=ObjectAnimator.ofFloat(imgStar,"alpha",1f,0.7f,0.6f,0.3f,0.1f);

              final AnimatorSet animatorSet=new AnimatorSet();
              animatorSet.playTogether(move, scale2, alpha);

              animatorSet.setDuration(5000);

              animatorSet.start();

              animatorSet.addListener(new Animator.AnimatorListener() {
                  @Override
                  public void onAnimationStart(Animator animation) {


                  }

                  @Override
                  public void onAnimationEnd(Animator animation) {
                      Glide.with(MainActivity.this).load(R.mipmap.yes).into(imgYes);

                  }

                  @Override
                  public void onAnimationCancel(Animator animation) {


                  }

                  @Override
                  public void onAnimationRepeat(Animator animation) {

                  }
              });

              break;





        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        
    }

    @Override
    public void getShow(NewsBean newsBean) {
        if (newsBean != null) {
            List<NewsBean.DataBean> data = newsBean .getData();
            if (data != null) {
                list.clear();
                list.addAll(data);
                newsAdapter.notifyDataSetChanged();
            }
        }

    }
  /*  public void img_back(View v){
        Toast.makeText(this, "123456", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
