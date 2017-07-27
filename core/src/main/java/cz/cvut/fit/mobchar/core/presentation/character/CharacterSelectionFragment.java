package cz.cvut.fit.mobchar.core.presentation.character;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cz.cvut.fit.mobchar.core.R;
import cz.cvut.fit.mobchar.core.business.managers.ManagerFactory;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.ICharacterManager;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.ICharacterModuleManager;
import cz.cvut.fit.mobchar.core.presentation.about.AboutActivity;
import cz.cvut.fit.mobchar.core.presentation.character.dialogs.CharacterAboutSelectionDialog;
import cz.cvut.fit.mobchar.core.presentation.character.dialogs.CharacterModuleSelectionDialog;
import cz.cvut.fit.mobchar.core.presentation.character.dialogs.CharacterOptionSelectionDialog;
import cz.cvut.fit.mobchar.core.structures.list_items.CharacterListItem;
import cz.cvut.fit.mobchar.core.structures.modules.CharacterModule;
import cz.cvut.fit.mobchar.core.structures.modules.Module;
import cz.cvut.fit.mobchar.core_library.adapters.dual_line.McDualItemAdapter;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialog;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialogClickListener;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialogItem;
import cz.cvut.fit.mobchar.core_library.fragments.McFragment;

import static cz.cvut.fit.mobchar.core.presentation.dialogs.AboutSelectionDialog.CORE_POSITION;
import static cz.cvut.fit.mobchar.core.presentation.dialogs.OptionSelectionDialog.DELETE;
import static cz.cvut.fit.mobchar.core.presentation.dialogs.OptionSelectionDialog.EXPORT;

/**
 * Created by shanemat (FIT-CTU) on 27.7.2017 as fragment displaying list
 * of available characters.
 *
 * Since you are reading this, you might be interested in what to do
 * so the Core will recognize your module and show it here. Well
 * there is just a few things, you must DO ;))
 *
 * STEPS:
 * 1) create new module and specify its meta data (see EModuleMetaData)
 * - note that all of this data must be specified!
 *
 * 2) in your module notate activities, you want to be executed, with
 * intent-filters attributes from CharacterModule
 *
 * 3) in case you want to add your own icon for your adventure in lists
 * go to Module. In its inner class IconMatcher define your abbreviation
 * and extend switch in method getIconForAbbreviation by your icon.
 *
 * TADA, you've just connected your module to the Core! Gratz :))
 */

public class CharacterSelectionFragment extends McFragment {

    /// tag for logging purposes
    private static final String TAG = "Character Selection";

    /// constant for identifying creation request
    private static final int REQUEST_CREATION = 0;

    /// constant for identifying import request
    private static final int REQUEST_IMPORT = 1;

    /// constant for identifying about request
    private static final int REQUEST_ABOUT = 2;

    /// tag for identifying dialog of module picking
    private static final String DIALOG_MODULE_PICK = "ModulePicking";

    /// tag for identifying dialog with character options
    private static final String DIALOG_CHARACTER_OPTIONS = "CharacterOption";

    /// position, user has chosen for options
    private int mChosenPosition;

    /// list of available modules
    private ArrayList<CharacterModule> mModules = new ArrayList<>();

    /// list of characters
    private ArrayList<CharacterListItem> mCharacters;

    /// layout shown when no module has been found
    private LinearLayout mNoModuleFoundLayout;

    /// layout shown when no character has been found
    private LinearLayout mNoCharacterLayout;

    /// button for adding new character
    private ImageView mCreateNewCharacterButton;

    /// list view showing available characters
    private ListView mListView;

    /// adapter controlling the data in list
    private McDualItemAdapter<CharacterListItem> mAdapter;

    /**
     * Method encapsulating creation of this fragment (so that plain constructor
     * should be no longer called).
     *
     * @return Returns prepared instance of this fragment.
     */
    public static CharacterSelectionFragment newInstance() {
        return new CharacterSelectionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findAvailableModules();
        findAvailableCharacters(mModules);

        mChosenPosition = -1;
        mAdapter = new McDualItemAdapter<>(getActivity(), mCharacters);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_selection_character, container, false);

        mNoCharacterLayout = (LinearLayout) view.findViewById(R.id.no_character_layout);
        mNoModuleFoundLayout = (LinearLayout) view.findViewById(R.id.no_module_layout);

        mCreateNewCharacterButton = (ImageView) view.findViewById(R.id.new_character_button);

        mListView = (ListView) view.findViewById(R.id.character_list);

