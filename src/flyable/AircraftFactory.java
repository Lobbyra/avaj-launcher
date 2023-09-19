package flyable;

import java.util.Random;

/**
 * AircraftFactory
 */
public class AircraftFactory {
    /* STUFF TO ASSURE THIS CLASS AS A SINGLETON */
    private static AircraftFactory instance = null;

    private AircraftFactory() {
    }

    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }
        return instance;
    }

    public Flyable newAircraft(
        String p_type,
        String p_name,
        Coordinates p_coordinates
    ) throws Exception {
        String[] aircraftsTypes = {"JetPlane", "Helicopter", "Baloon"};
        String selectedType = null;
        Random rand = new Random();

        for (String type : aircraftsTypes) {
            if (type.equals(p_type)) {
                selectedType = type;
            }
        }
        if (selectedType == null) {
            throw new Exception("Type not found !");
        }
        switch (selectedType) {
            case "JetPlane":
                return (
                    new JetPlane(
                        Math.abs(rand.nextInt()),
                        p_name,
                        p_coordinates
                    )
                );
            case "Helicopter":
                return (
                    new Helicopter(
                        Math.abs(rand.nextInt()),
                        p_name,
                        p_coordinates
                    )
                );
            case "Baloon":
                return (
                    new Baloon(
                        Math.abs(rand.nextInt()),
                        p_name,
                        p_coordinates
                    )
                );
            default:
                break;
        }
        return (new Aircraft(rand.nextInt(), p_name, p_coordinates));
    }
}
