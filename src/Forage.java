import java.util.Random;

public class Forage extends Event{
    private int times = 0;

    public Forage(){
        super("Forage");
    }

    public int addWood() {
        Random random = new Random();
        int chance = random.nextInt(20);
        return chance;
    }

    public int addRedFruit(){
        Random random = new Random();
        int chance = random.nextInt(7);
        return chance;
    }

    public int getTimes(){
        return times;
    }

    public void addTimes(){
        times++;
    }

    public void resetTimes(){
        times = 0;
    }


}
