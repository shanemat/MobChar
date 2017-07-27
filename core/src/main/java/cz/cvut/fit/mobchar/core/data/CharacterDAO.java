package cz.cvut.fit.mobchar.core.data;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.data.interfaces.ICharacterDAO;
import cz.cvut.fit.mobchar.core.structures.list_items.CharacterListItem;
import cz.cvut.fit.mobchar.core.structures.modules.CharacterModule;

import static cz.cvut.fit.mobchar.core_library.enums.Tables.DrDPlayer.CharacterTable.CLASS;
import static cz.cvut.fit.mobchar.core_library.enums.Tables.DrDPlayer.CharacterTable.ID;
import static cz.cvut.fit.mobchar.core_library.enums.Tables.DrDPlayer.CharacterTable.NAME;
import static cz.cvut.fit.mobchar.core_library.enums.Tables.DrDPlayer.CharacterTable.XP;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as Data Access Object
 * implementing methods defined in ICharacterDAO.
 */

public class CharacterDAO implements ICharacterDAO {

    /// the only instance of this singleton
    private static CharacterDAO sInstance;

    /**
     * Method for obtaining instance of this singleton
     *
     * @return Returns instance of this singleton.
     */
    public static CharacterDAO getInstance() {
        if (sInstance == null) {
            sInstance = new CharacterDAO();
        }

        return sInstance;
    }

    @Override
    public ArrayList<CharacterListItem> getCharactersFrom(Context context, CharacterModule module) {
        String[] projection = new String[]{ID, NAME, XP};
        Cursor cursor = context.getContentResolver().query(module.getProviderURI(), projection, null, null, null);

        ArrayList<CharacterListItem> characters = new ArrayList<>();

        if (cursor == null) {
            return characters;
        }

        try {
            while (cursor.moveToNext()) {
                CharacterListItem character = getCharacterFrom(cursor, module);
                characters.add(character);
            }
        } finally {
            cursor.close();
        }

        return characters;
    }

    /**
     * This method extracts information about Adventure and packs it together
     * with module into AdventureListItem.
     *
     * @param cursor Cursor, which data are extracted from
     * @param module Module of the extracted adventure
     * @return Returns filled AdventureListItem.
     */
    private CharacterListItem getCharacterFrom(Cursor cursor, CharacterModule module) {
        long id = cursor.getLong(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));

        int xp = cursor.getInt(cursor.getColumnIndex(XP));
        int classID = cursor.getInt(cursor.getColumnIndex(CLASS));

        return new CharacterListItem(id, name, xp, classID, module);
    }
}
