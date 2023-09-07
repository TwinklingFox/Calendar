package com.example.calendar.presentation;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.calendar.entity.ApiRequest;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuotesViewModel extends ViewModel {
    ApiRequest apiRequest = new ApiRequest();

    private final MutableLiveData<String> quoteLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> authorLiveData = new MutableLiveData<>();

    public LiveData<String> getQuoteLiveData() {
        return quoteLiveData;
    }
    public LiveData<String> getAuthorLiveData() {
        return authorLiveData;
    }

    @SuppressLint("CheckResult")
    public void getApiResult() throws IOException {

        Observable.defer(() -> Observable.fromCallable(apiRequest.getApiResult()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> {
                    quoteLiveData.setValue(result.get(0));
                    authorLiveData.setValue(result.get(1));
                })
                .doOnError(error -> {
                    quoteLiveData.setValue(error.getMessage());
                    authorLiveData.setValue(error.getMessage());
                })
                .subscribe();
    }
}
