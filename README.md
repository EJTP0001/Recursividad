Ejercicio 1 — Factorial: de recursión lineal a diseño consciente

Tareas:

- Sin ejecutar el código, dibuje el árbol o la cadena de llamadas para factorial (5) e indique qué información queda pendiente en cada nivel de la pila.

        factorial(5) -> 5 * factorial(4)
        factorial(4) -> 4 * factorial(3)
        factorial(3) -> 3 * factorial(2)
        factorial(2) -> 2 * factorial(1)
        factorial(1) -> 1 * factorial(0)
        factorial(0) -> 1

- Explique por qué esta función no genera un árbol ramificado como Fibonacci, sino una sola rama de profundidad n.

        A diferencia de Fibonacci aqui cada llamada a factorial(n) genera exactamente una unica llamada recursiva (n-1), 
        no hay ramificacion porque el problema no se divide en multiples subproblemas independientes que deban resolverse 
        a la vez en el mismo nivel.

- Escriba la recurrencia temporal T(n) y justifique, sin usar frases vacías, por qué su complejidad temporal es lineal y su complejidad espacial también es lineal.

      Recurrencia T(n): T(n) =T(n−1)+C, donde C es el tiempo constante de la comparacion y la multiplicacion.
      Complejidad Temporal: Es lineal O(n) porque se realizan exactamente n llamadas antes de llegar al caso base.
      Complejidad Espacial: Es lineal O(n) porque cada llamada añade un nuevo marco a la pila
      y no se libera ninguno hasta alcanzar el caso base.

- Reescriba el algoritmo en una versión iterativa y compare formalmente qué costo desaparece al eliminar la recursión.
  
       public static long factorialIterativo(int n) {
           long res = 1;
           for (int i = 2; i <= n; i++) res *= i;
           return res;
       }

      Al eliminar la recursion desaparece el costo espacial de la pila O(n), reduciendolo a O(1), 
      ya que solo usamos una variable para el acumulador y otra para el indice.

- Diseñe una versión de recursividad de cola con acumulador y argumente por qué en Java sigue sin garantizarse una optimización real del stack.
   
      public static long factorialCola(int n, long acumulador) {
          if (n <= 1) return acumulador;
          return factorialCola(n - 1, n * acumulador);
      }

      En Java esto no garantiza optimizacion porque en este lenguaje no se optimiza la recursion de cola, lo que resulta que
      en cada llamada sigue creando un marco en el stack, por lo que el riesgo de que se llene la pila y nos de StackOverFlowError persiste

- Explique en qué contexto práctico seguiría prefiriendo la versión recursiva, aun sabiendo que la iterativa usa menos memoria.

      Preferiria la version recursiva en contextos donde la legibilidad y la cercania a la definicion sean mas importantes
      que el rendimiento extremo, o en lenguajes que si optimicen la recursion de cola


Ejercicio 2 — Fibonacci: detectar el verdadero origen del costo

- Construya manualmente el árbol de llamadas de fib(6). No se acepta solo el resultado final; debe marcar cuáles subproblemas se repiten.

        fib(6) -> fib(5) + fib(4)
        fib(5) -> fib(4) + fib(3)
        fib(4) -> fib(3) + fib(2)
        fib(3) -> fib(2) + fib(1)
        fib(2) -> fib(1) + fib(0) 
-       fib(1) -> 1
        fib(0) -> 0
        fib(1) -> 1 
        fib(2) -> fib(1) + fib(0)
        fib(1) -> 1
        fib(0) -> 0
        fib(3) -> fib(2) + fib(1)
        fib(2) -> fib(1) + fib(0)
        fib(1) -> 1
        fib(0) -> 0
        fib(1) -> 1
        fib(4) -> fib(3) + fib(2)
        fib(3) -> fib(2) + fib(1) 
        fib(2) -> fib(1) + fib(0)
        fib(1) -> 1 
        fib(0) -> 0
        fib(1) -> 1
        fib(2) -> fib(1) + fib(0)
        fib(1) -> 1
        fib(0) -> 0

           
- Explique por qué el costo de este algoritmo no crece por tener recursión, sino por recalcular subproblemas ya resueltos.
 
      El costo no es alto por ser recursivo, sino por la recomputacion redundante,
      el algoritmo no recuerda que ya resolvio por ejemplo fib(4) (que es una de las que se repiten) y vuelve a procesar todo su subarbol cada vez que aparece en una rama distinta.

- Señale al menos tres llamadas redundantes dentro de su árbol y explique cómo esas repeticiones empujan el tiempo hacia crecimiento exponencial.

      Las llamadas a fib(4), fib(3) y fib(2) se repiten multiples veces, lo que lleva al tiempo hacia crecimiento exponecial porque 
      el numero de nodos en el arbol de llamadas crece de forma brusca al sumar las dos ramas anteriores.

    
- Analice cómo cambia la complejidad temporal y espacial cuando pasa de la versión recursiva ingenua a la memorizada (ver teoría).

      Temporal: Pasa de exponencial O(2^n) a lineal O(n) ya que cada subproblema se resuelve solo una vez.
      Espacial: Se mantiene O(n) por la pila de llamadas, pero se añade un costo adicional de O(n) para almacenar los resultados en una estructura (arreglo o mapa).

- Explique por qué la tabulación iterativa en este caso no solo cambia la implementación, sino también la forma de pensar el problema: de arriba hacia abajo vs. de abajo hacia arriba.

      La tabulacion cambia el enfoque a "abajo hacia arriba", resolviendo primero fib(0) y fib(1) y construyendo la solucion paso a paso hasta llegar a n. 
      Esto elimina la necesidad de la pila de llamadas recursivas.

