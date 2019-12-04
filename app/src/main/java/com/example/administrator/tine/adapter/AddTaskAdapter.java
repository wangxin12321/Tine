package com.example.administrator.tine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.administrator.tine.R;
import com.example.administrator.tine.bean.Task;
import com.example.administrator.tine.db.TaskDao;
import com.google.android.material.snackbar.Snackbar;


import java.util.List;


public class AddTaskAdapter extends RecyclerView.Adapter<AddTaskAdapter.MyViewHolder> {
    private Context context;
    private List<Task> list;
    private TaskDao taskDao;

    public AddTaskAdapter(Context context, List<Task> list, TaskDao taskDao) {
        this.taskDao=taskDao;
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
    public void onBindViewHolder(@NonNull final AddTaskAdapter.MyViewHolder holder, final int position) {
        holder.tvaddTask.setText(list.get(position).task);
        holder.tvaddTaskDate.setText(list.get(position).date);
        holder.tvaddTaskTime.setText(list.get(position).time);
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 0) {
                    Snackbar.make(v, "没有内容了", Snackbar.LENGTH_SHORT).show();
                } else {
                    //删除自带默认动画
                    Task task=new Task();
                    task.setTask(list.get(position).task);
                    task.setDate(list.get(position).date);
                    task.setTime(list.get(position).time);
                    removeData(position);
                    taskDao.del(task);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(int position,Task task){
        list.add(position,task);
        notifyItemInserted(position);
    }

    public void removeData(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvaddTask,tvDelete,tvaddTaskDate,tvaddTaskTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvaddTaskDate=itemView.findViewById(R.id.tv_add_task_date);
            tvaddTaskTime=itemView.findViewById(R.id.tv_add_task_time);
            tvaddTask=itemView.findViewById(R.id.tv_add_task_name);
            tvDelete=itemView.findViewById(R.id.tv_delete_task);
        }
    }

//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView tvaddTaskName,tvDelete,tvaddTaskDate,tvaddTaskTime;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvaddTaskName=itemView.findViewById(R.id.tv_add_task_name);
//            tvaddTaskDate=itemView.findViewById(R.id.tv_add_task_date);
//            tvaddTaskTime=itemView.findViewById(R.id.tv_add_task_time);
//            tvDelete=itemView.findViewById(R.id.tv_delete_task);
//        }
//    }


}
