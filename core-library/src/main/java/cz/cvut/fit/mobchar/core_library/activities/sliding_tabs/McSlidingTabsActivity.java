package cz.cvut.fit.mobchar.core_library.activities.sliding_tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core_library.R;
import cz.cvut.fit.mobchar.core_library.activities.McActivity;
import cz.cvut.fit.mobchar.core_library.fragments.McFragment;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as activity providing
 * ability to change fragments based on swiping left or right.
 */

public abstract class McSlidingTabsActivity extends McActivity {

    /// widget for displaying pages
    protected ViewPager mPager;

    /// layout containing tabs
    protected McSlidingTabLayout mTabs;

    /// adapter for handling exchange of pages
    private FragmentStatePagerAdapter mAdapter;

    /// variable indicating, whether activity should draw tabs or not
    private boolean mHasTabs = true;

    /// fragments to be shown
    private ArrayList<Pair<String, McFragment>> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.a_sliding_tabs;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public void reload() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(McSlidingTabsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPostCreate() {
        super.onPostCreate();

        prepareFragments();
        prepareAdapter();
        preparePager();

        if (mHasTabs) {
            prepareTabs();
        }
    }

    /**
     * This method prepares the adapter of pager.
     */
    private void prepareAdapter() {
        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                Pair<String, McFragment> pair = mFragments.get(position);
                return pair.second;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                Pair<String, McFragment> pair = mFragments.get(position);
                return pair.first;
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public int getItemPosition(Object object) {
                McFragment fragment = (McFragment) object;
                if (fragment != null && fragment.getActivity() != null) {
                    fragment.reload();
                }

                return super.getItemPosition(object);
            }
        };
    }

    /**
     * This method initiates the pager.
     */
    private void preparePager() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }

    /**
     * This method prepares the upper tabs.
     */
    private void prepareTabs() {
        mTabs = (McSlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);

        mTabs.setCustomTabColorizer(new McSlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.color_light);
            }
        });

        mTabs.setViewPager(mPager);
    }

    /**
     * This method prepares fragments, that should be displayed in this activity. If you need
     * another fragment to be shown or edit the way fragments are being displayed, this is the place
     * you should edit.
     *
     * Add fragments in order you want them displayed by calling
     * void addFragment(Pair<String, McFragment>).
     */
    protected abstract void prepareFragments();

    /**
     * This method adds new Fragment to its list of Fragments. Note that you
     * have to pass Pair with fragment's name as first and fragment itself as
     * second part of Pair.
     *
     * @param fragment Pair including <Name of fragment, fragment itself> to be added
     */
    protected void addFragment(Pair<String, McFragment> fragment) {
        mFragments.add(fragment);
    }

    protected void setHasTabs(boolean hasTabs) {
        mHasTabs = hasTabs;
    }
}
