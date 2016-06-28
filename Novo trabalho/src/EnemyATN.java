//Interface (Strategy) das ações dos inimigos 


public interface EnemyATN {
    
    void draw(Enemy e, long delta);    
    Enemy behavior(Enemy e, long delta);
    
}
