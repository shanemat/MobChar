package cz.cvut.fit.mobchar.core_library.dialogs;

/**
 * Created by shanemat (FIT-CTU) on 23.7.2017 as listener, which should
 * be implemented to define correct behavior towards specific options
 * of McDialog and its subclasses being chosen.
 */

public abstract class McDialogClickListener {

    /**
     * This method defines behavior, when dialog item passed as parameter
     * has been clicked.
     *
     * @param item Dialog item, which has been chosen
     */
    public abstract void onClick(McDialogItem item);
}
