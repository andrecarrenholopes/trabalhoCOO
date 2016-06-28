import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import java.util.ArrayList;

public class GameFacade {
	
	static ArrayList <Fase> arrayFases;
	static Player player;
    static ArrayList<Enemy> arrayEnemy1;
    private static long nextEnemy1;
    static ArrayList<Enemy> arrayEnemy2;
    private static  long nextEnemy2;
    private static ArrayList<Background> backg1;
    private static ArrayList<Background> backg2;
	
	static int vidas =  1; /*  quantidade de vidas do jogador */
	static int fases = 1;
	
	public static void playGame() {

		/* Indica que o jogo está em execução */
		boolean running = true;

		/* variáveis usadas no controle de tempo efetuado no main loop */
		long delta;
		long currentTime = System.currentTimeMillis();
		
		//inicializa a parte grafica
		GameLib.initGraphics();
		
		//le os arquivos de fases
		try{
			int c = 1;
						
			FileReader entrada = new FileReader("src/arquivoPrincipal.txt");
			BufferedReader lerEntrada = new BufferedReader(entrada);
			
			String linha = lerEntrada.readLine();
			vidas = Integer.parseInt(linha);
			
			while (linha != null) {
				arrayFases.add(initFase(linha,c));
				c++;
				System.out.println(linha);
				linha = lerEntrada.readLine();
			}
			entrada.close();
		}
		catch (IOException e) {
			System.out.println("erro ao ler o arquivo");
		}
		
		
		 //Enquanto o jogo esta rodando
        while(running){
            
            delta = System.currentTimeMillis() - currentTime;
            currentTime = System.currentTimeMillis();
            GameFacade.checkAllCollisions(); 
            GameFacade.update(delta);
            
            GameFacade.throwEnemies();
            GameFacade.throwPowerUp();
            
            //Para a execucao do jogo quando o "esc" for pressionado
            if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;
            
            GameFacade.paint(delta);
            
            GameLib.display();
            busyWait(currentTime+5);
        }
        System.exit(0);
	}
	
	public static Fase initFase(String arq_Fase, int c) {
		Fase novaFase = new Fase(c);
		
		//abre o arquivo de configuracao da fase
		FileReader entrada = new FileReader(arq_Fase);
		BufferedReader lerEntrada = new BufferedReader(entrada);
		String linha = lerEntrada.readLine();

		//le cada linha
		while (linha != null) {
			String [] config = linha.split(" ");
			if(config[0]=="INIMIGO") {
				ArrayList<Projectile> ps = new ArrayList<Projectile>();
				if(config[1]=="1") {
					Enemy e = new Enemy(0, config[2], 0, 0, 0, 0, 0, new Enemy1(), ps, Components.INACTIVE, config[3], config[4], 11);
				}
				else {
					Enemy e = new Enemy(0, config[2], 0, 0, 0, 0, 0, new Enemy2(), ps, Components.INACTIVE, config[3], config[4], 11);
				}
				novaFase.add(e);
			}
			else {
				
			}
			//System.out.println("config: " + config[0]);
			//System.out.println(linha);
			
			//le a proxima linha
			linha = lerEntrada.readLine();
		}
		//fecha o arquivo e retorna o objeto fase
		entrada.close();
		
		return novaFase; 
	}
	
	
	//Metodo que delega a checagem das colisoes para cada elemento 
    public static void checkAllCollisions(){
        long currentTime = System.currentTimeMillis();
        
        for (Components e : getAllEnemies()) {
            Enemy enemy = (Enemy)e;
            enemy.checkCollisions(player.getProjectiles(), currentTime);
        }
        player.checkCollisions(GameFacade.getAllEnemies(), currentTime);
        //player.checkPowerUpCollisions(powerUp);
    }
    
  //Metodo que une todos os inimigos em uma lista genericamente
    public static ArrayList<Components> getAllEnemies(){
        ArrayList<Components> enemies = new ArrayList<Components>();
        for(Fase fase: arrayFases) {
        	enemies.addAll(fase.Enemys);
        }
        return enemies;
    }
	
    //Metodo que atualiza as posicoes dos Components
    public static void update(long delta){
        player.behavior(delta);
        //powerUp.behavior(delta);
        for (Components enemy : getAllEnemies()) {
            enemy.behavior(delta);
        }
    }
    
    //Metodo que lanca inimigos na tela
    public static void throwEnemies(){
        long currentTime = System.currentTimeMillis();    
        
        if (currentTime > nextTIEFighter) {
            for (int i = 0; i < TIEfighters.size() ;i++) {
                Enemy enemy = TIEfighters.get(i);
                
                if (enemy.getState() == Element.INACTIVE) {
                    enemy.setState(Element.ACTIVE);
                    enemy.setCoordX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);                    
                    enemy.setCoordY(-10);
                    enemy.setVeloc(0.20 + Math.random() * 0.15);
                    enemy.setAngle(3 * Math.PI / 2);
                    enemy.setVelocRot(0);
                    enemy.setNextShot(currentTime + 500);
                    enemy.getProjectiles().clear();
                    
                    nextTIEFighter = (currentTime + 600);
                    i = TIEfighters.size();
                }
            }
        }
        
        
        if (currentTime > nextImperialStarDestroyer) {
            for (int i = 0; i < ImperialStarDestroyers.size() ;i++) {
                Enemy enemy = ImperialStarDestroyers.get(i);
                
                if(enemy.getState() == Element.INACTIVE){
                    if(i == 0) DestroyerSpawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
                    
                    enemy.setCoordX(DestroyerSpawnX);
                    enemy.setCoordY(-10);
                    enemy.setVeloc(0.42);
                    enemy.setAngle((3 * Math.PI) / 2);
                    enemy.setVelocRot(00);
                    enemy.setState(Element.ACTIVE);
                    
                    if (i<ImperialStarDestroyers.size()-1) nextImperialStarDestroyer = (currentTime + 120);
                    else nextImperialStarDestroyer = (long) (currentTime + 3000 + Math.random() * 3000);
                    
                    i = ImperialStarDestroyers.size();
               }
            
            }
        }
        
        if(currentTime > nextRock){
            for (int i = 0; i < rocks.size() ;i++) {
                Enemy enemy = rocks.get(i);
                
                if (enemy.getState() == Element.INACTIVE) {
                    enemy.setState(Element.ACTIVE);
                    enemy.setCoordX(Math.random() * (GameLib.WIDTH - 20.0) + 10.0);                    
                    enemy.setCoordY(-10);
                    enemy.setVeloc(0.04 + Math.random() * 0.3);
                    enemy.setAngle(3 * Math.PI / 2);
                    enemy.setVelocRot(0);
                    enemy.setRadius(15 + Math.random() * 15);
                    
                    nextRock = (currentTime + 5000);
                    i = rocks.size();
                }
            }
        }
    }
}
