package parser.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.ClassDefinitionException;


public class ReflectionHelper {

    public static SyntaxNode createInstanceOf (Class<?> c, Object ... params)
                                                                             throws InstantiationException,
                                                                             IllegalAccessException,
                                                                             IllegalArgumentException,
                                                                             InvocationTargetException,
                                                                             ClassDefinitionException
    {

        Constructor<?> cstor = findConstructor(c, params);

        return (SyntaxNode) cstor.newInstance(params);
    }

    public static Constructor<?> findConstructor (Class<?> c, Object ... params)
                                                                                throws ClassDefinitionException
    {
        Class<?>[] types = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++)
        {
            types[i] = params[i].getClass();
        }

        Constructor<?>[] constructors = c.getConstructors();
        for (Constructor<?> con : constructors)
        {
            Class<?>[] conParams = con.getParameterTypes();
            if (paramsEqual(types, conParams)) { return con; }
        }

        throw new ClassDefinitionException(c.getCanonicalName());
    }

    private static boolean paramsEqual (Class<?>[] c1, Class<?>[] c2)
    {
        if (c1.length != c2.length) { return false; }
        for (int i = 0; i < c1.length; i++)
        {
            boolean similar = c1[i].isAssignableFrom(c2[i]) || c2[i].isAssignableFrom(c1[i]);
            if (!similar) { return false; }
        }

        return true;
    }
}
