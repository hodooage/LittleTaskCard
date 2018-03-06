package com.z.ho.littletaskcard;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.z.ho.littletaskcard.databinding.ActivityAddAimBinding;
import com.z.ho.littletaskcard.entity.Aim;

import java.util.Random;

import static com.z.ho.littletaskcard.R.layout.activity_add_aim;

public class AddAimActivity extends AppCompatActivity {
    ActivityAddAimBinding mBinding;

    private int importance=0;

    private int[] defaultPictures={
            R.drawable.r1,
            R.drawable.r2,
            R.drawable.r3,
            R.drawable.r4,
            R.drawable.r5,
            R.drawable.r6,
            R.drawable.r7,
            R.drawable.r8
    };

    private int[] pictures=defaultPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_add_aim);

        //沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        initView();
        initListener();
    }

    public void initView(){
        mBinding= DataBindingUtil.setContentView(this,activity_add_aim);
    }

    public void initListener(){
        //seekBar改变时设置重要性和图片库
        mBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                importance=i;
                pictures=returnPictures(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //骰子按钮根据图库随机
        mBinding.random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomImage();
            }
        });
    }


    //根据重要性返回不同的图库
    public int[] returnPictures(int i){
        switch (i){
            case 0:
                return new int[]{
                        R.drawable.r1,
                        R.drawable.r2,
                        R.drawable.r3,
                        R.drawable.r4,
                        R.drawable.r5,
                        R.drawable.r6,
                        R.drawable.r7,
                        R.drawable.r8
                };
            case 1:
                return new int[]{
                        R.drawable.sr1,
                        R.drawable.sr2,
                        R.drawable.sr3,
                        R.drawable.sr4,
                        R.drawable.sr5,
                };
            case 2:
                return new int[]{
                        R.drawable.ssr1,
                        R.drawable.ssr2,
                        R.drawable.ssr3
                };
            default:
                return new int[]{0};
        }
    }

    //随机图片
    public void randomImage(){
        Random random=new Random();
        int index=random.nextInt(pictures.length);
        Glide.with(this).load(pictures[index]).into(mBinding.imageView);
    }

    //创建目标
    public Aim createAim(String aimName, int aimImage){

        return null;
    }

    public static void  startAction(Context context){
        Intent intent=new Intent(context,AddAimActivity.class);
        context.startActivity(intent);
    }
}
