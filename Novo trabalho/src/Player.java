import java.util.ArrayList;
import java.util.Collection;

public class Player extends Components{
    
    //Atributos do Player
    private double velocX;
    private double velocY;
    private double explosionStart;
    private double explosionEnd;
    private long nextShot;
    private long powerTime;
    private PlayerATN action;
    private ArrayList<Projectile> projectiles;
    

    //COnstrutores
    public Player(double velocX, double velocY, double explosionStart, double explosionEnd, long nextShot, long powerTime, PlayerATN action, int state, double coordX, double coordY, double radius, ArrayList<Projectile> projectiles) {
        super(state, coordX, coordY, radius);
        this.velocX = velocX;
        this.velocY = velocY;
        this.explosionStart = explosionStart;
        this.explosionEnd = explosionEnd;
        this.nextShot = nextShot;
        this.powerTime = powerTime;
        this.action = action;
        this.projectiles = projectiles;
    }

    public Player(double velocX, double velocY, double explosionStart, double explosionEnd, long nextShot, long powerTime, PlayerATN action, ArrayList<Projectile> projectiles) {
        this.velocX = velocX;
        this.velocY = velocY;
        this.explosionStart = explosionStart;
        this.explosionEnd = explosionEnd;
        this.nextShot = nextShot;
        this.powerTime = powerTime;
        this.action = action;
        this.projectiles = projectiles;
    }

    //Metodo que desenha o Player
    @Override
    public void draw(long delta) {
        
	action.draw(this, delta);
    }

    //Comportamento do Player
    @Override
    public void behavior(long delta) {

        action.behavior(this, delta);
    }
    
    //Metodo que checa colisoes
    public void checkCollisions(Collection<Components> elements, long currentTime){
            
        action.checkCollisions(this, elements, currentTime);
    }
    
    /*
    //Metodo que trata a conquista do Power-Up 
    public void checkPowerUpCollisions(PowerUp power){
        
        if (power.getState() == ACTIVE && getState() == ACTIVE) {
            double dx = power.getCoordX() - getCoordX();
            double dy = power.getCoordY() - getCoordY();
            double dist = Math.sqrt(dx * dx + dy * dy);
                                        
            if (dist < (getRadius() + power.getRadius()) * 0.8) {
                                           
                if(Math.random() < 0.5) setPowerStatus(new PlayerShield());   
                else setPowerStatus(new PlayerDoubleShot()); 
                
                powerTime = System.currentTimeMillis() + 15000;
                power.setState(INACTIVE);
            }
        }
    }
    */

    //Metodo que adiciona o privilegio de ter projeteis ao Player
    void addProjectile(Projectile p) {
        projectiles.add(p);
    }
    
    //Getters e Setters
    public PlayerATN getPowerStatus() {
        return action;
    }

    public void setPowerStatus(PlayerATN powerStatus) {
        this.action = powerStatus;
    }
    
    public double getVelocX() {
        return velocX;
    }

    public void setVelocX(double velocX) {
        this.velocX = velocX;
    }

    public double getVelocY() {
        return velocY;
    }

    public void setVelocY(double velocY) {
        this.velocY = velocY;
    }

    public double getExplosionStart() {
        return explosionStart;
    }

    public void setExplosionStart(double explosionStart) {
        this.explosionStart = explosionStart;
    }

    public double getExplosionEnd() {
        return explosionEnd;
    }

    public void setExplosionEnd(double explosionEnd) {
        this.explosionEnd = explosionEnd;
    }

    public long getNextShot() {
        return nextShot;
    }

    public void setNextShot(long nextShot) {
        this.nextShot = nextShot;
    }

    public long getPowerTime() {
        return powerTime;
    }

    public void setPowerTime(long powerTime) {
        this.powerTime = powerTime;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
    
    
}
