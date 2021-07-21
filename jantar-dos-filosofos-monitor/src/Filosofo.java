public class Filosofo extends Thread {

    final static int TEMPO_MAXIMO = 100;
    Mesa mesa;
    int filosofo;
    String nome;

    public Filosofo(String nomeF, Mesa mesadejantar, int fil) {
        nome = nomeF;
        mesa = mesadejantar;
        filosofo = fil;
    }

    public void run() {

        while (true) {
            pensar(1000);

            getGarfos();

            comer(1000);

            returnGarfos();
        }
    }

    public void pensar(int tempo) {
        try {
            System.out.println("");
            System.out.println(getNome() + " está PENSANDO");
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("");
            System.out.println("O Filófoso PENSOU demais");
        }
    }

    public void comer(int tempo) {
        try {
            System.out.println("");
            System.out.println(getNome() + " está COMENDO");
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("");
            System.out.println("O Filósofo COMEU demais");
        }
    }

    public String getNome() {
        return nome;
    }

    public void getGarfos() {
        mesa.pegarGarfos(filosofo);
    }

    public void returnGarfos() {
        mesa.returningGarfos(filosofo);
    }
}
