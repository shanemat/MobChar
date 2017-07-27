package cz.cvut.fit.mobchar.core.presentation.adventure;

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
import cz.cvut.fit.mobchar.core.business.managers.interfaces.IAdventureManager;
import cz.cvut.fit.mobchar.core.business.managers.interfaces.IAdventureModuleManager;
import cz.cvut.fit.mobchar.core.presentation.about.AboutActivity;
import cz.cvut.fit.mobchar.core.presentation.adventure.dialogs.AdventureAboutSelectionDialog;
import cz.cvut.fit.mobchar.core.presentation.adventure.dialogs.AdventureModuleSelectionDialog;
import cz.cvut.fit.mobchar.core.presentation.adventure.dialogs.AdventureOptionSelectionDialog;
import cz.cvut.fit.mobchar.core.structures.list_items.AdventureListItem;
import cz.cvut.fit.mobchar.core.structures.modules.AdventureModule;
import cz.cvut.fit.mobchar.core.structures.modules.Module;
import cz.cvut.fit.mobchar.core_library.adapters.simple_line.McSimpleItemAdapter;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialog;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialogClickListener;
import cz.cvut.fit.mobchar.core_library.dialogs.McDialogItem;
import cz.cvut.fit.mobchar.core_library.fragments.McFragment;

import static cz.cvut.fit.mobchar.core.presentation.dialogs.AboutSelectionDialog.CORE_POSITION;
import static cz.cvut.fit.mobchar.core.presentation.dialogs.OptionSelectionDialog.DELETE;
import static cz.cvut.fit.mobchar.core.presentation.dialogs.OptionSelectionDialog.EXPORT;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as fragment displaying list
 * of available adventures.
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
 * intent-filters attributes from AdventureModule
 *
 * 3) in case you want to add your own icon for your adventure in lists
 * go to Module. In its inner class IconMatcher define your abbreviation
 * and extend switch in method getIconForAbbreviation by your icon.
 *
 * TADA, you've just connected your module to the Core! Gratz :))
 */

public class AdventureSelectionFragment extends McFragment {

    /// tag for logging purposes
    private static final String TAG = "Adventure Selection";

    /// constant for identifying creation request
    private static final int REQUEST_CREATION = 0;

    /// constant for identifying import request
    private static final int REQUEST_IMPORT = 1;

    /// constant for identifying about request
    private static final int REQUEST_ABOUT = 2;

    /// tag for identifying dialog of module picking
    private static final String DIALOG_MODULE_PICK = "ModulePicking";

    /// tag for identifying dialog with adventure options
    private static final String DIALOG_ADVENTURE_OPTIONS = "AdventureOption";

    /// position, user has chosen for options
    private int mChosenPosition;

    /// list of available modules
    private ArrayList<AdventureModule> mModules = new ArrayList<>();

    /// list of adventures
    private ArrayList<AdventureListItem> mAdventures;

    /// layout shown when no module has been found
    private LinearLayout mNoModuleFoundLayout;

    /// layout shown when no character has been found
    private LinearLayout mNoAdventureLayout;

    /// button for adding new adventure
    private ImageView mCreateNewAdventureButton;

    /// list view showing available adventures
    private ListView mListView;

    /// adapter controlling the data in list
    private McSimpleItemAdapter<AdventureListItem> mAdapter;

    /**
     * Method encapsulating creation of this fragment (so that plain constructor
     * should be no longer called).
     *
     * @return Returns prepared instance of this fragment.
     */
    public static AdventureSelectionFragment newInstance() {
        return new AdventureSelectionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findAvailableModules();
        findAvailableAdventures(mModules);

        mChosenPosition = -1;
        mAdapter = new McSimpleItemAdapter<>(getActivity(), mAdventures);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_selection_adventure, container, false);

        mNoAdventureLayout = (LinearLayout) view.findViewById(R.id.no_adventure_layout);
        mNoModuleFoundLayout = (LinearLayout) view.findViewById(R.id.no_module_layout);

        mCreateNewAdventureButton = (ImageView) view.findViewById(R.id.new_adventure_button);

        mListView = (ListView) view.findViewById(R.id.adventure_list);

