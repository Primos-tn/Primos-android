package pingo.mobile.com.ui.common;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    // True if we are still waiting for the last set of data to load.
    private boolean isLoading = false;
    private int page = 0;
    private int lastVisibleItem, totalItemCount;

    RecyclerView.LayoutManager mLayoutManager;

    /**
     * @param layoutManager
     */
    public EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        super.onScrolled(view, dx, dy);
        int totalItemCount = mLayoutManager.getItemCount();
        lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            onLoadMore(this.page++, totalItemCount);
        }
    }

    /**
     * @return
     */
    public void setIsLoading(boolean loading) {
        this.isLoading = loading;
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount);

}