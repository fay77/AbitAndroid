package fr.castorflex.android.verticalviewpager.sample.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import fr.castorflex.android.verticalviewpager.sample.R;

/**
 * Created by feng.gao on 2017/5/17.
 */

public class RxJavaMainActivity extends Activity {
    private String[] titles = new String[]{ "zip符合练习"  , "flatMap练习"  };
    private ListView mListView;
    private RxJavaListAdapter mRxJavaListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manu_list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mRxJavaListAdapter = new RxJavaListAdapter(titles, this);
        mListView.setAdapter(mRxJavaListAdapter);

    }
}
