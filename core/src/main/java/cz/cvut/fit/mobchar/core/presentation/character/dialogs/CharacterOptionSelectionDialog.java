package cz.cvut.fit.mobchar.core.presentation.character.dialogs;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.R;
import cz.cvut.fit.mobchar.core.presentation.dialogs.OptionSelectionDialog;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialogItem;

/**
 * Created by shanemat (FIT-CTU) on 27.7.2017 as specialized dialog for character
 * core option selection dialogs.
 */

public class CharacterOptionSelectionDialog extends OptionSelectionDialog {

    /**
     * This method will bundle up the passed title to this fragment and return
     * prepared instance.
     *
     * @param title Title of this dialog
     * @return Return prepared instance of this fragment.
     */
    public static CharacterOptionSelectionDialog newInstance(String title) {
        CharacterOptionSelectionDialog dialog = new CharacterOptionSelectionDialog();
        return (CharacterOptionSelectionDialog) OptionSelectionDialog.newInstance(dialog, title);
    }

    @Override
    protected ArrayList<McDialogItem> prepareItems() {
        ArrayList<McDialogItem> items = new ArrayList<>();

        String deleteName = getString(R.string.dialog_character_selection_option_delete);
        Drawable deleteIcon = getResources().getDrawable(R.drawable.ic_delete);
        items.add(new McDialogItem(deleteIcon, deleteName, DELETE));

        String exportName = getString(R.string.dialog_character_selection_option_export);
        Drawable exportIcon = getResources().getDrawable(R.drawable.ic_export);
        items.add(new McDialogItem(exportIcon, exportName, EXPORT));

        return items;
    }
}
