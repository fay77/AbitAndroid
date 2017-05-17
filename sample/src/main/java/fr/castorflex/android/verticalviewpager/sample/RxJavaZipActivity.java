package fr.castorflex.android.verticalviewpager.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class RxJavaZipActivity extends Activity implements View.OnClickListener {
    private EditText mLeftEdt;
    private EditText mRightEdt;
    private Button mZipBtn;
    private TextView mResultTv;
    private Observable<Integer> observable1;
    private Observable<String> observable2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_zip_layout);
        mLeftEdt = (EditText) findViewById(R.id.left_edt);
        mRightEdt = (EditText) findViewById(R.id.right_edt);
        mZipBtn = (Button) findViewById(R.id.zip_btn);
        mResultTv = (TextView) findViewById(R.id.result_tv);

        mZipBtn.setOnClickListener(this);
       observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(Integer.valueOf(mLeftEdt.getText().toString()));
                e.onNext(Integer.valueOf(mLeftEdt.getText().toString()));
                e.onNext(Integer.valueOf(mLeftEdt.getText().toString()));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(mRightEdt.getText().toString());
                e.onNext(mRightEdt.getText().toString());
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());


    }

    @Override
    public void onClick(View view) {
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                mResultTv.setText(value);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
