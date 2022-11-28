package FiniteAutomaton;

import java.io.FileInputStream;
import java.util.*;

public class FiniteAutomaton {


    private static final Set<String> Q = new HashSet<>();
    private static final Set<String> E = new HashSet<>();
    private static final Set<String> q0 = new HashSet<>();
    private static final Set<String> F = new HashSet<>();
    private static final Map<Key, ArrayList<String>> d = new LinkedHashMap<>();

    private static final class Key {
        String s1, s2;

        public Key(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public String toString() {
            return s1 + " " + s2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (Objects.equals(s1, key.s1)) return true;
            return Objects.equals(s2, key.s2);
        }

        @Override
        public int hashCode() {
            int result = s1 != null ? s1.hashCode() : 0;
            result = 31 * result + (s2 != null ? s2.hashCode() : 0);
            return result;
        }
    }

    public void readFile(String fileName) throws Exception {
        try {
            Scanner scanner = new Scanner(new FileInputStream(fileName));
            readSets(scanner, Q);
            checkStates(Q);
            readSets(scanner, E);
            checkAlphabet(E);
            readSets(scanner, q0);
            checkFinalState(q0);
            readSets(scanner, F);
            checkFinalState(F);
            while (scanner.hasNextLine()) {
                String[] splittedInputLine = scanner.nextLine().split(" ");
                Key key = new Key(splittedInputLine[0], splittedInputLine[1]);
                //String[] key = {splittedInputLine[0], splittedInputLine[1]};
                String value = splittedInputLine[2];
                ArrayList<String> arrayList;
                if (d.containsKey(key)) {
                    arrayList = d.get(key);


                } else {
                    arrayList = new ArrayList<>();

                }
                arrayList.add(value);
                d.put(key, arrayList);
            }

        } catch (Exception ex) {
            throw ex;
        }


    }

    public void readSets(Scanner scanner, Set<String> set) throws Exception {
        try {
            if (scanner.hasNextLine()) {
                String[] splittedInputLine = scanner.nextLine().split(" ");
                set.addAll(Arrays.asList(splittedInputLine));
            }
        } catch (Exception ex) {
            throw new Exception("Something went wrong.");
        }
    }

    public void print() {
        System.out.println("Set of states: " + Q);
        System.out.println("Alphabet: " + E);
        System.out.println("Set of final states:" + q0);
        System.out.println("Transition functions:");
        for (Key k : d.keySet()) {
            if (d.get(k).size() == 1) {
                System.out.println("(" + k.s1 + "," + k.s2 + ")" + "->" + d.get(k));
            } else {
                for (String s: d.get(k)) {
                    System.out.println("(" + k.s1 + "," + k.s2 + ")" + "->" + "[" + s + "]");
                }
            }
        }
    }

    //validators
    public void checkStates(Set<String> set) throws Exception {
        if (set.size()<3) {
            throw new Exception("Set of states size invalid");
        }
        for (String s: set) {
            if (!s.matches("[a-zA-Z]+"))
                throw new Exception("States set invalid");
        }
    }
    public void checkAlphabet(Set<String> set) throws Exception {
        if (set.size()<1){
            throw new Exception("Invalid alphabet size");
        }
        for (String s: set) {
            if (!s.matches("[a-zA-Z]+[0-9]*"))
                throw new Exception("Alphabet invalid");
        }
    }
    public void checkFinalState(Set<String> set) throws Exception {
        if (set.size()<1){
            throw new Exception("Invalid final state size");
        }
        for (String s: set) {
            if (!s.matches("[a-zA-Z]+[0-9]*"))
                throw new Exception("Final state invalid");
        }
    }
    public void checkInitialState(Set<String> set) throws Exception{
        if (set.size()<1){
            throw new Exception("Invalid initial state size");
        }
        for (String s: set) {
            if (!s.matches("[a-zA-Z]+"))
                throw new Exception("Initial state invalid");
        }
    }


}