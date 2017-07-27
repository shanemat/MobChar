package cz.cvut.fit.mobchar.core.structures.modules;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import cz.cvut.fit.mobchar.core_library.enums.Extras;
import cz.cvut.fit.mobchar.core_library.enums.classes.ClassesDrD;

import static cz.cvut.fit.mobchar.core.structures.modules.Module.IconMatcher.*;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as class representing character
 * module of one game.
 */

public class CharacterModule extends Module {

    /// tag for logging purposes
    private static final String TAG = "Character Module";

    /// tag for recognition of type: CREATE
    public static final String ACTIVITY_CREATE = "cz.cvut.fit.mobchar.character.create";

    /// tag for recognition of type: DELETE
    public static final String ACTIVITY_DELETE = "cz.cvut.fit.mobchar.character.delete";

    /// tag for recognition of type: SHOW
    public static final String ACTIVITY_SHOW = "cz.cvut.fit.mobchar.character.show";

    /// tag for recognition of type: IMPORT
    public static final String ACTIVITY_IMPORT = "cz.cvut.fit.mobchar.character.import";

    /// tag for recognition of type: EXPORT
    public static final String ACTIVITY_EXPORT = "cz.cvut.fit.mobchar.character.export";

    /// tag for recognition of type: ABOUT
    public static final String ACTIVITY_ABOUT = "cz.cvut.fit.mobchar.character.about";

    /// application context
    private Context mContext;

    /// game's abbreviation
    private String mAbbreviation;

    /**
     * Base constructor including abbreviation and package name.
     *
     * @param context      Application context
     * @param name         Module's name
     * @param abbreviation Abbreviation of module
     * @param packageName  Name of its package
     * @param providerURI  URI for accessing module's database
     */
    public CharacterModule(Context context, String name, String abbreviation,
                           String packageName, Uri providerURI) {
        super(context, name, abbreviation, packageName, providerURI);

        mContext = context;
        mAbbreviation = abbreviation;
    }

    @Override
    protected String getExtraIDTag() {
        return Extras.EXTRA_CHARACTER_ID;
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

    /**
     * This method returns appropriate icon for character class based
     * on class and game.
     *
     * @param classID ID of character's class (game specific)
     * @return Returns appropriate icon for character's class.
     */
    public Drawable getClassIcon(int classID) {

        int resourceID;
        switch (mAbbreviation) {
            case DRD:
                ClassesDrD DrDClass = ClassesDrD.getClassFromId(classID);
                resourceID = ClassesDrD.getIconResourceID(DrDClass);
                break;

            default:
                Log.e(TAG, "Unknown abbreviation found: " + mAbbreviation + "!");
                resourceID = -1;
        }

        return mContext.getResources().getDrawable(resourceID);
    }
}
