package com.z.ho.littletaskcard.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.z.ho.littletaskcard.R;
import com.z.ho.littletaskcard.entity.Aim;

import java.util.List;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class AimAdapter extends RecyclerView.Adapter<AimAdapter.ViewHolder> {
    private Context mContext;

    private List<Aim> mAimList;

    static class ViewHolder extends  RecyclerView.ViewHolder{
        CardView cardView;
        ImageView aim_image;
        TextView aim_name;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView= ( CardView ) itemView;
            aim_name=itemView.findViewById(R.id.aim_name);
            aim_image=itemView.findViewById(R.id.aim_image);
        }
    }

    public AimAdapter(List<Aim> aimList){
        mAimList=aimList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.aim_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Aim aim=mAimList.get(position);
        holder.aim_name.setText(aim.aimName);
        Glide.with(mContext).load(aim.getAimImageId()).into(holder.aim_image);

    }


    @Override
    public int getItemCount() {
        return mAimList.size();
    }
}
