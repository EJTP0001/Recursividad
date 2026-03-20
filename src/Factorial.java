public class Factorial {

    //Version Recursiva Lineal
    public static long factorialRecursivo(int n) {
        if (n <= 1) return 1;
        return n * factorialRecursivo(n - 1);
    }

    //Version Iterativa
    public static long factorialIterativo(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    //Version con Recursividad de Cola
    public static long factorialCola(int n, long acumulador) {
        if (n <= 1) return acumulador;
        return factorialCola(n - 1, n * acumulador);
    }
}