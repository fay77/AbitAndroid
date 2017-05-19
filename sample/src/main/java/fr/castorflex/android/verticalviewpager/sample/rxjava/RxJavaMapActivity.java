package fr.castorflex.android.verticalviewpager.sample.rxjava;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import fr.castorflex.android.verticalviewpager.sample.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by feng.gao on 2017/5/19.
 */

public class RxJavaMapActivity extends Activity {
    private static final String TAG = "RxJavaMapActivity";
    private ListView mListView;
    private RxJavaShowTvAdapter mRxjavaAdapter;
    private EditText mEditText;
    private Button mButton;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mEditText = (EditText) findViewById(R.id.edt);
        mButton = (Button) findViewById(R.id.map_btn);
        mRxjavaAdapter = new RxJavaShowTvAdapter(mData, this);
        mListView.setAdapter(mRxjavaAdapter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText.clearFocus();
                startMap(Integer.valueOf(mEditText.getText().toString()));
            }
        });

    }

    private void startMap(final int num) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(num);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 4; i++) {
                    list.add("i am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
                mData.add(s);
                Log.d(TAG, Thread.currentThread().getName());
                mRxjavaAdapter.notifyDataSetChanged();

            }
        });
    }

}
