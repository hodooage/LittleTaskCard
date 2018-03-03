package com.z.ho.littletaskcard.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
            cardView= ( CardView ) itemView;     }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Aim aim=mAimList.get(position);
        holder.aim_name.setText(aim.aimName);
    }


    @Override
    public int getItemCount() {
        return mAimList.size();
    }
}
