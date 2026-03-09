public class Anagrams{
    public static void main(String[]args){
       char[] char=word.toCharArray();
       throws Exception{
        if(args!=1){
            System.out.println("Usage: java input file");
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

                D.put(word, D.getOrDefault(word, 0) + 1);
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

       } 
    }
}