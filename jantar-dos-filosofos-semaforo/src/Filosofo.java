public class Filosofo extends Thread{
    
	int id;

    // ESTADOS

    final int PENSANDO = 0;
    final int FAMINTO = 1;
    final int COMENDO = 2;
    
    public Filosofo(String nome, int id) {
        super(nome);
        this.id = id;
    }

    public void ComFome() {
        JantarDosFilosofos.estado[this.id] = 1;
        System.out.println(getName() + " está COM FOME!");
    }

    public void Come() {
        JantarDosFilosofos.estado[this.id] = 2;
        System.out.println(getName() + " está COMENDO!");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void Pensa() {
        JantarDosFilosofos.estado[this.id] = 0;
        System.out.println(getName() + " está PENSANDO!");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void SoltarGarfos() throws InterruptedException {
        JantarDosFilosofos.mutex.acquire();
        Pensa();

        // Quando um filosofo soltar os garfos, seus vizinhos iniciam a tentativa
        // de pegar os garfos
        JantarDosFilosofos.filosofos[VizinhoDaEsquerda()].TentarObterGarfos();
        JantarDosFilosofos.filosofos[VizinhoDaDireita()].TentarObterGarfos();
        JantarDosFilosofos.mutex.release();
    }

    public void PegarGarfos() throws InterruptedException {
        JantarDosFilosofos.mutex.acquire();
        ComFome();
        // Caso a condição seja verdadeira, semaforo(1)
        // Permitindo que o filosofo obtenha os garfos
        TentarObterGarfos();        
        JantarDosFilosofos.mutex.release();
        // Caso a condição não seja verdadeira, o filosofo fica em espera, até chegar
        // sua proxima tentativa de pegar os garfos
        JantarDosFilosofos.semaforos[this.id].acquire(); //semaforos[this.id] = new Semaphore(0)
    }

    public void TentarObterGarfos() {
        
        // Se o filosofo estiver com fome e os vizinhos não estiverem comendo
        // eh chamado o metodo come();
        if (JantarDosFilosofos.estado[this.id] == 1 && JantarDosFilosofos.estado[VizinhoDaEsquerda()] != 2 && JantarDosFilosofos.estado[VizinhoDaDireita()] != 2) {
            Come();
            JantarDosFilosofos.semaforos[this.id].release();//semaforos[this.id] = new Semaphore(1)
        } else {
            System.out.println(getName() + " não conseguiu comer!");
        }

    }

    @Override
    public void run() {
        try {
            Pensa();
            do {
                PegarGarfos();
                Thread.sleep(1000L);
                SoltarGarfos();
            } while (true);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
            return;
        }
    }

    public int VizinhoDaDireita() {
        return (this.id + 1) % 5;
    }

    public int VizinhoDaEsquerda() {

        // Filosofo 0 recebe vizinho a esquerda 4, porque se calcularmos
        // o resultado será -1.
        if (this.id == 0) {            
            return 4;
        } else {
            return (this.id - 1) % 5;
        }
    }
}
