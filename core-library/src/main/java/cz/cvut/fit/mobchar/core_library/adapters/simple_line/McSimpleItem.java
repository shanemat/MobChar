package cz.cvut.fit.mobchar.core_library.adapters.simple_line;

import android.graphics.drawable.Drawable;

import cz.cvut.fit.mobchar.core_library.lists.McListItem;

/**
 * Created by shanemat (FIT-CTU) on 23.7.2017 as object representing simple list item
 * consisting of:
 * * one icon
 * * one text line
 */

public class McSimpleItem extends McListItem {

    /// icon of this list item
    private Drawable mIcon;

    /// text, which should be shown in this item
    private String mText;

    /**
     * Classic constructor.
     *
     * @param icon Icon, which should be shown
     * @param text Text of this list item
     */
    public McSimpleItem(Drawable icon, String text) {

        mIcon = icon;
        mText = text;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getText() {
        return mText;
    }
}
