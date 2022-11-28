import FiniteAutomaton.FiniteAutomaton;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            FiniteAutomaton finiteAutomaton = new FiniteAutomaton();
            finiteAutomaton.readFile("FiniteAutomaton.in");
            finiteAutomaton.print();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
