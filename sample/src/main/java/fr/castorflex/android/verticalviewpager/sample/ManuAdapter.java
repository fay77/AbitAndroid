package fr.castorflex.android.verticalviewpager.sample;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import fr.castorflex.android.verticalviewpager.sample.rxjava.RxJavaMainActivity;

/**
 * Created by feng.gao on 2017/5/16.
 */

public class ManuAdapter extends BaseAdapter {
    private String[] titles;
    private Context mContext;

    public ManuAdapter(String[] titles , Context context) {
        this.titles = titles;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return titles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_layout, null);
            holder.mButton = (Button) view.findViewById(R.id.button);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mButton.setText(titles[i]);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (i) {
                    case 0:
                        mContext.startActivity(new Intent(mContext, MainActivity.class));
                        break;
                    case 1:
                        mContext.startActivity(new Intent(mContext, RxJavaMainActivity.class));

                        break;
                }

            }
        });
        return view;
    }

    public class ViewHolder {
        private Button mButton;

    }
}
