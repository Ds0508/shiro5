package kuang.simgle;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumSingle {

    instance;

    public EnumSingle getInstance(){
        return instance;
    }
}
class Test{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingle instance = EnumSingle.instance;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance1 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance1);
    }
}
