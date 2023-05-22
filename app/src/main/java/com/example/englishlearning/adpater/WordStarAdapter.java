package com.example.englishlearning.adpater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishlearning.Bean.WordListBean;
import com.example.englishlearning.R;

import java.util.ArrayList;
import java.util.List;

public class WordStarAdapter extends RecyclerView.Adapter<WordStarAdapter.InnerHolder> {


    public List<WordListBean> mData;

    public WordStarAdapter(List<WordListBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //将XML布局文件，加载到Java代码（和Activity的onCreate()的setContentVIew()作用一样）
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word_list, null, false);
        //设置背景也可以在XML中设置
//        view.setBackgroundResource(R.drawable.item_press);
//        view.setClickable(true);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.word.setText(mData.get(position).getWord());
        holder.trans.setText(mData.get(position).getTrans());


        //给成员接口变量（onClickListener，onLongClickListener）进行非空判断，非空触发回调
        if (onClickListener != null) {
            holder.search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v, position);
                }
            });
        }
        if (onLongClickListener != null) {
            holder.search.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongClickListener.onLongClick(v, position);
                    return true;
                }
            });
        }


    }



    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }






    /**
     * 内部类，继承RecyclerView.ViewHolder，作用就是声明item中的控件，findViewById
     */
    public class InnerHolder extends RecyclerView.ViewHolder {
        public TextView word;
        public TextView trans;
        public ImageView search;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.text_itls_word);
            trans = itemView.findViewById(R.id.text_itls_mean);
            search=itemView.findViewById(R.id.img_itls_search);
        }
    }



    /**
     * 点击、长按监听三件套
     */
    //1.定义接口
    public interface OnClickListener {
        void onClick(View v, int position);
    }

    public interface OnLongClickListener {
        void onLongClick(View v, int position);
    }

    //2.声明接口
    private OnClickListener onClickListener;
    private OnLongClickListener onLongClickListener;

    //3.set接口
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }


}
