package me.g13n.gridimagesearch;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public abstract class EndlessScrollListener implements OnScrollListener {
	public EndlessScrollListener() {
	}

	public EndlessScrollListener(int visibleThreshold) {
		this.visibleThreshold = visibleThreshold;
	}

	public EndlessScrollListener(int visibleThreshold, int startOffset) {
		this.visibleThreshold = visibleThreshold;
		this.startingOffsetIndex = startOffset;
		this.currentOffset = startOffset;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (!loading && (totalItemCount < previousTotalItemCount)) {
			this.currentOffset = this.startingOffsetIndex;
			this.previousTotalItemCount = totalItemCount;
			if (totalItemCount == 0) {
				this.loading = true;
			}
		}

		if (loading) {
			if (totalItemCount > previousTotalItemCount) {
				loading = false;
				previousTotalItemCount = totalItemCount;
				currentOffset += visibleThreshold;
			}
		}

		if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
			onLoadMore(currentOffset + 1, totalItemCount);
			loading = true;
		}
	}
	
	public abstract void onLoadMore(int offset, int totalItemsCount);
	
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// Don't take any action on changed
	}

	private int startingOffsetIndex = 0;
	private boolean loading = true;
	private int previousTotalItemCount = 0;
	private int currentOffset = 0;
	private int visibleThreshold = 3;
}
