public abstract class Pet implements Adoptable, Vaccinable {
    protected String petId;
    protected String name;
    protected String specie;
    protected int age;
    protected String breed;
    protected boolean adoptionStatus;
    protected boolean vaccinated;

    public String getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public boolean getAdoptionStatus(){
        return adoptionStatus;
    }

    public boolean getVaccinated(){
        return vaccinated;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAdoptionStatus(boolean adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public abstract void displayInfo();

}
