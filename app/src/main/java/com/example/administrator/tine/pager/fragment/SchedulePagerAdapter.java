package com.example.administrator.tine.pager.fragment;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.tine.R;
import com.example.administrator.tine.bean.Databean;
import com.example.administrator.tine.view.TimeView;

import java.util.ArrayList;
import java.util.List;

class SchedulePagerAdapter extends RecyclerView.Adapter<SchedulePagerAdapter.MyViewHolder> {

    private Context context;
    private List<Databean> list;
    private String title;

    public SchedulePagerAdapter(Context context, List<Databean> list,String title) {
        this.context=context;
        this.list=list;
        this.title=title;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_time,viewGroup,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.timeView.setTime(list.get(i).getTime());
        myViewHolder.tv_time.setText(list.get(i).getTime());
        myViewHolder.tv_content.setText(list.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_content;
        TextView tv_time;
        TimeView timeView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content=itemView.findViewById(R.id.tv_content);
            tv_time=itemView.findViewById(R.id.tv_time);
            timeView=itemView.findViewById(R.id.timeView);
        }
    }
}
