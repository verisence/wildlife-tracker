import java.sql.Timestamp;

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

}
