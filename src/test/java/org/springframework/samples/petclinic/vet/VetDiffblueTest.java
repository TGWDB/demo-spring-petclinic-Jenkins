package org.springframework.samples.petclinic.vet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VetDiffblueTest {
  /**
   * Method under test: {@link Vet#getSpecialtiesInternal()}
   */
  @Test
  void testGetSpecialtiesInternal() {
    // Arrange, Act and Assert
    assertTrue((new Vet()).getSpecialtiesInternal().isEmpty());
  }

  /**
   * Method under test: {@link Vet#getSpecialtiesInternal()}
   */
  @Test
  void testGetSpecialtiesInternal2() {
    // Arrange
    Vet vet = new Vet();
    vet.setFirstName("Jane");
    vet.setId(1);
    vet.setLastName("Doe");
    HashSet<Specialty> specialties = new HashSet<>();
    vet.setSpecialtiesInternal(specialties);

    // Act
    Set<Specialty> actualSpecialtiesInternal = vet.getSpecialtiesInternal();

    // Assert
    assertTrue(actualSpecialtiesInternal.isEmpty());
    assertSame(specialties, actualSpecialtiesInternal);
  }

  /**
   * Method under test: {@link Vet#getSpecialties()}
   */
  @Test
  void testGetSpecialties() {
    // Arrange, Act and Assert
    assertTrue((new Vet()).getSpecialties().isEmpty());
  }

  /**
   * Method under test: {@link Vet#getSpecialties()}
   */
  @Test
  void testGetSpecialties2() {
    // Arrange
    Vet vet = new Vet();
    vet.setFirstName("Jane");
    vet.setId(1);
    vet.setLastName("Doe");
    vet.setSpecialtiesInternal(new HashSet<>());

    // Act and Assert
    assertTrue(vet.getSpecialties().isEmpty());
  }

  /**
   * Method under test: {@link Vet#getNrOfSpecialties()}
   */
  @Test
  void testGetNrOfSpecialties() {
    // Arrange, Act and Assert
    assertEquals(0, (new Vet()).getNrOfSpecialties());
  }

  /**
   * Method under test: {@link Vet#getNrOfSpecialties()}
   */
  @Test
  void testGetNrOfSpecialties2() {
    // Arrange
    Vet vet = new Vet();
    vet.setFirstName("Jane");
    vet.setId(1);
    vet.setLastName("Doe");
    vet.setSpecialtiesInternal(new HashSet<>());

    // Act and Assert
    assertEquals(0, vet.getNrOfSpecialties());
  }

  /**
   * Method under test: {@link Vet#addSpecialty(Specialty)}
   */
  @Test
  void testAddSpecialty() {
    // Arrange
    Vet vet = new Vet();

    Specialty specialty = new Specialty();
    specialty.setId(1);
    specialty.setName("Canines");

    // Act
    vet.addSpecialty(specialty);

    // Assert
    List<Specialty> specialties = vet.getSpecialties();
    assertEquals(1, specialties.size());
    assertEquals(1, vet.getSpecialtiesInternal().size());
    assertEquals(1, vet.getNrOfSpecialties());
    assertSame(specialty, specialties.get(0));
  }

  /**
   * Method under test: {@link Vet#addSpecialty(Specialty)}
   */
  @Test
  void testAddSpecialty2() {
    // Arrange
    Vet vet = new Vet();
    vet.setFirstName("Jane");
    vet.setId(1);
    vet.setLastName("Doe");
    HashSet<Specialty> specialties = new HashSet<>();
    vet.setSpecialtiesInternal(specialties);

    Specialty specialty = new Specialty();
    specialty.setId(1);
    specialty.setName("Canines");

    // Act
    vet.addSpecialty(specialty);

    // Assert
    List<Specialty> specialties2 = vet.getSpecialties();
    assertEquals(1, specialties2.size());
    Set<Specialty> specialtiesInternal = vet.getSpecialtiesInternal();
    assertEquals(1, specialtiesInternal.size());
    assertEquals(1, vet.getNrOfSpecialties());
    assertSame(specialties, specialtiesInternal);
    assertSame(specialty, specialties2.get(0));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Vet}
   *   <li>{@link Vet#setSpecialtiesInternal(Set)}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    Vet actualVet = new Vet();
    HashSet<Specialty> specialties = new HashSet<>();
    actualVet.setSpecialtiesInternal(specialties);

    // Assert
    assertNull(actualVet.getId());
    assertNull(actualVet.getFirstName());
    assertNull(actualVet.getLastName());
    Set<Specialty> specialtiesInternal = actualVet.getSpecialtiesInternal();
    assertTrue(specialtiesInternal.isEmpty());
    assertSame(specialties, specialtiesInternal);
  }
}
