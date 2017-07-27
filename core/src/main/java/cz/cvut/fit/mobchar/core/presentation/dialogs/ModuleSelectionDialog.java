package cz.cvut.fit.mobchar.core.presentation.dialogs;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

import cz.cvut.fit.mobchar.core.structures.modules.Module;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialog;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialogItem;

/**
 * Created by shanemat (FIT-CTU) on 26.7.2017 as abstract dialog containing
 * common logic for module showing dialogs.
 */

public abstract class ModuleSelectionDialog extends McDialog {

    /// available modules
    private ArrayList<Module> mModules;

    /**
     * This method will bundle up the passed title to this fragment and return
     * prepared instance.
     *
     * @param dialog Instance of created dialog
     * @param title  Title of this dialog
     * @return Return prepared instance of this fragment.
     */
    public static ModuleSelectionDialog newInstance(ModuleSelectionDialog dialog, String title) {
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);

        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mTitle = args.getString(ARGS_TITLE);

        mModules = getModules();
    }

    @Override
    protected ArrayList<McDialogItem> prepareItems() {

        ArrayList<McDialogItem> items = new ArrayList<>();
        for (int i = 0; i < mModules.size(); i++) {
            Module module = mModules.get(i);
            McDialogItem item = new McDialogItem(module.getIcon(), module.getName(), i);
            items.add(item);
        }

        return items;
    }

    /**
     * This method should prepare modules, which should be shown
     * in this dialog.
     *
     * @return Returns array list of prepared modules.
     */
    protected abstract ArrayList<Module> getModules();
}
