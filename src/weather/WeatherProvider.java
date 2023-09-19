package weather;
import java.util.Random;

import flyable.Coordinates;

public class WeatherProvider {
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    /* STUFF TO ASSURE THIS CLASS AS A SINGLETON */
    private static WeatherProvider instance = null;

    private WeatherProvider() {
    }

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        Random rand = new Random();

        return this.weather[Math.abs(rand.nextInt()) % 4];
    }
}
