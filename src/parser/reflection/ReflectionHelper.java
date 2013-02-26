package parser.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.ClassDefinitionException;

public class ReflectionHelper {

    public static SyntaxNode createInstanceOf(Class<?> c, Object... params) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassDefinitionException
    {
        
        Constructor<?> cstor = findConstructor(c, params);        
        
        return (SyntaxNode)cstor.newInstance(params);
    }
    
    public static Constructor<?> findConstructor(Class<?> c, Object ... params) throws ClassDefinitionException
    {
        Class<?>[] types = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++)
        {
            types[i] = params[i].getClass();
        }
        
        try {
            return c.getConstructor(types);
        }
        catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
            throw new ClassDefinitionException(c.getCanonicalName());            
        }
    }
}
