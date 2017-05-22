package org.pms.common;

import org.pms.domain.User;
import org.springframework.core.io.Resource;

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

    Resource getResource(String classPath);

}
