package cc.poh.first.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cc.poh.first.R;
import cc.poh.first.databinding.ActivityMainBinding;
import cc.poh.first.ui.discover.DiscoverFragment;
import cc.poh.first.ui.home.OnPostClick;
import cc.poh.first.ui.home.PostFragment;
import cc.poh.first.ui.post.EditPostActivity;
import cc.poh.first.ui.utils.BottomNavBehavior;
import cc.poh.first.ui.home.HomeFragment;
import cc.poh.first.ui.utils.FabBehavior;

public class MainActivity extends AppCompatActivity implements OnPostClick {
    public static final int MENU_HOME = 0;
    public static final int MENU_DISCOVER = 1;
    public static final int MENU_MORE = 2;

    public ActivityMainBinding mBinding;

    private HomeFragment mHomeFragment = new HomeFragment();
    private DiscoverFragment mDiscoverFragment = new DiscoverFragment();
    private MoreFragment mProfileFragment = new MoreFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        loadFragment(mHomeFragment);
                        mBinding.fabNew.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.navigation_discover:
                        loadFragment(mDiscoverFragment);
                        mBinding.fabNew.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.navigation_more:
                        loadFragment(mProfileFragment);
                        mBinding.fabNew.setVisibility(View.GONE);
                        return true;
                }
                return false;
            };

    private FloatingActionButton.OnClickListener mFabOnClickListener =
            view -> startActivity(new Intent(this, EditPostActivity.class));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initialiseNav(mBinding.bottomNav);
        initialiseFab(mBinding.fabNew);
        initialiseFrame();
    }

    @Override
    public void onPostClick(int postId) {
        loadFragment(PostFragment.forPost(postId));
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_frame);
        if (fragment instanceof HomeFragment) {
            mBinding.fabNew.setVisibility(View.VISIBLE);
            mBinding.bottomNav.getMenu().getItem(MENU_HOME).setChecked(true);

        } else if (fragment instanceof DiscoverFragment) {
            mBinding.fabNew.setVisibility(View.VISIBLE);
            mBinding.bottomNav.getMenu().getItem(MENU_DISCOVER).setChecked(true);

        } else if (fragment instanceof MoreFragment) {
            mBinding.fabNew.setVisibility(View.GONE);
            mBinding.bottomNav.getMenu().getItem(MENU_MORE).setChecked(true);

        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        getSupportFragmentManager()
            .beginTransaction()
            // animation must be called before replace
            //.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .addToBackStack(null)
            .replace(R.id.container_frame, fragment)
            .commit();
    }

    private void initialiseNav(BottomNavigationView nav) {
        nav.setOnNavigationItemSelectedListener(mOnNavItemSelectedListener);

        // Set the scroll behaviour
        // LayoutParams params = (LayoutParams) nav.getLayoutParams();
        // params.setBehavior(new BottomNavBehavior());
    }

    private void initialiseFab(FloatingActionButton fab) {
        fab.setOnClickListener(mFabOnClickListener);

        // Set scroll behaviour
        // int offset = this.getResources().getDimensionPixelOffset(R.dimen.bottom_nav_height);
        // LayoutParams params = (LayoutParams) fab.getLayoutParams();
        // params.setBehavior(new FabBehavior(offset));
    }

    private void initialiseFrame() {
        // Load the home fragment into the container
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_frame, mHomeFragment);
        transaction.commit();
    }
}
