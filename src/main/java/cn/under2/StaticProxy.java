package cn.under2;

public class StaticProxy {
    public static void main(String[] args) {
        WeddingCompany company = new WeddingCompany(new You());
        company.HappyMarry();
    }
}

interface Marry{
    void HappyMarry();
}

class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("你要结婚了。。");
    }
}
class WeddingCompany implements Marry{

    private Marry target;

    public  WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();;
        after();

    }

    private void after() {
        System.out.println("结婚之后，吃饭");
    }

    private void before() {
        System.out.println("结婚前，布置现场");

    }
}
