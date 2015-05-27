package org.pms.applicationbuilder;

import org.pms.models.User;

/**
 * User: tijo.
 */
public interface PMSApplicationBuilder {

    void applicationInitializer();

    void applicationDestroyer();

    boolean destroyUserSessionFromSessionMap(String sessionContextKey);

    boolean isUserSessionActive(String sessionContextKey);

    void addUserInSessionMap(User currentUser, String sessionContextKey);

    void destroyEntireUserSessionMap();

    User getUserFromUserSessionMap(String sessionContextKey);

}
