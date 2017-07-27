package cz.cvut.fit.mobchar.core_library.enums.classes;

import android.util.Log;

import cz.cvut.fit.mobchar.core_library.R;

/**
 * Enum describing possible classes of DrD character.
 */
public enum ClassesDrD {

    ALCHEMIST(0),
    MAGICIAN(1),
    RANGER(2),
    THIEF(3),
    WARRIOR(4);

    /// tag for logging purposes
    private static final String TAG = "DrD Class";

    /// id for storing and recognition in database
    private int mId;

    /**
     * Necessary constructor
     *
     * @param id ID of class
     */
    ClassesDrD(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    /**
     * Method for obtaining class based on its ID
     *
     * @param id ID of enum member
     * @return Member of enum or for invalid ID
     */
    public static ClassesDrD getClassFromId(int id) {
        switch (id) {
            case 0:
                return ALCHEMIST;
            case 1:
                return MAGICIAN;
            case 2:
                return RANGER;
            case 3:
                return THIEF;
            case 4:
                return WARRIOR;

            default:
                Log.e(TAG, "Unknown ID of class: " + id + "!");
                return null;
        }
    }

    /**
     * This method will return id of localized name of class
     * passed as parameter.
     *
     * @param classes Class, which name should be returned
     * @return Returns localized name of given class.
     */
    public static int getResourceId(ClassesDrD classes) {
        switch (classes) {
            case ALCHEMIST:
                return R.string.drd_class_alchemist;
            case MAGICIAN:
                return R.string.drd_class_magician;
            case RANGER:
                return R.string.drd_class_ranger;
            case THIEF:
                return R.string.drd_class_thief;
            case WARRIOR:
                return R.string.drd_class_warrior;

            default:
                Log.e(TAG, "Unknown drd class: " + classes + "!");
                return -1;
        }
    }

    /**
     * This method will return id of icon for class
     * passed as parameter.
     *
     * @param classes Class, which name should be returned
     * @return Returns localized name of given class.
     */
    public static int getIconResourceID(ClassesDrD classes) {
        switch (classes) {
            case ALCHEMIST:
                return R.drawable.ic_alchemist;
            case MAGICIAN:
                return R.drawable.ic_magician;
            case RANGER:
                return R.drawable.ic_ranger;
            case THIEF:
                return R.drawable.ic_thief;
            case WARRIOR:
                return R.drawable.ic_warrior;

            default:
                Log.e(TAG, "Unknown drd class: " + classes + "!");
                return -1;
        }
    }
}
