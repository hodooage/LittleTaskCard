package com.z.ho.littletaskcard;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.z.ho.littletaskcard.adapter.AimAdapter;
import com.z.ho.littletaskcard.databinding.ActivityMainBinding;
import com.z.ho.littletaskcard.entity.Aim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    private Aim[] aims={
            new Aim("r1",R.drawable.r1),
            new Aim("r2",R.drawable.r2),
            new Aim("r3",R.drawable.r3),
            new Aim("r4",R.drawable.r4),
            new Aim("r5",R.drawable.r5),
            new Aim("r6",R.drawable.r6),
            new Aim("r7",R.drawable.r7),
            new Aim("r8",R.drawable.r8),
            new Aim("sr1",R.drawable.sr1),
            new Aim("sr2",R.drawable.sr2),
            new Aim("sr3",R.drawable.sr3),
            new Aim("sr4",R.drawable.sr4),
            new Aim("sr5",R.drawable.sr5),
            new Aim("ssr1",R.drawable.ssr1),
            new Aim("ssr2",R.drawable.ssr2),
            new Aim("ssr3",R.drawable.ssr3),
    };
    private List<Aim> aimList=new ArrayList<>();

    private AimAdapter aimAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        initAims();

        GridLayoutManager layoutManager=new GridLayoutManager(this,1);

        mBinding.recycleView.setLayoutManager(layoutManager);

        aimAdapter=new AimAdapter(aimList);

        mBinding.recycleView.setAdapter(aimAdapter);

        mBinding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAimActivity.startAction(MainActivity.this);
            }
        });
    }

    private void initAims(){
        aimList.clear();
        for (int i=0;i<16;i++){
            Random random=new Random();
            int index=random.nextInt(aims.length);
            aimList.add(aims[index]);
        }
    }



}
