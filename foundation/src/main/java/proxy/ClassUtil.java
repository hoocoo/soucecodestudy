package proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassUtil {
    public static String mackProxyClass(Class<?> c) throws Exception {
        if (!c.isInterface()) {
            throw new Exception("代理的类必须是接口");
        }
        StringBuffer importsp = new StringBuffer();
        importsp.append("import java.lang.reflect.Method;\n");
        importsp.append("import proxy.MyProxy;\n");
        importsp.append("import proxy.MyInvocationHandler;\n");
        importsp.append("import " + c.getName() + ";\n");
        StringBuilder publicStaticMethods = new StringBuilder();
        //public static Method add;
        StringBuilder publicMethods = new StringBuilder();
        publicMethods.append("public MyInvocationHandler myInvocationHandler;\n");
        StringBuilder constructorsp = new StringBuilder();
        String interFaceName = c.getName().substring(c.getName().lastIndexOf(".") + 1);
        constructorsp.append("public ").append("" + interFaceName + "Porxy").
                append("(MyInvocationHandler myInvocationHandler) { "
                        + "this.myInvocationHandler = myInvocationHandler;"
                        + "}");
        publicStaticMethods.append(" static { try {  ");
        StringBuilder classsp = new StringBuilder();
        classsp.append("public class").append(" " + interFaceName + "Porxy").append(" extends MyProxy implements ").append(interFaceName).append("{");
        StringBuilder allMethods = new StringBuilder();
        Method[] Methods = c.getMethods();
        int curr = 0;
        for (Method m_ : Methods) {
            curr++;
            publicMethods.append("public static Method ").append(m_.getName() + String.valueOf(curr)).append(";\n");
            publicStaticMethods.append("").append(m_.getName() + String.valueOf(curr)).append("=");
            publicStaticMethods.append("Class.forName(\"" + c.getName() + "\")" + ".getMethod(\"" + m_.getName() + "\", ");
            StringBuilder sp = new StringBuilder();
            StringBuilder spArgs = new StringBuilder();
            spArgs.append("Object[] o ={");
            //public
            sp.append(Modifier.toString(m_.getModifiers()).replace("abstract", "")).append(" ");
            //void | java.lang.String
            sp.append(m_.getReturnType().getName()).append(" ");
            //add()|get()
            sp.append(m_.getName().concat("("));
            StringBuilder methodCLass = new StringBuilder();
            if (m_.getParameterTypes().length > 0) {
                Class<?>[] claszz = m_.getParameterTypes();
                int methodOffset = 0;
                methodCLass.append("new Class[] { ");
                for (Class<?> c_ : claszz) {
                    String paramStr = "obj" + String.valueOf(++methodOffset);
                    spArgs.append(paramStr.concat(","));
                    sp.append(c_.getName().toString().concat(" ").concat(paramStr)).append(",");
                    methodCLass.append("Class.forName(\"" + c_.getName()).append("\"),");
                }
                sp = new StringBuilder(sp.substring(0, sp.length() - 1));
                spArgs = new StringBuilder(spArgs.substring(0, spArgs.length() - 1));
                methodCLass = new StringBuilder(methodCLass.substring(0, methodCLass.length() - 1));
            }
            if (methodCLass.length() > 0) {
                methodCLass.append("}");
            } else {
                methodCLass.append("new Class[0]");
            }
            sp.append("){\n");
            spArgs.append("}");
            sp.append(spArgs + ";\n");
            if (sp.toString().contains("void")) {
                sp.append("try {\n this.myInvocationHandler.invoke(this,").append(m_.getName() + String.valueOf(curr)).append(",").append("o);\n return;\n");
                sp.append("} catch (Throwable e) {e.printStackTrace();}}");
            } else {
                sp.append("try {return "
                        + "("
                        + m_.getReturnType().getName()
                        + ")"
                        + "this.myInvocationHandler.invoke(this,").append(m_.getName() + String.valueOf(curr)).append(",").append("o);\n");
                sp.append("} catch (Exception e) {e.printStackTrace();} return null;");
            }
            publicStaticMethods.append(methodCLass).append(");\n");
            allMethods.append(sp);
        }
        publicStaticMethods.append("} catch(Exception e){}}");
        classsp.append(publicMethods)
                .append(publicStaticMethods)
                .append(constructorsp).append(allMethods).append("}");
        classsp.append("}");
        importsp.append(classsp);
        return importsp.toString();
    }

    public static String mackMultiProxyClass(Class<?>[] cs) throws Exception {
        StringBuffer importsp = new StringBuffer();
        importsp.append("import java.lang.reflect.Method;\n");
        importsp.append("import proxy.MyProxy;\n");
        importsp.append("import proxy.MyInvocationHandler;\n");
        StringBuilder publicStaticMethods = new StringBuilder();
        publicStaticMethods.append(" static { try {  ");
        //public static Method add;
        StringBuilder publicMethods = new StringBuilder();
        publicMethods.append("public MyInvocationHandler myInvocationHandler;\n");
        int curr = 0;
        StringBuilder constructorsp = new StringBuilder();
        String interFaceName = cs[0].getName().substring(cs[0].getName().lastIndexOf(".") + 1);
        constructorsp.append("public ").append("" + interFaceName + "Porxy").
                append("(MyInvocationHandler myInvocationHandler) { "
                        + "this.myInvocationHandler = myInvocationHandler;"
                        + "}");
        StringBuilder allMethods = new StringBuilder();
        StringBuilder classsp = new StringBuilder();
        classsp.append("public class").append(" " + interFaceName + "Porxy").append(" extends MyProxy implements ");
        for (Class<?> c : cs) {
            if (!c.isInterface()) {
                throw new Exception("代理的类必须是接口");
            }
            classsp.append(c.getName().substring(c.getName().lastIndexOf(".") + 1)).append(",");
            importsp.append("import " + c.getName() + ";\n");
            Method[] Methods = c.getMethods();
            for (Method m_ : Methods) {
                curr++;
                publicMethods.append("public static Method ").append(m_.getName() + String.valueOf(curr)).append(";\n");
                publicStaticMethods.append("").append(m_.getName() + String.valueOf(curr)).append("=");
                publicStaticMethods.append("Class.forName(\"" + c.getName() + "\")" + ".getMethod(\"" + m_.getName() + "\", ");
                StringBuilder sp = new StringBuilder();
                StringBuilder spArgs = new StringBuilder();
                spArgs.append("Object[] o ={");
                //public
                sp.append(Modifier.toString(m_.getModifiers()).replace("abstract", "")).append(" ");
                //void | java.lang.String
                sp.append(m_.getReturnType().getName()).append(" ");
                //add()|get()
                sp.append(m_.getName().concat("("));
                StringBuilder methodCLass = new StringBuilder();
                if (m_.getParameterTypes().length > 0) {
                    Class<?>[] claszz = m_.getParameterTypes();
                    int methodOffset = 0;
                    methodCLass.append("new Class[] { ");
                    for (Class<?> c_ : claszz) {
                        String paramStr = "obj" + String.valueOf(++methodOffset);
                        spArgs.append(paramStr.concat(","));
                        sp.append(c_.getName().toString().concat(" ").concat(paramStr)).append(",");
                        methodCLass.append("Class.forName(\"" + c_.getName()).append("\"),");
                    }
                    sp = new StringBuilder(sp.substring(0, sp.length() - 1));
                    spArgs = new StringBuilder(spArgs.substring(0, spArgs.length() - 1));
                    methodCLass = new StringBuilder(methodCLass.substring(0, methodCLass.length() - 1));
                }
                if (methodCLass.length() > 0) {
                    methodCLass.append("}");
                } else {
                    methodCLass.append("new Class[0]");
                }
                sp.append("){\n");
                spArgs.append("}");
                sp.append(spArgs + ";\n");
                if (sp.toString().contains("void")) {
                    sp.append("try {\n this.myInvocationHandler.invoke(this,").append(m_.getName() + String.valueOf(curr)).append(",").append("o);\n return;\n");
                    sp.append("} catch (Throwable e) {e.printStackTrace();}}");
                } else {
                    sp.append("try {return "
                            + "("
                            + m_.getReturnType().getName()
                            + ")"
                            + "this.myInvocationHandler.invoke(this,").append(m_.getName() + String.valueOf(curr)).append(",").append("o);\n");
                    sp.append("} catch (Exception e) {e.printStackTrace();} return null;}");
                }
                publicStaticMethods.append(methodCLass).append(");\n");
                allMethods.append(sp);
            }
        }
        classsp = new StringBuilder(classsp.substring(0, classsp.length() - 1)).append("{");
        publicStaticMethods.append("} catch(Exception e){}}");
        classsp.append(publicMethods)
                .append(publicStaticMethods)
                .append(constructorsp).append(allMethods).append("");
        classsp.append("}");
        importsp.append(classsp);
        return importsp.toString();
    }
    public static void main(String[] args) throws Exception {
        System.out.println(mackMultiProxyClass(new Class<?>[]{Idto.class}));
    }
}
