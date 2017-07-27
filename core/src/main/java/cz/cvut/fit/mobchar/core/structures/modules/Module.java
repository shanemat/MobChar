package cz.cvut.fit.mobchar.core.structures.modules;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import cz.cvut.fit.mobchar.core.R;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as abstract class containing
 * common functionality for modules.
 */

public abstract class Module {

    /// name of module
    private String mName;

    /// icon of this module
    private Drawable mIcon;

    /// URI for accessing module's database
    private Uri mProviderURI;

    /// name of module's package
    private String mPackageName;

    /// name of activity that should be started for creation
    protected String mCreateActivity;

    /// name of activity that should be started for deletion
    protected String mDeleteActivity;

    /// name of activity that should be started for showing details
    protected String mShowActivity;

    /// name of activity that should be started for import
    protected String mImportActivity;

    /// name of activity that should be started for export
    protected String mExportActivity;

    /// name of activity that should be started for information about module
    protected String mAboutActivity;

    /**
     * Class responsible for matching icons to modules.
     */
    protected static class IconMatcher {
        protected static final String DRD = "DrD";

        /**
         * This method will return icon connected with module specified by its
         * abbreviation.
         *
         * @param context      Application context
         * @param abbreviation Abbreviation of module
         * @return Returns icon connected with module.
         */
        private static Drawable getIconForAbbreviation(Context context, String abbreviation) {

            int iconId;
            switch (abbreviation) {
                case DRD:
                    iconId = R.drawable.ic_drd;
                    break;

                default:
                    iconId = R.drawable.ic_tome;
            }

            return context.getResources().getDrawable(iconId);
        }
    }

    /**
     * Base constructor including abbreviation and package name.
     *
     * @param context      Application context
     * @param name         Module's name
     * @param abbreviation Abbreviation of module
     * @param packageName  Name of its package
     * @param providerURI  URI for accessing module's database
     */
    protected Module(Context context, String name, String abbreviation,
                     String packageName, Uri providerURI) {
        mName = name;
        mIcon = IconMatcher.getIconForAbbreviation(context, abbreviation);

        mPackageName = packageName;
        mProviderURI = providerURI;
    }

    public String getName() {
        return mName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public Uri getProviderURI() {
        return mProviderURI;
    }

    /**
     * This method will return intent for starting activity responsible for creating
     * object for this module.
     *
     * @return Returns prepared Intent or NULL if this module does not support creating.
     */
    public Intent getCreationIntent() {
        if (mCreateActivity == null) {
            return null;
        }

        Intent intent = new Intent();
        intent.setClassName(mPackageName, mCreateActivity);

        return intent;
    }

    /**
     * This method will return intent for starting activity responsible for deleting
     * object for this module.
     *
     * @param objectID ID of object, which should be deleted
     * @return Returns prepared Intent or NULL if this module does not support deleting.
     */
    public Intent getDeletionIntent(long objectID) {
        if (mDeleteActivity == null) {
            return null;
        }

        Intent intent = new Intent();
        intent.setClassName(mPackageName, mDeleteActivity);
        intent.putExtra(getExtraIDTag(), objectID);

        return intent;
    }

    /**
     * This method will return intent for starting activity responsible for showing
     * object for this module.
     *
     * @param objectID ID of adventure, which should be shown
     * @return Returns prepared Intent or NULL if this module does not support showing.
     */
    public Intent getShowingIntent(long objectID) {
        if (mShowActivity == null) {
            return null;
        }

        Intent intent = new Intent();
        intent.setClassName(mPackageName, mShowActivity);
        intent.putExtra(getExtraIDTag(), objectID);

        return intent;
    }

    /**
     * This method will return intent for starting activity responsible for importing
     * object to this module.
     *
     * @return Returns prepared Intent or NULL if this module does not support imports.
     */
    public Intent getImportIntent() {
        if (mImportActivity == null) {
            return null;
        }

        Intent intent = new Intent();
        intent.setClassName(mPackageName, mImportActivity);

        return intent;
    }

    /**
     * This method will return intent for starting activity responsible for exporting
     * object from this module.
     *
     * @param objectID ID of adventure, which should be exported
     * @return Returns prepared Intent or NULL if this module does not support exporting.
     */
    public Intent getExportIntent(long objectID) {
        if (mExportActivity == null) {
            return null;
        }

        Intent intent = new Intent();
        intent.setClassName(mPackageName, mExportActivity);
        intent.putExtra(getExtraIDTag(), objectID);

        return intent;
    }

    /**
     * This method will return intent for starting activity responsible for showing
     * information about this module.
     *
     * @return Returns prepared Intent or NULL if this module does not support showing.
     */
    public Intent getAboutIntent() {
        if (mAboutActivity == null) {
            return null;
        }

        Intent intent = new Intent();
        intent.setClassName(mPackageName, mAboutActivity);

        return intent;
    }

    /**
     * This method should return tag, which is being used for retrieving ID
     * from Extras bundle.
     *
     * @return Returns tag for obtaining ID from extras.
     */
    protected abstract String getExtraIDTag();

    /**
     * This method should set appropriate activity based on its type.
     *
     * @param activityType Type of activity
     * @param activityName Activity's name
     */
    public abstract void setActivity(String activityType, String activityName);
}
