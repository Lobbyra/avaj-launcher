package flyable;

import weather.WeatherProvider;

public class Baloon extends Aircraft {

    public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
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
                msg = "😎 The air is getting hotter ! It's very nice for me.";
                this.coordinates.longitude += 2;
                this.coordinates.height += 4;
                if (this.coordinates.height > 100) {
                    this.coordinates.height = 100;
                }
                break;
            case "RAIN":
                msg = "💧 It's raining ! Prepare your K-way ";
                this.coordinates.height -= 5;
                if (this.coordinates.height <= 0) {
                    isLanding = true;
                    this.coordinates.height = 0;
                }
                break;
            case "FOG":
                msg = "🌫  Where I am? Who I am? I'm lost...";
                this.coordinates.height -= 3;
                if (this.coordinates.height <= 0) {
                    isLanding = true;
                    this.coordinates.height = 0;
                }
                break;
            case "SNOW":
                msg = "🥶 It's freezing, not stonks :(";
                this.coordinates.height -= 15;
                if (this.coordinates.height <= 0) {
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
                "🎈 %s#%s(%d)(x:%d, y:%d, x:%d): %s",
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
            msg = "yey, we landing.";
            System.out.println(
                String.format(
                    "🎈 %s#%s(%d)(x:%d, y:%d, x:%d): %s",
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
