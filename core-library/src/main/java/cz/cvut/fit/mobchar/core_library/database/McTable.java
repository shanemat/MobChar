package cz.cvut.fit.mobchar.core_library.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as abstract object representing one
 * table.
 */

public abstract class McTable {

    /// name of table
    private String mName;

    /// collection of columns
    private ArrayList<McColumn> mColumns;

    /**
     * Constructor with given name
     *
     * @param name Name of the table
     */
    protected McTable(String name) {
        mName = name;
        mColumns = new ArrayList<>();
    }

    /**
     * This method adds specified column to its list.
     *
     * @param column Column, which should be added
     */
    protected void addColumn(McColumn column) {
        mColumns.add(column);
    }

    /**
     * This method should create this table in given database.
     *
     * @param database Database in which table should be created
     */
    public void createTable(SQLiteDatabase database) {
        String createScript = "CREATE TABLE " + mName + " (";
        int size = mColumns.size();

        for (int i = 0; i < size; i++) {
            McColumn column = mColumns.get(i);
            createScript += column.getCreationString();
            if (i != size - 1) {
                createScript += ", ";
            }
        }
        createScript += ");";
        database.execSQL(createScript);
    }
}
