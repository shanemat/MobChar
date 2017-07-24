package cz.cvut.fit.mobchar.core_library.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cz.cvut.fit.mobchar.core_library.interfaces.IAReloadable;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as base activity used throughout
 * the app. This activity defines how toolbar should be handled.
 */

public abstract class McActivity extends AppCompatActivity implements IAReloadable {

    /// toolbar of activity
    protected Toolbar mToolbar;

    /**
     * Views are inflated, so here is the right place to find all your views, read data from intents etc.
     */
    protected void onCreateInit() {
        // intentionally left blank
    }

    /**
     * All views are inflated - even toolbar, so here you should bind data to views.
     */
    protected void onPostCreate() {
        // intentionally left blank
    }

    /**
     * This method should return the resource ID of layout, which should be used
     * for specific activity.
     *
     * @return Returns ID of layout, which is to be used for this activity.
     */
    protected abstract int getLayoutResId();

    /**
     * This method should return the resource ID of toolbar, which should be used
     * for specific activity.
     *
     * @return Returns ID of toolbar, which is to be used for this activity.
     */
    protected abstract int getToolbarId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        onCreateInit();

        mToolbar = (Toolbar) findViewById(getToolbarId());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        onPostCreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