        setUpWidgets();
        handleVisibility();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.m_selection_adventure, menu);

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
        findAvailableAdventures(mModules);
        handleVisibility();

        mAdapter.setItems(mAdventures);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * This method prepares the widgets functionality.
     */
    private void setUpWidgets() {
        mCreateNewAdventureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showModuleSelectionDialog(REQUEST_CREATION);
            }
        });

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdventureListItem adventure = mAdventures.get(position);

                Intent intent = adventure.getShowingIntent();
                startActivity(intent);
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mChosenPosition = position;

                showAdventureOptionsDialog();
                return true;
            }
        });
    }

    /**
     * This method searches in Android OS for modules, that can manage adventures. Results will
     * populate the mModules ArrayList.
     */
    private void findAvailableModules() {
        IAdventureModuleManager manager = ManagerFactory.getAdventureModuleManager();
        PackageManager packageManager = getActivity().getPackageManager();

        ArrayList<Module> modules = manager.getAdventureModules(getActivity(), packageManager);
        for (Module module : modules) {
            mModules.add((AdventureModule) module);
        }
    }

    /**
     * This method retrieves adventures from list of modules.
     *
     * @param modules Modules, which the adventures should be extracted from
     */
    private void findAvailableAdventures(ArrayList<AdventureModule> modules) {
        IAdventureManager manager = ManagerFactory.getAdventureManager();
        mAdventures = manager.getAdventures(getActivity(), modules);
    }

    /**
     * This method shows only the one of three views, which should be shown, and hides the rest.
     */
    private void handleVisibility() {
        if (mModules.isEmpty()) {
            mNoModuleFoundLayout.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            mNoAdventureLayout.setVisibility(View.GONE);
        } else {
            mNoModuleFoundLayout.setVisibility(View.GONE);

            if (mAdventures.isEmpty()) {
                mNoAdventureLayout.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
            } else {
                mNoAdventureLayout.setVisibility(View.GONE);
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
     * of new adventure.
     *
     * @param requestCode Code specifying which type of request is called
     */
    private void showModuleSelectionDialog(final int requestCode) {
        String dialogTitle = getString(R.string.adventure_module_picking_title);
        FragmentManager manager = getFragmentManager();

        AdventureModuleSelectionDialog dialog = AdventureModuleSelectionDialog.newInstance(dialogTitle);
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

        dialog.setTargetFragment(AdventureSelectionFragment.this, requestCode);
        dialog.show(manager, DIALOG_MODULE_PICK);
    }

    /**
     * This method fires up the dialog for picking option what abouts activity to show.
     */
    private void showAboutsSelectionDialog() {
        String dialogTitle = getString(R.string.adventure_module_picking_title);
        FragmentManager manager = getFragmentManager();

        AdventureAboutSelectionDialog dialog = AdventureAboutSelectionDialog.newInstance(dialogTitle);
        dialog.setListener(new McDialogClickListener() {
            @Override
            public void onClick(McDialogItem item) {
                int pickedModulePosition = item.getOptionNumber();
                startAboutActivity(getActivity(), pickedModulePosition);
            }
        });

        dialog.setTargetFragment(AdventureSelectionFragment.this, REQUEST_ABOUT);
        dialog.show(manager, DIALOG_MODULE_PICK);
    }

    /**
     * This method fires up the dialog for picking option how to interact with adventure.
     */
    private void showAdventureOptionsDialog() {
        String dialogTitle = getString(R.string.adventure_options_title);
        FragmentManager manager = getFragmentManager();

        McDialog optionsDialog = AdventureOptionSelectionDialog.newInstance(dialogTitle);
        optionsDialog.setListener(new McDialogClickListener() {

            @Override
            public void onClick(McDialogItem item) {
                AdventureListItem adventure = mAdventures.get(mChosenPosition);
                int option = item.getOptionNumber();

                switch (option) {
                    case DELETE:
                        Intent deletionIntent = adventure.getDeletionIntent();
                        attemptToStartActivity(getActivity(), deletionIntent);
                        break;

                    case EXPORT:
                        Intent exportIntent = adventure.getExportIntent();
                        attemptToStartActivity(getActivity(), exportIntent);
                        break;

                    default:
                        Log.e(TAG, "Adventure selection fragment does not implement " +
                                "choice of this option!");
                        break;
                }
            }
        });

        optionsDialog.show(manager, DIALOG_ADVENTURE_OPTIONS);
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
            String errorText = getString(R.string.adventure_selection_error_toast_text);
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
