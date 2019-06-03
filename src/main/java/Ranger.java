import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Ranger {
    private int id;
    private String name;
    private int badgeNumber;

    public Ranger(String name, int badgeNumber) {
        this.name = name;
        this.badgeNumber = badgeNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    @Override
    public boolean equals(Object otherObject) {
        if(!(otherObject instanceof Ranger))
            return false;
        else {
            Ranger comparisonObj = (Ranger) otherObject;
            return this.id == comparisonObj.getId() && this.name.equals(comparisonObj.getName()) && this.badgeNumber==
                    comparisonObj.getBadgeNumber();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO rangers (name, badgenumber) VALUES(:name, :badgenumber)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("badgenumber", this.badgeNumber)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Ranger> all(){

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM rangers")
                    .executeAndFetch(Ranger.class);
        }
    }

    public static Ranger find(int id){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM rangers WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
        }
    }

    public void update(String name, int badgeNumber){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE rangers SET name = :name, badgenumber = :badgenumber WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("badgenumber", badgeNumber)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM rangers WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
