package cz.cvut.fit.mobchar.core_library.database;

import android.util.Log;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 representing one column in table. This
 * class is being used in McTable.
 */

public class McColumn {

    /// tag for logging purposes
    private static final String TAG = "McColumn";

    /**
     * Class describing possible types of values
     */
    public static final class ValueType {
        public static final int NONE = 0;
        public static final int TEXT = 1;
        public static final int INTEGER = 2;
        public static final int NUMERIC = 3;

        static String prepareString(int type) {
            switch (type) {
                case NONE:      return "";
                case TEXT:      return " TEXT";
                case INTEGER:   return " INTEGER";
                case NUMERIC:   return " NUMERIC";
                default:
                    Log.e(TAG, "Unknown value type >> " + type);
                    return "";
            }
        }
    }

    /**
     * Class describing possible ways of incrementation
     */
    public static final class Incrementation {
        public static final int NO = 0;
        public static final int AUTOINCREMENT = 1;

        static String prepareString(int type) {
            switch (type) {
                case NO:            return "";
                case AUTOINCREMENT: return " AUTOINCREMENT";
                default:
                    Log.e(TAG, "Unknown incrementation type >> " + type);
                    return "";
            }
        }
    }

    /**
     * Class describing possible ways of nullability
     */
    public static final class Nullability {
        public static final int CAN_BE_NULL = 0;
        public static final int NOT_NULL = 1;

        static String prepareString(int type) {
            switch (type) {
                case CAN_BE_NULL:   return "";
                case NOT_NULL:      return " NOT_NULL";
                default:
                    Log.e(TAG, "Unknown nullability type >> " + type);
                    return "";
            }
        }
    }

    /**
     * Class describing possible key types
     */
    public static final class KeyType {
        public static final int NORMAL = 0;
        public static final int FOREIGN_KEY = 1;
        public static final int PRIMARY_KEY = 2;
    }

    /// column name
    private String mName;

    /// type of values stored
    private int mValueType;

    /// optional reference to other table
    private String mReference;

    /// optional type of key this column is
    private int mKeyType;

    /// whether null values are allowed
    private int mNullability;

    /// whether should be incremented
    private int mIncrementation;

    /**
     * Shorten form of constructor used for columns with
     *  - KeyType = NORMAL
     *  - Incrementation = NO
     *   and without reference
     *
     * @param name Name of column
     * @param valueType Type of values
     * @param nullability Whether can be null
     */
    public McColumn(String name, int valueType, int nullability) {
        mName = name;
        mValueType = valueType;
        mReference = null;
        mKeyType = KeyType.NORMAL;
        mNullability = nullability;
        mIncrementation = Incrementation.NO;
    }

    /**
     * Complete constructor excluding reference (will be null).
     *
     * @param name Name of column
     * @param valueType Type of values
     * @param keyType Type of key (primary or no)
     * @param incrementation Whether should be auto-incremented
     * @param nullability Whether can be null
     */
    public McColumn(String name, int valueType, int keyType, int incrementation, int nullability) {
        mName = name;
        mValueType = valueType;
        mKeyType = keyType;
        mIncrementation = incrementation;
        mNullability = nullability;
        mReference = null;
    }

    /**
     * Complete constructor including reference.
     *
     * @param name Name of column
     * @param valueType Type of values
     * @param keyType Type of key (primary or no)
     * @param incrementation Whether should be auto-incremented
     * @param nullability Whether can be null
     * @param reference The reference to another table
     */
    public McColumn(String name, int valueType, int keyType, int incrementation, int nullability, String reference) {
        mName = name;
        mValueType = valueType;
        mKeyType = keyType;
        mIncrementation = incrementation;
        mNullability = nullability;
        mReference = reference;
    }

    /**
     * This method prepares the String for creating this column
     *
     * @return Return prepared String with SQL command.
     */
    String getCreationString() {
        String command = mName + ValueType.prepareString(mValueType);
        switch (mKeyType) {
            case KeyType.PRIMARY_KEY:
                command += " PRIMARY KEY" + Incrementation.prepareString(mIncrementation);
                break;

            case KeyType.FOREIGN_KEY:
                command += " REFERENCES " + mReference;
                break;

            case KeyType.NORMAL:
                command += Nullability.prepareString(mNullability);
                break;
        }

        return command;
    }
}
