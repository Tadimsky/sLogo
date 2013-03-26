package parser.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.ClassDefinitionException;


/**
 * This is a class that makes it much easier to do reflection.
 * It has a few methods that return the information that is needed.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ReflectionHelper {

    /**
     * Creates an instance of the specified class with
     * the specified parameters.
     * 
     * @param c The Class to create.
     * @param params The parameters to pass into the constructor
     * @return The SyntaxNode that has been created.
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws ClassDefinitionException
     */
    public static SyntaxNode createInstanceOf (Class<?> c, Object ... params)
                                                                             throws InstantiationException,
                                                                             IllegalAccessException,
                                                                             IllegalArgumentException,
                                                                             InvocationTargetException,
                                                                             ClassDefinitionException {

        Constructor<?> cstor = findConstructor(c, params);

        return (SyntaxNode) cstor.newInstance(params);
    }

    /**
     * Finds the Constructor of a specified class that take in the
     * provided parameters.
     * 
     * @param c The Class to search
     * @param params The parameters to find a constructor for
     * @return The constructor required to instantiate.
     * @throws ClassDefinitionException
     */
    public static Constructor<?> findConstructor (Class<?> c, Object ... params)
                                                                                throws ClassDefinitionException {
        Class<?>[] types = toClassArray(params);

        Constructor<?>[] constructors = c.getConstructors();
        for (Constructor<?> con : constructors) {
            Class<?>[] conParams = con.getParameterTypes();
            if (paramsEqual(types, conParams)) {
                return con;
            }
        }

        throw new ClassDefinitionException(c.getCanonicalName());
    }

    /**
     * Finds and returns the requested method.
     * 
     * @param c The class to search in.
     * @param methodName The name of the Method.
     * @param params The parameters that the method takes in.
     * @return
     * @throws NoSuchMethodException
     */
    public static Method findMethod (Class<?> c, String methodName, Class<?>[] params)
                                                                                      throws NoSuchMethodException {
        Method res = c.getMethod(methodName, params);
        return res;
    }

    /**
     * Finds and returns the requested method
     * 
     * @param c The class to search in.
     * @param methodName The method name
     * @param params An array of objects that contain the parameters for the method.
     * @return The requested Method
     * @throws NoSuchMethodException
     */
    public static Method findMethod (Class<?> c, String methodName, Object[] params)
                                                                                    throws NoSuchMethodException {
        Class<?>[] types = toClassArray(params);
        return findMethod(c, methodName, types);
    }

    private static boolean paramsEqual (Class<?>[] c1, Class<?>[] c2) {
        if (c1.length != c2.length) {
            return false;
        }
        for (int i = 0; i < c1.length; i++) {
            boolean similar = c1[i].isAssignableFrom(c2[i]) || c2[i].isAssignableFrom(c1[i]);
            if (!similar) {
                return false;
            }
        }

        return true;
    }

    private static Class<?>[] toClassArray (Object ... params) {
        Class<?>[] types = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            types[i] = params[i].getClass();
            // Override Primitive Types
            if (types[i] == Integer.class) {
                types[i] = int.class;
            }
            if (types[i] == Boolean.class) {
                types[i] = boolean.class;
            }
        }
        return types;
    }
}
