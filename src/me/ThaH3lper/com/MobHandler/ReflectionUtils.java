package me.ThaH3lper.com.MobHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Deprecated
public class ReflectionUtils
{
  public static <T> T getFieldValue(Class<?> src, String name, Class<T> type, Object from)
    throws SecurityException, NoSuchFieldException
  {
    Field field = src.getDeclaredField(name);
    field.setAccessible(true);
    try
    {
      return type.cast(field.get(from));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return null;
  }

  public static void setFieldValue(Class<?> src, String name, Object in, Object value)
    throws SecurityException, NoSuchFieldException
  {
    Field field = src.getDeclaredField(name);
    field.setAccessible(true);

    if (Modifier.isFinal(field.getModifiers()))
    {
      Field modifiers = Field.class.getDeclaredField("modifiers");
      modifiers.setAccessible(true);
      try
      {
        modifiers.set(field, Integer.valueOf(field.getModifiers() & 0xFFFFFFEF));
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    try
    {
      field.set(in, value);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static <T> T invokeMethod(Class<?> src, String name, Class<T> returnType, Object in, Class<?>[] args, Object[] params)
    throws SecurityException, NoSuchMethodException
  {
    Method method = src.getDeclaredMethod(name, args);
    method.setAccessible(true);
    try
    {
      return returnType.cast(method.invoke(in, params));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return null;
  }
}