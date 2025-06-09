import java.util.ArrayList;
import java.util.Scanner;

public class PetAdoptionCenterSystem {
    public static void main(String[] args){
        PetAdoptionCenterManager petAdoptionCenterManager = new PetAdoptionCenterManager();
        Scanner scanner = new Scanner(System.in);
        boolean petAdoptionCenterSystemValidation;
        Pet pet;
        Adopter adopter;
        try {
            do{

                System.out.println("Welcome to the Pet Adoption Center System!\n" +
                        "\n" +
                        "Please select an operation:\n" +
                        "1. Add a pet.\n" +
                        "2. Display available pets.\n" +
                        "3. Search available pets by specie, age, breed and/or vaccinated\n" +
                        "4. Update pet information\n" +
                        "5. Remove pet\n" +
                        "6. Register an adopter\n" +
                        "7. Display adopters\n" +
                        "8. Adopt a pet");
                int operation = scanner.nextInt();
                switch (operation){
                    case 1:
                        System.out.println("Insert pet name:");
                        String name = scanner.next();
                        System.out.println("Insert age:");
                        int age = scanner.nextInt();
                        if(age < 0) {
                            throw new InvalidPetDataException("Age can not be negative");
                        }
                        System.out.println("Insert breed:");
                        String breed = scanner.next();
                        System.out.println("Is the pet vaccinated? (y/n)");
                        String vaccinatedValidation = scanner.next();
                        boolean vaccinated;
                        if(vaccinatedValidation.equalsIgnoreCase("y")){
                            vaccinated = true;
                        } else if (vaccinatedValidation.equalsIgnoreCase("n")){
                            vaccinated = false;
                        } else {
                            throw new IncorrectInputException("Invalid input, just 'y' for 'yes' or 'n' for 'no'");
                        }
                        System.out.println("Insert the specie:");
                        String specie = scanner.next();
                        String petId;
                        if(specie.equalsIgnoreCase("Dog")){
                            System.out.println("Insert pet ID");
                            petId = scanner.next();
                            if(petId.startsWith("DOG")){
                                pet = new Dog(petId, name, age, breed,vaccinated);
                            } else {
                                throw new InvalidPetDataException("Invalid input, the pet Id needs to start with the pet specie and then a number");
                            }
                        } else if(specie.equalsIgnoreCase("Cat")){
                            System.out.println("Insert pet ID");
                            petId = scanner.next();
                            if(petId.startsWith("CAT")){
                                pet = new Cat(petId, name, age, breed,vaccinated);
                            } else {
                                throw new InvalidPetDataException("Invalid input, the pet Id needs to start with the pet specie and then a number");
                            }
                        } else if(specie.equalsIgnoreCase("Bird")){
                            System.out.println("Insert pet ID");
                            petId = scanner.next();
                            if(petId.startsWith("BIRD")){
                                pet = new Bird(petId, name, age, breed,vaccinated);
                            } else {
                                throw new InvalidPetDataException("Invalid input, the pet Id needs to start with the pet specie and then a number");
                            }
                        } else {
                            throw new IncorrectInputException("Invalid input, the center just accept Dogs, Cat or Birds");
                        }
                        petAdoptionCenterManager.addPet(pet);
                        break;
                    case 2:
                        petAdoptionCenterManager.displayAvailablePets();
                        break;
                    case 3:
                        System.out.println("Do you want to search by specie? (y/n)");
                        String answerValidation = scanner.next();
                        String specieToSearch;
                        if(answerValidation.equalsIgnoreCase("y")){
                            System.out.println("Insert specie:");
                            specieToSearch = scanner.next();
                            if(!(specieToSearch.equalsIgnoreCase("Dog") || specieToSearch.equalsIgnoreCase("Cat") ||specieToSearch.equalsIgnoreCase("Bird"))){
                                throw new InvalidPetDataException("Invalid input, the center just accept Dogs, Cat or Birds");
                            }
                        } else if(answerValidation.equalsIgnoreCase("n")){
                            specieToSearch = null;
                        } else {
                            throw new IncorrectInputException("Invalid input");
                        }
                        System.out.println("Do you want to search by age? (y/n)");
                        answerValidation = scanner.next();
                        String ageToSearch;
                        if(answerValidation.equalsIgnoreCase("y")){
                            System.out.println("Insert age:");
                            ageToSearch = scanner.next();
                            if(Integer.parseInt(ageToSearch) < 0){
                                throw new InvalidPetDataException("Invalid input, the age can not be negative");
                            }
                        } else if(answerValidation.equalsIgnoreCase("n")){
                            ageToSearch = null;
                        } else {
                            throw new IncorrectInputException("Invalid input");
                        }
                        System.out.println("Do you want to search by breed? (y/n)");
                        answerValidation = scanner.next();
                        String breedToSearch;
                        if(answerValidation.equalsIgnoreCase("y")){
                            System.out.println("Insert breed:");
                            breedToSearch = scanner.next();
                        } else if(answerValidation.equalsIgnoreCase("n")){
                            breedToSearch = null;
                        } else {
                            throw new IncorrectInputException("Invalid input");
                        }
                        System.out.println("Do you want to search vaccinated pets? (y/n)");
                        answerValidation = scanner.next();
                        String vaccinatedToSearch;
                        if(answerValidation.equalsIgnoreCase("y")){
                            vaccinatedToSearch = answerValidation;
                        } else if(answerValidation.equalsIgnoreCase("n")){
                            vaccinatedToSearch = null;
                        } else {
                            throw new IncorrectInputException("Invalid input");
                        }
                        petAdoptionCenterManager.searchPets(specieToSearch, ageToSearch, breedToSearch,vaccinatedToSearch);
                        break;
                    case 4:
                        System.out.println("Insert pet Id of the pet that you want to update information:");
                        String petIdToUpdate = scanner.next();
                        if(petAdoptionCenterManager.validatePet(petIdToUpdate)){
                            System.out.println("Select the field that you want to update\n" +
                                    "1. Pet Id\n" +
                                    "2. Name\n" +
                                    "3. Age\n" +
                                    "4. Breed\n" +
                                    "5. Adoption status\n" +
                                    "6. Vaccinated status");
                            int field = scanner.nextInt();
                            String newValue;
                            if(field > 0 && field <=7){
                                if(field==6){
                                    System.out.println("Is the pet adopted? (y/n)");
                                    newValue = scanner.next();
                                    if (!(newValue.equalsIgnoreCase("y") || newValue.equalsIgnoreCase("n"))) {
                                        throw new IncorrectInputException("Invalid input");
                                    }
                                } else if(field==7){
                                    System.out.println("Is the pet vaccinated? (y/n)");
                                    newValue = scanner.next();
                                    if (!(newValue.equalsIgnoreCase("y") || newValue.equalsIgnoreCase("n"))) {
                                        throw new IncorrectInputException("Invalid input");
                                    }
                                } else {
                                    System.out.println("Insert new value");
                                    newValue = scanner.next();
                                }
                                petAdoptionCenterManager.updatePetInfo(petIdToUpdate,field,newValue);
                            } else {
                                throw new IncorrectInputException("Incorrect input, insert just numbers from 1 to 7");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Insert pet ID");
                        String petIdToRemove = scanner.next();
                        if(petIdToRemove.startsWith("DOG") || petIdToRemove.startsWith("CAT") ||petIdToRemove.startsWith("BIRD")){
                            if (petAdoptionCenterManager.validatePet(petIdToRemove)){
                                petAdoptionCenterManager.removePet(petIdToRemove);
                            }
                        } else {
                            throw new InvalidPetDataException("Invalid input, the pet Id needs to start with the pet specie in upperLower (DOG,CAT or BIRD)");
                        }
                        break;
                    case 6:
                        System.out.println("Insert name");
                        String adopterName = scanner.next();
                        System.out.println("Insert phone number");
                        String phoneNumber = scanner.next();
                        if (phoneNumber.length() != 10){
                            throw new IncorrectInputException("The phone number have 10 digits not " + phoneNumber.length() + " digits");
                        }
                        System.out.println("Insert adopter Id");
                        String adopterId = scanner.next();
                        adopter = new Adopter(adopterId,adopterName,phoneNumber);
                        petAdoptionCenterManager.registerAdopter(adopter);
                        break;
                    case 7:
                        petAdoptionCenterManager.displayAdopters();
                        break;
                    case 8:
                        System.out.println("Insert adopter Id");
                        String adopterIdToAdopt = scanner.next();
                        System.out.println("Insert pet ID");
                        String petIdToAdopt = scanner.next();
                        if(petIdToAdopt.startsWith("DOG") || petIdToAdopt.startsWith("CAT") ||petIdToAdopt.startsWith("BIRD")){
                            petAdoptionCenterManager.adoptPet(adopterIdToAdopt,petIdToAdopt);
                        } else {
                            throw new InvalidPetDataException("Invalid input, the pet Id needs to start with the pet specie in upperLower (DOG,CAT or BIRD)");
                        }
                        break;
                    default:
                        throw new IncorrectInputException("Incorrect input, insert just numbers from 1 to 7");
                }
                System.out.println("Do you want to make another operation? (y/n)");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("y")) {
                    petAdoptionCenterSystemValidation = true;
                } else if (answer.equalsIgnoreCase("n")) {
                    petAdoptionCenterSystemValidation = false;
                } else {
                    throw new IncorrectInputException("Invalid input");
                }
            }while (petAdoptionCenterSystemValidation);
        } catch (InvalidPetDataException | IncorrectInputException | AdopterNotFoundException | PetAlreadyAdoptedException | PetNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
