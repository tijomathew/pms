package org.pms.applicationbuilder;

import org.pms.models.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * User: tijo.
 */
@Scope("singleton")
@Component
public class PMSApplicationBuilderImpl implements ApplicationContextAware, PMSApplicationBuilder {

    private ApplicationContext applicationContext;
    private final Map<String, User> userSessionMap;
    private ResourceLoader resourceLoader;

    public PMSApplicationBuilderImpl() {
        userSessionMap = new HashMap<>();
    }

    @Override
    @PostConstruct
    public void applicationInitializer() {
        //NO OP
    }

    @Override
    @PreDestroy
    public void applicationDestroyer() {
        destroyEntireUserSessionMap();
    }

    @Override
    public boolean destroyUserSessionFromSessionMap(String sessionContextKey) {
        boolean removedIndicator = false;
        Object returnPreviousMappedValue = userSessionMap.remove(sessionContextKey);
        if (returnPreviousMappedValue != null || returnPreviousMappedValue == null) {
            removedIndicator = true;
        }
        return removedIndicator;
    }

    @Override
    public boolean isUserSessionActive(String sessionContextKey) {
        return userSessionMap.containsKey(sessionContextKey);
    }

    @Override
    public void addUserInSessionMap(User currentUser, String sessionContextKey) {
        if (!userSessionMap.containsKey(sessionContextKey)) {
            userSessionMap.put(sessionContextKey, currentUser);
        }
    }

    @Override
    public void destroyEntireUserSessionMap() {
        if (!userSessionMap.isEmpty()) {
            userSessionMap.clear();
        }
    }

    @Override
    public User getUserFromUserSessionMap(String sessionContextKey) {
        User userFromSessionMap = null;
        if (userSessionMap.containsKey(sessionContextKey)) {
            userFromSessionMap = userSessionMap.get(sessionContextKey);
        }
        return userFromSessionMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Resource getResource(String classPath) {
        Resource resource = null;
        if (!classPath.isEmpty()) {
            resource = this.applicationContext.getResource(classPath);
        }
        return resource;
    }
}
