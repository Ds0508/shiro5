package kuang.factory;

public class DbbFactory extends ParentFactory {
    @Override
    protected Car createCar() {
        return new Dbb();
    }
}
