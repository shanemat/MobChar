package cz.cvut.fit.mobchar.core.presentation.dialogs;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.R;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialogItem;

/**
 * Created by shanemat (FIT-CTU) on 26.7.2017 as abstract dialog containing
 * common functionality for showing available about activities of modules.
 */

public abstract class AboutSelectionDialog extends ModuleSelectionDialog {

    /// constant identifying core module
    public static final int CORE_POSITION = -1;

    @Override
    protected ArrayList<McDialogItem> prepareItems() {
        ArrayList<McDialogItem> items = new ArrayList<>();

        items.add(prepareCoreModule());
        items.addAll(super.prepareItems());

        return items;
    }

    /**
     * This method prepares the place holder for core module.
     *
     * @return Returns prepared list item with core information.
     */
    private McDialogItem prepareCoreModule() {
        String name = getString(R.string.core_name);
        Drawable icon = getResources().getDrawable(R.drawable.ic_core);
        return new McDialogItem(icon, name, CORE_POSITION);
    }
}
