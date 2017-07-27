package cz.cvut.fit.mobchar.core.structures.list_items;

import android.content.Intent;

import cz.cvut.fit.mobchar.core.structures.modules.CharacterModule;
import cz.cvut.fit.mobchar.core_library.adapters.dual_line.McDualItem;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as list item, which
 * is able to resolve its character activities.
 */

public class CharacterListItem extends McDualItem implements Comparable<CharacterListItem> {

    /// character's ID inside its module
    private long mID;

    /// character's module
    private CharacterModule mModule;

    /**
     * Classic full constructor.
     *
     * @param ID      ID of character (inside its module)
     * @param name    Name of character
     * @param xp      Number of character's experience points
     * @param classID ID of class of character
     * @param module  Characters's native module
     */
    public CharacterListItem(long ID, String name, int xp, int classID, CharacterModule module) {
        super(module.getClassIcon(classID), name, "XP: " + xp);

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
    public int compareTo(CharacterListItem other) {
        int moduleResult = mModule.getName().compareTo(other.mModule.getName());
        if (moduleResult != 0) {
            return moduleResult;
        } else {
            int primaryResult = mPrimaryText.compareTo(other.mPrimaryText);
            if (primaryResult != 0) {
                return primaryResult;
            } else {
                return mSecondaryText.compareTo(other.mSecondaryText);
            }
        }
    }
}
