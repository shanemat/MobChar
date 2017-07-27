package cz.cvut.fit.mobchar.core.data;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.data.interfaces.IAdventureDAO;
import cz.cvut.fit.mobchar.core.structures.list_items.AdventureListItem;
import cz.cvut.fit.mobchar.core.structures.modules.AdventureModule;

import static cz.cvut.fit.mobchar.core_library.enums.Tables.DrDPJ.AdventureTable.ID;
import static cz.cvut.fit.mobchar.core_library.enums.Tables.DrDPJ.AdventureTable.NAME;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as Data Access Object
 * implementing methods defined in IAdventureDAO.
 */

public class AdventureDAO implements IAdventureDAO {

    /// the only instance of this singleton
    private static AdventureDAO sInstance;

    /**
     * Method for obtaining instance of this singleton
     *
     * @return Returns instance of this singleton.
     */
    public static AdventureDAO getInstance() {
        if (sInstance == null) {
            sInstance = new AdventureDAO();
        }

        return sInstance;
    }

    @Override
    public ArrayList<AdventureListItem> getAdventuresFrom(Context context, AdventureModule module) {
        String[] projection = new String[]{ID, NAME};
        Cursor cursor = context.getContentResolver().query(module.getProviderURI(), projection, null, null, null);

        ArrayList<AdventureListItem> adventures = new ArrayList<>();

        if (cursor == null) {
            return adventures;
        }

        try {
            while (cursor.moveToNext()) {
                AdventureListItem adventure = getAdventureFrom(cursor, module);
                adventures.add(adventure);
            }
        } finally {
            cursor.close();
        }

        return adventures;
    }

    /**
     * This method extracts information about Adventure and packs it together
     * with module into AdventureListItem.
     *
     * @param cursor Cursor, which data are extracted from
     * @param module Module of the extracted adventure
     * @return Returns filled AdventureListItem.
     */
    private AdventureListItem getAdventureFrom(Cursor cursor, AdventureModule module) {
        long id = cursor.getLong(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));

        return new AdventureListItem(id, name, module);
    }
}
