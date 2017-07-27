package cz.cvut.fit.mobchar.core.data.interfaces;

import android.content.Context;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.structures.list_items.CharacterListItem;
import cz.cvut.fit.mobchar.core.structures.modules.CharacterModule;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as interface providing methods
 * for obtaining characters from their module's database.
 */

public interface ICharacterDAO {

    /**
     * This method will retrieve all characters from specified module.
     *
     * @param context Application context
     * @param module  Module, which characters should be extracted
     * @return Returns arrayList of module's characters.
     */
    ArrayList<CharacterListItem> getCharactersFrom(Context context, CharacterModule module);
}
