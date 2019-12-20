package com.weaverboot.tools.classTools;

import java.beans.Introspector;
import java.io.Closeable;
import java.io.Externalizable;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;

/**
 *
 * Class 工具类
 *
 * 此类来源于Spring 的 ClassUtils
 *
 * @Author : Jaylan Zhou
 * @Date : 2019-12-20 13:52
 * @Version : 1.0
 */
public class ClassTools {

    private ClassTools(){



    }

    public static final String ARRAY_SUFFIX = "[]";

    private static final String INTERNAL_ARRAY_PREFIX = "[";

    private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";

    private static final char PACKAGE_SEPARATOR = '.';

    private static final char PATH_SEPARATOR = '/';

    private static final char INNER_CLASS_SEPARATOR = '$';

    public static final String CGLIB_CLASS_SEPARATOR = "$$";

    public static final String CLASS_FILE_SUFFIX = ".class";


    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<>(8);

    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap<>(8);

    private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap<>(32);

    private static final Map<String, Class<?>> commonClassCache = new HashMap<>(64);

    private static final Set<Class<?>> javaLanguageInterfaces;


    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
        primitiveWrapperTypeMap.put(Byte.class, byte.class);
        primitiveWrapperTypeMap.put(Character.class, char.class);
        primitiveWrapperTypeMap.put(Double.class, double.class);
        primitiveWrapperTypeMap.put(Float.class, float.class);
        primitiveWrapperTypeMap.put(Integer.class, int.class);
        primitiveWrapperTypeMap.put(Long.class, long.class);
        primitiveWrapperTypeMap.put(Short.class, short.class);
        primitiveWrapperTypeMap.put(Void.class, void.class);

