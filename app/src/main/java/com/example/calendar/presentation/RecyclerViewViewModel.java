package com.example.calendar.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.calendar.entity.DbRequest;
import com.example.calendar.entity.IRequestService;
import com.example.calendar.entity.Note;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewViewModel extends ViewModel {
    private final MutableLiveData<List<Note>> noteLiveData = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<CompositeDisposable> compDisposableLiveData = new MutableLiveData<>();
    IRequestService.DbMethods dbRequest = new DbRequest();

    public LiveData<List<Note>> getNoteLiveData() {
        insertDb(new Note("Моя первая заметка", "Кстати, тщательные исследования конкурентов призывают нас к новым свершениям, которые, в свою очередь, должны быть заблокированы в рамках своих собственных рациональных ограничений. А ещё сделанные на базе интернет-аналитики выводы освещают чрезвычайно интересные особенности картины в целом, однако конкретные выводы, разумеется, объединены в целые кластеры себе подобных. Но диаграммы связей и по сей день остаются уделом либералов, которые жаждут быть рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок."));
        insertDb(new Note("Моя вторая заметка", "Неожиданно, но тщательные исследования конкурентов призывают нас к новым свершениям, которые, в свою очередь, должны быть заблокированы в рамках своих собственных рациональных ограничений. А ещё сделанные на базе интернет-аналитики выводы освещают чрезвычайно интересные особенности картины в целом, однако конкретные выводы, разумеется, объединены в целые кластеры себе подобных. Но диаграммы связей и по сей день остаются уделом либералов, которые жаждут быть рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок."));
        getDbAll();
        return noteLiveData;
    }

    public LiveData<CompositeDisposable> getCompDisposableLiveData() {
        return compDisposableLiveData;
    }
    public void getDbAll() {
        Disposable disposable = Single.create((SingleOnSubscribe<List<Note>>) emitter -> emitter.onSuccess(dbRequest.getAll()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(noteLiveData::setValue);
        disposables.add(disposable);
        compDisposableLiveData.setValue(disposables);
    }
    public void insertDb(Note note) {
        Disposable disposable = Completable.fromAction(() -> dbRequest.insert(note))
                .subscribeOn(Schedulers.io())
                .subscribe(() -> System.out.println("ok"), throwable -> System.out.println(throwable.getMessage()));
        disposables.add(disposable);
        compDisposableLiveData.setValue(disposables);
    }

    /*public void getDbById() {

    }*/

    public void updateDb() {

    }

    public void deleteDb() {

    }
}
