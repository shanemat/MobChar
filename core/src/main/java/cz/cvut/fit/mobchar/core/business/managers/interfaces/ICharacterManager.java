package cz.cvut.fit.mobchar.core.business.managers.interfaces;

import android.content.Context;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.structures.list_items.CharacterListItem;
import cz.cvut.fit.mobchar.core.structures.modules.CharacterModule;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as interface for obtaining characters
 * from their modules.
 */

public interface ICharacterManager {

    /**
     * This method will extract all characters from modules specified as argument.
     *
     * @param context Application context
     * @param modules Module, which characters should be extracted from
     * @return Returns all characters belonging to specified modules.
     */
    ArrayList<CharacterListItem> getCharacters(Context context, ArrayList<CharacterModule> modules);
}
