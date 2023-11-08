import java.io.BufferedReader;
import java.io.FileReader;

import flyable.Aircraft;
import flyable.AircraftFactory;
import flyable.Coordinates;
import weather.WeatherTower;

public class App {
    public static void main(String[] args) throws Exception {
        // ARG ERR MANAGMENT
        if (args.length != 1) {
            System.out.println("ERROR: Allowing only one argument and must be the path of the scenario file");
            return ;
        }
        // OPEN THE SCENARIO FILE
        BufferedReader scenarioReader = null;
        try {
            scenarioReader = new BufferedReader(new FileReader(args[0]));
        } catch (Exception e) {
            System.err.println(
                String.format(
                    "ERROR: Wasn't able to open the file : [%s]",
                    e.getMessage()
                )
            );
            return ;
        }
        String line = null;
        WeatherTower tower = new WeatherTower();
        Integer roundNumber = null;
        // READ, PARSE AND CREATE AIRCRAFT ON THE FLY
        try {
            line = scenarioReader.readLine();
            roundNumber = Integer.parseInt(line);
            line = scenarioReader.readLine();
            if (line == null) {
                scenarioReader.close();
                throw new Exception("No plane found");
            }
            String[] splittedPlaneString = line.split(" ");
            while (line != null) {
                splittedPlaneString = line.split(" ");
                if (splittedPlaneString.length != 5) {
                    scenarioReader.close();
                    throw new Exception(
                        String.format(
                            "Invalid plane : [%s]",
                            line
                        )
                    );
                }
                Aircraft newAircraft = (
                    (Aircraft) AircraftFactory.getInstance().newAircraft(
                        splittedPlaneString[0],
                        splittedPlaneString[1],
                        new Coordinates(
                            Integer.valueOf(splittedPlaneString[2]),
                            Integer.valueOf(splittedPlaneString[3]),
                            Integer.valueOf(splittedPlaneString[4])
                        )
                    )
                );
                tower.register(newAircraft);
                newAircraft.registerTower(tower);
                line = scenarioReader.readLine();
            }
        } catch (Exception e) {
            System.err.println(
                String.format(
                    "ERROR: Fail while reading the file : [%s]",
                    e.getMessage()
                )
            );
            scenarioReader.close();
            return ;
        }
        // START THE SIMULATION
        Integer maxRound = roundNumber;
        while (roundNumber > 0 && tower.isFlyableOnAir()) {
            System.out.println(
                String.format(
                    "\n-------🌈 The weather is changing (%d / %d) 🌈-------",
                    (maxRound - roundNumber) + 1,
                    maxRound
                )
            );
            tower.changeWeather();
            // Thread.sleep(1000);
            roundNumber -= 1;
        }
        if (tower.isFlyableOnAir() == false) {
            System.out.println("All flyables landed! The weather is really bad.");
        }
        scenarioReader.close();
    }
}
