package com.z.ho.littletaskcard;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.z.ho.littletaskcard.adapter.AimAdapter;
import com.z.ho.littletaskcard.databinding.ActivityMainBinding;
import com.z.ho.littletaskcard.entity.Aim;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ActivityMainBinding mBinding;

    //用于随机的测试数据
//    private Aim[] aims={
//            new Aim("背十个单词",R.drawable.r1,0),
//            new Aim("r2",R.drawable.r2,0),
//            new Aim("r3",R.drawable.r3,0),
//            new Aim("r4",R.drawable.r4,0),
//            new Aim("r5",R.drawable.r5,0),
//            new Aim("r6",R.drawable.r6,0),
//            new Aim("r7",R.drawable.r7,0),
//            new Aim("r8",R.drawable.r8,0),
//            new Aim("sr1",R.drawable.sr1,1),
//            new Aim("sr2",R.drawable.sr2,1),
//            new Aim("sr3",R.drawable.sr3,1),
//            new Aim("sr4",R.drawable.sr4,1),
//            new Aim("sr5",R.drawable.sr5,1),
//            new Aim("ssr1",R.drawable.ssr1,2),
//            new Aim("ssr2",R.drawable.ssr2,2),
//            new Aim("ssr3",R.drawable.ssr3,2),
//    };
    private List<Aim> aimList=new ArrayList<>();

    private AimAdapter aimAdapter;

    // TODO: 2018/3/9 0009 建立空白提示卡
    private Aim noAim=new Aim("咸鱼咸鱼咸鱼咸鱼咸鱼咸鱼~",R.drawable.no_aim_transparent,4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        //初始化数据
        initAims();

        GridLayoutManager layoutManager=new GridLayoutManager(this,1);

        mBinding.recycleView.setLayoutManager(layoutManager);

        //设置适配器
        setRecycleView();

        //设置监听事件
        initListener();

    }

    //向适配器中添加数据
    private void initAims(){
        aimList.clear();

        //生成随机测试数据
//        for (int i=0;i<16;i++){
//            Random random=new Random();
//            int index=random.nextInt(aims.length);
//            aimList.add(aims[index]);
//        }

        //读取本地数据库中的数据
        Connector.getDatabase();
        //使用litepal查询所有数据
        //aimList= DataSupport.findAll(Aim.class);
        //条件查询：状态为0，即未完成的任务
       if(DataSupport.where("state=?","0").find(Aim.class).size()!=0){
           aimList=DataSupport.where("state=?","0").find(Aim.class);
           Log.e(TAG, "initAims: !null");
       }else{
           aimList.add(noAim);
       }
        Collections.reverse(aimList);
    }

    private void setRecycleView(){
        aimAdapter=new AimAdapter(aimList);
        mBinding.recycleView.setAdapter(aimAdapter);
    }

    private void initListener(){
        mBinding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAimActivity.startAction(MainActivity.this);
            }
        });

        mBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initAims();
                setRecycleView();
                mBinding.swipeRefresh.setRefreshing(false);
            }
        });

        mBinding.userCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public static void  startAction(Context context){
        Intent intent=new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
}
