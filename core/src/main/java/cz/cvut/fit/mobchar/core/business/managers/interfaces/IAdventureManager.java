package cz.cvut.fit.mobchar.core.business.managers.interfaces;

import android.content.Context;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.structures.list_items.AdventureListItem;
import cz.cvut.fit.mobchar.core.structures.modules.AdventureModule;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as interface for obtaining adventures
 * from their modules.
 */

public interface IAdventureManager {

    /**
     * This method will extract all adventures from modules specified as argument.
     *
     * @param context Application context
     * @param modules Module, which adventures should be extracted from
     * @return Returns all adventures belonging to specified modules.
     */
    ArrayList<AdventureListItem> getAdventures(Context context, ArrayList<AdventureModule> modules);
}
