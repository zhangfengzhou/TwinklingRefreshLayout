package com.lcodecore.twinklingrefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.lcodecore.tkrefreshlayout.v3.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.v3.TwinklingRefreshLayout;
import com.lcodecore.twinklingrefreshlayout.adapter.MusicAdapter;
import com.lcodecore.twinklingrefreshlayout.adapter.SimpleAdapter;

/**
 * Created by lcodecore on 2016/10/1.
 */

public class ListViewFragment extends Fragment {

    private View rootView;
    private MusicAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_listview, container, false);
            setupListView((ListView) rootView.findViewById(R.id.listView));
        }
        return rootView;
    }

    private void setupListView(ListView listView) {
        TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) rootView.findViewById(R.id.refresh);
        ProgressLayout headerView = new ProgressLayout(getContext());
        refreshLayout.setHeaderView(headerView);
        View exHeader = View.inflate(getContext(),R.layout.header_music,null);
        refreshLayout.addFixedExHeader(exHeader);
        refreshLayout.setEnableOverlayRefreshView(false);
//        refreshLayout.setFloatRefresh(true);
        adapter = new MusicAdapter();
        listView.setAdapter(adapter);
        adapter.refreshCard();

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refreshCard();
                        refreshLayout.finishRefreshing();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.loadMoreCard();
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });
    }




}
