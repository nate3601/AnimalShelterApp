package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalShelterTest {
    AnimalShelter testShelter;
    Animal tintin;

    @BeforeEach
    public void setUp(){
        testShelter = new AnimalShelter();
        tintin = new Animal();
        tintin.setName("Tintin");
    }

    @Test
    public void testRegisterAnimalEmpty(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animal to the set and check if it is successful
        assertTrue(testShelter.registerAnimal(tintin));
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
        assertTrue(testShelter.registerAnimal(tintin));
        //check that the set is now 1 larger
        assertEquals(1, testShelter.size());
        //add the same animal
        assertFalse(testShelter.registerAnimal(tintin));
        //check that the set still contains 1 animal
        assertEquals(1, testShelter.size());
    }
    @Test
    public void testAdoptAnimalEmpty(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //attempt to adopt animal, check that it returns false
        assertFalse(testShelter.adoptAnimal(tintin));
    }
    @Test
    public void testAdoptAnimalOnlyOne(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animal to the set
        assertTrue(testShelter.registerAnimal(tintin));
        //check that the set is now 1 larger
        assertEquals(1, testShelter.size());
        //adopt tintin, check true
        assertTrue(testShelter.adoptAnimal(tintin));
        //check that the set is now empty
        assertEquals(0, testShelter.size());
    }
    @Test
    public void testAdoptAnimalMany(){
        //check that the set is empty
        assertEquals(0, testShelter.size());
        //add animals to the set and check if it is successful
        assertTrue(testShelter.registerAnimal(tintin));
        Animal snowy = new Animal();
        assertTrue(testShelter.registerAnimal(snowy));
        //check that the set contains 2 animals
        assertEquals(2, testShelter.size());
        //check that you can adopt both animals
        assertTrue(testShelter.adoptAnimal(tintin));
        assertTrue(testShelter.adoptAnimal(snowy));
    }

    @Test
    public void testListOfResidentsEmpty(){
        assertTrue("".equals(testShelter.listOfResidents()));
    }

    @Test
    public void testListOfResidentsMultiple(){
        //register tintin
        assertTrue(testShelter.registerAnimal(tintin));
        //create new animal and register
        Animal coozie = new Animal();
        coozie.setName("Coozie");
        assertTrue(testShelter.registerAnimal(coozie));
        assertTrue(testShelter.listOfResidents().equals("\n" + "0 -> Tintin" + "\n" + "1 -> Coozie"));
    }

    @Test
    public void testSelectAnimalAt(){
        //register tintin
        assertTrue(testShelter.registerAnimal(tintin));
        //create new animal and register
        Animal coozie = new Animal();
        assertTrue(testShelter.registerAnimal(coozie));
        assertTrue(coozie == testShelter.selectAnimalAt(1));
    }


}