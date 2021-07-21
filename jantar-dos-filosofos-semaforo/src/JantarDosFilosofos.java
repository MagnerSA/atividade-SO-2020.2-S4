import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JantarDosFilosofos {

    static int[] estado = new int[5];
    static Semaphore mutex = new Semaphore(1);
	
    static Semaphore[] semaforos = new Semaphore[5]; //semaforo para cada filosofo

    static Filosofo[] filosofos = new Filosofo[5]; //array de Filosofos

    public static void main(String[] args) {

        // Todos filosofos iniciam pensando

        for (int i = 0; i < estado.length; i++) {
            estado[i] = 0;
        }

        // Inicializando filosofos

        filosofos[0] = new Filosofo("Filósofo 1", 0);
        filosofos[1] = new Filosofo("Filósofo 2", 1);
        filosofos[2] = new Filosofo("Filósofo 3", 2);
        filosofos[3] = new Filosofo("Filósofo 4", 3);
        filosofos[4] = new Filosofo("Filósofo 5", 4);
        
        // Que garfos pertencem a que filosofos

        for (int i = 0; i < filosofos.length; i++) {            
            System.out.println("Garfo " + i + " - Filosofo " + i + " - Garfo " + (i + 1) % 5);            
        }
        
        
        
        // Inicia o semaforo de cada filosofo em 0

        for (int i = 0; i < semaforos.length; i++) {
            semaforos[i] = new Semaphore(0);
        }

        // Inicia tentativa de cada filosofo  

        for (int i = 0; i < filosofos.length; i++) {
            filosofos[i].start();
        }

        try {
            Thread.sleep(10000);
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(JantarDosFilosofos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
