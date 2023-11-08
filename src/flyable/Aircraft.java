package flyable;

import weather.WeatherTower;

public class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        id = p_id;
        name = p_name;
        coordinates = p_coordinates;
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        super.registerTower(p_tower);
        System.out.println(
            String.format(
                "🗼 Tower says: %s#%s(%d)(x:%d, y:%d, z:%d) registered to weather tower.",
                this.getClass().getSimpleName(),
                name,
                id,
                coordinates.longitude,
                coordinates.latitude,
                coordinates.height
            )
        );
    }

    @Override
    public void updateConditions() {
        // EMPTY FUNCTION THAT WILL BE OVERWRITTEN BY CHILD CLASSES
    }
}
