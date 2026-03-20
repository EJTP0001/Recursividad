public class Ejercicio4 {

    // Fragmento A
    public static int potenciaRapida(int x, int n) {
        if (n == 0) return 1;
        int mitad = potenciaRapida(x, n / 2);
        if (n % 2 == 0) return mitad * mitad;
        else return x * mitad * mitad;
    }

    // Fragmento B
    public static void invertirMejorado(char[] arr, int i, int j) {
        if (i >= j) return;
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        invertirMejorado(arr, i + 1, j - 1);
    }

    // Fragmento C
    public static int contarUnosIterativo(int[] arr) {
        int contador = 0;
        for (int num : arr) {
            if (num == 1) contador++;
        }
        return contador;
    }
}