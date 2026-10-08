package final_project;
import java.io.*;
import java.util.*;

public class MovieStore {
	    private ArrayList<Movie> movies = new ArrayList<>();
	    private String fileName;

	    public MovieStore(String fileName) {
	        this.fileName = fileName;
	        loadFromFile();
	    }

	    // Add movie
	    public void addMovie(Movie m) {
	        movies.add(m);
	    }

	    // Remove movie by title
	    public boolean removeMovieByTitle(String title) {
	        for (int i = 0; i < movies.size(); i++) {
	            if (movies.get(i).getTitle().equalsIgnoreCase(title)) {
	                movies.remove(i);
	                return true;
	            }
	        }
	        return false;
	    }

	    // Display all movies
	    public void displayAllMovies() {
	        if (movies.isEmpty()) {
	            System.out.println("No movies available.");
	            return;
	        }

	        System.out.printf("%-25s %-20s %-6s %-15s\n",
	                "Title", "Actor/Actress", "Year", "Genre");
	        System.out.println("------------------------------------------------------------------");

	        for (Movie m : movies) {
	            System.out.println(m);
	        }
	    }

	    // Search methods
	    public ArrayList<Movie> searchByTitle(String title) {
	        ArrayList<Movie> results = new ArrayList<>();
	        for (Movie m : movies) {
	            if (m.getTitle().equalsIgnoreCase(title)) {
	                results.add(m);
	            }
	        }
	        return results;
	    }

	    public ArrayList<Movie> searchByActor(String actor) {
	        ArrayList<Movie> results = new ArrayList<>();
	        for (Movie m : movies) {
	            if (m.getActor().equalsIgnoreCase(actor)) {
	                results.add(m);
	            }
	        }
	        return results;
	    }

	    public ArrayList<Movie> searchByYear(int year) {
	        ArrayList<Movie> results = new ArrayList<>();
	        for (Movie m : movies) {
	            if (m.getYear() == year) {
	                results.add(m);
	            }
	        }
	        return results;
	    }

	    public ArrayList<Movie> searchByGenre(String genre) {
	        ArrayList<Movie> results = new ArrayList<>();
	        for (Movie m : movies) {
	            if (m.getGenre().equalsIgnoreCase(genre)) {
	                results.add(m);
	            }
	        }
	        return results;
	    }

	    // Sorting
	    public void sortByTitle(boolean ascending) {
	        movies.sort((a, b) -> ascending ?
	                a.getTitle().compareToIgnoreCase(b.getTitle()) :
	                b.getTitle().compareToIgnoreCase(a.getTitle()));
	    }

	    public void sortByActor(boolean ascending) {
	        movies.sort((a, b) -> ascending ?
	                a.getActor().compareToIgnoreCase(b.getActor()) :
	                b.getActor().compareToIgnoreCase(a.getActor()));
	    }

	    public void sortByGenre(boolean ascending) {
	        movies.sort((a, b) -> ascending ?
	                a.getGenre().compareToIgnoreCase(b.getGenre()) :
	                b.getGenre().compareToIgnoreCase(a.getGenre()));
	    }

	    // Load from file
	    public void loadFromFile() {
	        try {
	            File file = new File(fileName);
	            if (!file.exists()) {
	                System.out.println("Movie file not found. Creating new file.");
	                file.createNewFile();
	                return;
	            }

	            Scanner input = new Scanner(file);

	            while (input.hasNextLine()) {
	                String line = input.nextLine();
	                String[] parts = line.split(";");

	                if (parts.length != 4) continue;

	                try {
	                    String title = parts[0];
	                    String actor = parts[1];
	                    int year = Integer.parseInt(parts[2]);
	                    String genre = parts[3];

	                    movies.add(new Movie(title, actor, year, genre));
	                } catch (NumberFormatException e) {
	                    System.out.println("Skipping invalid line: " + line);
	                }
	            }

	            input.close();
	        } catch (IOException e) {
	            System.out.println("Error loading file.");
	        }
	    }

	    // Save to file
	    public void saveToFile() {
	        try {
	            PrintWriter out = new PrintWriter(new FileWriter(fileName));

	            for (Movie m : movies) {
	                out.println(m.getTitle() + ";" +
	                        m.getActor() + ";" +
	                        m.getYear() + ";" +
	                        m.getGenre());
	            }

	            out.close();
	            System.out.println("Movies saved successfully.");

	        } catch (IOException e) {
	            System.out.println("Error saving file.");
	        }
	    }
	}


