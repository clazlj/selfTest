package demo;

import java.math.BigDecimal;
import java.util.Observable;
import java.util.Observer;

public class ObserveDemo {

    public static void main(String[] args) {
        HousePrice housePrice = new HousePrice();

        HouseBuyer houseBuyer1 = new HouseBuyer("houseBuyer1");
        HouseBuyer houseBuyer2 = new HouseBuyer("houseBuyer2");
        HouseBuyer houseBuyer3 = new HouseBuyer("houseBuyer3");
        housePrice.addObserver(houseBuyer1);
        housePrice.addObserver(houseBuyer2);
        housePrice.addObserver(houseBuyer3);

        housePrice.setPrice(BigDecimal.valueOf(1000.25));
    }


    private static class HousePrice extends Observable {
        private BigDecimal price;

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
            setChanged();
            notifyObservers(price);
        }
    }

    private static class HouseBuyer implements Observer {
        private String name;

        public HouseBuyer(String name) {
            this.name = name;
        }

        @Override
        public void update(Observable o, Object arg) {

            System.out.println(name + "收到被观察者的变更消息");
        }
    }

}
