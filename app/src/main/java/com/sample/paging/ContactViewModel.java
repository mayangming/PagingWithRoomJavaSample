package com.sample.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
    }


    /**
     * 使用DataSource.Factory方式
     * @return
     */
    public DataSource.Factory<Integer, ContactBean> getDataSource(){
        return contactRepository.getContactsForPaging();
    }

    public LiveData<PagedList<ContactBean>> getData(){
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)    //每页显示的词条数
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10) //首次加载的数据量
                .setPrefetchDistance(5)     //距离底部还有多少条数据时开始预加载
                .build();
        return new LivePagedListBuilder<Integer,ContactBean>(contactRepository.getContactsForPaging(), config)
                .build();
    }

    public void insert(ContactBean contactBean){
        contactRepository.insert(contactBean);
    }

    public void insert(List<ContactBean> contactBeanList){
        contactRepository.insert(contactBeanList);
    }

}