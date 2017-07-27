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

public interface ICharacterModuleManager {

    /**
     * This method searches Android OS for character modules and retrieves them.
     *
     * @param context Application context
     * @param manager OS package manager
     * @return Returns character modules.
     */
    ArrayList<Module> getCharacterModules(Context context, PackageManager manager);
}
