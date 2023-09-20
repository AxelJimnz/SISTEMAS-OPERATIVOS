package ejercicioFilosofos;

public class Principal {
    public static void main(String[] args) {
        Mesa m = new Mesa(7);
        for(int i=1; i<=7; i++){
            Filosofo f = new Filosofo(m, i);
            f.start();
        }
    }
}
