package flyable;

import weather.WeatherProvider;

public class JetPlane extends Aircraft {

    public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
        //TODO Auto-generated constructor stub
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
                msg = "😎 My engines getting hotter, will we fall?";
                this.coordinates.latitude += 10;
                this.coordinates.height += 2;
                if (this.coordinates.height > 100) {
                    this.coordinates.height = 100;
                }
                break;
            case "RAIN":
                msg = "💧 There is more weight on my wing, will we fall?";
                this.coordinates.latitude += 5;
                break;
            case "FOG":
                msg = "🌫 I can't see the ground, will we fall?";
                this.coordinates.latitude += 1;
                break;
            case "SNOW":
                msg = "🥶 My fuel is cold, will we fall?";
                this.coordinates.height -= 7;
                if (this.coordinates.height <= 0) {
                    // LANDING
                    isLanding = true;
                    this.coordinates.height = 0;
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
                "🛫 %s#%s(%d)(x:%d, y:%d, x:%d): %s",
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
            System.out.println(
                String.format(
                    "🛫 %s#%s(%d)(x:%d, y:%d, x:%d): %s",
                    this.getClass().getSimpleName(),
                    this.name,
                    this.id,
                    coordinates.longitude,
                    coordinates.latitude,
                    coordinates.height,
                    msg
                )
            );
            msg = "ok, we didn't fall. landing.";
        }
        if (isLanding == true) {
            weatherTower.unregister(this);
        }
    }
}
