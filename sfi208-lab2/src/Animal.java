/**
 * Animal is a java class that will take in an animal and set all its details to be used later
 * 
 * @author Michael Ginsberg (sfi208)
 * UTSA CS 3443 - Lab 2
 * Spring 2021
 */
public class Animal 
{
    private String name;
    private String type;
    private boolean diet;
    private String zoneCode;

    // Constructor
    // @ param name - name of animal
    // @ param type - type of animal
    // @ param diet - diet of the animal
    // @ param zoneCode - zone the animal is in
    public Animal(String name, String type, boolean diet, String zoneCode) 
    {
        this.name = name;
        this.type = type;
        this.diet = diet;
        this.zoneCode = zoneCode;
    }

    // Method to set up an animal
    public Animal() 
    {
        name = "";
        type="";
        diet = false;
    }

    
    // getters and setters
    
    // @ return animal name
    public String getAnimalName() 
    {
        return this.name;
    }

    // @ param name of animal
    public void setAnimalName(String name) 
    {
        this.name = name;
    }

    // @ return type of animal
    public String getAnimalType() 
    {
        return this.type;
    }

    // @ param type of animal
    public void setAnimalType(String type)
    {
        this.type = type;
    }

    // @ return diet of animal
    public boolean diet() 
    {
        return diet;
    }

    // @ param diet of animal
    public void setDiet(boolean diet) 
    {
        this.diet = diet;
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
    
    // Method to format the printing of the animal information
    public String toString() 
    {
        return ">>" + name + " - " + type + " " + (!diet ? "(Vegetarian)" : "(Carnivore)");
    }
}