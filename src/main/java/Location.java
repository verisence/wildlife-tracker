import org.sql2o.Connection;

import java.util.List;

public class Location {
    private int id;
    private String name;

    public Location(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object otherObject) {
        if(!(otherObject instanceof Location))
            return false;
        else {
            Location comparisonObj = (Location) otherObject;
            return this.id == comparisonObj.getId() && this.name.equals(comparisonObj.getName());
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO locations (name) VALUES(:name)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Location> all(){

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM locations")
                    .executeAndFetch(Location.class);
        }
    }

    public static Location find(int id){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM locations WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
        }
    }

    public void update(String name){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE locations SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM locations WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
