
import java.util.Collection;

//Interface (Strategy) da acao do Player

public interface PlayerATN {
    public void draw(Player p, long delta);
    public void behavior(Player p, long delta);
    public void checkCollisions(Player p, Collection<Components> elements, long currentTime);
}