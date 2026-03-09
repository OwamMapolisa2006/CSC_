import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Anagrams {

    public static String signature(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Usage: java inputfile");
            return;
        }

        String inputfile = args[0];
        System.out.println("Data file: " + inputfile);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputfile), "latin1"));

        Map<String, Integer> D = new HashMap<>();

        String line;
        int linenumber = 0;

        while ((line = reader.readLine()) != null) {

            linenumber++;

            String[] words = line.split("\\s+");

            for (String w : words) {

                String W = w.replaceAll("[0123456789(,.;:_!?\\-\\[\\].)]", "");
                String word = W;

                if (word.isEmpty()) continue;

                if (D.containsKey(word)) {
                    D.put(word, D.get(word) + 1);
                } else {
                    D.put(word, 1);
                }
            }
        }

        reader.close();

        Map<String, List<String>> A = new HashMap<>();

        for (String w : D.keySet()) {

            String a = signature(w);

            if (!A.containsKey(a)) {
                A.put(a, new ArrayList<>());
            }

            A.get(a).add(w);
        }

        PrintWriter out = new PrintWriter("anagrams");

        for (String key : A.keySet()) {

            List<String> list = A.get(key);

            if (list.size() > 1) {

                String anagramlist = String.join(" ", list);

                out.println(anagramlist + "\\\\");

                for (int i = 0; i < list.size() - 1; i++) {

                    int space = anagramlist.indexOf(" ");

                    anagramlist =
                            anagramlist.substring(space + 1) + " " +
                            anagramlist.substring(0, space);

                    out.println(anagramlist + "\\\\");
                }
            }
        }

        out.close();

        List<String> aa = Files.readAllLines(Paths.get("anagrams"));
        Collections.sort(aa);

        Files.createDirectories(Paths.get("latex"));

        PrintWriter latex = new PrintWriter("latex/theAnagrams.tex");

        char letter = 'X';

        for (String lemma : aa) {

            char initial = lemma.charAt(0);

            if (Character.toLowerCase(initial) != Character.toLowerCase(letter)) {

                letter = initial;

                latex.println("\n\\vspace{14pt}");
                latex.println("\\noindent\\textbf{\\Large " +
                        Character.toUpperCase(initial) +
                        "}\\\\*[+12pt]");
            }

            latex.print(lemma);
        }

        latex.close();

        Files.deleteIfExists(Paths.get("anagrams"));
    }
}