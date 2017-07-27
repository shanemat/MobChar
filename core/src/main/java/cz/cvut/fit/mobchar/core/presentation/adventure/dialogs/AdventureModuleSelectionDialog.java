package cz.cvut.fit.mobchar.core.presentation.adventure.dialogs;

import android.content.pm.PackageManager;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.business.managers.ManagerFactory;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.IAdventureModuleManager;
import cz.cvut.fit.mobchar.core.presentation.dialogs.ModuleSelectionDialog;
import cz.cvut.fit.mobchar.core.structures.modules.Module;

/**
 * Created by shanemat (FIT-CTU) on 26.7.2017 as specified dialog for showing
 * available adventure modules.
 */

public class AdventureModuleSelectionDialog extends ModuleSelectionDialog {

    /**
     * This method will bundle up the passed title to this fragment and return
     * prepared instance.
     *
     * @param title Title of this dialog
     * @return Return prepared instance of this fragment.
     */
    public static AdventureModuleSelectionDialog newInstance(String title) {
        AdventureModuleSelectionDialog dialog = new AdventureModuleSelectionDialog();
        return (AdventureModuleSelectionDialog) ModuleSelectionDialog.newInstance(dialog, title);
    }

    @Override
    protected ArrayList<Module> getModules() {
        IAdventureModuleManager manager = ManagerFactory.getAdventureModuleManager();
        PackageManager packageManager = getActivity().getPackageManager();

        return manager.getAdventureModules(getActivity(), packageManager);
    }
}
