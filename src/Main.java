import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of elemnents of your array:");

        int numberOfElements = scan.nextInt();
        int evenCounter = 0;
        int oddCounter = 0;
        int differenceCounter = numberOfElements;
        int repetitionCounter = 0;
        int[] array = new int[numberOfElements];


        //Scans the integers and places them in the array
        System.out.println("Enter " + numberOfElements + " element(s) of your array:");

        for (int arrayIndex = 0; arrayIndex < numberOfElements; arrayIndex++) {
            array[arrayIndex] = scan.nextInt();
        }
        scan.close();
        int[] arrayCopy = new int[numberOfElements];
        System.arraycopy(array, 0, arrayCopy, 0, arrayCopy.length);

        //Gets the number of different elements and even and odd numbers.
        for (int differenceIndex = 0; differenceIndex < numberOfElements; differenceIndex++) {
            for (int difComparisonIndex = differenceIndex + 1; difComparisonIndex < numberOfElements; difComparisonIndex++) {
                if (array[differenceIndex] == array[difComparisonIndex]) {
                    differenceCounter--;
                    difComparisonIndex = numberOfElements - 1;
                }
            }
            if (array[differenceIndex] % 2 == 0) {
                evenCounter++;
            }
            else {
                oddCounter++;
            }
        }
        System.out.println();


        //Creates a new array with only the unique numbers
        int[] uniqueArray = new int[differenceCounter];
        boolean isUnique = true;
        int specialIndex = 0;
        for (int arrayIndex = 0; arrayIndex < numberOfElements; arrayIndex++) {
            for (int uniqueIndex = 0; uniqueIndex < differenceCounter; uniqueIndex++) {
                if (uniqueArray[uniqueIndex] == array[arrayIndex]) {
                    isUnique = false;
                }
            }
            if (isUnique) {
                uniqueArray[specialIndex] = array[arrayIndex];
                specialIndex++;
            }
            isUnique = true;
        }


        //Gets the frequency of repetition of each element in the array and saves a number of times each number is repeated
        int[] dedicatedRepCounter = new int[differenceCounter];
        for (int repetitionIndex = 0; repetitionIndex < differenceCounter; repetitionIndex++) {
            for (int repComparisonIndex = 0; repComparisonIndex < numberOfElements; repComparisonIndex++) {
                if (uniqueArray[repetitionIndex] == array[repComparisonIndex]) {
                    repetitionCounter++;
                }
            }
            dedicatedRepCounter[repetitionIndex] = repetitionCounter;
            System.out.println("The number " + uniqueArray[repetitionIndex] + " makes up " + ((Double.valueOf(repetitionCounter) / Double.valueOf(numberOfElements)) * 100) + "% of the array");
            repetitionCounter = 0;
        }


        //Sorts the dedicatedRepCounter and uniqueArray according to dedicatedRepCounter
        int tempElement;
        int tempRep;
        for (int sortOut = 0; sortOut < differenceCounter; sortOut++) {
            for (int sortIn = 0; sortIn < differenceCounter - 1; sortIn++) {
                if (dedicatedRepCounter[sortIn] > dedicatedRepCounter[sortIn + 1]) {
                tempElement = uniqueArray[sortIn];
                tempRep = dedicatedRepCounter[sortIn];
                uniqueArray[sortIn] = uniqueArray[sortIn + 1];
                dedicatedRepCounter[sortIn] = dedicatedRepCounter[sortIn + 1];
                uniqueArray[sortIn + 1] = tempElement;
                dedicatedRepCounter[sortIn + 1] = tempRep;
                }
            }
        }


        //Checks for numbers which occur the most times
        int[] sameRepetitions = new int[differenceCounter];
        int repetitionIndex = 0;
        String repeatedNumbers = String.valueOf(uniqueArray[differenceCounter - 1]);
        for (int sameOccuranceCounter = differenceCounter - 2; sameOccuranceCounter >= 0; sameOccuranceCounter--) {
            if (dedicatedRepCounter[differenceCounter - 1] == dedicatedRepCounter[sameOccuranceCounter]) {
                repeatedNumbers = repeatedNumbers + ", " + uniqueArray[sameOccuranceCounter];
            }
            else {
                sameOccuranceCounter = 0;
            }
        }


        //Sorts the uniqueArray from smallest to largest
        uniqueArray = bubbleSort(uniqueArray);

        //Sorts the array from smallest to largest
        array = bubbleSort(array);

        //Checks if the numbers are palindromes
        int[] palindromes = new int[differenceCounter];
        int palindromeIndex = 0;
        boolean isPalindrome = true;
        int palindromeCounter = 0;
        for (int arrayPalIndex = 0; arrayPalIndex < differenceCounter; arrayPalIndex++) {
            for (int palindromeCheck = 0; palindromeCheck < String.valueOf(uniqueArray[arrayPalIndex]).length(); palindromeCheck++) {
                if (String.valueOf(uniqueArray[arrayPalIndex]).charAt(palindromeCheck) != String.valueOf(uniqueArray[arrayPalIndex]).charAt(String.valueOf(uniqueArray[arrayPalIndex]).length() - palindromeCheck - 1)) {
                    isPalindrome = false;
                }
            }
            if (isPalindrome) {
                palindromeCounter++;
                palindromes[palindromeIndex] = uniqueArray[arrayPalIndex];
                palindromeIndex++;
            }
            isPalindrome = true;
        }


        //Sorts the Palindromes array
        palindromes = bubbleSort(palindromes);


        //Finds the largest Palindrome that is smaller than the largest element in the array
        int largestPalindrome = 0;
        boolean largestPalindromeExists = false;
        for (int nextPalIndex = differenceCounter - 1; nextPalIndex >= 0; nextPalIndex--) {
            if (palindromes[nextPalIndex] < uniqueArray[differenceCounter - 1]) {
                largestPalindrome = palindromes[nextPalIndex];
                largestPalindromeExists = true;
                nextPalIndex = 0;
            }
        }

        //Finds the sum of all the elements
        int sum = 0;
        for (int sumIndex = 0; sumIndex < numberOfElements; sumIndex++) {
            sum += array[sumIndex];
        }


        System.out.println("------------------------------------------------");
        System.out.println("There are " + numberOfElements + " elements in your array.");
        System.out.println("------------------------------------------------");
        System.out.println("There are " + differenceCounter + " different elements.");
        System.out.println("------------------------------------------------");
        System.out.println("There are " + evenCounter + " even numbers and there are " + oddCounter + " odd numbers.");
        System.out.println("------------------------------------------------");
        System.out.println("Number(s) " + repeatedNumbers + " occur(s) the most in the array at " + dedicatedRepCounter[differenceCounter - 1] + " times.");
        System.out.println("------------------------------------------------");
        System.out.println("The largest number in the array is " + uniqueArray[differenceCounter - 1]);
        System.out.println("------------------------------------------------");
        if (uniqueArray.length == 1) {
            System.out.println("Your array has no second smallest number.");
        }
        else {
            System.out.println("The second smallest number in the array is " + uniqueArray[1]);
        }
        System.out.println("------------------------------------------------");
        System.out.println("The average of your array is " + (Double.valueOf(sum) / Double.valueOf(numberOfElements)));
        System.out.println("------------------------------------------------");


        //Finds the standard deviation for each number
        double sqrtOfDifferences = 0;
        for (int sdIndex = 0; sdIndex < differenceCounter; sdIndex++) {
            sqrtOfDifferences += (Math.pow(Double.valueOf(uniqueArray[sdIndex]) - (Double.valueOf(sum) / Double.valueOf(numberOfElements)), 2));
        }
        System.out.println("Standard deviation of your array is " + Math.sqrt((1 / Double.valueOf(numberOfElements)) * sqrtOfDifferences));


        System.out.println("------------------------------------------------");
        if (numberOfElements % 2 == 0) {
            System.out.println("The median of your array is " + (Double.valueOf(array[numberOfElements / 2] + array[numberOfElements / 2 - 1]) / 2));
        }
        else {
            System.out.println("The median of your array is " + array[(numberOfElements - 1) / 2]);
        }
        System.out.println("------------------------------------------------");
        System.out.println("The sum of all numbers in the array is " + sum);
        System.out.println("------------------------------------------------");
        System.out.println("There are " + palindromeCounter + " palindromes in your array.");
        System.out.println("------------------------------------------------");
        if (largestPalindromeExists) {
            System.out.println("The largest palindrome that is smaller than the greatest number in the array is " + largestPalindrome);
        }
        else {
            System.out.println("The largest palindrome that is smaller than the greatest number doesn't exist.");
        }
        System.out.println("------------------------------------------------");


        //Writes out the entered numbers in reverse order
        String numbersInReverseOrder = "";
        for (int reverseIndex = numberOfElements - 1; reverseIndex >= 0; reverseIndex--) {
            if (reverseIndex == numberOfElements - 1) {
                numbersInReverseOrder = String.valueOf(arrayCopy[reverseIndex]);
            }
            else {
                numbersInReverseOrder = numbersInReverseOrder + ", " + arrayCopy[reverseIndex];
            }
        }
        System.out.println("The entered numbers in reverse order are " + numbersInReverseOrder);
    }

//Sorts the given array from smallest to largest
    public static int[] bubbleSort(int [] someArray) {
        int tmp;
        for (int sortOut = 0; sortOut < someArray.length; sortOut++) {
            for (int sortIn = 0; sortIn < someArray.length - 1; sortIn++) {
                if (someArray[sortIn] > someArray[sortIn + 1]) {
                    tmp = someArray[sortIn];
                    someArray[sortIn] = someArray[sortIn + 1];
                    someArray[sortIn + 1] = tmp;
                }
            }
        }
        return someArray;
    }
}
