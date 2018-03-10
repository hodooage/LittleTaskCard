package com.z.ho.littletaskcard.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.z.ho.littletaskcard.R;
import com.z.ho.littletaskcard.entity.Aim;
import com.z.ho.littletaskcard.entity.My;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class AimAdapter extends RecyclerView.Adapter<AimAdapter.ViewHolder> {
    private Context mContext;

    private List<Aim> mAimList;

    static class ViewHolder extends  RecyclerView.ViewHolder{
        CardView cardView;
        ImageView aim_image,aim_importance_image;
        TextView aim_name;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView= ( CardView ) itemView;
            aim_name=itemView.findViewById(R.id.aim_name);
            aim_image=itemView.findViewById(R.id.aim_image);
            aim_importance_image=itemView.findViewById(R.id.aim_importance_image);
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
        final ViewHolder holder= new ViewHolder(view);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position=holder.getAdapterPosition();
                Toast.makeText(mContext, mAimList.get(position).getAimName(), Toast.LENGTH_SHORT).show();
                showDialog(position);
                return true;
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Aim aim=mAimList.get(position);
        holder.aim_name.setText(aim.getAimName());
        Glide.with(mContext).load(aim.getAimImageId()).into(holder.aim_image);
        switch (aim.getImportance()){
            case 0:
                holder.aim_importance_image.setImageResource(R.drawable.heart1);
                break;
            case 1:
                holder.aim_importance_image.setImageResource(R.drawable.heart2);
                break;
            case 2:
                holder.aim_importance_image.setImageResource(R.drawable.heart3);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mAimList.size();
    }

    public void showDialog(final int position){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
        alertDialog.setIcon(R.drawable.warning);
        alertDialog.setTitle("完成确认");
        alertDialog.setMessage("确定完成任务了吗？");
        alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(mContext, "点击了"+position, Toast.LENGTH_SHORT).show();
                Aim currentAim=mAimList.get(position);

                //更改任务卡的状态
                if(currentAim.isSaved()){
                    currentAim.setState(1);
                    currentAim.save();
                }

                //增加我的爱心值
                My my=DataSupport.findFirst(My.class);
                if(null==my){
                    my=new My();
                    my.setMyName("小可爱");
                    my.setToDefault("myHeart");
                    my.setMyHeart(my.getMyHeart()+currentAim.getImportance()+1);
                    my.save();
                }else{
                    my.setMyHeart(my.getMyHeart()+currentAim.getImportance()+1);
                    my.save();
                }


            }
        });
        alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }
}
