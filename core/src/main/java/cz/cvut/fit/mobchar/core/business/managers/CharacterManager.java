package cz.cvut.fit.mobchar.core.business.managers;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import cz.cvut.fit.mobchar.core.business.managers.interfaces.ICharacterManager;
import cz.cvut.fit.mobchar.core.data.DAOFactory;
import cz.cvut.fit.mobchar.core.data.interfaces.ICharacterDAO;
import cz.cvut.fit.mobchar.core.structures.list_items.CharacterListItem;
import cz.cvut.fit.mobchar.core.structures.modules.CharacterModule;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as class implementing ICharacterManager
 * interface and thus providing ways to retrieve characters from their modules.
 */

public class CharacterManager implements ICharacterManager {

    /// the only instance of this singleton
    private static CharacterManager sInstance;

    /**
     * This method returns the only instance of this singleton.
     *
     * @return Returns instance of this singleton.
     */
    public static CharacterManager getInstance() {
        if (sInstance == null) {
            sInstance = new CharacterManager();
        }

        return sInstance;
    }

    @Override
    public ArrayList<CharacterListItem> getCharacters(Context context, ArrayList<CharacterModule> modules) {
        ICharacterDAO dao = DAOFactory.getCharacterDAO();
        ArrayList<CharacterListItem> characters = new ArrayList<>();

        for (CharacterModule module : modules) {
            characters.addAll(dao.getCharactersFrom(context, module));
        }

        Collections.sort(characters);
        return characters;
    }
}
