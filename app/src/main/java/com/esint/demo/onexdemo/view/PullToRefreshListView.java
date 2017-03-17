package com.esint.demo.onexdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.esint.demo.onexdemo.R;

/**
 * Created by Linsa on 2017/3/17:10:21.
 * des: 自定义ListView，带有上拉加载下拉刷新功能
 */

public class PullToRefreshListView extends ListView implements AbsListView.OnScrollListener {
    /** 是否正在加载更多 */
    private boolean isLoadMore=false;
    private View footerView;

    private onRefreshListener refreshListener;
    private int footermeasuredHeight;

    public void setOnRefreshListener(onRefreshListener refreshListener){
        this.refreshListener = refreshListener;
    }

    public PullToRefreshListView(Context context) {
        this(context, null);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addFooter();
        setOnScrollListener(this);
    }


    /**
     * 添加底部条目
     */
    private void addFooter() {
        footerView = View.inflate(getContext(), R.layout.listview_footer, null);
        footerView.measure(0, 0);
        footermeasuredHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, 0, 0, -footermeasuredHeight);
        addFooterView(footerView);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState==SCROLL_STATE_IDLE && getLastVisiblePosition()==getCount()-1 && isLoadMore==false){
            isLoadMore=true;
            footerView.setPadding(0,0,0,0);
            setSelection(getCount()-1);
            if (refreshListener!=null){
                refreshListener.loadMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /** 进行刷新时候的数据回调 */
    public interface onRefreshListener{
        /**
         * 上拉加载方法
         */
        void loadMore();
    }

    public void finish(){
        if (isLoadMore) {
            footerView.setPadding(0, 0, 0, -footermeasuredHeight);
            isLoadMore = false;
        }
    }

}
