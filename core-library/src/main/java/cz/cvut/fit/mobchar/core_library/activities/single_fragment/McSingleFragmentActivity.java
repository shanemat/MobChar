package cz.cvut.fit.mobchar.core_library.activities.single_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import cz.cvut.fit.mobchar.core_library.R;
import cz.cvut.fit.mobchar.core_library.activities.McActivity;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as template for creating
 * activities hosting exactly one fragment.
 */

public abstract class McSingleFragmentActivity extends McActivity {

    /**
     * This method should create fragment, which is to be located inside this activity
     * and return its instance.
     *
     * @return Returns instance of fragment, which should be located in this activity.
     */
    protected abstract Fragment createFragment();

    @Override
    protected int getLayoutResId() {
        return R.layout.a_single_fragment;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                        .add(R.id.fragment, fragment)
                        .commit();
        }
    }

    @Override
    public void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(McSingleFragmentActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
