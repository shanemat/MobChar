package cz.cvut.fit.mobchar.core.presentation.character.dialogs;

import android.content.pm.PackageManager;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.business.managers.ManagerFactory;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.ICharacterModuleManager;
import cz.cvut.fit.mobchar.core.presentation.dialogs.ModuleSelectionDialog;
import cz.cvut.fit.mobchar.core.structures.modules.Module;

/**
 * Created by shanemat (FIT-CTU) on 27.7.2017 as specified dialog for showing
 * available character modules.
 */

public class CharacterModuleSelectionDialog extends ModuleSelectionDialog {

    /**
     * This method will bundle up the passed title to this fragment and return
     * prepared instance.
     *
     * @param title Title of this dialog
     * @return Return prepared instance of this fragment.
     */
    public static CharacterModuleSelectionDialog newInstance(String title) {
        CharacterModuleSelectionDialog dialog = new CharacterModuleSelectionDialog();
        return (CharacterModuleSelectionDialog) ModuleSelectionDialog.newInstance(dialog, title);
    }

    @Override
    protected ArrayList<Module> getModules() {
        ICharacterModuleManager manager = ManagerFactory.getCharacterModuleManager();
        PackageManager packageManager = getActivity().getPackageManager();

        return manager.getCharacterModules(getActivity(), packageManager);
    }
}