        // Map entry iteration is less expensive to initialize than forEach with lambdas
        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet()) {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
            registerCommonClasses(entry.getKey());
        }

        Set<Class<?>> primitiveTypes = new HashSet<>(32);
        primitiveTypes.addAll(primitiveWrapperTypeMap.values());
        Collections.addAll(primitiveTypes, boolean[].class, byte[].class, char[].class,
                double[].class, float[].class, int[].class, long[].class, short[].class);
        primitiveTypes.add(void.class);
        for (Class<?> primitiveType : primitiveTypes) {
            primitiveTypeNameMap.put(primitiveType.getName(), primitiveType);
        }

        registerCommonClasses(Boolean[].class, Byte[].class, Character[].class, Double[].class,
                Float[].class, Integer[].class, Long[].class, Short[].class);
        registerCommonClasses(Number.class, Number[].class, String.class, String[].class,
                Class.class, Class[].class, Object.class, Object[].class);
        registerCommonClasses(Throwable.class, Exception.class, RuntimeException.class,
                Error.class, StackTraceElement.class, StackTraceElement[].class);
        registerCommonClasses(Enum.class, Iterable.class, Iterator.class, Enumeration.class,
                Collection.class, List.class, Set.class, Map.class, Map.Entry.class, Optional.class);

        Class<?>[] javaLanguageInterfaceArray = {Serializable.class, Externalizable.class,
                Closeable.class, AutoCloseable.class, Cloneable.class, Comparable.class};
        registerCommonClasses(javaLanguageInterfaceArray);
        javaLanguageInterfaces = new HashSet<>(Arrays.asList(javaLanguageInterfaceArray));
    }


    private static void registerCommonClasses(Class<?>... commonClasses) {
        for (Class<?> clazz : commonClasses) {
            commonClassCache.put(clazz.getName(), clazz);
        }
    }


    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassTools.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                }
                catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }


    public static ClassLoader overrideThreadContextClassLoader(ClassLoader classLoaderToUse) {
        Thread currentThread = Thread.currentThread();
        ClassLoader threadContextClassLoader = currentThread.getContextClassLoader();
        if (classLoaderToUse != null && !classLoaderToUse.equals(threadContextClassLoader)) {
            currentThread.setContextClassLoader(classLoaderToUse);
            return threadContextClassLoader;
        }
        else {
            return null;
        }
    }

    public static Class<?> forName(String name, ClassLoader classLoader)
            throws ClassNotFoundException, LinkageError {

        Class<?> clazz = resolvePrimitiveClassName(name);
        if (clazz == null) {
            clazz = commonClassCache.get(name);
        }
        if (clazz != null) {
            return clazz;
        }

        // "java.lang.String[]" style arrays
        if (name.endsWith(ARRAY_SUFFIX)) {
            String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
            Class<?> elementClass = forName(elementClassName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }

        // "[Ljava.lang.String;" style arrays
        if (name.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name.endsWith(";")) {
            String elementName = name.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name.length() - 1);
            Class<?> elementClass = forName(elementName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }

        // "[[I" or "[[Ljava.lang.String;" style arrays
        if (name.startsWith(INTERNAL_ARRAY_PREFIX)) {
            String elementName = name.substring(INTERNAL_ARRAY_PREFIX.length());
            Class<?> elementClass = forName(elementName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }

        ClassLoader clToUse = classLoader;
        if (clToUse == null) {
            clToUse = getDefaultClassLoader();
        }
        try {
            return Class.forName(name, false, clToUse);
        }
        catch (ClassNotFoundException ex) {
            int lastDotIndex = name.lastIndexOf(PACKAGE_SEPARATOR);
            if (lastDotIndex != -1) {
                String innerClassName =
                        name.substring(0, lastDotIndex) + INNER_CLASS_SEPARATOR + name.substring(lastDotIndex + 1);
                try {
                    return Class.forName(innerClassName, false, clToUse);
                }
                catch (ClassNotFoundException ex2) {
                    // Swallow - let original exception get through
                }
            }
            throw ex;
        }
    }

    public static Class<?> resolveClassName(String className, ClassLoader classLoader)
            throws IllegalArgumentException {

        try {
            return forName(className, classLoader);
        }
        catch (IllegalAccessError err) {
            throw new IllegalStateException("Readability mismatch in inheritance hierarchy of class [" +
                    className + "]: " + err.getMessage(), err);
        }
        catch (LinkageError err) {
            throw new IllegalArgumentException("Unresolvable class definition for class [" + className + "]", err);
        }
        catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException("Could not find class [" + className + "]", ex);
        }
    }

    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            forName(className, classLoader);
            return true;
        }
        catch (IllegalAccessError err) {
            throw new IllegalStateException("Readability mismatch in inheritance hierarchy of class [" +
                    className + "]: " + err.getMessage(), err);
        }
        catch (Throwable ex) {
            // Typically ClassNotFoundException or NoClassDefFoundError...
            return false;
        }
    }

    public static boolean isVisible(Class<?> clazz, ClassLoader classLoader) {
        if (classLoader == null) {
            return true;
        }
        try {
            if (clazz.getClassLoader() == classLoader) {
                return true;
            }
        }
        catch (SecurityException ex) {
            // Fall through to loadable check below
        }

        // Visible if same Class can be loaded from given ClassLoader
        return isLoadable(clazz, classLoader);
    }

    public static boolean isCacheSafe(Class<?> clazz, ClassLoader classLoader) {
        
        try {
            ClassLoader target = clazz.getClassLoader();
            // Common cases
            if (target == classLoader || target == null) {
                return true;
            }
            if (classLoader == null) {
                return false;
            }
            // Check for match in ancestors -> positive
            ClassLoader current = classLoader;
            while (current != null) {
                current = current.getParent();
                if (current == target) {
                    return true;
                }
            }
            // Check for match in children -> negative
            while (target != null) {
                target = target.getParent();
                if (target == classLoader) {
                    return false;
                }
            }
        }
        catch (SecurityException ex) {
            // Fall through to loadable check below
        }

        // Fallback for ClassLoaders without parent/child relationship:
        // safe if same Class can be loaded from given ClassLoader
        return (classLoader != null && isLoadable(clazz, classLoader));
    }

    private static boolean isLoadable(Class<?> clazz, ClassLoader classLoader) {
        try {
            return (clazz == classLoader.loadClass(clazz.getName()));
            // Else: different class with same name found
        }
        catch (ClassNotFoundException ex) {
            // No corresponding class found at all
            return false;
        }
    }


    public static Class<?> resolvePrimitiveClassName(String name) {
        Class<?> result = null;
        // Most class names will be quite long, considering that they
        // SHOULD sit in a package, so a length check is worthwhile.
        if (name != null && name.length() <= 8) {
            // Could be a primitive - likely.
            result = primitiveTypeNameMap.get(name);
        }
        return result;
    }

    public static boolean isPrimitiveWrapper(Class<?> clazz) {
        
        return primitiveWrapperTypeMap.containsKey(clazz);
    }

    public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        
        return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
    }

    public static boolean isPrimitiveArray(Class<?> clazz) {
        
        return (clazz.isArray() && clazz.getComponentType().isPrimitive());
    }

    public static boolean isPrimitiveWrapperArray(Class<?> clazz) {
        
        return (clazz.isArray() && isPrimitiveWrapper(clazz.getComponentType()));
    }

    public static Class<?> resolvePrimitiveIfNecessary(Class<?> clazz) {
        
        return (clazz.isPrimitive() && clazz != void.class ? primitiveTypeToWrapperMap.get(clazz) : clazz);
    }

    public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        if (lhsType.isPrimitive()) {
            Class<?> resolvedPrimitive = primitiveWrapperTypeMap.get(rhsType);
            if (lhsType == resolvedPrimitive) {
                return true;
            }
        }
        else {
            Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
            if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAssignableValue(Class<?> type, Object value) {
        
        return (value != null ? isAssignable(type, value.getClass()) : !type.isPrimitive());
    }

    public static String convertResourcePathToClassName(String resourcePath) {
        
        return resourcePath.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }

    public static String convertClassNameToResourcePath(String className) {
        
        return className.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }

    public static String addResourcePathToPackagePath(Class<?> clazz, String resourceName) {
        if (!resourceName.startsWith("/")) {
            return classPackageAsResourcePath(clazz) + '/' + resourceName;
        }
        return classPackageAsResourcePath(clazz) + resourceName;
    }

    public static String classPackageAsResourcePath(Class<?> clazz) {
        if (clazz == null) {
            return "";
        }
        String className = clazz.getName();
        int packageEndIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
        if (packageEndIndex == -1) {
            return "";
        }
        String packageName = className.substring(0, packageEndIndex);
        return packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
    }



    public static Class<?>[] toClassArray(Collection<Class<?>> collection) {
        return collection.toArray(new Class<?>[0]);
    }

    public static Class<?>[] getAllInterfaces(Object instance) {
        return getAllInterfacesForClass(instance.getClass());
    }

    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz) {
        return getAllInterfacesForClass(clazz, null);
    }

    public static Class<?>[] getAllInterfacesForClass(Class<?> clazz, ClassLoader classLoader) {
        return toClassArray(getAllInterfacesForClassAsSet(clazz, classLoader));
    }

    public static Set<Class<?>> getAllInterfacesAsSet(Object instance) {
        return getAllInterfacesForClassAsSet(instance.getClass());
    }

    public static Set<Class<?>> getAllInterfacesForClassAsSet(Class<?> clazz) {
        return getAllInterfacesForClassAsSet(clazz, null);
    }

    public static Set<Class<?>> getAllInterfacesForClassAsSet(Class<?> clazz, ClassLoader classLoader) {
        
        if (clazz.isInterface() && isVisible(clazz, classLoader)) {
            return Collections.singleton(clazz);
        }
        Set<Class<?>> interfaces = new LinkedHashSet<>();
        Class<?> current = clazz;
        while (current != null) {
            Class<?>[] ifcs = current.getInterfaces();
            for (Class<?> ifc : ifcs) {
                if (isVisible(ifc, classLoader)) {
                    interfaces.add(ifc);
                }
            }
            current = current.getSuperclass();
        }
        return interfaces;
    }

    @SuppressWarnings("deprecation")  // on JDK 9
    public static Class<?> createCompositeInterface(Class<?>[] interfaces, ClassLoader classLoader) {
        return Proxy.getProxyClass(classLoader, interfaces);
    }


    public static Class<?> determineCommonAncestor(Class<?> clazz1, Class<?> clazz2) {
        if (clazz1 == null) {
            return clazz2;
        }
        if (clazz2 == null) {
            return clazz1;
        }
        if (clazz1.isAssignableFrom(clazz2)) {
            return clazz1;
        }
        if (clazz2.isAssignableFrom(clazz1)) {
            return clazz2;
        }
        Class<?> ancestor = clazz1;
        do {
            ancestor = ancestor.getSuperclass();
            if (ancestor == null || Object.class == ancestor) {
                return null;
            }
        }
        while (!ancestor.isAssignableFrom(clazz2));
        return ancestor;
    }

    public static boolean isJavaLanguageInterface(Class<?> ifc) {
        return javaLanguageInterfaces.contains(ifc);
    }

    public static boolean isInnerClass(Class<?> clazz) {
        return (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers()));
    }

    @Deprecated
    public static boolean isCglibProxy(Object object) {
        return isCglibProxyClass(object.getClass());
    }

    @Deprecated
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    @Deprecated
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains(CGLIB_CLASS_SEPARATOR));
    }

    public static Class<?> getUserClass(Object instance) {
        return getUserClass(instance.getClass());
    }

    public static Class<?> getUserClass(Class<?> clazz) {
        if (clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null && superclass != Object.class) {
                return superclass;
            }
        }
        return clazz;
    }


    public static String getDescriptiveType(Object value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (Proxy.isProxyClass(clazz)) {
            String prefix = clazz.getName() + " implementing ";
            StringJoiner result = new StringJoiner(",", prefix, "");
            for (Class<?> ifc : clazz.getInterfaces()) {
                result.add(ifc.getName());
            }
            return result.toString();
        }
        else {
            return clazz.getTypeName();
        }
    }

    public static boolean matchesTypeName(Class<?> clazz, String typeName) {
        return (typeName != null &&
                (typeName.equals(clazz.getTypeName()) || typeName.equals(clazz.getSimpleName())));
    }

    public static String getShortName(String className) {
        int lastDotIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
        int nameEndIndex = className.indexOf(CGLIB_CLASS_SEPARATOR);
        if (nameEndIndex == -1) {
            nameEndIndex = className.length();
        }
        String shortName = className.substring(lastDotIndex + 1, nameEndIndex);
        shortName = shortName.replace(INNER_CLASS_SEPARATOR, PACKAGE_SEPARATOR);
        return shortName;
    }

    public static String getShortName(Class<?> clazz) {
        return getShortName(getQualifiedName(clazz));
    }

    public static String getShortNameAsProperty(Class<?> clazz) {
        String shortName = getShortName(clazz);
        int dotIndex = shortName.lastIndexOf(PACKAGE_SEPARATOR);
        shortName = (dotIndex != -1 ? shortName.substring(dotIndex + 1) : shortName);
        return Introspector.decapitalize(shortName);
    }

    public static String getClassFileName(Class<?> clazz) {
        
        String className = clazz.getName();
        int lastDotIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
        return className.substring(lastDotIndex + 1) + CLASS_FILE_SUFFIX;
    }

    public static String getPackageName(Class<?> clazz) {
        
        return getPackageName(clazz.getName());
    }

    public static String getPackageName(String fqClassName) {
        int lastDotIndex = fqClassName.lastIndexOf(PACKAGE_SEPARATOR);
        return (lastDotIndex != -1 ? fqClassName.substring(0, lastDotIndex) : "");
    }

    /**
     * Return the qualified name of the given class: usually simply
     * the class name, but component type class name + "[]" for arrays.
     * @param clazz the class
     * @return the qualified name of the class
     */
    public static String getQualifiedName(Class<?> clazz) {
        
        return clazz.getTypeName();
    }

    public static String getQualifiedMethodName(Method method) {
        return getQualifiedMethodName(method, null);
    }

    public static String getQualifiedMethodName(Method method, Class<?> clazz) {
        return (clazz != null ? clazz : method.getDeclaringClass()).getName() + '.' + method.getName();
    }

    public static boolean hasConstructor(Class<?> clazz, Class<?>... paramTypes) {
        return (getConstructorIfAvailable(clazz, paramTypes) != null);
    }


    public static <T> Constructor<T> getConstructorIfAvailable(Class<T> clazz, Class<?>... paramTypes) {
        
        try {
            return clazz.getConstructor(paramTypes);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }

    public static boolean hasMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        return (getMethodIfAvailable(clazz, methodName, paramTypes) != null);
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        
        if (paramTypes != null) {
            try {
                return clazz.getMethod(methodName, paramTypes);
            }
            catch (NoSuchMethodException ex) {
                throw new IllegalStateException("Expected method not found: " + ex);
            }
        }
        else {
            Set<Method> candidates = findMethodCandidatesByName(clazz, methodName);
            if (candidates.size() == 1) {
                return candidates.iterator().next();
            }
            else if (candidates.isEmpty()) {
                throw new IllegalStateException("Expected method not found: " + clazz.getName() + '.' + methodName);
            }
            else {
                throw new IllegalStateException("No unique method found: " + clazz.getName() + '.' + methodName);
            }
        }
    }


    public static Method getMethodIfAvailable(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        
        if (paramTypes != null) {
            try {
                return clazz.getMethod(methodName, paramTypes);
            }
            catch (NoSuchMethodException ex) {
                return null;
            }
        }
        else {
            Set<Method> candidates = findMethodCandidatesByName(clazz, methodName);
            if (candidates.size() == 1) {
                return candidates.iterator().next();
            }
            return null;
        }
    }

    public static int getMethodCountForName(Class<?> clazz, String methodName) {
        
        int count = 0;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (methodName.equals(method.getName())) {
                count++;
            }
        }
        Class<?>[] ifcs = clazz.getInterfaces();
        for (Class<?> ifc : ifcs) {
            count += getMethodCountForName(ifc, methodName);
        }
        if (clazz.getSuperclass() != null) {
            count += getMethodCountForName(clazz.getSuperclass(), methodName);
        }
        return count;
    }

    public static boolean hasAtLeastOneMethodWithName(Class<?> clazz, String methodName) {
        
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals(methodName)) {
                return true;
            }
        }
        Class<?>[] ifcs = clazz.getInterfaces();
        for (Class<?> ifc : ifcs) {
            if (hasAtLeastOneMethodWithName(ifc, methodName)) {
                return true;
            }
        }
        return (clazz.getSuperclass() != null && hasAtLeastOneMethodWithName(clazz.getSuperclass(), methodName));
    }


    public static Method getInterfaceMethodIfPossible(Method method) {
        if (Modifier.isPublic(method.getModifiers()) && !method.getDeclaringClass().isInterface()) {
            Class<?> current = method.getDeclaringClass();
            while (current != null && current != Object.class) {
                Class<?>[] ifcs = current.getInterfaces();
                for (Class<?> ifc : ifcs) {
                    try {
                        return ifc.getMethod(method.getName(), method.getParameterTypes());
                    }
                    catch (NoSuchMethodException ex) {
                        // ignore
                    }
                }
                current = current.getSuperclass();
            }
        }
        return method;
    }

    public static boolean isUserLevelMethod(Method method) {
        return (method.isBridge() || (!method.isSynthetic() && !isGroovyObjectMethod(method)));
    }

    private static boolean isGroovyObjectMethod(Method method) {
        return method.getDeclaringClass().getName().equals("groovy.lang.GroovyObject");
    }

    private static boolean isOverridable(Method method, Class<?> targetClass) {
        if (Modifier.isPrivate(method.getModifiers())) {
            return false;
        }
        if (Modifier.isPublic(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) {
            return true;
        }
        return (targetClass == null ||
                getPackageName(method.getDeclaringClass()).equals(getPackageName(targetClass)));
    }


    public static Method getStaticMethod(Class<?> clazz, String methodName, Class<?>... args) {
        
        try {
            Method method = clazz.getMethod(methodName, args);
            return Modifier.isStatic(method.getModifiers()) ? method : null;
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }

    private static Set<Method> findMethodCandidatesByName(Class<?> clazz, String methodName) {
        Set<Method> candidates = new HashSet<>(1);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                candidates.add(method);
            }
        }
        return candidates;
    }

}
