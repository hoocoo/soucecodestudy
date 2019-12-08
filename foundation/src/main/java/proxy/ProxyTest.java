package proxy;

public class ProxyTest {

    public static void main(String[] args) throws Exception {

        Idto d = (Idto) PorxyFactory.newProxyInstancewWithMultiClass(DtoImpl.class.getInterfaces(), new Handler(new DtoImpl()));
        d.add();
    }
}