package cz.cvut.fit.mobchar.core.structures.list_items;

import android.content.Intent;

import cz.cvut.fit.mobchar.core.structures.modules.AdventureModule;
import cz.cvut.fit.mobchar.core_library.adapters.simple_line.McSimpleItem;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as list item, which
 * is able to resolve its adventure activities.
 */

public class AdventureListItem extends McSimpleItem implements Comparable<AdventureListItem> {

    /// adventure's ID inside its module
    private long mID;

    /// adventure's module
    private AdventureModule mModule;

    /**
     * Classic full constructor.
     *
     * @param ID     ID of adventure (inside its module)
     * @param name   Name of adventure
     * @param module Adventure's native module
     */
    public AdventureListItem(long ID, String name, AdventureModule module) {
        super(module.getIcon(), name);

        mID = ID;
        mModule = module;
    }

    public Intent getShowingIntent() {
        return mModule.getShowingIntent(mID);
    }

    public Intent getDeletionIntent() {
        return mModule.getDeletionIntent(mID);
    }

    public Intent getExportIntent() {
        return mModule.getExportIntent(mID);
    }

    @Override
    public int compareTo(AdventureListItem other) {
        int moduleResult = mModule.getName().compareTo(other.mModule.getName());
        if (moduleResult != 0) {
            return moduleResult;
        } else {
            return mText.compareTo(other.mText);
        }
    }
}
