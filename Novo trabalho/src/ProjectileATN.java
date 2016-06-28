//Interface (Strategy) das acoes dos projeteis 

public interface ProjectileATN {
    
    void draw(Projectile p, long delta);
    
    Projectile behavior(Projectile p, long delta);
}
