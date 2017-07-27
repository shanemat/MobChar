package cz.cvut.fit.mobchar.core.presentation.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.cvut.fit.mobchar.core.R;
import cz.cvut.fit.mobchar.core_library.fragments.McFragment;

/**
 * Created by shanemat (FIT-CTU) on 26.7.2017 as fragment holding
 * "about" information of core module.
 */

public class AboutFragment extends McFragment {

    /**
     * Method encapsulating creation of this fragment (so that plain constructor
     * should be no longer called).
     *
     * @return Returns prepared instance of this fragment.
     */
    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_about_core, null);
    }

    @Override
    public void reload() {
        /// intentionally left blank - there is nothing to be reloaded
    }
}
