package models;

public class GameInfo {
    private final int id;
    private String name;
    private String genre;
    private String length;

    public GameInfo(int id, String name, String genre, String length) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.length = length;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String gameName) {
        this.name = gameName;
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
        return " ID: " + id + " Name: " + name  + " Genre: " + genre + " Length: " + length + ".";
    }




}
