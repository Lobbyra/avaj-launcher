package flyable;

import weather.Tower;
import weather.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        Tower tower = weatherTower;
        tower.register(this);
    }

    abstract public void updateConditions();
}
