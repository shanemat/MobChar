package cz.cvut.fit.mobchar.core_library.adapters.dual_line;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import cz.cvut.fit.mobchar.core_library.lists.McListItem;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as object representing simple list item
 * consisting of:
 * * one icon
 * * two text lines (primary and secondary)
 */

public class McDualItem extends McListItem {

    /// icon of this list item
    private Drawable mIcon;

    /// main text, which should be shown in this item
    protected String mPrimaryText;

    /// side text, which should be shown in this item
    protected String mSecondaryText;

    /**
     * Classic constructor.
     *
     * @param icon          Icon, which should be shown
     * @param primaryText   Main text of this list item
     * @param secondaryText Side text of this list item
     */
    public McDualItem(Drawable icon, String primaryText, String secondaryText) {

        mIcon = icon;
        mPrimaryText = primaryText;
        mSecondaryText = secondaryText;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getPrimaryText() {
        return mPrimaryText;
    }

    public String getSecondaryText() {
        return mSecondaryText;
    }
}
