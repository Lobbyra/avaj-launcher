package flyable;

import weather.WeatherProvider;

public class Helicopter extends Aircraft {

    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        String msg = null;
        Boolean isLanding = false;
        String currWeather = WeatherProvider.getInstance().getCurrentWeather(
            this.coordinates
        );
        switch (currWeather) {
            case "SUN":
                msg = "😎 Zap Zap Zap";
                this.coordinates.longitude += 10;
                this.coordinates.height += 2;
                if (this.coordinates.height > 100) {
                    this.coordinates.height = 100;
                }
                break;
            case "RAIN":
                msg = "💧 Flop Flop Flop";
                this.coordinates.longitude += 5;
                break;
            case "FOG":
                msg = "🌫 Schwoup Schwoup Schwoup";
                this.coordinates.longitude += 1;
                break;
            case "SNOW":
                msg = "🥶 Clack Clack Clack";
                this.coordinates.height -= 12;
                if (this.coordinates.height <= 0) {
                    // LANDING
                    isLanding = true;
                    coordinates.height = 0;
                }
                break;
            default:
                System.out.println(
                    String.format(
                        "Error: [%s] weather invalid",
                        currWeather
                    )
                );
                break;
        }
        System.out.println(
            String.format(
                "🚁 %s#%s(%d)(x:%d, y:%d, x:%d): %s",
                this.getClass().getSimpleName(),
                this.name,
                this.id,
                coordinates.longitude,
                coordinates.latitude,
                coordinates.height,
                msg
            )
        );
        if (isLanding == true) {
            msg = "ploc. landing.";
            System.out.println(
                String.format(
                    "🚁 %s#%s(%d)(x:%d, y:%d, x:%d): %s",
                    this.getClass().getSimpleName(),
                    this.name,
                    this.id,
                    coordinates.longitude,
                    coordinates.latitude,
                    coordinates.height,
                    msg
                )
            );
        }
        if (isLanding == true) {
            weatherTower.unregister(this);
        }
    }
}
