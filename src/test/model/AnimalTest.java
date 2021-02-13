package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {
    Animal tintin;

    @BeforeEach
    public void setUp() {
        tintin = new Animal();
        tintin.setName("Tintin");
        tintin.setAge(11);
        tintin.setBreed("Black Lab");
        tintin.setSpecies("Dog");
        tintin.setActivityLevel(Animal.ActivityLevel.HIGH_ENERGY);
        tintin.setDietSize(Animal.DietSize.MEDIUM_DIET);
    }

    public String daySchedule(String dayOfWeek, String medsAM, String medsPM) {
        return dayOfWeek + ":" + System.lineSeparator() +
                "AM: Feed dog food of medium size, " +
                "meds to include: " + medsAM + "." +
                "Exercise for 2 hours" + System.lineSeparator() +
                "PM: Feed dog food of medium size, " +
                "meds to include:  " + medsPM + "." + System.lineSeparator();
    }

    @Test
    public void testListMedicationsEmpty(){
        assertTrue("none.".equals(tintin.listMedications()));
    }

    @Test
    public void testListMedicationsMultiple(){
        tintin.logMedication("test1", 2, Animal.MedicationFrequency.ONCE_DAILY);
        tintin.logMedication("test2", 5, Animal.MedicationFrequency.TWICE_DAILY);
        assertTrue("test1 2mg, Once Daily. test2 5mg, Twice Daily.".equals(tintin.listMedications()));
    }

    @Test
    public void testLogMedicationEmpty() {
        //check that the med set is empty
        assertEquals(0, tintin.medications.size());
        //add medication to the meds set
        tintin.logMedication("testMed1", 5, Animal.MedicationFrequency.ONCE_DAILY);
        //check that the set is now 1 larger
        assertEquals(1, tintin.medications.size());
        //add another medication
        tintin.logMedication("testMed2", 2, Animal.MedicationFrequency.TWICE_DAILY);
        //check that the set contains 2 meds
        assertEquals(2, tintin.medications.size());
    }


    @Test
    public void testShowCharacteristicsNoMeds() {
        //call show characteristics, check that it matches
        assertTrue(tintin.showCharacteristics().equals(
                        "Species: Dog" + System.lineSeparator() +
                        "Breed: Black Lab" + System.lineSeparator() +
                        "Name: Tintin" + System.lineSeparator() +
                        "Age: 11" + System.lineSeparator() +
                        "Diet Size: Medium" + System.lineSeparator() +
                        "Activity Level: High Energy" + System.lineSeparator() +
                        "Medications: none."
        ));
    }

    @Test
    public void testShowCharacteristicsMultipleMeds() {
        //log meds
        tintin.logMedication("testMed1", 5, Animal.MedicationFrequency.ONCE_DAILY);
        tintin.logMedication("testMed2", 2, Animal.MedicationFrequency.TWICE_DAILY);
        tintin.showCharacteristics();
        //call show characteristics, check that it matches
        assertTrue(tintin.showCharacteristics().equals(
                "Species: Dog" + System.lineSeparator() +
                        "Breed: Black Lab" + System.lineSeparator() +
                        "Name: Tintin" + System.lineSeparator() +
                        "Age: 11" + System.lineSeparator() +
                        "Diet Size: Medium" + System.lineSeparator() +
                        "Activity Level: High Energy" + System.lineSeparator() +
                        "Medications: testMed1 5mg, Once Daily. testMed2 2mg, Twice Daily."
        ));
    }

    @Test
    public void testConvertActivityStyle(){
        //check that calling each of the styles works
        assertTrue("Immobilized".equals(tintin.convertActivityLevel(Animal.ActivityLevel.IMMOBILIZED)));
        assertTrue("Recovering".equals(tintin.convertActivityLevel(Animal.ActivityLevel.RECOVERING)));
        assertTrue("Low Energy".equals(tintin.convertActivityLevel(Animal.ActivityLevel.LOW_ENERGY)));
        assertTrue("High Energy".equals(tintin.convertActivityLevel(Animal.ActivityLevel.HIGH_ENERGY)));
    }

    @Test
    public void testConvertDietSize(){
        //check that calling each of the styles works
        assertTrue("Small".equals(tintin.convertDietSize(Animal.DietSize.SMALL_DIET)));
        assertTrue("Medium".equals(tintin.convertDietSize(Animal.DietSize.MEDIUM_DIET)));
        assertTrue("Large".equals(tintin.convertDietSize(Animal.DietSize.LARGE_DIET)));
    }

    @Test
    public void testConvertMedicationFrequency(){
        //check that calling each of the styles works
        assertTrue("Once Daily".equals(tintin.convertMedicationFrequency(Animal.MedicationFrequency.ONCE_DAILY)));
        assertTrue("Twice Daily".equals(tintin.convertMedicationFrequency(Animal.MedicationFrequency.TWICE_DAILY)));
    }
}
