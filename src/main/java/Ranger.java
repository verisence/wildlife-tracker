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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return getId() == ranger.getId() &&
                getBadgeNumber() == ranger.getBadgeNumber() &&
                getName().equals(ranger.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBadgeNumber());
    }
}
