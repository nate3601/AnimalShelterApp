package model;

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
        assertTrue(testShelter.registerAnimal(testAnimal));
        //check that the set is now 1 larger
        assertEquals(1, testShelter.size());
        //add another animal and check if it is successful
        Animal snowy = new Animal();
        assertTrue(testShelter.registerAnimal(snowy));
        //check that the set contains 2 animals
        assertEquals(2, testShelter.size());

    }
    @Test
    public void testRegisterAnimalAlreadyThere(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animal to the set
        assertTrue(testShelter.registerAnimal(testAnimal));
        //check that the set is now 1 larger
        assertEquals(1, testShelter.size());
        //add the same animal
        assertFalse(testShelter.registerAnimal(testAnimal));
        //check that the set still contains 1 animal
        assertEquals(1, testShelter.size());
    }
    @Test
    public void testAdoptAnimalEmpty(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //attempt to adopt animal, check that it returns false
        assertFalse(testShelter.adoptAnimal(testAnimal));
    }
    @Test
    public void testAdoptAnimalOnlyOne(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animal to the set
        assertTrue(testShelter.registerAnimal(testAnimal));
        //check that the set is now 1 larger
        assertEquals(1, testShelter.size());
        //adopt testAnimal, check true
        assertTrue(testShelter.adoptAnimal(testAnimal));
        //check that the set is now empty
        assertEquals(0, testShelter.size());
    }
    @Test
    public void testAdoptAnimalMany(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animals to the set and check if it is successful
        assertTrue(testShelter.registerAnimal(testAnimal));
        Animal snowy = new Animal();
        assertTrue(testShelter.registerAnimal(snowy));
        //check that the set contains 2 animals
        assertEquals(2, testShelter.size());
        //check that you can adopt both animals
        assertTrue(testShelter.adoptAnimal(testAnimal));
        assertTrue(testShelter.adoptAnimal(snowy));
    }

    @Test
    public void testListOfResidentsEmpty(){
        assertEquals(testShelter.listOfResidents(), "");
    }

    @Test
    public void testListOfResidentsMultiple(){
        //register testAnimal
        assertTrue(testShelter.registerAnimal(testAnimal));
        //create new animal and register
        Animal testAnimal2 = new Animal();
        testAnimal2.setName("TestName2");
        assertTrue(testShelter.registerAnimal(testAnimal2));
        assertEquals("\n" + "0 -> TestName" + "\n" + "1 -> TestName2", testShelter.listOfResidents());
    }

    @Test
    public void testSelectAnimalAt(){
        //register testAnimal
        assertTrue(testShelter.registerAnimal(testAnimal));
        //create new animal and register
        Animal testAnimal2 = new Animal();
        assertTrue(testShelter.registerAnimal(testAnimal2));
        assertSame(testAnimal2, testShelter.selectAnimalAt(1));
    }


}