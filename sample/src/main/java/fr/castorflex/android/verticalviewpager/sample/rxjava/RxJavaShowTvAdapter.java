package fr.castorflex.android.verticalviewpager.sample.rxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.sample.R;

/**
 * Created by feng.gao on 2017/5/19.
 */

public class RxJavaShowTvAdapter extends BaseAdapter {
    private List<String> mList = new ArrayList<>();
    private Context mContext;

    public RxJavaShowTvAdapter(List<String> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.rxjava_list_item, null);
            holder.mTextView = (TextView) view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mTextView.setText(mList.get(i));
        return view;
    }

    public class ViewHolder {
        private TextView mTextView;
    }
}
