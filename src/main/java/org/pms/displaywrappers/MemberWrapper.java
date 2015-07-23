package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.MemberDto;
import org.pms.helpers.GridRow;
import org.pms.models.Member;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class MemberWrapper implements GridRow {

    private Member memberBean;

    private String[] VALID_BEAN_PROPERTIES = {"memberID", "name"};

    public MemberWrapper(Member memberBean) {
        this.memberBean = memberBean;
    }

    @Override
    public Long getId() {
        return memberBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                convertedResult.add(BeanUtils.getProperty(this.memberBean, VALID_BEAN_PROPERTIES[i]).toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return convertedResult;
    }
}
