package cz.cvut.fit.mobchar.core_library.lists;

import android.content.Context;

/**
 * Created by shanemat (FIT-CTU) on 23.7.2017 as abstract class for
 * holders across apps.
 */

public abstract class McHolder {

    /**
     * This method should populate this holder with information
     * stored in item passed as parameter.
     *
     * Note that cast will be necessary and so zyou should first check, that it
     * is possible and return in case it's not!
     *
     * @param context Application context
     * @param item Item holding information, which should be used for populating
     */
    public abstract void populate(Context context, McListItem item);
}
