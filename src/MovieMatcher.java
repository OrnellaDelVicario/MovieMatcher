import java.util.*;

public class MovieMatcher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Movie[] movies = {
                new Movie("Alien (1979)", 5),
                new Movie("Aliens (1986)", 5),
                new Movie("Alien Resurrection (1997)", 2),
                new Movie("Prometheus (2012)", 4),
                new Movie("Alien: Covenant (2017)", 4)
        };

        System.out.println("\n👾 Welcome to the Alien Movie Finder System!");
        System.out.println("\nRate each film from 1 to 5 aliens instead of stars! 👽👽👽");
        int rating = scanner.nextInt();

        List<Movie> matchedMovies = findMoviesByRating(movies, rating);
        if (matchedMovies.isEmpty()) {
            System.out.println("No aliens detected through the telescope. 🔭");
        } else {
            System.out.println("👽 Alien franchise movies with " + rating + " alien(s):");
            for (Movie movie : matchedMovies) {
                System.out.println(" 🛸 " + movie.title);
            }
        }
    }

    public static List<Movie> findMoviesByRating(Movie[] movies, int rating) {
        List<Movie> result = new ArrayList<>();
        int low = 0, high = movies.length - 1;

        // Asegúrate de que el arreglo esté ordenado
        Arrays.sort(movies, Comparator.comparingInt(movie -> movie.rating));

        while (low <= high) {
            int mid = (low + high) / 2;
            if (movies[mid].rating == rating) {
                // Expansión izquierda
                int left = mid;
                while (left >= 0 && movies[left].rating == rating) {
                    left--;
                }
                left++;

                // Expansión derecha
                int right = mid;
                while (right < movies.length && movies[right].rating == rating) {
                    right++;
                }
                right--;

                for (int i = left; i <= right; i++) {
                    result.add(movies[i]);
                }

                break;
            } else if (movies[mid].rating < rating) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }
}
