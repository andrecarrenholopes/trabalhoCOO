
public class Components {
	
	//Constantes que representam os estados possiveis dos componentes
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    public static final int EXPLODING = 2;
    
    //Atributos comuns dos Componentes do jogo
    private int state;
    private double coordX;
    private double coordY;
    private double radius;

    //Construtor
    public Components(int state, double coordX, double coordY, double radius) {
        this.state = state;
        this.coordX = coordX;
        this.coordY = coordY;
        this.radius = radius;
    }
    
    
    //Getters e Setters
    public Components(){
        this(0,0,0,0);
    }
    
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    
}
