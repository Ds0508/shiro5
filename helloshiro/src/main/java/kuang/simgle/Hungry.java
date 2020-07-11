package kuang.simgle;

import java.util.HashMap;

//饿汉式
public class Hungry {

    //一开始就加载，可能会造成浪费空间
    private byte[] data1 = new byte[1024];
    private byte[] data2 = new byte[1024];
    private byte[] data3 = new byte[1024];
    private byte[] data4 = new byte[1024];

    private Hungry(){  //构造器私有

    }
    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
