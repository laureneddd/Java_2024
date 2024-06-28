import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExercise {

    
    public static void main(String[] args) {
        
        //1. find the sum of two integers.
        int n1 = 2, n2 = 4;
        BiFunction<Integer, Integer, Integer> sum = (i, j) -> i + j;
        
        System.out.println("The sum is : " + sum.apply(n1, n2));
            
            
        //2. check if a given string is empty.
        String str = "Add";
        
        Predicate<String> stringIsEmpty = string -> string.isEmpty();

        System.out.println("Check if string '" + str + "' is empty :" + stringIsEmpty.test(str));
        
        //3. convert a list of strings to uppercase and lowercase
        List<String> list = List.of("Add", "Bob", "Cat");
        
        Consumer<List<String>> upperCase = strings -> {
            List<String> upperlist = new ArrayList<>();
            for(String s : strings) {
                upperlist.add(s.toUpperCase());
            }
            System.out.println(upperlist);

        };
        upperCase.accept(list);

        Consumer<List<String>> lowerCase = strings -> {
            List<String> lowerlist = new ArrayList<>();
            for(String s : strings) {
                lowerlist.add(s.toLowerCase());
            }
            System.out.println(lowerlist);

        };
        lowerCase.accept(list);

        //4. filter out even and odd numbers from a list of integers.
        List<Integer> filter  = List.of(1, 2, 3, 4);
        
        Consumer<List<Integer>> filterOddEven = intlist -> {
            List<Integer> odd = new ArrayList<>();
            List<Integer> even = new ArrayList<>();
            for(Integer i : intlist) {
                if(i % 2 == 0) {
                    even.add(i);
                }
                else {
                    odd.add(i);
                }
            }
            System.out.println("The odd list : " + odd);
            System.out.println("The even list : " + even);
        }; 
        filterOddEven.accept(filter);

        //5. sort a list of strings in alphabetical order.
        List<String> sort  = new ArrayList<>(List.of("odd", "even", "cat", "add"));
        
        Consumer<List<String>> sortString = string -> Collections.sort(string); 
        sortString.accept(sort);
        System.out.println(sort);

        //6. find the average of a list of doubles.
                List<Double> average  = List.of(1.2, 2.34, 4.33, 5.666);
        
        Function<List<Double>, Double> averageCal = doublelist -> {
            double sumCal = 0;
            for(double b : doublelist) {
                sumCal += b;
            }
            return sumCal / doublelist.size();
        };
        
        System.out.println("The average is : " + averageCal.apply(average));

        //7. remove duplicates from a list of integers.
        List<Integer> duplicates  = List.of(1, 4, 2, 3, 1, 4, 5);
        
        Consumer<List<Integer>> duplicatesRemover = newlist -> {
            List<Integer> afterRemove = new ArrayList<>();
            for(int i : newlist) {
                if (!afterRemove.contains(i)) {
                    afterRemove.add(i);
                }
            }
             System.out.println("The list without duplicates is : " + afterRemove);
        };
        duplicatesRemover.accept(duplicates);

        //8. calculate the factorial of a given number. n! = n * (n - 1) * (n - 2) ... * 1
        int target = 5;
    
        Consumer<Integer> number = nums -> {
            int ans = 1;
            for(int i = 1; i <= target; i++) {
                ans *= i;
            }
             System.out.println("The factorial of " + target + " is : " + ans);
        };
        number.accept(target);


        //9. check if a number is prime.
        // prime: a natural number greater than 1 that is not a product of two smaller natural numbers
        int testnumber = 29;
    
        Predicate<Integer> isPrime = testPrime -> {
            if (testPrime <= 1) {
                return false;
            }
            for (int i = 1; i < Math.sqrt(testPrime); i++) {
                if (testPrime % i == 0) {
                    return false;
                }
            }
            return true;
        };
        
        System.out.println("Check if " + testnumber + " is a prime? " + isPrime.test(testnumber));

        //10. concatenate two strings.
        String str1 = "dai";
        String str2 = "zhuoer";

        BiFunction<String, String, String> combine  = (s1, s2) -> s1 + s2;
        System.out.println("The combine of two strirngs is: " + combine.apply(str1, str2));


        //11. find the maximum and minimum values in a list of integers.
        List<Integer> listForMargin = List.of(5, 6, 7, 2, 1, 9, 3);

        Consumer<List<Integer>> marginValue = findMargin -> {
            List<Integer> sortedlist = new ArrayList<>(findMargin);
            Collections.sort(sortedlist);
            System.out.println("The minimum value in the list is: " + sortedlist.get(0));
            System.out.println("The maximum value in the list is: " + sortedlist.get(sortedlist.size() - 1));
        };

        marginValue.accept(listForMargin);


        //12. multiply and sum all elements in a list of integers.
        List<Integer> listForSum = List.of(5, 6, 7, 2, 1, 9, 3);

        Consumer<List<Integer>> sumAndProduct = findSum -> {
            int sumlist = 0;
            int productlist = 1;
            for(int i : findSum) {
                sumlist += i;
                productlist *= i; 
            }
            System.out.println("The sum of elements in the list is: " + sumlist);
            System.out.println("The product of elements in the list is: " + productlist);

        };
        sumAndProduct.accept(listForSum);


        //13. count words in a sentence.
        String sentence = "Hello, my name is zhuoer dai";
        
        Consumer<String> contWord = wordstring -> {
            int cont = 0;
            //split string by space and comma 
            String[] words = wordstring.split("[,\\s]+");
            for (String word: words) {
                cont++;
            }
            System.out.println("The words number in the sentence is: " + cont); 
        };
        contWord.accept(sentence);


        //14. check if a given string is a palindrome.
        String palindrome = "asdfgfdsa";
        Predicate<String> isPalindrome = sPalindrome -> {
            int first = 0, last = sPalindrome.length() - 1;
            while(first != last) {
                if (sPalindrome.charAt(first) != sPalindrome.charAt(last)) {
                    return false;
                }
                else {
                    first++;
                    last--;
                }
            }
            return true;
        };
        System.out.println("Check if '" + palindrome + "' is a palindrome: " + isPalindrome.test(palindrome));

        //15. calculate the sum of squares of all odd and even numbers in a list.
        List<Integer> squaIntegers = List.of(2, 3, 4, 6, 7, 8);

        Consumer<List<Integer>> squareCal = squarelist -> {
            List<Integer> odd = new ArrayList<>();
            List<Integer> even = new ArrayList<>();
            int oddsum = 0, evensum = 0;

            for(int num : squarelist) {
                if (num % 2 == 0) {
                    even.add(num);
                }   
                else {
                    odd.add(num);
                }
            }
            for (int oddn : odd) {
                oddsum += oddn * oddn;
            }
            for (int evenn : even) {
                evensum += evenn * evenn;
        }
            System.out.println("The sum of square of all odd numbers in list is : " + oddsum);
            System.out.println("The sum of square of all even numbers in list is : " + evensum);
        };

        squareCal.accept(squaIntegers);

    
    }
}
