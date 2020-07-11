package kuang.factory;

//简单工厂
public class simpleFactory {
    /**
     *
     * @param name 产品名称
     * @return
     */
    public Car createCar(String name){
        if (name == null || name.equals("")) {
            return null;
        }
        if (name.equals("liangzai")) {
            return new BMW();
        }else if (name.equals("Dbb")){
            return new Dbb();
        }
        return null;
    }
}
