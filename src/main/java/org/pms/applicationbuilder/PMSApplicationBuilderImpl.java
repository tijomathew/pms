package org.pms.applicationbuilder;

import org.pms.models.*;
import org.pms.services.FamilyService;
import org.pms.services.MemberService;
import org.pms.services.ParishService;
import org.pms.services.PrayerUnitService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private ParishService parishService;
    @Autowired
    private PrayerUnitService prayerUnitService;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private MemberService memberService;

    public PMSApplicationBuilderImpl() {
        userSessionMap = new HashMap<>();
    }

    @Override
    @PostConstruct
    public void applicationInitializer() {
       /* Long count = 1l;*/
       /* List<Parish> parishList = parishService.getAllParish();
        for (Parish parish : parishList) {
            List<PrayerUnit> prayerUnitListUnderParish = parish.getPrayerUnits();
            for (PrayerUnit prayerUnit : prayerUnitListUnderParish) {
                prayerUnit.setPrayerUnitNo(count);
                prayerUnitService.updatePrayerUnit(prayerUnit);
                count += 1;
            }
        }*/



        /*List<Parish> parishList1 = parishService.getAllParish();
        for (Parish parish : parishList1) {
            List<PrayerUnit> prayerUnitListUnderParish = parish.getPrayerUnits();
            for (PrayerUnit prayerUnit : prayerUnitListUnderParish) {
                List<Family> familyList = prayerUnit.getMappedFamilies();
                for (Family family : familyList) {
                    family.setFamilyNo(count);
                    familyService.updateFamily(family);
                    count += 1;
                }
            }
        }*/


       /* List<Parish> parishList2 = parishService.getAllParish();
        for (Parish parish : parishList2) {
            List<PrayerUnit> prayerUnitListUnderParish = parish.getPrayerUnits();
            for (PrayerUnit prayerUnit : prayerUnitListUnderParish) {
                List<Family> familyList = prayerUnit.getMappedFamilies();
                for (Family family : familyList) {
                    List<Member> memberList = family.getMemberList();
                    for (Member member : memberList) {
                        member.setMemberNo(count);
                        memberService.updateMember(member);
                        count += 1;
                    }
                }
            }
        }*/

        /*System.out.println("done");*/

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
