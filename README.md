# Animal Shelter

## Record of housed animals

The application will be used by the employees of a shelter,
in order to keep records of the animals that they are housing.
They will be able to register new animals (specifying their characteristics),
unregister them when they are adopted, log medical conditions,
and produce a week's schedule for a given animal including:
-  feeding times
-  outdoor/exercise time
-  medications

This project is of interest to me because I have recently started 
volunteering at a vet clinic (I'm bored from COVID and miss my dog),
and I thought it would be a good system to try to imitate. This is because
I am somewhat aware of the necessary functionality of the system, as well
as its applicability to the real world.

## User Stories

- as a user, I want to be able to register a new animal to the shelter,
specifying its characteristics
- as a user, I want to be able to unregister an animal once they are adopted
- as a user, I want to be able to log medical conditions, and necessary medications
- as a user, I want to be able to select an animal and view it in detail
- as a user, I want to be able to save my animal shelter to file
- as a user, I want to be able to load my animal shelter from file

## Phase 4: Task 2
I decided to test and implement one of my classes so that it is robust.
The class I chose to make robust was the AnimalShelter.java class, specifically its
methods registerAnimal() and adoptAnimal(), because these are the only ones that have a 
requires clause. 

## Phase 4: Task 3
I believe there is a large amount of refactoring that can be done, based on my UML
diagram. There is a high degree of coupling, specifically with the SelectAnimalPanel
and its sub-panels (AnimalPanel, LogMedicationPanel, ShowCharacteristicsPanel, and 
AdoptPanel). This was necessary due to the construction of the CardLayout used for
switching between windows, as I needed separate classes for each panel that are related
through association rather than extension/implementation in order to deal with the
collection of cards. However, if I found another window-switching-based that did not require
me to pass in the same card collection to each panel, I could reduce the coupling in 
the system by allowing the sub-panels of SelectAnimalPanel to simply extend it, which
would make it so they no longer have ot keep track of their own AnimalPanel and 
AnimalShelterApp.  


 