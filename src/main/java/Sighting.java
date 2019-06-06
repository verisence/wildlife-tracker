import org.sql2o.Connection;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;
import java.util.Date;

public class Sighting {
    private int id;
    private int animalId;
    private int rangerId;
    private int locationId;
    private Timestamp date;
    private String animalType;

    public Sighting(int animalId, int rangerId, int locationId, String animalType){
        this.animalId = animalId;
        this.rangerId = rangerId;
        this.locationId = locationId;
        this.date = new Timestamp(new Date().getTime());
        this.animalType = animalType;
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

    public String getDate(){
        return DateFormat.getDateInstance().format(this.date);
    }

    public String getAnimalType() {
        return this.animalType;
    }

    public String getLocationName() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT name FROM locations WHERE id=:id")
                    .addParameter("id", this.locationId)
                    .executeAndFetchFirst(String.class);
        }
    }

    public String getAnimalName() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT name FROM animals WHERE id=:id AND type=:type")
                    .addParameter("id", this.animalId)
                    .executeAndFetchFirst(String.class);
        }
    }

    public String getRangerName() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT name FROM rangers WHERE id=:id")
                    .addParameter("id", this.rangerId)
                    .executeAndFetchFirst(String.class);
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (animalId, rangerId, locationId, date, animalType) VALUES (:animalId," +
                    " " +
                    ":rangerId, :locationId, :date, :animalType)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("locationId", this.locationId)
                    .addParameter("date", this.date)
                    .addParameter("animalType", this.animalType)
                    .throwOnMappingFailure(false)
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

    public static List<Sighting> allAnimals() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE animalType=:type")
                    .addParameter("type", Animal.ANIMAL_TYPE)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static List<Sighting> allEndangered() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE animalType=:type")
                    .addParameter("type", EndangeredAnimal.ANIMAL_TYPE)
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

//    public void update(int animalId, int locationId, int rangerId, String animalType){
//        try(Connection con = DB.sql2o.open()){
//            String sql = "UPDATE sightings SET animalId = :animalId, rangerId = :rangerId, locationId = :locationId, " +
//                    " animalType = :animalType  WHERE id" +
//                    " = " +
//                    ":id";
//            con.createQuery(sql)
//                    .addParameter("animalId", animalId)
//                    .addParameter("rangerId", rangerId)
//                    .addParameter("locationId", locationId)
//                    .addParameter("animalType", animalType)
//                    .throwOnMappingFailure(false)
//                    .executeUpdate();
//        }
//    }

    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
