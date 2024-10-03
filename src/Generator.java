import java.util.ArrayList;

/**
 * The Generator class represents a generic resource generating item in the game.
 * Generators have a name, a construction cost, and a resource production rate.
 */
public abstract class Generator implements Comparable, Score {
    private String name;
    private int resourceProductionRate;
    private int numberConstructed;

    /**
     * Creates a new Generator with the given name, construction cost, and resource production rate.
     *
     * @param name                  the name of the Generator
     * @param resourceProductionRate the rate at which the Generator produces resources per unit of time
     * @param numberConstructed     the number of units of this generator constructed at this time
     */
    public Generator(String name, int resourceProductionRate, int numberConstructed) {
        this.name = name;
        this.resourceProductionRate = resourceProductionRate;
        this.numberConstructed = numberConstructed;
    }

    /**
     * Gets the name of the Generator.
     *
     * @return the name of the Generator
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the resource production rate of the Generator.
     *
     * @return the resource production rate of the Generator
     */
    public int getResourceProductionRate() {
        return resourceProductionRate;
    }

    /**
     * Gets the number of units constructed of this Generator.
     *
     * @return the number of units constructed of the generator
     */
    public int getNumberConstructed() {
        return numberConstructed;
    }

    public void addNumberConstructed(int add){numberConstructed = numberConstructed + add;}

    //Collect the appropriate amount of resources, based off how many generators you have
    public int collectResources(){
        if(this.getNumberConstructed() > 0){
            return (this.resourceProductionRate * this.numberConstructed);
        }
        else{
            return 0;
        }
    }

    @Override
    public int compareTo(Object other){
        if(this.getNumberConstructed() == ((Generator)other).getNumberConstructed()){
            return 0;
        }
        else if(this.getNumberConstructed() > ((Generator)other).getNumberConstructed()){
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public String toString(){
        return (name+ ": " + numberConstructed);
    }

    @Override
    public int scoreImpact(){
        return 500;
    }

}