package pl.edu.wat.wcy.swingdynamicgui.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReflectionUtils {
    private static Logger LOGGER = Logger.getLogger(ReflectionUtils.class.getName());

    public static Object getValue(Object object, Field field) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalArgumentException | IllegalAccessException e) {
        }
        return null;
    }

    public static Object getValue(Object object, String fieldName) {
        try {
            Field field = getField(object, fieldName);
            if (field == null) {
                return null;
            }
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalArgumentException | IllegalAccessException e) {
        }
        return null;
    }

    public static Field getField(Object obj, String fieldName) {
        return getField(obj.getClass(), fieldName);

    }

    public static Field getField(Class cl, String fieldName) {
        try {
            return cl.getDeclaredField(fieldName);
        } catch (NoSuchFieldException | SecurityException e) {
            if (cl.getSuperclass() != null) {
                return getField(cl.getSuperclass(), fieldName);
            }
        }
        return null;
    }

    public static List<Field> getAllFieldsWithAnnotation(Class<?> type, Class<? extends Annotation> annotationClass) {
        return getAllFieldsWithAnnotation(new LinkedList<>(), type, annotationClass);

    }

    public static void setValue(Object object, String fieldName, Object value) {
        setValue(object, getField(object, fieldName), value);
    }

    public static void setValue(Object object, Field field, Object value) {
        if (field == null) {
            return;
        }

        try {
            field.setAccessible(true);
            if (value != null) {
                Class valueClass = value.getClass();

                if (value != null && isNumber(valueClass)) {
                    setNumberValue(object, field, (Number) value);
                } else if (isArrayOrCollection(valueClass) && !isArrayOrCollection(field.getType())) {
                    Collection c = getObjectAsCollection(value);
                    Object destinationObject = null;
                    if (!c.isEmpty()) {
                        destinationObject = c.iterator().next();
                    }
                    field.set(object, destinationObject);
                } else {
                    field.set(object, value);
                }
            } else {
                field.set(object, value);
            }

        } catch (IllegalArgumentException | IllegalAccessException
                | NullPointerException e) {
            LOGGER.log(Level.INFO, "Error with settings value for field", e);

        }
    }


    public static void setNumberValue(Object object, Field field, Number value)
            throws IllegalArgumentException, IllegalAccessException {
        Class<?> fieldType = field.getType();
        if (field.getType().isPrimitive()) {
            if (isInt(fieldType)) {
                field.setInt(object, value.intValue());
            } else if (isLong(fieldType)) {
                field.setLong(object, value.longValue());
            } else if (isShort(fieldType)) {
                field.setShort(object, value.shortValue());
            } else if (isByte(fieldType)) {
                field.setByte(object, value.byteValue());
            } else if (isFloat(fieldType)) {
                field.setFloat(object, value.floatValue());
            } else if (isDouble(fieldType)) {
                field.setDouble(object, value.doubleValue());
            }
        } else {
            field.set(object, value);
        }

    }

    public static boolean isNumber(Class<?> type) {
        if (type.equals(Integer.class) || type.equals(int.class)) {
            return type.equals(Integer.class) || type.equals(int.class);
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return type.equals(Float.class) || type.equals(float.class);
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return type.equals(Double.class) || type.equals(double.class);
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return type.equals(Long.class) || type.equals(long.class);
        } else if (type.equals(Short.class) || type.equals(short.class)) {
            return type.equals(Short.class) || type.equals(short.class);
        } else if (type.equals(Byte.class) || type.equals(byte.class)) {
            return type.equals(Byte.class) || type.equals(byte.class);
        }
        return false;
    }

    public static boolean isIntAndDerivative(Class<?> type) {
        if (type.equals(Integer.class) || type.equals(int.class)) {
            return type.equals(Integer.class) || type.equals(int.class);
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return type.equals(Long.class) || type.equals(long.class);
        } else if (type.equals(Short.class) || type.equals(short.class)) {
            return type.equals(Short.class) || type.equals(short.class);
        } else if (type.equals(Byte.class) || type.equals(byte.class)) {
            return type.equals(Byte.class) || type.equals(byte.class);
        }
        return false;
    }

    public static boolean isInt(Class<?> type) {
        return type.equals(Integer.class) || type.equals(int.class);
    }

    public static boolean isLong(Class<?> type) {
        return type.equals(Long.class) || type.equals(long.class);
    }

    public static boolean isShort(Class<?> type) {
        return type.equals(Short.class) || type.equals(short.class);
    }

    public static boolean isByte(Class<?> type) {
        return type.equals(Byte.class) || type.equals(byte.class);
    }

    public static boolean isFloatAndDerivative(Class<?> type) {
        if (type.equals(Float.class) || type.equals(float.class)) {
            return type.equals(Float.class) || type.equals(float.class);
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return type.equals(Double.class) || type.equals(double.class);
        }
        return false;
    }

    public static boolean isFloat(Class<?> type) {
        return type.equals(Float.class) || type.equals(float.class);
    }

    public static boolean isDouble(Class<?> type) {
        return type.equals(Double.class) || type.equals(double.class);
    }

    public static boolean isInstance(Object pattern, Class... toCheck) {
        boolean assignable = false;
        for (int i = 0; i < toCheck.length; i++) {
            if (toCheck[i].isInstance(pattern)) {
                assignable = true;
                i = toCheck.length;
            }
        }
        return assignable;
    }

    public static boolean isTypeOfAll(Class<?> objClass, Class<?>... isTypeOf) {
        boolean valid = true;
        int i = 0;
        if (objClass == null) {
            return false;
        }
        while (valid && i < isTypeOf.length) {
            valid &= isTypeOf[i].isAssignableFrom(objClass);
            i++;
        }
        return valid && isTypeOf.length > 0;
    }

    /**
     * @param type
     * @param isTypeOf
     * @return
     */
    public static boolean isTypeOfAtLeastOne(Class<?> type,
                                             Class<?>... isTypeOf) {
        boolean valid = false;
        int i = 0;
        while (!valid && i < isTypeOf.length) {
            valid = isTypeOf[i].isAssignableFrom(type);
            i++;
        }
        return valid && isTypeOf.length > 0;
    }

    private static List<Field> getAllFieldsWithAnnotation(List<Field> fields, Class<?> type, Class<? extends Annotation> annotationClass) {

        if (type.getSuperclass() != null) {
            getAllFieldsWithAnnotation(fields, type.getSuperclass(), annotationClass);
        }
        Arrays.stream(type.getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            if (field.getAnnotation(annotationClass) != null) {
                fields.add(field);
            }
        });
        return fields;
    }

    public static boolean isBoolean(Class<?> type) {
        return isTypeOfAtLeastOne(type, boolean.class, Boolean.class);
    }

    public static boolean isEnum(Class<?> type) {
        return isTypeOfAtLeastOne(type, Enum.class);
    }


    public static boolean isJavaLangClass(Class cls) {
        return cls.getName().startsWith("java.lang");
    }

    public static boolean isUserDefinedClass(Class cls) {

        return !(
                isJavaLangClass(cls) || isNumber(cls) || isEnum(cls)
                        || isBoolean(cls) || isDate(cls)
        );
    }

    public static boolean isDate(Class<?> type) {
        return isTypeOfAtLeastOne(type, Date.class, Calendar.class);
    }

    public static boolean isArrayOrCollection(Class cls) {
        return Collection.class.isAssignableFrom(cls) || cls.isArray();
    }

    public static Collection getObjectAsCollection(Object object) {
        if (object == null) {
            return new ArrayList(0);
        }

        if (object instanceof Collection) {
            return (Collection) object;
        } else if (object.getClass().isArray()) {
            return Arrays.asList((Object[]) object);
        } else {
            return Arrays.asList(object);
        }
    }
}
