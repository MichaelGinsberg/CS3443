import java.io.*;
import java.util.*;
/**
 * Zoo is a java class that will take in a zoo name along with csv files that will be used to 
 * create the zones and animals within the zones. The class will also be able to relocate and save
 * the results back to the files
 * 
 * @author Michael Ginsberg (sfi208)
 * UTSA CS 3443 - Lab 2
 * Spring 2021
 */
public class Zoo 
{
	private String zooName;
    private ArrayList<Zone> zones;
    private String animalsCsvFilePath;
    private String zonesCsvFilePath;

    // Constructor
    // @ param name of the zoo
    public Zoo(String zooName) 
    {
    	this.zooName=zooName;
        zones = new ArrayList<>();
    }
    
    // Method to load zones from csv file
    public void loadZones(String csvFile) throws IOException
    {
        this.zonesCsvFilePath = csvFile;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int lineNo = 1;
        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) 
        {
            String[] zonesStr = line.split(cvsSplitBy);
            if(zonesStr.length < 3)
            {
                br.close();
                throw new IOException("Error while reading csv "+csvFile+". Insufficient data at line "+lineNo+"\n");
            }
            Zone.SafetyRating rating = Zone.SafetyRating.valueOf(zonesStr[1]);
            Zone zone = new Zone(zonesStr[0], zonesStr[2], rating);
            addZone(zone);
            lineNo++;
        }
        br.close();
    }
    // Method to load animals from csv file
    // @ param csvFile - takes in the file path
    public void loadAnimals(String csvFile) throws IOException
    {
        this.animalsCsvFilePath = csvFile;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int lineNo = 1;
        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) 
        {
            String[] animalStr = line.split(cvsSplitBy);
            if(animalStr.length < 4)
            {
                br.close();
                throw new IOException("Error while reading csv "+csvFile+". Insufficient data at line "+lineNo+"\n");
            }
            Zone zone = getZoneFromZoneCode(animalStr[3]);
            if(zone == null)
            {
                br.close();
                throw new IOException("Invalid zone code at line "+lineNo+".");
            }
            boolean diet = Boolean.parseBoolean(animalStr[2]);
            Animal animal = new Animal(animalStr[0], animalStr[1], diet, animalStr[3]);
            zone.addAnimal(animal);
            lineNo++;
        }
        br.close();
    }

    // Method to relocate an animal into another zone
    // @ param animalName - name of animal that must be relocated
    // @ param newZoneCode - zone code of the zone the animal will be moved to
    public void relocate(String animalName, String newZoneCode)
    {
        Animal targetAnimal = null;
        Zone newZone = null;
        Zone oldZone = null;
        boolean flagNewZoneFound = false;
        boolean flagAnimalFound = false;
        
        zone_loop:

        for(Zone zone : zones)
        {
            if(zone.getZoneCode().equals(newZoneCode))
            {
                newZone = zone;
                flagNewZoneFound = true;
            }
            for(Animal cur: zone.getAnimals())
            {
                if(flagAnimalFound && flagNewZoneFound)
                {
                    break zone_loop;
                }
                if(cur.getAnimalName().equals(animalName))
                {
                    targetAnimal = cur;
                    flagAnimalFound = true;
                }
            }
        }
        
        if(targetAnimal == null || newZone == null)
        {
            return;
        }
        for(Zone zone: zones)
        {
            if(zone.getZoneCode().equals(targetAnimal.getZoneCode()))
            {
                oldZone = zone;
                break;
            }
        }
        oldZone.removeAnimal(targetAnimal);
        newZone.addAnimal(targetAnimal);
        targetAnimal.setZoneCode(newZoneCode);
    }

    // Method to save all information back into the file
    public void save() throws IOException
    {
        PrintWriter zoneWriter = new PrintWriter(new File(zonesCsvFilePath));
        PrintWriter animalsWriter = new PrintWriter(new File(animalsCsvFilePath));  
        for(Zone zone : getZones())
        {
            zoneWriter.println(
                String.format("%s,%s,%s",zone.getZoneName(), zone.getSafety().toString(), zone.getZoneCode()));
            for(Animal animal: zone.getAnimals())            		
            {
                String diet = (animal.diet())?"TRUE":"FALSE";
                animalsWriter.println(
                String.format("%s,%s,%s,%s",animal.getAnimalName(),animal.getAnimalType(),diet,animal.getZoneCode()));
            }
        }
        zoneWriter.flush();
        zoneWriter.close();
        animalsWriter.flush();
        animalsWriter.close();
    }
    
    // Method to get the zone code
    // @ return the current zone
    private Zone getZoneFromZoneCode(String zoneCode)
    {
        for(Zone curzone: getZones())
        {
            if(curzone.getZoneCode().equals(zoneCode))
                return curzone;
        }
        return null;
    }
    // Method to add zones to the array list
    // @ param x - name of new zone
    public void addZone(Zone x) 
    {
        zones.add(x);
    }

    // getters and setters

    // @ return zooName - name of zoo
    public String getZooName() 
    {
        return zooName;
    }

    // @ return number of zones
    public int getZoneCount() 
    {
        return zones.size();
    }

    // @ return zones
    public ArrayList<Zone> getZones()
    {
        return zones;
    }
    
    // @ param zooName - name of zoo
    public void setZooName(String zooName) 
    {
        this.zooName=zooName;
    }

    // @ param arraylist of zones
    public void setZones(ArrayList<Zone> zones) 
    {
        this.zones=zones;
    }

    // Method toString sets the printing format of zoo and calls for zone
    public String toString() 
    {
        String data = "Welcome to " +zooName+"!\n" + "----------------------------------\n";
        int zoneCount = getZoneCount();
        for (int i=0; i<zoneCount;i++) 
        {
            data+=zones.get(i) + "\n";
        }
        return data;
    }
}