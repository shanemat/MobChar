package cz.cvut.fit.mobchar.core.structures.modules;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import cz.cvut.fit.mobchar.core_library.enums.Extras;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as class representing adventure
 * module of one game.
 */

public class AdventureModule extends Module {

    /// tag for logging purposes
    private static final String TAG = "Adventure Module";

    /// tag for recognition of type: CREATE
    public static final String ACTIVITY_CREATE = "cz.cvut.fit.mobchar.adventure.create";

    /// tag for recognition of type: DELETE
    public static final String ACTIVITY_DELETE = "cz.cvut.fit.mobchar.adventure.delete";

    /// tag for recognition of type: SHOW
    public static final String ACTIVITY_SHOW = "cz.cvut.fit.mobchar.adventure.show";

    /// tag for recognition of type: IMPORT
    public static final String ACTIVITY_IMPORT = "cz.cvut.fit.mobchar.adventure.import";

    /// tag for recognition of type: EXPORT
    public static final String ACTIVITY_EXPORT = "cz.cvut.fit.mobchar.adventure.export";

    /// tag for recognition of type: ABOUT
    public static final String ACTIVITY_ABOUT = "cz.cvut.fit.mobchar.adventure.about";

    /**
     * Base constructor including abbreviation and package name.
     *
     * @param context      Application context
     * @param name         Module's name
     * @param abbreviation Abbreviation of module
     * @param packageName  Name of its package
     * @param providerURI  URI for accessing module's database
     */
    public AdventureModule(Context context, String name, String abbreviation,
                           String packageName, Uri providerURI) {
        super(context, name, abbreviation, packageName, providerURI);
    }

    @Override
    protected String getExtraIDTag() {
        return Extras.EXTRA_ADVENTURE_ID;
    }

    @Override
    public void setActivity(String activityType, String activityName) {
        switch (activityType) {
            case ACTIVITY_CREATE:
                mCreateActivity = activityName;
                break;

            case ACTIVITY_DELETE:
                mDeleteActivity = activityName;
                break;

            case ACTIVITY_SHOW:
                mShowActivity = activityName;
                break;

            case ACTIVITY_IMPORT:
                mImportActivity = activityName;
                break;

            case ACTIVITY_EXPORT:
                mExportActivity = activityName;
                break;

            case ACTIVITY_ABOUT:
                mAboutActivity = activityName;
                break;

            default:
                Log.e(TAG, "Unknown type of activity found: " + activityType + "!" +
                        "No activity has been set!");
        }
    }
}
