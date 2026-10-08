package final_project;

public class Movie {
	    private String title;
	    private String actor;
	    private int year;
	    private String genre;

	    // Default constructor
	    public Movie() {
	        this.title = "";
	        this.actor = "";
	        this.year = 0;
	        this.genre = "";
	    }

	    // Overloaded constructor
	    public Movie(String title, String actor, int year, String genre) {
	        this.title = title;
	        this.actor = actor;
	        this.year = year;
	        this.genre = genre;
	    }

	    // Getters and setters
	    public String getTitle() { return title; }
	    public void setTitle(String title) { this.title = title; }

	    public String getActor() { return actor; }
	    public void setActor(String actor) { this.actor = actor; }

	    public int getYear() { return year; }
	    public void setYear(int year) { this.year = year; }

	    public String getGenre() { return genre; }
	    public void setGenre(String genre) { this.genre = genre; }

	    
	    public String toString() {
	        return String.format("%-25s %-20s %-6d %-15s",
	                title, actor, year, genre);
	    }
	}


