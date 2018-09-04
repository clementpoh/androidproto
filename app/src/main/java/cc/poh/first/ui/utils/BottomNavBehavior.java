package cc.poh.first.ui.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by cpoh on 28/2/2018.
 *
 * https://stackoverflow.com/questions/44777869/hide-show-bottomnavigationview-on-scroll
 */

public class BottomNavBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {
    private static final int DOWN = 1;

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        return dependency instanceof CoordinatorLayout;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,@NonNull
                                       BottomNavigationView view, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull BottomNavigationView view,
                               @NonNull View target, int dx, int dy,
                               int dxUnconsumed, int dyUnconsumed,
                               @ViewCompat.NestedScrollType int type) {
        if (dy < 0) {
            showBottomNavigationView(view);
        } else if (dy > 0) {
            hideBottomNavigationView(view);
        } else if (!target.canScrollVertically(DOWN)) {
            showBottomNavigationView(view);
        }
    }

    private void hideBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(view.getHeight()).setDuration(100);
    }

    private void showBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(0).setDuration(100);
    }
}

