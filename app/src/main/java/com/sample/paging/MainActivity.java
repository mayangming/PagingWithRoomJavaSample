package com.sample.paging;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页加载库测试
 */
public class MainActivity extends AppCompatActivity {
    ContactViewModel contactViewModel;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    LiveData<PagedList<ContactBean>> liveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.insert).setOnClickListener(this::onClick);
        findViewById(R.id.query).setOnClickListener(this::onClick);
        recyclerView = findViewById(R.id.rv);
        initRecycleView();
        initViewModel();
        initPage1();
    }

    private void initViewModel() {
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                insert();
                break;
            case R.id.query:
//                query();

                contactViewModel.getData().observe(this, new Observer<PagedList<ContactBean>>() {
                    @Override
                    public void onChanged(PagedList<ContactBean> contactBeans) {
                        contactAdapter.submitList(contactBeans);
                    }
                });
                break;
        }
    }

    private void initRecycleView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter();
        recyclerView.setAdapter(contactAdapter);
    }



    /**
     * 初始化分页加载库
     */
    private void initPage1() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)    //每页显示的词条数
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10) //首次加载的数据量
                .setPrefetchDistance(5)     //距离底部还有多少条数据时开始预加载
                .build();
        liveData = new LivePagedListBuilder<Integer,ContactBean>(contactViewModel.getDataSource(), config)
                .build();

    }

    private void insert() {
        List<ContactBean> contactBeans = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ContactBean contactBean = new ContactBean();
            contactBean.setContent("第" + i + "个数据");
            contactBeans.add(contactBean);
        }//asn
        contactViewModel.insert(contactBeans);
    }

    private void query() {
        //观察者模式，将Adapter注册进去，当liveData发生改变事通知Adapter
        liveData.observe(this, new Observer<PagedList<ContactBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<ContactBean> subjectsBeans) {
                contactAdapter.submitList(subjectsBeans);
            }
        });
    }
}
