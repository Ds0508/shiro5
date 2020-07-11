package kuang.simgle;

//静态内部类
public class Holder {
    private Holder(){

    }
    public static Holder getInstance(){  //调用静态内部类
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}
