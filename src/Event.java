/**
 * The Event class represents a generic randomly occuring event in the game.
 * Events have a name
 */
public abstract class Event implements Score{
    private String name;

    /**
     * Creates a new Event with the given name
     *
     * @param name the name of the event
     */
    public Event(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the event.
     *
     * @return the name of the event
     */
    public String getName() {
        return name;
    }

    @Override
    public int scoreImpact(){
        //You get 50 points for surviving every event
        return 50;
    }

}