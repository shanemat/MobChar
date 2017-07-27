package cz.cvut.fit.mobchar.core.presentation.dialogs;

import android.os.Bundle;

import cz.cvut.fit.mobchar.core_library.dialogs.McDialog;

/**
 * Created by shanemat (FIT-CTU) on 26.7.2017 as abstract dialog containing common
 * functionality for core option selection dialogs.
 */

public abstract class OptionSelectionDialog extends McDialog {

    /// constant indicating choice for deletion
    public static final int DELETE = 0;

    /// constant indicating choice for export
    public static final int EXPORT = 1;

    /**
     * This method will bundle up the passed title to this fragment and return
     * prepared instance.
     *
     * @param dialog Instance of created dialog
     * @param title  Title of this dialog
     * @return Return prepared instance of this fragment.
     */
    public static OptionSelectionDialog newInstance(OptionSelectionDialog dialog, String title) {
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);

        dialog.setArguments(args);
        return dialog;
    }
}
