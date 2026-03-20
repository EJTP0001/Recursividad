public class Main {
    public static void main(String[] args) {

        System.out.println("FACTORIAL:");
        System.out.println("Recursivo: " + Factorial.factorialRecursivo(5));
        System.out.println("Iterativo: " + Factorial.factorialIterativo(5));
        System.out.println("Cola rec: " + Factorial.factorialCola(5, 1));

        System.out.println("\nEJERCICIO 4:");
        System.out.println("Potencia (2^10): " + Ejercicio4.potenciaRapida(2, 10));

        char[] c = "holaMUndo".toCharArray();
        Ejercicio4.invertirMejorado(c, 0, c.length - 1);
        System.out.println("Invertir: " + new String(c));

        int[] b = {1, 0, 1, 1, 0, 1, 1};
        System.out.println("Contar unos: " + Ejercicio4.contarUnosIterativo(b));
    }
}