- Defienda cuál de las tres versiones usaría si el programa debe responder 100,000 consultas de Fibonacci con distintos valores de n en una misma ejecución.

      Yo usaria tabulacion, ya que una vez calculados los valores, cualquier consulta adicional es O(1), 
       y ya que tambien la version recursiva incluso con memorización podria agotar el stack con valores de n muy grandes.


Ejercicio 3 — Torres de Hanoi: cuando la explosión no es un defecto

- Para n = 3, trace a mano la secuencia completa de llamadas y movimientos. Luego explique por qué el movimiento central del disco más grande divide la solución en dos problemas simétricos.
  
      hanoi(3, A, B, C)
      hanoi(2, A, C, B)
      hanoi(1, A, B, C) -> A a C
        A a B
      hanoi(1, C, A, B) -> C a B
      A a C (Movimiento del disco mas grande)
      hanoi(2, B, A, C)
      hanoi(1, B, C, A) -> B a A
        B a C
      hanoi(1, A, B, C) -> A a C

- Escriba la recurrencia M(n) del número mínimo de movimientos y resuélvala hasta llegar a la forma cerrada.

      M(n)=2M(n−1)+1
      M(1)=1
      M(n)=2[2M(n−2)+1]+1
      M(n)=22M(n−2)+2+1
      M(n)=22[2M(n−3)+1]+2+1
      M(n)=23M(n−3)+22+21+20
      M(n)=2n−1M(1)+2n−2+2n−3+...+21+20
      M(n)=2n−1+2n−2+2n−3+...+21+20
      M(n)=2^n−1/2-1
      M(n)=2n−1


- Explique por qué Hanoi también tiene crecimiento exponencial, pero por una razón distinta a Fibonacci: aquí no hay recomputación inútil, sino que cada llamada representa trabajo estructuralmente necesario.

      En Fibonacci, el costo exponencial se puedo decir que es por error (recomputar). En Hanoi, es estructuralmente necesario
      para mover n discos, es fisicamente imposible hacerlo en menos de 2n−1 pasos, cada llamada recursiva realiza un movimiento real e indispensable.

- Argumente por qué no existe una versión iterativa trivial que mejore la complejidad del problema. Puede cambiar la forma de implementarlo, pero no el número mínimo de movimientos requerido.
  
      No existe una version que mejore la complejidad temporal porque no puedes hacer el proceso más rápido porque hay un limite fisico de movimientos que no puedes saltar.
      Aunque usemos un bucle, seguiremos necesitando 2n−1 operaciones.

- Diseñe una estrategia iterativa o con pila manual que simule la recursión. No se evalúa por ser más rápida, sino por demostrar que entendiste qué información guarda cada marco de llamada.

      Se puede usar una pila que guarde objetos  con los parámetros (n, origen, auxiliar, destino), lo que nos demuestra que el stack de la recursion solo automatiza el almacenamiento de estos estados pendientes.

- Responda: ¿qué cambia si el objetivo del programa no es imprimir movimientos, sino únicamente contar cuántos se necesitan? Rediseñe la solución y compare sus costos.

      Si solo queremos el numero, la solucion es simplemente retornar (long) Math.pow(2, n) - 1, el costo pasa de O(2n) a O(1) o O(logn) 
      dependiendo de la implementacion de la potencia, ya que no necesitamos imprimir cada paso intermedio.

- Por qué Hanoi es un excelente contraejemplo para el estudiante que cree que si una recursión es O(2^n), entonces está mal diseñada.

      Hanoi demuestra que una complejidad exponencial no siempre significa un mal diseño, a veces el problema 
      es simplemente pesado de cualquier forma y la recursion es la forma más elegante de expresarlo.

Ejercicio 4 — Mutaciones de código: cambiar la complejidad con cambios mínimos 

- En el fragmento A, reemplace la idea recursiva lineal por exponenciación rápida. Debe justificar por qué el problema deja de reducirse de n en n y pasa a reducirse por mitades.

      public static int potenciaRapida(int x, int n) {
          if (n == 0) return 1;
          int mitad = potenciaRapida(x, n / 2);
          if (n % 2 == 0) return mitad * mitad;
      else return x * mitad * mitad;
      }

      Justificacion: El problema se reduce por mitades (n/2) en lugar de uno en uno (n−1), lo que cambia la complejidad
      de O(n) a O(logn)

- En el fragmento B, explique por qué la lógica parece lineal, pero puede degradarse por el costo acumulado de concatenaciones y subcadenas. Luego proponga una versión que reduzca ese costo.

      La logica parece ser lineal, pero s.substring(1) crea una nueva cadena en cada llamada y la concatenacion + tambien crea objetos nuevos, el costo real es O(n2).
      Mejora: Usar un StringBuilder y un método auxiliar que pase los índices para evitar crear subcadenas.

      public static void invertirMejorado(char[] arr, int i, int j) {
          if (i >= j) return;
          char temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
          invertirMejorado(arr, i + 1, j - 1);
      }

      Esto reduce el costo a lineal O(n) en tiempo.
    
- En el fragmento C, argumente por qué cambiarlo a iterativo mejora el espacio pero no el tiempo asintótico. Luego responda: vale la pena cambiarlo siempre.
  
      Cambiarlo a iterativo mejora el espacio (de O(n) a O(1)) porque eliminjava Maina la pila de llamadas, pero el tiempo sigue siendo O(n) ya que obligatoriamente hay que revisar cada elemento del arreglo una vez.
      ¿Vale la pena cambiarlo siempre? No, porque si el arreglo es pequeño y la legibilidad es prioridad, la recursion es aceptable, y si el arreglo tiene millones de elementos, el cambio a iterativo es obligatorio para evitar un StackOverflow.

Ojo para correr desde la terminal (asi lo hice yo):

cd src
javac *.java
java Main

:)