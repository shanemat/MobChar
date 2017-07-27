package cz.cvut.fit.mobchar.core.presentation.about;

import android.support.v4.app.Fragment;

import cz.cvut.fit.mobchar.core_library.activities.single_fragment.McSingleFragmentActivity;

/**
 * Created by shanemat (FIT-CTU) on 26.7.2017 as activity holding
 * about fragment.
 */

public class AboutActivity extends McSingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return AboutFragment.newInstance();
    }

    @Override
    public void reload() {
        /// intentionally left blank - nothing to be reloaded
    }
}
