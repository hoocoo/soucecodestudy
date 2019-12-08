package proxy;
import java.lang.reflect.Method;
public class IdtoProxy extends MyProxy implements Idto, Idto2 {
    public MyInvocationHandler myInvocationHandler;
    public static Method add1;
    public static Method get2;
    public static Method adda3;
    public static Method geta4;

    static {
        try {
            add1 = Class.forName("proxy.Idto").getMethod("add", new Class[0]);
            get2 = Class.forName("proxy.Idto").getMethod("get", new Class[0]);
            adda3 = Class.forName("proxy.Idto2").getMethod("adda", new Class[0]);
            geta4 = Class.forName("proxy.Idto2").getMethod("geta", new Class[0]);
        } catch (Exception e) {
        }
    }

    public IdtoProxy(MyInvocationHandler myInvocationHandler) {
        this.myInvocationHandler = myInvocationHandler;
    }

    public void add() {
        Object[] o = {};
        try {
            this.myInvocationHandler.invoke(this, add1, o);
            return;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public java.lang.String get() {
        Object[] o = {};
        try {
            return (java.lang.String) this.myInvocationHandler.invoke(this, get2, o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void adda() {
        Object[] o = {};
        try {
            this.myInvocationHandler.invoke(this, adda3, o);
            return;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public java.lang.String geta() {
        Object[] o = {};
        try {
            return (java.lang.String) this.myInvocationHandler.invoke(this, geta4, o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}