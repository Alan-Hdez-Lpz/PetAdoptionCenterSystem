import java.util.ArrayList;

public class Adopter {
    private String adopterId;
    private String name;
    private String phoneNumber;
    private ArrayList<Pet> adoptedPets;

    public Adopter(String adopterId, String name, String phoneNumber) {
        this.adopterId = adopterId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.adoptedPets = new ArrayList<>();
    }

    public String getAdopterId() {
        return adopterId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Pet> getAdoptedPets() {
        return adoptedPets;
    }

    public void setAdopterId(String adopterId) {
        this.adopterId = adopterId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdoptedPets(ArrayList<Pet> adoptedPets) {
        this.adoptedPets = adoptedPets;
    }
}
