package cz.cvut.fit.mobchar.core.business.managers;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import cz.cvut.fit.mobchar.core.business.managers.interfaces.IAdventureManager;
import cz.cvut.fit.mobchar.core.data.DAOFactory;
import cz.cvut.fit.mobchar.core.data.interfaces.IAdventureDAO;
import cz.cvut.fit.mobchar.core.structures.list_items.AdventureListItem;
import cz.cvut.fit.mobchar.core.structures.modules.AdventureModule;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as class implementing IAdventureManager
 * interface and thus providing ways to retrieve adventures from their modules.
 */

public class AdventureManager implements IAdventureManager {

    /// the only instance of this singleton
    private static AdventureManager sInstance;

    /**
     * This method returns the only instance of this singleton.
     *
     * @return Returns instance of this singleton.
     */
    public static AdventureManager getInstance() {
        if (sInstance == null) {
            sInstance = new AdventureManager();
        }

        return sInstance;
    }

    @Override
    public ArrayList<AdventureListItem> getAdventures(Context context, ArrayList<AdventureModule> modules) {
        IAdventureDAO dao = DAOFactory.getAdventureDAO();
        ArrayList<AdventureListItem> adventures = new ArrayList<>();

        for (AdventureModule module : modules) {
            adventures.addAll(dao.getAdventuresFrom(context, module));
        }

        Collections.sort(adventures);
        return adventures;
    }
}
