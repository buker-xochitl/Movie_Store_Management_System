package final_project;
import java.util.*;

public class MovieStoreApp {

    private static final String MANAGER_PASSWORD = "admin123";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MovieStore store = new MovieStore("movies.txt");

        int choice;

        do {
            System.out.println("\n=== MOVIE STORE ===");
            System.out.println("1. Manager Mode");
            System.out.println("2. User Mode");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = getInt(input);

            switch (choice) {
                case 1:
                    managerLogin(store, input);
                    break;
                case 2:
                    userMenu(store, input);
                    break;
                case 3:
                    store.saveToFile();
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 3);
    }

    // Manager login
    public static void managerLogin(MovieStore store, Scanner input) {
        System.out.print("Enter password: ");
        String pass = input.nextLine();

        if (pass.equals(MANAGER_PASSWORD)) {
            managerMenu(store, input);
        } else {
            System.out.println("Incorrect password.");
        }
    }

    // Manager menu
    public static void managerMenu(MovieStore store, Scanner input) {
        int choice;

        do {
            System.out.println("\n=== MANAGER MENU ===");
            System.out.println("1. Add Movie");
            System.out.println("2. Remove Movie");
            System.out.println("3. Display All Movies");
            System.out.println("4. Search Movies");
            System.out.println("5. Sort Movies");
            System.out.println("6. Save & Return");
            System.out.print("Enter choice: ");

            choice = getInt(input);

            switch (choice) {
                case 1:
                    addMovie(store, input);
                    break;
                case 2:
                    removeMovie(store, input);
                    break;
                case 3:
                    store.displayAllMovies();
                    break;
                case 4:
                    searchMenu(store, input);
                    break;
                case 5:
                    sortMenu(store, input);
                    break;
                case 6:
                    store.saveToFile();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (true);
    }

    // User menu
    public static void userMenu(MovieStore store, Scanner input) {
        int choice;

        do {
            System.out.println("\n=== USER MENU ===");
            System.out.println("1. Display All Movies");
            System.out.println("2. Search Movies");
            System.out.println("3. Sort Movies");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            choice = getInt(input);

            switch (choice) {
                case 1:
                    store.displayAllMovies();
                    break;
                case 2:
                    searchMenu(store, input);
                    break;
                case 3:
                    sortMenu(store, input);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (true);
    }

    // Add movie
    public static void addMovie(MovieStore store, Scanner input) {
        System.out.print("Enter title: ");
        String title = input.nextLine();

        System.out.print("Enter actor/actress: ");
        String actor = input.nextLine();

        System.out.print("Enter year: ");
        int year = getInt(input);

        System.out.print("Enter genre: ");
        String genre = input.nextLine();

        store.addMovie(new Movie(title, actor, year, genre));
        System.out.println("Movie added.");
    }

    // Remove movie
    public static void removeMovie(MovieStore store, Scanner input) {
        System.out.print("Enter title to remove: ");
        String title = input.nextLine();

        if (store.removeMovieByTitle(title)) {
            System.out.println("Movie removed.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    // Search menu
    public static void searchMenu(MovieStore store, Scanner input) {
        System.out.println("\nSearch by:");
        System.out.println("1. Title");
        System.out.println("2. Actor");
        System.out.println("3. Year");
        System.out.println("4. Genre");
        System.out.print("Enter choice: ");

        int choice = getInt(input);
        ArrayList<Movie> results = new ArrayList<>();

        switch (choice) {
            case 1:
                System.out.print("Enter title: ");
                results = store.searchByTitle(input.nextLine());
                break;
            case 2:
                System.out.print("Enter actor: ");
                results = store.searchByActor(input.nextLine());
                break;
            case 3:
                System.out.print("Enter year: ");
                results = store.searchByYear(getInt(input));
                break;
            case 4:
                System.out.print("Enter genre: ");
                results = store.searchByGenre(input.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            for (Movie m : results) {
                System.out.println(m);
            }
        }
    }

    // Sort menu
    public static void sortMenu(MovieStore store, Scanner input) {
        System.out.println("\nSort by:");
        System.out.println("1. Title");
        System.out.println("2. Actor");
        System.out.println("3. Genre");
        System.out.print("Enter choice: ");

        int field = getInt(input);

        System.out.println("Order:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.print("Enter choice: ");

        int order = getInt(input);
        boolean ascending = (order == 1);

        switch (field) {
            case 1:
                store.sortByTitle(ascending);
                break;
            case 2:
                store.sortByActor(ascending);
                break;
            case 3:
                store.sortByGenre(ascending);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Movies sorted.");
        store.displayAllMovies();
    }

    // Safe integer input
    public static int getInt(Scanner input) {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}
