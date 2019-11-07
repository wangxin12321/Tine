package com.example.administrator.tine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.administrator.tine.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class AddTaskAdapter extends RecyclerView.Adapter<AddTaskAdapter.MyViewHolder> {

    private Context context;
    private List<String> list;

    public AddTaskAdapter(Context context,List<String> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public AddTaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.addtask_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddTaskAdapter.MyViewHolder holder,final int position) {
        holder.tvaddTask.setText(list.get(position));
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 1) {
                    Snackbar.make(v, "此条目不能删除", Snackbar.LENGTH_SHORT).show();
                } else {
                    //删除自带默认动画
                    removeData(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(int position){
        list.add(position,"我是商品"+position);
        notifyItemInserted(position);
    }

    public void removeData(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvaddTask,tvDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvaddTask=itemView.findViewById(R.id.tv_add_task);
            tvDelete=itemView.findViewById(R.id.tv_delete_task);
        }
    }
}
