import java.util.Random;

public class BeastAttack extends Event {
    public BeastAttack(){
        super("Beast Attack");
    }

    public int attackSeverity(int multiple){
        Random random = new Random();
        return multiple * (random.nextInt(11));
    }
}