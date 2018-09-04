package cc.poh.first.ui.utils;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by cpoh on 28/2/2018.
 *
 * https://stackoverflow.com/questions/44777869/hide-show-bottomnavigationview-on-scroll
 */

public class NavigationScrollListener extends RecyclerView.OnScrollListener {
    BottomNavigationView mNavigation;

    public NavigationScrollListener(BottomNavigationView navigation) {
        super();
        this.mNavigation = navigation;
    }

    /**
     * Callback method to be invoked when the RecyclerView has been scrolled. This will be
     * called after the scroll has completed.
     * <p>
     * This callback will also be called if visible item range changes after a layout
     * calculation. In that case, dx and dy will be 0.
     *
     * @param recyclerView The RecyclerView which scrolled.
     * @param dx           The amount of horizontal scroll.
     * @param dy           The amount of vertical scroll.
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0 && mNavigation.isShown()) {
            mNavigation.setVisibility(View.GONE);
        } else if (dy < 0) {
            mNavigation.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Callback method to be invoked when RecyclerView's scroll state changes.
     *
     * @param recyclerView The RecyclerView whose scroll state has changed.
     * @param newState     The updated scroll state. One of {@link #SCROLL_STATE_IDLE},
     *                     {@link #SCROLL_STATE_DRAGGING} or {@link #SCROLL_STATE_SETTLING}.
     */
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState) {
            case SCROLL_STATE_IDLE:
                mNavigation.setVisibility(View.VISIBLE);
        }
    }
}
