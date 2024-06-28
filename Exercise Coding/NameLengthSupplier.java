import java.util.*;

public class NameLengthSupplier {
    public static void main(String[] args) {
        String n1 = "Alice";
        String n2 = "Bbbb";
        String n3 = "Cccccc";
        String n4 = "Dddddddddd";
        List<String> names = List.of(n1, n2, n3, n4);

        Supplier<List<Integer>> nameLengths = () -> {
            
            List<Integer> lengths = new ArrayList<>();
            for (String name : names) {
                lengths.add(name.length());
            }
            return lengths;
        };

        System.out.println(nameLengths.get());
    }
}