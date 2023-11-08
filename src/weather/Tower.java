package weather;
import java.util.ArrayList;
import java.util.List;

import flyable.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        Boolean alreadyIn = false;

        for (Flyable flyable : observers) {
            if (flyable == p_flyable) {
                alreadyIn = true;
            }
        }
        if (alreadyIn == false) {
            observers.add(p_flyable);
        }
    }

    public void unregister(Flyable p_flyable) {
        List<Flyable> newObs = new ArrayList<>();

        for (Flyable flyable : observers) {
            if (flyable == p_flyable) {
                continue ;
            }
            newObs.add(flyable);
        }
        observers = newObs;
    }

    protected void conditionChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }

    public Boolean isFlyableOnAir() {
        return (observers.size() > 0);
    }
}
