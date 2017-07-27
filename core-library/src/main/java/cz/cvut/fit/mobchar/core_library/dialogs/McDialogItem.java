package cz.cvut.fit.mobchar.core_library.dialogs;

import android.graphics.drawable.Drawable;

import cz.cvut.fit.mobchar.core_library.adapters.simple_line.McSimpleItem;

/**
 * Created by shanemat (FIT-CTU) on 23.7.2017 as object representing one
 * object shown in dialog.
 *
 * Note that number of option should be declared inside specific dialog
 * and this object should just stored that information, not handle it
 * in any way.
 */

public class McDialogItem extends McSimpleItem {

    /// number identifying option linked with this item
    private int mOptionNumber;

    /**
     * Classic constructor.
     *
     * @param icon         Icon, which should be shown next to text
     * @param text         Text associated with this item
     * @param optionNumber Identifying number for option linked with this item
     */
    public McDialogItem(Drawable icon, String text, int optionNumber) {
        super(icon, text);

        mOptionNumber = optionNumber;
    }

    public int getOptionNumber() {
        return mOptionNumber;
    }
}
