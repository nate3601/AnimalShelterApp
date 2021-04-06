package model;

import exceptions.AnimalAlreadyRegisteredException;
import exceptions.AnimalNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalShelterTest {
    AnimalShelter testShelter;
    Animal testAnimal;

    @BeforeEach
    public void setUp(){
        testShelter = new AnimalShelter();
        testAnimal = new Animal();
        testAnimal.setName("TestName");
    }

    @Test
    public void testRegisterAnimalEmpty(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animal to the set and check if it is successful
        try {
            testShelter.registerAnimal(testAnimal);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        //check that the set is now 1 larger
        assertEquals(1, testShelter.size());
        //add another animal and check if it is successful
        Animal snowy = new Animal();
        try {
            testShelter.registerAnimal(snowy);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        //check that the set contains 2 animals
        assertEquals(2, testShelter.size());

    }
    @Test
    public void testRegisterAnimalAlreadyThere(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animal to the set
        try {
            testShelter.registerAnimal(testAnimal);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        //check that the set is now 1 larger
        assertEquals(1, testShelter.size());
        //add the same animal
        try {
            testShelter.registerAnimal(testAnimal);
            fail("should have thrown exception");
        } catch (AnimalAlreadyRegisteredException e) {
            //good
        }
        //check that the set still contains 1 animal
        assertEquals(1, testShelter.size());
    }
    @Test
    public void testAdoptAnimalEmpty(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //attempt to adopt animal, check that it returns false
        try {
            testShelter.adoptAnimal(testAnimal);
            fail("should have thrown exception");
        } catch (AnimalNotFoundException e) {
            //good
        }
    }
    @Test
    public void testAdoptAnimalOnlyOne(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animal to the set
        try {
            testShelter.registerAnimal(testAnimal);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        //check that the set is now 1 larger
        assertEquals(1, testShelter.size());
        //adopt testAnimal, check true
        try {
            testShelter.adoptAnimal(testAnimal);
        } catch (AnimalNotFoundException e) {
            fail("should not have thrown exception");
        }
        //check that the set is now empty
        assertEquals(0, testShelter.size());
    }
    @Test
    public void testAdoptAnimalMany(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animals to the set and check if it is successful
        try {
            testShelter.registerAnimal(testAnimal);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        Animal snowy = new Animal();
        try {
            testShelter.registerAnimal(snowy);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        //check that the set contains 2 animals
        assertEquals(2, testShelter.size());
        //check that you can adopt both animals
        try {
            testShelter.adoptAnimal(testAnimal);
        } catch (AnimalNotFoundException e) {
            fail("should not have thrown exception");
        }
        try {
            testShelter.adoptAnimal(snowy);
        } catch (AnimalNotFoundException e) {
            fail("should not have thrown exception");
        }
    }

    @Test
    public void testListOfResidentsEmpty(){
        assertEquals(testShelter.listOfResidents(), "");
    }

    @Test
    public void testListOfResidentsMultiple(){
        //register testAnimal
        try {
            testShelter.registerAnimal(testAnimal);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        //create new animal and register
        Animal testAnimal2 = new Animal();
        testAnimal2.setName("TestName2");
        try {
            testShelter.registerAnimal(testAnimal2);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        assertEquals("\n" + "0 -> TestName" + "\n" + "1 -> TestName2", testShelter.listOfResidents());
    }

    @Test
    public void testSelectAnimalAt(){
        //register testAnimal
        try {
            testShelter.registerAnimal(testAnimal);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        //create new animal and register
        Animal testAnimal2 = new Animal();
        try {
            testShelter.registerAnimal(testAnimal2);
        } catch (AnimalAlreadyRegisteredException e) {
            fail("should not have thrown exception");
        }
        assertSame(testAnimal2, testShelter.selectAnimalAt(1));
    }


}