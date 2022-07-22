import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {
       int numOfMovies = 0;
       int randomMovie = 0;
       String selectedMovie = "";
       StringBuilder movies = new StringBuilder();

       File movieFile = new File("movies.txt");
       Scanner scanner = new Scanner(movieFile);

       while (scanner.hasNextLine()) {
           String line = scanner.nextLine();

           movies.append(line).append("\n");
           numOfMovies++;
       }
        randomMovie = (int) (Math.random() * numOfMovies);


        try (Stream<String> lines = Files.lines(Paths.get("movies.txt"))) {
            selectedMovie = lines.skip(randomMovie).findFirst().get();
            System.out.println(selectedMovie);
        }
        Game game = new Game();
        game.Start(selectedMovie);
    }
}

