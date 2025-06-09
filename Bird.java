public class Bird extends Pet{

    public Bird(String petId, String name, int age, String breed, boolean vaccinated) {
        this.petId = petId;
        this.name = name;
        this.specie = "Bird";
        this.age = age;
        this.breed = breed;
        this.adoptionStatus = false;
        this.vaccinated = vaccinated;
    }

    @Override
    public boolean isAvailableForAdoption() {
        return !getAdoptionStatus();
    }

    @Override
    public void markAsAdopted() {
        setAdoptionStatus(true);
    }

    @Override
    public void vaccinate() {
        setVaccinated(true);
    }

    @Override
    public boolean isVaccinated() {
        return getVaccinated();
    }

    @Override
    public void displayInfo() {
        System.out.println("Bird " + getPetId() + " : " + getName() + " Breed: " + getBreed() + ", Age: " + getAge() + ", Vaccinated: " + getVaccinated() +
                ", Adopted: " + getAdoptionStatus());
    }
}
