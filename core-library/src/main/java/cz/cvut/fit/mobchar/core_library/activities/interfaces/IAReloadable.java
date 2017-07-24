package cz.cvut.fit.mobchar.core_library.activities.interfaces;

/**
 * Created by shanemat (FIT-CTU) on 24.7.2017 as simple interface, which
 * extends activities by method for reloading their contents and showing
 * system messages.
 */

public interface IAReloadable {

    /**
     * This method should reload activity's content.
     */
    void reload();

    /**
     * This method will in some way show message passed as argument.
     *
     * @param message Message, which should be shown
     */
    void showMessage(String message);
}
