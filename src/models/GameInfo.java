package models;

public class GameInfo {
    private int id;
    private String gameName;
    private String genre;
    private String length;

    public GameInfo(int id, String gameName, String genre, String length) {
        this.id = id;
        this.gameName = gameName;
        this.genre = genre;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return gameName;
    }

    public void setName(String gameName) {
        this.gameName = gameName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString(){
        return "The game with an id of " + id + " and called " + gameName  + " Genre: " + genre + " Length: " + length + ".";
    }


}
