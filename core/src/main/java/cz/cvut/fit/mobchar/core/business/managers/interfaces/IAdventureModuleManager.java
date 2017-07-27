package cz.cvut.fit.mobchar.core.business.managers.interfaces;

import android.content.Context;
import android.content.pm.PackageManager;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.structures.modules.Module;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as interface
 * for communication between core and modules providing
 * adventure management.
 */

public interface IAdventureModuleManager {

    /**
     * This method searches Android OS for adventure modules and retrieves them.
     *
     * @param context Application context
     * @param manager OS package manager
     * @return Returns adventure modules.
     */
    ArrayList<Module> getAdventureModules(Context context, PackageManager manager);
}
