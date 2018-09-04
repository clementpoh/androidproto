package cc.poh.first.ui.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.Toast;

/**
 * Created by cpoh on 28/2/2018.
 *
 * https://stackoverflow.com/questions/44777869/hide-show-bottomnavigationview-on-scroll
 */

public class FabBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
    private static final int DOWN = 1;
    private final int mOffset;

    public FabBehavior(int offset) {
        mOffset = offset;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof CoordinatorLayout;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,@NonNull
                                       FloatingActionButton view, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton view,
                               @NonNull View target, int dx, int dy,
                               int dxUnconsumed, int dyUnconsumed,
                               @ViewCompat.NestedScrollType int type) {
        if (dy < 0) {
            showFabView(view);
        } else if (dy > 0) {
            hideFabView(view);
        } else if (!target.canScrollVertically(DOWN)) {
            showFabView(view);
        }
    }

    private void hideFabView(FloatingActionButton view) {
        view.clearAnimation();
        view.animate().translationY(mOffset).setDuration(100);
    }

    private void showFabView(FloatingActionButton view) {
        view.clearAnimation();
        view.animate().translationY(0).setDuration(100);
    }
}

