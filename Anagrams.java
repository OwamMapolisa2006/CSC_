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
       } 
    }
}