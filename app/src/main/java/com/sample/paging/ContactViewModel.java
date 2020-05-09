package com.sample.paging;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.paging.DataSource;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
    }


    public DataSource.Factory<Integer, ContactBean> getDataSource(){
        return contactRepository.getContactsForPaging();
    }

    public void insert(ContactBean contactBean){
        contactRepository.insert(contactBean);
    }

    public void insert(List<ContactBean> contactBeanList){
        contactRepository.insert(contactBeanList);
    }

}