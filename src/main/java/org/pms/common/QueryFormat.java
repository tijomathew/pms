package org.pms.common;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by tijo on 12/10/15.
 */
public enum QueryFormat {
    ASCENDING, DESCENDING, NONE;

    @Override
    public String toString() {
        if (this == ASCENDING) {
            return "asc";
        } else if (this == DESCENDING) {
            return "desc";
        }

        return "none";
    }

    public static QueryFormat getQueryFormatter(String formatterValue) {
        QueryFormat queryFormat;
        if (formatterValue.equals("asc")) {
            queryFormat = QueryFormat.ASCENDING;
        } else if (formatterValue.equals("desc")) {
            queryFormat = QueryFormat.DESCENDING;
        } else {
            queryFormat = QueryFormat.NONE;
        }
        return queryFormat;
    }

    public <T> Comparator<T> by(final String propertyName, Class<T> beanClass) {
        if (this == NONE) {
            throw new IllegalStateException("A comparator cannot be determined for a not defined ordering logic!");
        }
        Comparator<T> comparator = null;
        if (this == ASCENDING) {
            comparator = new Comparator<T>() {
                @Override
                public int compare(T t, T t1) {
                    Object left = getProperty(t, propertyName);
                    Object right = getProperty(t1, propertyName);
                    if (left != null && right != null) {
                        if (left.equals(right)) {
                            return 0;
                        }
                        if (left instanceof DateTime) {
                            return ((DateTime) left).getMillis() > ((DateTime) right).getMillis() ? 1 : -1;
                        } else if (left instanceof Date) {
                            return ((Date) left).getTime() > ((Date) right).getTime() ? 1 : -1;
                        } else if (left instanceof Double) {
                            return (Double) left > (Double) right ? 1 : -1;
                        } else if (left instanceof Integer) {
                            return (Integer) left > (Integer) right ? 1 : -1;
                        } else if (left instanceof Long) {
                            return (Long) left > (Long) right ? 1 : -1;
                        } else if (left instanceof BigDecimal) {
                            return ((BigDecimal) left).compareTo((BigDecimal) right);
                        } else if (left instanceof String) {
                            return ((String) left).compareTo((String) right);
                        }
                        return left.hashCode() > right.hashCode() ? 1 : -1;
                    } else if (left == null && right == null) {
                        return 0;
                    } else if (left == null) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            };
        } else if (this == DESCENDING) {
            comparator = new Comparator<T>() {
                @Override
                public int compare(T t, T t1) {
                    Object left = getProperty(t, propertyName);
                    Object right = getProperty(t1, propertyName);
                    if (left != null && right != null) {
                        if (left.equals(right)) {
                            return 0;
                        }
                        if (left instanceof DateTime) {
                            return ((DateTime) left).getMillis() > ((DateTime) right).getMillis() ? -1 : 1;
                        } else if (left instanceof Date) {
                            return ((Date) left).getTime() > ((Date) right).getTime() ? -1 : 1;
                        } else if (left instanceof Double) {
                            return (Double) left > (Double) right ? -1 : 1;
                        } else if (left instanceof Integer) {
                            return (Integer) left > (Integer) right ? -1 : 1;
                        } else if (left instanceof Long) {
                            return (Long) left > (Long) right ? -1 : 1;
                        } else if (left instanceof BigDecimal) {
                            return ((BigDecimal) right).compareTo((BigDecimal) left);
                        } else if (left instanceof String) {
                            return ((String) right).compareTo((String) left);
                        }
                        return left.hashCode() > right.hashCode() ? -1 : 1;
                    } else if (left == null && right == null) {
                        return 0;
                    } else if (left == null) {
                        return +1;
                    } else {
                        return -1;
                    }

                }
            };
        }
        return comparator;
    }

    public static Object getProperty(final Object bean, final String name) {
        if (StringUtils.isNotBlank(name)) {
            try {
                if (name.contains("[")) {
                    return PropertyUtils.getIndexedProperty(bean, name);
                } else if (name.contains("(")) {
                    return PropertyUtils.getMappedProperty(bean, name);
                } else if (name.contains(".")) {
                    return PropertyUtils.getNestedProperty(bean, name);
                } else {
                    return PropertyUtils.getSimpleProperty(bean, name);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
