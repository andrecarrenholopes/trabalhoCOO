import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//super classe dos inimigos
public class Enemy extends Components {

     //Atributos comuns aos inimigos
    private double veloc;
    private int quando;
    private double angle;
    private double velocRot;
    private double explosionStart;
    private double explosionEnd;
    private long nextShot;
    private static long nextEnemy;
    private EnemyATN action;
    private ArrayList<Projectile> projectiles;
    
    
    //Construtores
    public Enemy(int state, int coordX, int coordY, int radius) {
        super(state, coordX, coordY, radius);
    }
    
    public Enemy(double veloc, int quando, double angle, double velocRot, double explosionStart, double explosionEnd, long nextShot, EnemyATN action, ArrayList<Projectile> projectiles,int state, double coordX, double coordY, double radius) {
        super(state, coordX, coordY, radius);
        this.veloc = veloc;
        this.quando = quando;
        this.angle = angle;
        this.velocRot = velocRot;
        this.explosionStart = explosionStart;
        this.explosionEnd = explosionEnd;
        this.nextShot = nextShot;
        this.action = action;
        this.projectiles = projectiles;
    }
    
    public Enemy(){
        this(0, 0, 0, 0, 0, 0, 0, null,null, 0, 0, 0, 0);
    }
    
    //Comportamento de acordo com a acao definida pelo EnemyATN e de seus Projeteis
    @Override
    public void behavior(long delta) {
        action.behavior(this, delta);
        
        for (Projectile projectile : projectiles) {
            projectile.behavior(delta);
        }
    }
    
    //Metodo que desenha o Enemy e seus projeteis
    @Override
    public void draw(long delta) {
        
        action.draw(this, delta); 
        
        for (Projectile projectile : projectiles) {
            projectile.draw(delta);
        }
    }
    
    //Metodo que checa a colisao dos Projeteis do Player com os inimigos
    public void checkCollisions(Collection<Projectile> elements, long currentTime){
        
     
        for (Iterator<Projectile> iterator = elements.iterator(); iterator.hasNext();) {
            Components next = iterator.next();
            
            if (getState() == ACTIVE && next.getState() == ACTIVE) {
                
                double dx = getCoordX() - next.getCoordX();
                double dy = getCoordY() - next.getCoordY();
                double dist = Math.sqrt(dx * dx + dy * dy);
                
                if (dist < getRadius()) {
                    
                    setState(EXPLODING);

                    explosionStart = currentTime;
                    explosionEnd = currentTime + 500;
                }
            }
        }
        
    }
    
    //Metodo que adiciona o privilegio de ter projeteis ao inimigo   
    void addProjectile(Projectile p) {
        projectiles.add(p);
    }
    
    //Getters e Setters do inimigo
    public EnemyATN getAction() {
        return action;
    }

    public void setAction(EnemyATN action) {
        this.action = action;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    
    public double getVeloc() {
        return veloc;
    }

    public void setVeloc(double veloc) {
        this.veloc = veloc;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getVelocRot() {
        return velocRot;
    }

    public void setVelocRot(double velocRot) {
        this.velocRot = velocRot;
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

    public long getNextEnemy() {
        return nextEnemy;
    }

    public void setNextEnemy(long nextEnemy) {
        this.nextEnemy = nextEnemy;
    }   
}
