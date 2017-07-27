package cz.cvut.fit.mobchar.core.data.interfaces;

import android.content.Context;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.structures.list_items.AdventureListItem;
import cz.cvut.fit.mobchar.core.structures.modules.AdventureModule;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as interface providing methods
 * for obtaining adventures from their module's database.
 */

public interface IAdventureDAO {

    /**
     * This method will retrieve all adventures from specified module.
     *
     * @param context Application context
     * @param module  Module, which adventures should be extracted
     * @return Returns arrayList of module's adventures.
     */
    ArrayList<AdventureListItem> getAdventuresFrom(Context context, AdventureModule module);
}
