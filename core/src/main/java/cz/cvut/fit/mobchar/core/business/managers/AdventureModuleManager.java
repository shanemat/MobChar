package cz.cvut.fit.mobchar.core.business.managers;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.business.managers.interfaces.IAdventureModuleManager;
import cz.cvut.fit.mobchar.core.structures.modules.AdventureModule;
import cz.cvut.fit.mobchar.core.structures.modules.Module;

import static cz.cvut.fit.mobchar.core.business.enums.EModuleMetaData.*;
import static cz.cvut.fit.mobchar.core.structures.modules.AdventureModule.*;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as singleton handling
 * requests from presentation layer associated with communication
 * with adventure modules.
 */

public class AdventureModuleManager extends ModuleManager implements IAdventureModuleManager {

    /// the only instance of this singleton
    private static AdventureModuleManager sInstance;

    /**
     * This method returns the only instance of this singleton
     *
     * @return Returns instance of this singleton.
     */
    public static AdventureModuleManager getInstance() {
        if (sInstance == null) {
            sInstance = new AdventureModuleManager();
        }

        return sInstance;
    }

    @Override
    public ArrayList<Module> getAdventureModules(Context context, PackageManager manager) {
        ArrayList<Module> modules = new ArrayList<>();

        findActivitiesOfType(context, manager, modules, ACTIVITY_CREATE);
        findActivitiesOfType(context, manager, modules, ACTIVITY_DELETE);
        findActivitiesOfType(context, manager, modules, ACTIVITY_SHOW);

        findActivitiesOfType(context, manager, modules, ACTIVITY_IMPORT);
        findActivitiesOfType(context, manager, modules, ACTIVITY_EXPORT);
        findActivitiesOfType(context, manager, modules, ACTIVITY_ABOUT);

        return modules;
    }

    @Override
    protected Module prepareNewModule(Context context, String packageName, Bundle metaData) {
        String name = metaData.getString(META_MODULE_NAME);
        String abbreviation = metaData.getString(META_MODULE_ABBREVIATION);

        String uriText = metaData.getString(META_MODULE_PROVIDER_URI);
        Uri uri = Uri.parse(uriText);

        return new AdventureModule(context, name, abbreviation, packageName, uri);
    }
}
