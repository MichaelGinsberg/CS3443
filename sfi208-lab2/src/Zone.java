import java.util.*;
/**
 * Zone is a java class that will take in the zones name, code and rating and format
 * them to be printed when called. It also sets up the animals in each zonw.
 * 
 * @author Michael Ginsberg (sfi208)
 * UTSA CS 3443 - Lab 2
 * Spring 2021
 */
public class Zone
{
    private String zoneName;
    private String zoneCode;   
    private ArrayList<Animal> animals;
    private SafetyRating safety;
    
    // Constructor
    // @param zoneName - name of zone
    // @param zoneCode - code for the zone
    // @param safety - safety rating for the zone
    public Zone(String zoneName, String zoneCode, SafetyRating safety) 
    {
        this.zoneName = zoneName;
        this.zoneCode = zoneCode;
        this.safety = safety;
        animals = new ArrayList<>();
    }

    // Method to add animal to zone
    // @param animal in animal
    public void addAnimal (Animal animal) 
    {
        animals.add(animal);
        animal.setZoneCode(this.zoneCode);
    }

    // Method to remove animal from animals
    // @param animal- animal that must be removed
    public void removeAnimal(Animal animal)
    {
        animals.remove(animal);
    }

    // getters and setters
    
    // @ return name of zone
    public String getZoneName() 
    {
        return this.zoneName;
    }
    
    // @ param name of zone
    public void setZoneName(String zoneName) 
    {
        this.zoneName = zoneName;
    }

    // @ return zone code
    public String getZoneCode() 
    {
        return this.zoneCode;
    }

    // @ param zone code
    public void setZoneCode(String zoneCode) 
    {
        this.zoneCode = zoneCode;
    }

    // @ return animal
    public ArrayList<Animal> getAnimals() 
    {
        return this.animals;
    }

    // @ para animal
    public void setAnimals(ArrayList<Animal> animals) 
    {
        this.animals = animals;
    }

    // @ return safety rating
    public SafetyRating getSafety() 
    {
        return this.safety;
    }

    // @ param safety rating
    public void setSafety(SafetyRating safety) 
    {
        this.safety = safety;
    }

    // Method to keep track of the number of animals per zone
    public int getAnimalCount()
    {
        return animals.size();
    }

    // Method to format the printing of the zones and call the animal class to print within the zone
    public String toString() 
    {
        String header = String.format("%s: %s: (%s):\n", zoneCode, zoneName, safety.toString());
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        sb.append("--------------------------------------\n");
        int count = getAnimalCount();
        for (int i = 0; i<count; i++) 
        {
            sb.append(animals.get(i) + "\n");
        }
        return sb.toString();
    }

    // Method that sets the possible enum values for safety rating
    public static enum SafetyRating
    {
        low, medium, high, critical;
    }

}
