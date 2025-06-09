import java.util.ArrayList;

public class PetAdoptionCenterManager {
    private ArrayList<Pet> pets;
    private ArrayList<Adopter> adopters;

    public PetAdoptionCenterManager() {
        this.pets = new ArrayList<>();
        this.adopters = new ArrayList<>();
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public ArrayList<Adopter> getAdopters() {
        return adopters;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public void setAdopters(ArrayList<Adopter> adopters) {
        this.adopters = adopters;
    }

    public void addPet(Pet pet) {
        ArrayList<Pet> pets = getPets();
        pets.add(pet);
        setPets(pets);
        System.out.println("Pet added");
    }
    public void removePet(String petId) {
        ArrayList<Pet> pets = getPets();
        boolean petRemoved = false;
        for(Pet pet : pets){
            if(pet.getPetId().equalsIgnoreCase(petId) && pet.isAvailableForAdoption()){
                petRemoved = true;
                pets.remove(pet);
                break;
            }
        }
        setPets(pets);
        if(petRemoved){
            System.out.println("Pet removed");
        } else {
            throw new PetNotFoundException("Pet not registered or already adopted");
        }
    }
    public void updatePetInfo(String petId, int field, String newValue) {
        ArrayList<Pet> pets = getPets();
        for(Pet pet : pets){
            if(pet.getPetId().equalsIgnoreCase(petId)){
                switch (field){
                    case 1:
                        if(pet.getSpecie().equalsIgnoreCase("Dog") && newValue.startsWith("DOG")){
                            pet.setPetId(newValue);
                        } else if(pet.getSpecie().equalsIgnoreCase("Cat") && newValue.startsWith("CAT")){
                            pet.setPetId(newValue);
                        } else if(pet.getSpecie().equalsIgnoreCase("Bird") && newValue.startsWith("BIRD")){
                            pet.setPetId(newValue);
                        } else {
                            throw new InvalidPetDataException("Invalid input, the pet Id needs to start with the pet specie and then a number");
                        }
                        break;
                    case 2:
                        pet.setName(newValue);
                        break;
                    case 3:
                        int newAge = Integer.parseInt(newValue);
                        if(newAge >= 0) {
                            pet.setAge(newAge);
                        } else {
                            throw new InvalidPetDataException("Age can not be negative");
                        }
                        break;
                    case 4:
                        pet.setBreed(newValue);
                        break;
                    case 5:
                        if(newValue.equalsIgnoreCase("y")){
                            pet.markAsAdopted();
                        } else {
                            pet.setAdoptionStatus(false);
                        }
                        break;
                    case 6:
                        if(newValue.equalsIgnoreCase("y")){
                            pet.vaccinate();
                        } else {
                            pet.setVaccinated(false);
                        }
                        break;
                    default:
                        break;
                }
                break;
            }
        }
        setPets(pets);
        System.out.println("Pet updated");
    }

    public Pet updatePetAdoptionStatus(String petId) {
        ArrayList<Pet> pets = getPets();
        Pet adoptedPet = null;
        for(Pet pet : pets){
            if(pet.getPetId().equalsIgnoreCase(petId)){
                if(pet.isAvailableForAdoption()){
                    pet.markAsAdopted();
                    adoptedPet = pet;
                    break;
                } else {
                    throw new PetAlreadyAdoptedException("The pet was already adopted");
                }
            }
        }
        setPets(pets);
        return adoptedPet;
    }

    public void addPetToAdopter(String adopterId, Pet adoptedPet) {
        ArrayList<Adopter> adopters = getAdopters();
        for(Adopter adopter : adopters){
            if(adopter.getAdopterId().equalsIgnoreCase(adopterId)){
                ArrayList<Pet> adoptedPets = adopter.getAdoptedPets();
                adoptedPets.add(adoptedPet);
                adopter.setAdoptedPets(adoptedPets);
                break;
            }
        }
        setAdopters(adopters);
    }

    public void registerAdopter(Adopter adopter) {
        ArrayList<Adopter> adopters = getAdopters();
        adopters.add(adopter);
        setAdopters(adopters);
        System.out.println("Adopter registered");
    }

    public void adoptPet(String adopterId, String petId) {
        if (validateAdopter(adopterId) && validatePet(petId)){
            Pet pet = updatePetAdoptionStatus(petId);
            addPetToAdopter(adopterId,pet);
            System.out.println("Pet adopted");
        }
    }
    public void displayAvailablePets(){
        for(Pet pet : getPets()){
            if(pet.isAvailableForAdoption()){
                pet.displayInfo();
            }
        }
    }
    public void displayAvailablePets(ArrayList<Pet> pets){
        for(Pet pet : pets){
            if(pet.isAvailableForAdoption()){
                pet.displayInfo();
            }
        }
    }
    public void searchPets(String species, String age, String breed, String vaccinated) {
        ArrayList<Pet> results = new ArrayList<>();

        for (Pet pet : getPets()) {
            if (species != null && !pet.getSpecie().equalsIgnoreCase(species)) {
                continue;
            }

            if (age != null && pet.getAge() != Integer.parseInt(age)) {
                continue;
            }

            if (breed != null && !pet.getPetId().equalsIgnoreCase(breed)) {
                continue;
            }

            if (vaccinated !=null && !pet.isVaccinated()) {
                continue;
            }
            results.add(pet);
        }
        displayAvailablePets(results);
    }

    public boolean validateAdopter(String adopterId){
        for(Adopter adopter : getAdopters()){
            if(adopter.getAdopterId().equalsIgnoreCase(adopterId)){
                return true;
            }
        }
        throw new AdopterNotFoundException("Adopter not registered");
    }

    public boolean validatePet(String petId){
        for(Pet pet : getPets()){
            if(pet.getPetId().equalsIgnoreCase(petId)){
                return true;
            }
        }
        throw new PetNotFoundException("Pet not registered");
    }

    public void displayAdopters(){
        for(Adopter adopter : getAdopters()){
            System.out.println("Adopter " + adopter.getAdopterId() + " : " + adopter.getName() + ", Phone number: " + adopter.getPhoneNumber());
            for (Pet pet : adopter.getAdoptedPets()){
                pet.displayInfo();
            }
        }
    }


}
