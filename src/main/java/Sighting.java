import org.sql2o.Connection;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Sighting {
    private int id;
    private int animalId;
    private int rangerId;
    private int locationId;
    private Timestamp date;

    public Sighting(int animalId, int rangerId, int locationId){
        this.animalId = animalId;
        this.rangerId = rangerId;
        this.locationId = locationId;
    }

    public int getId() {
        return id;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getRangerId() {
        return rangerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (animalid, rangerid, locationid, date) VALUES(:animalid, :rangerid, " +
                    ":locationid, now())";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animalid", this.animalId)
                    .addParameter("rangerid", this.rangerId)
                    .addParameter("locationid", this.locationId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all(){

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
        }
    }

    @Override
    public boolean equals (Object otherSighting){
        if (!(otherSighting instanceof Sighting)){
            return false;
        }else{
            Sighting sighting =(Sighting) otherSighting;
            return this.getId() == sighting.getId()&&
                    this.getAnimalId()==sighting.getAnimalId() &&
                    this.getLocationId()==sighting.getLocationId()&&
                    this.getRangerId()==sighting.getRangerId();
        }
    }

    public void update(int animalId, int locationId, int rangerId){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE sightings SET animalId = :animalId, locationId = :locationId, rangerId = :rangerId, " +
                    "date = now()" +
                    " WHERE id" +
                    " = " +
                    ":id";
            con.createQuery(sql)
                    .addParameter("animalId", animalId)
                    .addParameter("locationId", locationId)
                    .addParameter("rangerId", rangerId)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
