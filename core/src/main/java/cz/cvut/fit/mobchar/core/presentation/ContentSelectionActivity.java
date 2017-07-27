package cz.cvut.fit.mobchar.core.presentation;

import android.support.v4.util.Pair;

import cz.cvut.fit.mobchar.core.R;
import cz.cvut.fit.mobchar.core.presentation.adventure.AdventureSelectionFragment;
import cz.cvut.fit.mobchar.core.presentation.character.CharacterSelectionFragment;
import cz.cvut.fit.mobchar.core_library.activities.sliding_tabs.McSlidingTabsActivity;
import cz.cvut.fit.mobchar.core_library.fragments.McFragment;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as main activity of core. This activity
 * holds on fragment for each type of modules.
 */

public class ContentSelectionActivity extends McSlidingTabsActivity {

    @Override
    protected void prepareFragments() {

        String characterName = getString(R.string.tab_label_character);
        McFragment characterFragment = CharacterSelectionFragment.newInstance();

        addFragment(new Pair<>(characterName, characterFragment));

        String adventureName = getString(R.string.tab_label_adventure);
        McFragment adventureFragment = AdventureSelectionFragment.newInstance();

        addFragment(new Pair<>(adventureName, adventureFragment));
    }
}
