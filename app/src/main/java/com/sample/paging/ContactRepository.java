package com.sample.paging;

import android.app.Application;

import androidx.paging.DataSource;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    public ContactRepository(Application application){
        ConcatDataBase concatDataBase = ConcatDataBase.getDatabase(application);
        contactDao = concatDataBase.getContactDao();
    }

    public DataSource.Factory<Integer, ContactBean> getContactsForPaging(){
        return contactDao.queryPageContact();
    }

    public void insert(ContactBean contactBean){
        ConcatDataBase.dataBaseExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insert(contactBean);
            }
        });

    }

    public void insert(List<ContactBean> contactBeanList){
        ConcatDataBase.dataBaseExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insert(contactBeanList);
            }
        });

    }

//    /**
//     * 自定义数据源
//     */
//    class MyDataSource extends PageKeyedDataSource<Integer,ContactBean>{
//        @Override
//        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ContactBean> callback) {
//            callback.onResult(null);
//        }
//
//        @Override
//        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ContactBean> callback) {
//
//        }
//
//        @Override
//        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ContactBean> callback) {
//
//        }
//    }

}