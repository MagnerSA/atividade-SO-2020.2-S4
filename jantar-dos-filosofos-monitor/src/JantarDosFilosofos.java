import java.util.logging.Level;
import java.util.logging.Logger;

public class JantarDosFilosofos {

    static Filosofo filosofos[] = new Filosofo[5];

    public static void main(String[] args) {

        Mesa mesa = new Mesa(filosofos);

        // Inicializando filósofos

        filosofos[0] = new Filosofo("Filósofo 1", mesa, 0);
        filosofos[1] = new Filosofo("Filósofo 2", mesa, 1);
        filosofos[2] = new Filosofo("Filósofo 3", mesa, 2);
        filosofos[3] = new Filosofo("Filósofo 4", mesa, 3);
        filosofos[4] = new Filosofo("Filósofo 5", mesa, 4);

        for (int filosofo = 0; filosofo < 5; filosofo++) {
            filosofos[filosofo].start();
        }

        try {
            Thread.sleep(10000);
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(JantarDosFilosofos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
