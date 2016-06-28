
public class Projectile extends Components {
	//Atributos comum dos projeteis
    private double velocX;
    private double velocY;
    
    private ProjectileATN action;
    
    //Construtores
    public Projectile(){
        this(0, 0, null, 0, 0, 0, 0);
    }
    
    public Projectile(double velocX, double velocY, ProjectileATN action, int state,double coordX, double coordY, double radius) {
        super(state, coordX, coordY, radius);
        this.velocX = velocX;
        this.velocY = velocY;
        this.action = action;
    }

    public Projectile(double velocX, double velocY, ProjectileATN action) {
        this.velocX = velocX;
        this.velocY = velocY;
        this.action = action;
    }

    
    //Comportamento dos Projeteis
    @Override
    public void behavior(long delta) {
        action.behavior(this,delta);
    }

    //Metodo que desenha os Projeteis
    @Override
    public void draw(long delta) {
        action.draw(this, delta);
    }

    //Getters e Setters
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

    public ProjectileATN getAction() {
        return action;
    }

    public void setAction(ProjectileATN action) {
        this.action = action;
    }
    
}
