/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sopadeletras;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author batoi
 */
public class SopaDeLetras {

    static Scanner teclado;

    public static void main(String[] args) {
        teclado = new Scanner(System.in);

        int tamanyo = pedirNumero();

        char[][] sopa = generarSopaLetras(tamanyo);
        System.out.println("");
        imprimirSopaLetras(sopa, tamanyo);
         System.out.println("");
        visualizarResultados(sopa);

    }

    public static char[][] generarSopaLetras(int tamanyo) {
        if (tamanyo == 0) {
            return null;
        } else {
            char[][] sopaLetras = new char[tamanyo][tamanyo];
            String caracteres = "abcdefghijklmnopkrstuvwxyz";
            Random random = new Random();

            for (int i = 0; i < tamanyo; i++) {
                for (int j = 0; j < tamanyo; j++) {
                    int index = random.nextInt(caracteres.length());
                    sopaLetras[i][j] = caracteres.charAt(index);
                }
            }

            return sopaLetras;
        }
    }

    public static void imprimirSopaLetras(char[][] sopa, int tamanyo) {
        for (int i = 0; i < tamanyo; i++) {
            for (int j = 0; j < tamanyo; j++) {
                System.out.print(sopa[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int pedirNumero() {
        System.out.print("Introduce el tamaño de la sopa de letras: ");
        int numero = leerEntrada();
        return numero;
    }

    public static int leerEntrada() {
        do {
            int numero;
            if (teclado.hasNextInt()) {
                int numeroPositivo = teclado.nextInt();
                if (numeroPositivo > 0) {
                    numero = numeroPositivo;
                    return numero;
                } else {
                    System.out.print("Introduce el tamaño de la sopa de letras: ");
                }

            } else {
                System.out.print("Introduce el tamaño de la sopa de letras: ");
                teclado.next();
            }
        } while (true);
    }
    
    //PUNTO 1 CANTIDAD DE VOCALES EN FILAS

    public static int[] getIndicesFilasConMasVocales(char[][] sopaLetras) {
    int maxVocales = cantidadMaximaDeVocales(sopaLetras);
    int[] indicesFilasMaxVocales = new int[0];

    for (int i = 0; i < sopaLetras.length; i++) {
        int vocalesEnFila = 0;

        for (int j = 0; j < sopaLetras[i].length; j++) {
            if (esVocal(sopaLetras[i][j])) {
                vocalesEnFila++;
            }
        }

        if (vocalesEnFila == maxVocales) {
            indicesFilasMaxVocales = Arrays.copyOf(indicesFilasMaxVocales, indicesFilasMaxVocales.length + 1);
            indicesFilasMaxVocales[indicesFilasMaxVocales.length - 1] = i;
        }
    }

    return indicesFilasMaxVocales;
}


    public static boolean esVocal(char letra) {
        boolean esVocal = false;

        if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
            esVocal = true;
        }

        return esVocal;
    }

    public static int cantidadMaximaDeVocales(char[][] sopa) {
        String vocales = "aeiou";
        int maxCantidadVocales = 0;

        for (int i = 0; i < sopa.length; i++) {
            int cantidadVocales = 0;

            for (int j = 0; j < sopa[i].length; j++) {
                char caracter = Character.toLowerCase(sopa[i][j]);
                if (vocales.indexOf(caracter) != -1) {
                    cantidadVocales++;
                }
            }

            if (cantidadVocales > maxCantidadVocales) {
                maxCantidadVocales = cantidadVocales;
            }
        }

        return maxCantidadVocales;
    }

    public static void imprimirFilasConMasVocales(char[][] sopa, int[] indicesFilasMaxVocales) {
    if (indicesFilasMaxVocales.length > 0) {
        
        for (int i = 0; i < indicesFilasMaxVocales.length; i++) {
            System.out.println("1. Fila índice " + indicesFilasMaxVocales[i]+" con mayor número de vocales ");
        }
    } else {
        System.out.println("1. La matriz sólo tiene consonantes");
    }
}
    //PUNTO 2 CONSONANTES EN COLUMNAS
    
public static void imprimirColumnasConMasConsonantes(char[][] sopa, int[] indicesColumnasMaxConsonantes) {
    if (indicesColumnasMaxConsonantes.length > 0) {
        for (int i = 0; i < indicesColumnasMaxConsonantes.length; i++) {
            System.out.println("2. Columna índice " + indicesColumnasMaxConsonantes[i] + " con mayor número de consonantes");
        }
    } else {
        System.out.println("2. La matriz solo tiene vocales");
    }
}

public static int[] getIndicesColumnasConMasConsonantes(char[][] sopaLetras) {
    int maxConsonantes = cantidadMaximaDeConsonantes(sopaLetras);
    int[] indicesColumnasMaxConsonantes = new int[0];

    for (int j = 0; j < sopaLetras[0].length; j++) {
        int consonantesEnColumna = 0;

        for (int i = 0; i < sopaLetras.length; i++) {
            if (!esVocal(sopaLetras[i][j])) {
                consonantesEnColumna++;
            }
        }

        if (consonantesEnColumna == maxConsonantes) {
            indicesColumnasMaxConsonantes = Arrays.copyOf(indicesColumnasMaxConsonantes, indicesColumnasMaxConsonantes.length + 1);
            indicesColumnasMaxConsonantes[indicesColumnasMaxConsonantes.length - 1] = j;
        }
    }

    return indicesColumnasMaxConsonantes;
}

public static int cantidadMaximaDeConsonantes(char[][] sopa) {
    int maxCantidadConsonantes = 0;

    for (int j = 0; j < sopa[0].length; j++) {
        int cantidadConsonantes = 0;

        for (int i = 0; i < sopa.length; i++) {
            char caracter = Character.toLowerCase(sopa[i][j]);
            if (!esVocal(caracter)) {
                cantidadConsonantes++;
            }
        }

        if (cantidadConsonantes > maxCantidadConsonantes) {
            maxCantidadConsonantes = cantidadConsonantes;
        }
    }

    return maxCantidadConsonantes;
}
// PUNTO 3
public static boolean isLetraRepetidaEnFila(char[][] sopaLetras, int indiceFila) {
    if (sopaLetras == null || indiceFila < 0 || indiceFila >= sopaLetras.length) {
        return false;
    }

    char[] fila = sopaLetras[indiceFila];
    
    for (int i = 0; i < fila.length - 1; i++) {
        for (int j = i + 1; j < fila.length; j++) {
            if (fila[i] == fila[j]) {
                return true;
            }
        }
    }

    return false;
}



public static void imprimirFilasConLetrasRepetidas(char[][] sopaLetras) {
    for (int i = 0; i < sopaLetras.length; i++) {
     int contador=0;   
        if (isLetraRepetidaEnFila(sopaLetras, i)) {
            contador++;
            if(contador>0){
            System.out.println("3. Fila índice " + i + " con letras repetidas");
            }else{
                System.out.println("3. No hay filas con letras repetidas");
            }
        }
        
    }
}

/// PUNTO 4
    public static int[] getIdicesColumnasConMismaLetra(char[][] sopaLetras) {
        int maxCantidadLetra = cantidadMaximaDeMismaLetra(sopaLetras);
        int[] indicesColumnasMaxLetra = new int[0];

        for (int j = 0; j < sopaLetras[0].length; j++) {
            int cantidadLetraEnColumna = 0;

            for (int i = 0; i < sopaLetras.length; i++) {
                char letra = sopaLetras[i][j];
                if (cantidadDeLetraEnColumna(sopaLetras, j, letra) == sopaLetras.length) {
                    cantidadLetraEnColumna++;
                }
            }

            if (cantidadLetraEnColumna == maxCantidadLetra) {
                indicesColumnasMaxLetra = Arrays.copyOf(indicesColumnasMaxLetra, indicesColumnasMaxLetra.length + 1);
                indicesColumnasMaxLetra[indicesColumnasMaxLetra.length - 1] = j;
            }
        }

        return indicesColumnasMaxLetra;
    }

    public static int cantidadDeLetraEnColumna(char[][] sopa, int columna, char letra) {
        int cantidad = 0;
        for (int i = 0; i < sopa.length; i++) {
            if (sopa[i][columna] == letra) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public static int cantidadMaximaDeMismaLetra(char[][] sopa) {
        int maxCantidadLetra = 0;

        for (int j = 0; j < sopa[0].length; j++) {
            for (int i = 0; i < sopa.length; i++) {
                char letra = sopa[i][j];
                int cantidadLetra = cantidadDeLetraEnColumna(sopa, j, letra);

                if (cantidadLetra > maxCantidadLetra) {
                    maxCantidadLetra = cantidadLetra;
                }
            }
        }

        return maxCantidadLetra;
    }

    public static void imprimirColumnasConMismaLetra(char[][] sopa, int[] indicesColumnasMaxLetra) {
        if (indicesColumnasMaxLetra.length > 0) {
            for (int i : indicesColumnasMaxLetra) {
                System.out.println("4. Columna índice " + i + " con letras repetidas");
            }
        } else {
            System.out.println("4. No hay columnas con la misma letra");
        }
    }
    
    //punto 5
    public static boolean isDiagonalPrincipalDelMismoTipo(char[][] sopaLetras) {
        char tipoLetra = sopaLetras[0][0];
        for (int i = 1; i < sopaLetras.length; i++) {
            if (sopaLetras[i][i] == tipoLetra) {
                return false;
            }
        }
        return true;
    }
    public static void imprimirDiagonalPrincipal(char[][] sopaLetras) {
        if (isDiagonalPrincipalDelMismoTipo(sopaLetras)) {
            System.out.println("5. Diagonal principal con mezcla de vocales y consonantes");
        }else{
            System.out.println("5. Diagonal principal con el mismo tipo de letra.");
        }
    }
//punto 6
    public static boolean isDiagonalSecundariaDelMismoTipo(char[][] sopaLetras) {
        char tipoLetra = sopaLetras[0][sopaLetras.length - 1];
        for (int i = 1; i < sopaLetras.length; i++) {
            if (sopaLetras[i][sopaLetras.length - 1 - i] == tipoLetra) {
                return false;
            }
        }
        return true;
    }
    public static void imprimirDiagonalSecundaria(char[][] sopaLetras) {
       if (isDiagonalSecundariaDelMismoTipo(sopaLetras)) {
            System.out.println("6. Diagonal secundaria con mezcla de vocales y consonantes");
        }else{
           System.out.println("6. Diagonal secundaria con el mismo tipo de letra.");
       }

}
    public static void visualizarResultados(char[][] sopaLetras){
        int[] indicesFilasMasVocales = getIndicesFilasConMasVocales(sopaLetras);
        imprimirFilasConMasVocales(sopaLetras, indicesFilasMasVocales);
        int[] indicesColumnasMaxConsonantes = getIndicesColumnasConMasConsonantes(sopaLetras);
        imprimirColumnasConMasConsonantes(sopaLetras, indicesColumnasMaxConsonantes);
        imprimirFilasConLetrasRepetidas(sopaLetras);
        int[] indicesColumnasMaxLetra = getIdicesColumnasConMismaLetra(sopaLetras);
        imprimirColumnasConMismaLetra(sopaLetras, indicesColumnasMaxLetra);
        imprimirDiagonalPrincipal(sopaLetras);
        imprimirDiagonalSecundaria(sopaLetras);
    }
}