        setUpWidgets();
        handleVisibility();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.m_selection_character, menu);

        if (mModules.isEmpty()) {
            handleMenuVisibility(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                showModuleSelectionDialog(REQUEST_CREATION);
                return true;

            case R.id.imports:
                showModuleSelectionDialog(REQUEST_IMPORT);
                return true;

            case R.id.about:
                showAboutsSelectionDialog();
                return true;

            default:
                Log.e(TAG, "Menu option " + item.getTitle() + " has not been implemented");
                return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        reload();
    }

    @Override
    public void reload() {
        findAvailableCharacters(mModules);
        handleVisibility();

        mAdapter.setItems(mCharacters);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * This method prepares the widgets functionality.
     */
    private void setUpWidgets() {
        mCreateNewCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showModuleSelectionDialog(REQUEST_CREATION);
            }
        });

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CharacterListItem character = mCharacters.get(position);

                Intent intent = character.getShowingIntent();
                startActivity(intent);
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mChosenPosition = position;

                showCharacterOptionsDialog();
                return true;
            }
        });
    }

    /**
     * This method searches in Android OS for modules, that can manage characters. Results will
     * populate the mModules ArrayList.
     */
    private void findAvailableModules() {
        ICharacterModuleManager manager = ManagerFactory.getCharacterModuleManager();
        PackageManager packageManager = getActivity().getPackageManager();

        ArrayList<Module> modules = manager.getCharacterModules(getActivity(), packageManager);
        for (Module module : modules) {
            mModules.add((CharacterModule) module);
        }
    }

    /**
     * This method retrieves characters from list of modules.
     *
     * @param modules Modules, which the characters should be extracted from
     */
    private void findAvailableCharacters(ArrayList<CharacterModule> modules) {
        ICharacterManager manager = ManagerFactory.getCharacterManager();
        mCharacters = manager.getCharacters(getActivity(), modules);
    }

    /**
     * This method shows only the one of three views, which should be shown, and hides the rest.
     */
    private void handleVisibility() {
        if (mModules.isEmpty()) {
            mNoModuleFoundLayout.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            mNoCharacterLayout.setVisibility(View.GONE);
        } else {
            mNoModuleFoundLayout.setVisibility(View.GONE);

            if (mCharacters.isEmpty()) {
                mNoCharacterLayout.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
            } else {
                mNoCharacterLayout.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * This method will hide menu items in case there have been no modules found.
     *
     * @param menu Menu, which items should be hidden
     */
    private void handleMenuVisibility(Menu menu) {
        MenuItem addItem = menu.findItem(R.id.add);
        addItem.setVisible(false);

        MenuItem importItem = menu.findItem(R.id.imports);
        importItem.setVisible(false);

        MenuItem aboutItem = menu.findItem(R.id.about);
        aboutItem.setVisible(false);
    }

    /**
     * This method fires up the dialog for picking, which module to choose for creation
     * of new character.
     *
     * @param requestCode Code specifying which type of request is called
     */
    private void showModuleSelectionDialog(final int requestCode) {
        String dialogTitle = getString(R.string.character_module_picking_title);
        FragmentManager manager = getFragmentManager();

        CharacterModuleSelectionDialog dialog = CharacterModuleSelectionDialog.newInstance(dialogTitle);
        dialog.setListener(new McDialogClickListener() {
            @Override
            public void onClick(McDialogItem item) {
                int pickedModulePosition = item.getOptionNumber();
                switch (requestCode) {
                    case REQUEST_CREATION:
                        Intent creationIntent = mModules.get(pickedModulePosition).getCreationIntent();
                        attemptToStartActivity(getContext(), creationIntent);
                        break;

                    case REQUEST_IMPORT:
                        Intent importIntent = mModules.get(pickedModulePosition).getImportIntent();
                        attemptToStartActivity(getContext(), importIntent);
                        break;

                    default:
                        Log.e(TAG, "Unknown request code found: " + requestCode + "!");
                }
            }
        });

        dialog.setTargetFragment(CharacterSelectionFragment.this, requestCode);
        dialog.show(manager, DIALOG_MODULE_PICK);
    }

    /**
     * This method fires up the dialog for picking option what abouts activity to show.
     */
    private void showAboutsSelectionDialog() {
        String dialogTitle = getString(R.string.character_module_picking_title);
        FragmentManager manager = getFragmentManager();

        CharacterAboutSelectionDialog dialog = CharacterAboutSelectionDialog.newInstance(dialogTitle);
        dialog.setListener(new McDialogClickListener() {
            @Override
            public void onClick(McDialogItem item) {
                int pickedModulePosition = item.getOptionNumber();
                startAboutActivity(getActivity(), pickedModulePosition);
            }
        });

        dialog.setTargetFragment(CharacterSelectionFragment.this, REQUEST_ABOUT);
        dialog.show(manager, DIALOG_MODULE_PICK);
    }

    /**
     * This method fires up the dialog for picking option how to interact with character.
     */
    private void showCharacterOptionsDialog() {
        String dialogTitle = getString(R.string.character_options_title);
        FragmentManager manager = getFragmentManager();

        McDialog optionsDialog = CharacterOptionSelectionDialog.newInstance(dialogTitle);
        optionsDialog.setListener(new McDialogClickListener() {

            @Override
            public void onClick(McDialogItem item) {
                CharacterListItem character = mCharacters.get(mChosenPosition);
                int option = item.getOptionNumber();

                switch (option) {
                    case DELETE:
                        Intent deletionIntent = character.getDeletionIntent();
                        attemptToStartActivity(getActivity(), deletionIntent);
                        break;

                    case EXPORT:
                        Intent exportIntent = character.getExportIntent();
                        attemptToStartActivity(getActivity(), exportIntent);
                        break;

                    default:
                        Log.e(TAG, "Character selection fragment does not implement " +
                                "choice of this option!");
                        break;
                }
            }
        });

        optionsDialog.show(manager, DIALOG_CHARACTER_OPTIONS);
    }

    /**
     * This method will attempt to start activity specified by Intent. If this fails,
     * Toast will be shown informing user, what happened.
     *
     * @param context        Application context
     * @param preparedIntent Prepared intent used for activity starting
     */
    private void attemptToStartActivity(Context context, Intent preparedIntent) {
        if (preparedIntent == null) {
            String errorText = getString(R.string.character_selection_error_toast_text);
            Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(preparedIntent);
        }
    }

    /**
     * This method will start proper about activity.
     *
     * @param context              Application context
     * @param pickedModulePosition position of chosen module
     */
    private void startAboutActivity(Context context, int pickedModulePosition) {
        if (pickedModulePosition == CORE_POSITION) {
            Intent coreAboutIntent = new Intent(getActivity(), AboutActivity.class);
            startActivity(coreAboutIntent);
        } else {
            Intent aboutIntent = mModules.get(pickedModulePosition).getAboutIntent();
            attemptToStartActivity(context, aboutIntent);
        }
    }
}
