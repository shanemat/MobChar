package cz.cvut.fit.mobchar.core.business.managers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.mobchar.core.structures.modules.Module;

import static cz.cvut.fit.mobchar.core.business.enums.EModuleMetaData.*;

/**
 * Created by shanemat (FIT-CTU) on 25.7.2017 as class defining common methods
 * used in managers handling modules.
 */

public abstract class ModuleManager {

    /// tag for logging  purposes
    private static final String TAG = "ModuleManager";

    /**
     * This method finds all modules implementing specified activity type and adds those
     * activities to their modules.
     *
     * @param context      Application context
     * @param manager      OS package manager
     * @param modules      ArrayList of found modules
     * @param activityType Type of activity scan has run for
     */
    protected void findActivitiesOfType(Context context, PackageManager manager,
                                        ArrayList<Module> modules, String activityType) {
        Intent intent = new Intent(activityType);
        List<ResolveInfo> appList = manager.queryIntentActivities(intent, 0);

        for (final ResolveInfo info : appList) {
            processResolveInfo(context, manager, info, modules, activityType);
        }
    }

    /**
     * This method processes the resolve info retrieved from OS scan.
     *
     * @param context      Application context
     * @param manager      OS package manager
     * @param info         Retrieved resolve info
     * @param modules      ArrayList of found modules
     * @param activityType Type of activity scan has run for
     */
    private void processResolveInfo(Context context, PackageManager manager, ResolveInfo info,
                                    ArrayList<Module> modules, String activityType) {
        try {
            String packageName = info.activityInfo.packageName;
            String activityName = info.activityInfo.name;

            ApplicationInfo appIntel = manager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            Bundle metaData = appIntel.metaData;

            if (!checkMetaData(metaData, packageName)) {
                return;
            }

            for (Module module : modules) {
                if (packageName.equals(module.getPackageName())) {
                    module.setActivity(activityType, activityName);
                    return;
                }
            }

            Module module = prepareNewModule(context, packageName, metaData);
            module.setActivity(activityType, activityName);
            modules.add(module);

        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to load meta data" + e.getMessage());
        }
    }

    /**
     * Method, which checks, whether the found resolveInfo has all meta attributes set.
     *
     * @param metaData    Bundle with meta data
     * @param packageName Name of package being checked
     * @return Returns false in case some part of meta data is missing, true otherwise.
     */
    private boolean checkMetaData(Bundle metaData, String packageName) {
        boolean isValid = true;

        if (!metaData.containsKey(META_MODULE_NAME)) {
            Log.e(TAG, "Module: " + packageName + " does not provide module name!");
            isValid = false;
        }

        if (!metaData.containsKey(META_MODULE_ABBREVIATION)) {
            Log.e(TAG, "Module: " + packageName + " does not provide module abbreviation!");
            isValid = false;
        }

        if (!metaData.containsKey(META_MODULE_PROVIDER_URI)) {
            Log.e(TAG, "Module: " + packageName + " does not provide uri to obtain adventures!");
            isValid = false;
        }

        return isValid;
    }

    /**
     * This method prepares new Module.
     *
     * @param context     Application context
     * @param packageName Name of module's package
     * @param metaData    Module's meta data
     * @return Return prepares module structure.
     */
    protected abstract Module prepareNewModule(Context context, String packageName, Bundle metaData);
}
