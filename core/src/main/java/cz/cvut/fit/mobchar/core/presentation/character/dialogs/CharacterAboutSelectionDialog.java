package cz.cvut.fit.mobchar.core.presentation.character.dialogs;

import android.content.pm.PackageManager;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.business.managers.ManagerFactory;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.ICharacterModuleManager;
import cz.cvut.fit.mobchar.core.presentation.dialogs.AboutSelectionDialog;
import cz.cvut.fit.mobchar.core.presentation.dialogs.ModuleSelectionDialog;
import cz.cvut.fit.mobchar.core.structures.modules.Module;

/**
 * Created by shanemat (FIT-CTU) on 27.7.2017 as specialized dialog for showing
 * available about activities of character modules.
 */

public class CharacterAboutSelectionDialog extends AboutSelectionDialog {

    /**
     * This method will bundle up the passed title to this fragment and return
     * prepared instance.
     *
     * @param title Title of this dialog
     * @return Return prepared instance of this fragment.
     */
    public static CharacterAboutSelectionDialog newInstance(String title) {
        CharacterModuleSelectionDialog dialog = new CharacterModuleSelectionDialog();
        return (CharacterAboutSelectionDialog) ModuleSelectionDialog.newInstance(dialog, title);
    }

    @Override
    protected ArrayList<Module> getModules() {
        ICharacterModuleManager manager = ManagerFactory.getCharacterModuleManager();
        PackageManager packageManager = getActivity().getPackageManager();

        return manager.getCharacterModules(getActivity(), packageManager);
    }
}
