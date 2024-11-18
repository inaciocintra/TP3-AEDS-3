package casamentodepadroes;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoore {
    // Realiza a busca do padrão no texto
public static boolean search(String text, String pattern) {
        Map<Character, Integer> badCharTable = buildBadCharTable(pattern);
        int m = pattern.length();
        int n = text.length();
        int shift = 0;

        while (shift <= n - m) {
            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            if (j < 0) {
                return true; // Padrão encontrado
            }

            // Atualiza o deslocamento usando a tabela de caracteres ruins
            char mismatchedChar = text.charAt(shift + j);
            shift += Math.max(1, j - badCharTable.getOrDefault(mismatchedChar, -1));
        }

        return false; // Padrão não encontrado
    }

    // Constrói a tabela de caracteres ruins como um mapa
    private static Map<Character, Integer> buildBadCharTable(String pattern) {
        Map<Character, Integer> table = new HashMap<>();

        // Preenche as posições do padrão
        for (int i = 0; i < pattern.length(); i++) {
            table.put(pattern.charAt(i), i);
        }

        return table;
    }
}
