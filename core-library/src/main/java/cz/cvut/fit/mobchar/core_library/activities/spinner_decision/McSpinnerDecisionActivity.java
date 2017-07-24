package cz.cvut.fit.mobchar.core_library.activities.spinner_decision;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core_library.R;
import cz.cvut.fit.mobchar.core_library.activities.McActivity;
import cz.cvut.fit.mobchar.core_library.adapters.simple_line.McSimpleItem;
import cz.cvut.fit.mobchar.core_library.adapters.simple_line.McSimpleItemAdapter;
import cz.cvut.fit.mobchar.core_library.fragments.McFragment;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as activity, which allows
 * users to choose, which fragment to display, from spinner.
 */

public abstract class McSpinnerDecisionActivity extends McActivity {

    /// spinner holding item types
    private Spinner mSpinner;

    /// adapter for handling spinner
    private McSimpleItemAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.a_spinner_decision;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void onCreateInit() {
        super.onCreateInit();

        mSpinner = (Spinner) findViewById(R.id.spinner);
    }

    @Override
    protected void onPostCreate() {
        super.onPostCreate();

        prepareSpinner();
        restoreFragment();
    }

    /**
     * This method will prepare spinner.
     */
    private void prepareSpinner() {

        mAdapter = new McSimpleItemAdapter(this, prepareSpinnerData()) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return getView(position, convertView, parent);
            }
        };

        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setCurrentFragment(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // intentionally left blank
            }
        });
    }

    /**
     * This method replaces currently shown fragment with new one
     * retrieved from method getCurrentFragment(int position).
     *
     * @param position Position of option, which has been selected by user
     */
    private void setCurrentFragment(int position) {
        FragmentManager manager = getSupportFragmentManager();
        McFragment fragment = getCurrentFragment(position);

        if (fragment != null) {
            manager.beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
        }
    }

    /**
     * This method will restore fragment after CC or load first one.
     */
    private void restoreFragment() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment);

        if (fragment == null) {
            fragment = createFirstFragment();
            manager.beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit();
        }
    }

    /**
     * This method should prepare items, which will be shown in spinner.
     *
     * @return Returns prepared array list of spinner options.
     */
    protected abstract ArrayList<McSimpleItem> prepareSpinnerData();

    /**
     * This method should return prepared instance of fragment,
     * which should be shown to user, after selecting option
     * on position passed as parameter.
     *
     * @param position Position of option, which has been selected by user
     * @return Returns prepared instance of adequate fragment.
     */
    protected abstract McFragment getCurrentFragment(int position);

    /**
     * This method should determine, which fragment will be created first.
     *
     * @return Returns fragment, which should be shown first.
     */
    protected abstract McFragment createFirstFragment();
}
