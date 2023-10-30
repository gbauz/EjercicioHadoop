import ApartadoB.*;
import java.util.Scanner;

public class Main {
    interface Exercise {
        void execute(String[] args);
    }

    static class Exercise1 implements Exercise {
        @Override
        public void execute(String[] args) {
            Problema1.main(args);
        }
    }

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        Exercise[] exercises = new Exercise[]{
                new Exercise1(),
               
        };

        do {
            System.out.println("\nPROYECTO DEL GRUPO 2 - MAP/REDUCE \n" );
            System.out.println("Elija una opción para ejecutar: \n");
            for (int i = 0; i < exercises.length; i++) {
                System.out.println((i + 1) + ". Ejercicio " + (i + 1));
            }
            System.out.println((exercises.length + 1) + ". Salir");

            opcion = sc.nextInt();

            if (opcion >= 1 && opcion <= exercises.length) {
                exercises[opcion - 1].execute(args);
            } else if (opcion == exercises.length + 1) {
                System.out.println("Gracias por usar el programa");
            } else {
                System.out.println("Opción no válida");
            }
        } while (opcion != exercises.length + 1);
    }
}
