package com.zm.zhoukao3_moni_11.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zm.zhoukao3_moni_11.R;
import com.zm.zhoukao3_moni_11.bean.NewsBean;

import java.util.List;

/**
 * Created by 择木 on 2018/11/16.
 */

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private Context context;
    private List<NewsBean.DataBean> list;

    public NewsAdapter(Context context, List<NewsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_news, null);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder.imgShow);
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvName.setText(list.get(position).getAuthor_name());
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvTitle;
        private final ImageView imgShow;
        private final ImageView imgDel;

        public ViewHolder(View itemView) {
            super(itemView);
            imgShow = itemView.findViewById(R.id.img_show);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvName = itemView.findViewById(R.id.tv_name);
            imgDel = itemView.findViewById(R.id.img_del);
        }
    }

    //1.定义接口
    public interface OnItemClickListener {
        void onItemClick(View view, String data);
    }

    //定义成员变量
    private OnItemClickListener mOnItemClickListener;

    //设置方法暴露给外界
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    ////////////添加数据和删除数据///////////////
    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,getItemCount());
    }
}
