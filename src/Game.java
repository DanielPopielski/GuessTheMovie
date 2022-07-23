import java.util.*;
import java.util.stream.Collectors;

public class Game {
    public String hiddenMovie = "";
    char letter;

    public String fixedMovie;

    public int numberOfGoodLetters;

    public boolean guessedLetter;

    public boolean goodLetter = false;

    String changeCharToString;
    String input;

    ArrayList<String> letters = new ArrayList<String>();

    public ArrayList<Character> lettersOfMovie = new ArrayList<Character>();
    public ArrayList<Integer> numberOfGuessedLetter = new ArrayList<>();

    public ArrayList<Character> lettersOfHiddenMovie = new ArrayList<Character>();

    public void Start(String randomStringMovie) {
        hiddenMovie = randomStringMovie.replaceAll("[a-zA-Z]", "_");
        System.out.println(hiddenMovie);

        for (int i = 0; i<randomStringMovie.length(); i++) {
            lettersOfMovie.add(randomStringMovie.charAt(i));
        }

        for (int i =0; i<randomStringMovie.length(); i++) {
            lettersOfHiddenMovie.add(hiddenMovie.charAt(i));
        }

        for (int loops = 1; loops<10; loops++) {
            System.out.println("Guess a letter: ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            letter = input.charAt(0);
            changeCharToString = Character.toString(letter);
            int size = letters.size();


            if (letters.contains(changeCharToString)) {
                guessedLetter = true;
                System.out.println("You have already guessed this letter");
            } else {
                guessedLetter = false;
                letters.add(changeCharToString);
            }


            if (!guessedLetter) {
                for (int i = 0; i < randomStringMovie.length(); i++) {
                    if (letter == lettersOfMovie.get(i)) {
                        numberOfGuessedLetter.add(i);
                        numberOfGoodLetters++;
                    }
                }


                if (numberOfGoodLetters >= 1) {
                    loops--;
                }

                for (int j = 0; j<numberOfGuessedLetter.size(); j++) {
                    lettersOfHiddenMovie.set(numberOfGuessedLetter.get(j), letter);
                }

                Iterator itr = numberOfGuessedLetter.iterator();

                while (itr.hasNext()) {
                    int x = (Integer)itr.next();
                    if (x>=0) {
                    itr.remove();
                        }
                    }

                fixedMovie = lettersOfHiddenMovie.stream().map(Object::toString).collect(Collectors.joining(""));
                System.out.println("try: " + loops);
                System.out.println("Your typed letters: " + letters);
                System.out.println(fixedMovie);
                numberOfGoodLetters = 0;
                } else {
                    System.out.println("Please enter a new letter");
                    loops--;
                }

            if (fixedMovie.equals(randomStringMovie)) {
                    System.out.println("You have guessed a movie!");
                    break;
            } else if (loops == 10) {
                    System.out.println("Too bad, you did not guess a movie.");
                    System.out.println("Name of this movie was " + randomStringMovie + ".");
            } else {
                continue;
            }
        }
    }
}
