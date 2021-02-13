package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    Animal testAnimal;

    @BeforeEach
    public void setUp() {
        testAnimal = new Animal();
        testAnimal.setName("TestName");
        testAnimal.setAge(11);
        testAnimal.setBreed("Black Lab");
        testAnimal.setSpecies("Dog");
        testAnimal.setActivityLevel(Animal.ActivityLevel.HIGH_ENERGY);
        testAnimal.setDietSize(Animal.DietSize.MEDIUM_DIET);
    }

    @Test
    public void testListMedicationsEmpty(){
        assertEquals(testAnimal.listMedications(), "none.");
    }

    @Test
    public void testListMedicationsMultiple(){
        testAnimal.logMedication("test1", 2, Animal.MedicationFrequency.ONCE_DAILY);
        testAnimal.logMedication("test2", 5, Animal.MedicationFrequency.TWICE_DAILY);
        assertEquals(testAnimal.listMedications(), "test1 2mg, Once Daily. test2 5mg, Twice Daily.");
    }

    @Test
    public void testLogMedicationEmpty() {
        //check that the med set is empty
        assertEquals(0, testAnimal.medications.size());
        //add medication to the meds set
        testAnimal.logMedication("testMed1", 5, Animal.MedicationFrequency.ONCE_DAILY);
        //check that the set is now 1 larger
        assertEquals(1, testAnimal.medications.size());
        //add another medication
        testAnimal.logMedication("testMed2", 2, Animal.MedicationFrequency.TWICE_DAILY);
        //check that the set contains 2 meds
        assertEquals(2, testAnimal.medications.size());
    }


    @Test
    public void testShowCharacteristicsNoMeds() {
        //call show characteristics, check that it matches
        assertEquals("Species: Dog" + System.lineSeparator() +
                "Breed: Black Lab" + System.lineSeparator() +
                "Name: TestName" + System.lineSeparator() +
                "Age: 11" + System.lineSeparator() +
                "Diet Size: Medium" + System.lineSeparator() +
                "Activity Level: High Energy" + System.lineSeparator() +
                "Medications: none.", testAnimal.showCharacteristics());
    }

    @Test
    public void testShowCharacteristicsMultipleMeds() {
        //log meds
        testAnimal.logMedication("testMed1", 5, Animal.MedicationFrequency.ONCE_DAILY);
        testAnimal.logMedication("testMed2", 2, Animal.MedicationFrequency.TWICE_DAILY);
        testAnimal.showCharacteristics();
        //call show characteristics, check that it matches
        assertEquals("Species: Dog" + System.lineSeparator() +
                "Breed: Black Lab" + System.lineSeparator() +
                "Name: TestName" + System.lineSeparator() +
                "Age: 11" + System.lineSeparator() +
                "Diet Size: Medium" + System.lineSeparator() +
                "Activity Level: High Energy" + System.lineSeparator() +
                "Medications: testMed1 5mg, Once Daily. testMed2 2mg, Twice Daily.", testAnimal.showCharacteristics());
    }

    @Test
    public void testConvertActivityStyle(){
        //check that calling each of the styles works
        assertEquals(testAnimal.convertActivityLevel(Animal.ActivityLevel.IMMOBILIZED), "Immobilized");
        assertEquals(testAnimal.convertActivityLevel(Animal.ActivityLevel.RECOVERING), "Recovering");
        assertEquals(testAnimal.convertActivityLevel(Animal.ActivityLevel.LOW_ENERGY), "Low Energy");
        assertEquals(testAnimal.convertActivityLevel(Animal.ActivityLevel.HIGH_ENERGY), "High Energy");
    }

    @Test
    public void testConvertDietSize(){
        //check that calling each of the styles works
        assertEquals(testAnimal.convertDietSize(Animal.DietSize.SMALL_DIET), "Small");
        assertEquals(testAnimal.convertDietSize(Animal.DietSize.MEDIUM_DIET), "Medium");
        assertEquals(testAnimal.convertDietSize(Animal.DietSize.LARGE_DIET), "Large");
    }

    @Test
    public void testConvertMedicationFrequency(){
        //check that calling each of the styles works
        assertEquals(testAnimal.convertMedicationFrequency(Animal.MedicationFrequency.ONCE_DAILY), "Once Daily");
        assertEquals(testAnimal.convertMedicationFrequency(Animal.MedicationFrequency.TWICE_DAILY), "Twice Daily");
    }
}
