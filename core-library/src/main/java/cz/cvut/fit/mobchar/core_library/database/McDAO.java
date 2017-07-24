package cz.cvut.fit.mobchar.core_library.database;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as abstract object providing methods
 * for other DAOs.
 */

public abstract class McDAO {

    /**
     * This method will prepare the base where clause based on given column name.
     *
     * @param columnName Name of column, which should be in where clause
     * @return Returns prepared String with where clause.
     */
    protected String prepareWhereClause(String columnName) {
        return columnName + " = ?";
    }
}
