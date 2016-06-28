import java.awt.Color;

//Classe Background
class Background{
  double coordX;
  double coordY;
  double cont;
  double speed;
  double size;

  public Background(double coordX, double coordY, double cont, double speed, double size) {
      this.coordX = coordX;
      this.coordY = coordY;
      this.cont = cont;
      this.speed = speed;
      this.size = size;
  }
  
  public void draw(long delta){
	cont += speed * delta;
      
      if(size >= 3)GameLib.setColor(Color.GRAY);
      else GameLib.setColor(Color.DARK_GRAY);
      
      GameLib.fillRect(coordX, (coordY + cont) % GameLib.HEIGHT, size, size);
  }
}