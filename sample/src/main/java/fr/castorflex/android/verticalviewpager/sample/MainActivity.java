package fr.castorflex.android.verticalviewpager.sample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import fr.castorflex.android.verticalviewpager.sample.verticaltablayout.CustomGridView;
import fr.castorflex.android.verticalviewpager.sample.verticaltablayout.QTabView;
import fr.castorflex.android.verticalviewpager.sample.verticaltablayout.TabAdapter;
import fr.castorflex.android.verticalviewpager.sample.verticaltablayout.TabView;
import fr.castorflex.android.verticalviewpager.sample.verticaltablayout.VerticalTabLayout;

public class MainActivity extends Activity {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
        final VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.tablayout);
        tablayout.setTabAdapter(new MyTabAdapter());
        tablayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                verticalViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        verticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout.setTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        verticalViewPager.setAdapter(new DummyAdapter(getFragmentManager()));
        verticalViewPager.setPageMargin(getResources().
                getDimensionPixelSize(R.dimen.pagemargin));
        verticalViewPager.setPageMarginDrawable(new ColorDrawable(
                getResources().getColor(android.R.color.holo_green_dark)));

        verticalViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationY(vertMargin - horzMargin / 2);
                    } else {
                        view.setTranslationY(-vertMargin + horzMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
//                    view.setScaleX(scaleFactor);
//                    view.setScaleY(scaleFactor);
//
                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });
    }

    public class DummyAdapter extends FragmentPagerAdapter {
        List<PlaceholderFragment> fragments = new ArrayList<>();

        public DummyAdapter(FragmentManager fm) {
            super(fm);

            for (int i = 0; i < 5; i++) {
                fragments.add(PlaceholderFragment.newInstance(i));
            }
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
         public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PAGE 0";
                case 1:
                    return "PAGE 1";
                case 2:
                    return "PAGE 2";
                case 3:
                    return "PAGE 3";
                case 4:
                    return "PAGE 4";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        String[] array = new String[]{"Android 1", "Android 2", "Android 3",
                "Android 4", "Android 5", "Android 6", "Android 7", "Android 8",
                "Android 9", "Android 10", "Android 11", "Android 12", "Android 13",
                "Android 14", "Android 15", "Android 16"};

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

            Log.d("Debug", "creating fragment "
                    + getArguments().getInt(ARG_SECTION_NUMBER));

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 0:
                    break;

                case 1:
                    rootView.setBackgroundColor(Color.BLACK);
                    break;

                case 2:
                    rootView.setBackgroundColor(Color.BLUE);
                    break;

                case 3:
                    rootView.setBackgroundColor(Color.GREEN);
                    break;

                case 4:
                    rootView.setBackgroundColor(Color.RED);
                    break;
            }
            final CustomGridView listView = (CustomGridView) rootView.findViewById(R.id.listView);
            ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(getActivity(),
                    R.layout.list_item, R.id.text1, array);
            setListViewHeightBasedOnChildren(listView);
            listView.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();

            return rootView;
        }

        public static void setListViewHeightBasedOnChildren(GridView listView) {
            if(listView == null) return;
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }
    }

    class MyTabAdapter implements TabAdapter {

        List<String> titles;

        {
            titles = new ArrayList<>();
            Collections.addAll(titles, "Android", "IOS", "Web", "JAVA", "C++"
                    );
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public int getBadge(int position) {
            if (position == 5) return position;
            return 0;
        }

        @Override
        public QTabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public QTabView.TabTitle getTitle(int position) {
            return new QTabView.TabTitle.Builder(MainActivity.this)
                    .setContent(titles.get(position))
                    .setTextColor(Color.BLUE, Color.BLACK)
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return 0;
        }
    }

}
