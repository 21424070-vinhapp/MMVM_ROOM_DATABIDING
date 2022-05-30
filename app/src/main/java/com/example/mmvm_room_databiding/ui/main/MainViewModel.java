package com.example.mmvm_room_databiding.ui.main;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mmvm_room_databiding.data.model.entities.WorkEntity;
import com.example.mmvm_room_databiding.data.respositories.WorkRespository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<List<WorkEntity>>  listWork=new MutableLiveData<>();
    private MutableLiveData<Long>  idInsert=new MutableLiveData<>();
    private WorkRespository workRespository;
    private Context context;

    public MainViewModel(Context context)
    {
        this.context=context;
        workRespository=new WorkRespository(context);
    }

    public LiveData<List<WorkEntity>> getListWork(){
        return listWork;
    }

    public LiveData<Long> getIdInsert(){
        return idInsert;
    }

    public void queryListWork(){
        workRespository
                .getListWork()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<WorkEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<WorkEntity> workEntities) {
                        listWork.setValue(workEntities);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void queryInsertWork(WorkEntity workEntity)
    {
        workRespository
                .insertWork(workEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        idInsert.setValue(aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
