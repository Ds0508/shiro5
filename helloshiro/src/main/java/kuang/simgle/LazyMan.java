package kuang.simgle;

//懒汉式
public class LazyMan {
    private LazyMan(){
        System.out.println(Thread.currentThread().getName()+"ok");
    }
    private volatile static LazyMan lazyMan;

    public static LazyMan getInstance(){
        if (lazyMan == null) {
            synchronized (LazyMan.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan();  //不是原子性操作
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                LazyMan.getInstance();
            }).start();
            System.out.println();
        }
    }
}

