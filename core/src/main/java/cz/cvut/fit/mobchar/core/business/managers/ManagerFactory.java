package cz.cvut.fit.mobchar.core.business.managers;

import cz.cvut.fit.mobchar.core.business.managers.interfaces.IAdventureManager;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.IAdventureModuleManager;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.ICharacterManager;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.ICharacterModuleManager;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as object providing
 * implementations of specific interfaces.
 */

public class ManagerFactory {

    public static IAdventureManager getAdventureManager() {
        return AdventureManager.getInstance();
    }

    public static ICharacterManager getCharacterManager() {
        return CharacterManager.getInstance();
    }

    public static IAdventureModuleManager getAdventureModuleManager() {
        return AdventureModuleManager.getInstance();
    }

    public static ICharacterModuleManager getCharacterModuleManager() {
        return CharacterModuleManager.getInstance();
    }
}
