package cz.cvut.fit.mobchar.core.data;

import cz.cvut.fit.mobchar.core.data.interfaces.IAdventureDAO;
import cz.cvut.fit.mobchar.core.data.interfaces.ICharacterDAO;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as object providing
 * implementations of specific interfaces.
 */

public class DAOFactory {

    public static IAdventureDAO getAdventureDAO() {
        return AdventureDAO.getInstance();
    }
    public static ICharacterDAO getCharacterDAO() {
        return CharacterDAO.getInstance();
    }
}
