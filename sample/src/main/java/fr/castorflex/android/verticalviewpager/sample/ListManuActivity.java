package fr.castorflex.android.verticalviewpager.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

/**
 * Created by feng.gao on 2017/5/16.
 */

public class ListManuActivity extends Activity {
    private ListView mListView;
    private String[] titles = new String[]{"竖直viewpager + 弹性滑动srcollView" , "RxJava练习"};
    private ManuAdapter mManuAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manu_list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mManuAdapter = new ManuAdapter(titles, this);
        mListView.setAdapter(mManuAdapter);


    }
}
