package com.genesiscode.practicespringboot.repository;

import com.genesiscode.practicespringboot.domain.Student;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @BeforeEach
    void setUp() {
        loadData();
    }

    @Nested
    @DisplayName("Find by email Test")
    class FindByEmail {

        @Test
        @DisplayName("Find by email exists")
        void findByEmailExists() {
            //GIVEN
            String email = "josmariaguilar@gmail.com";

            //WHEN
            Optional<Student> studentOptional = underTest.findByEmail(email);

            //THEN
            assertThat(studentOptional.isPresent()).isTrue();
            Student student = studentOptional.get();
            assertAll(
                    () -> assertThat(student.getName()).isEqualTo("jose maria"),
                    () -> assertThat(student.getDob()).isEqualTo(LocalDate.of(1998, Month.JANUARY, 17)),
                    () -> assertThat(student.getAge()).isEqualTo(23)
            );
        }

        @Test
        @DisplayName("Find by email no exists")
        void findByEmailNoExists() {
            //GIVEN
            String email = "none@gmail.com";

            //WHEN
            Optional<Student> studentOptional = underTest.findByEmail(email);

            //THEN
            assertThat(studentOptional.isEmpty()).isTrue();
        }



    }

    @Nested
    @DisplayName("Find by Date of birth between start and end date")
    class FindByDobBetweenTest {

        @Test
        void findByDobBetweenWithMoreOneElement() {
            //GIVEN
            LocalDate start = LocalDate.of(1997, Month.JANUARY, 1);
            LocalDate end = LocalDate.of(2004, Month.JANUARY, 1);
            int sizeExpected = 2;
            //WHEN
            Collection<Student> students = underTest.findByDobBetween(start, end);

            //THEN
            assertAll(
                    () -> assertThat(students).isNotNull(),
                    () -> assertThat(students).isNotEmpty(),
                    () -> assertThat(students).hasSize(sizeExpected),
                    () -> assertThat(students)
                            .extracting(Student::getAge)
                            .containsExactly(23, 18),
                    () -> assertThat(students)
                            .extracting("name", "email")
                            .containsExactly(
                                    tuple("jose maria", "josmariaguilar@gmail.com"),
                                    tuple("darleen", "darleenmariana@gmail.com")
                            )
            );
        }

        @Test
        void findByDobBetweenWithOneElement() {
            //GIVEN
            LocalDate start = LocalDate.of(1997, Month.JANUARY, 1);
            LocalDate end = LocalDate.of(2000, Month.JANUARY, 1);
            int sizeExpected = 1;
            Student studentExpected = new Student("jose maria", "josmariaguilar@gmail.com",
                    LocalDate.of(1998, Month.JANUARY, 17));

            //WHEN
            List<Student> students = (List<Student>) underTest.findByDobBetween(start, end);

            //THEN
            assertAll(
                    () -> assertThat(students).isNotNull().isNotEmpty().hasSize(sizeExpected),
                    () -> assertThat(students.get(0))
                            .usingRecursiveComparison()
                            .ignoringFields("id")
                            .isEqualTo(studentExpected)

            );

        }

        @Test
        void findByDobBetweenWithZeroElement() {
            //GIVEN
            LocalDate start = LocalDate.of(2004, Month.JANUARY, 1);
            LocalDate end = LocalDate.of(2019, Month.JANUARY, 1);
            int sizeExpected = 0;

            //WHEN
            Collection<Student> students = underTest.findByDobBetween(start, end);

            //THEN
            assertAll(
                    () -> assertThat(students).isNotNull(),
                    () -> assertThat(students).isEmpty(),
                    () -> assertThat(students).hasSize(sizeExpected)
            );
        }
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    private void loadData() {
        Student jose_maria = new Student("jose maria", "josmariaguilar@gmail.com", LocalDate.of(1998, Month.JANUARY, 17));
        Student darleen = new Student("darleen", "darleenmariana@gmail.com", LocalDate.of(2003, Month.AUGUST, 1));
        Student carlos = new Student("carlos", "carlos@gmail.com", LocalDate.now());
        Student jason = new Student("jason", "jason@gmail.com", LocalDate.now());
        Student sara = new Student("sara", "sara@gmail.com", LocalDate.now());
        Student jimena = new Student("jimena", "jimena@gmail.com", LocalDate.now());
        Student esther = new Student("esther", "esther@gmail.com", LocalDate.now());
        Student paola = new Student("paola", "paola@gmail.com", LocalDate.now());
        Student dorian = new Student("dorian", "dorian@gmail.com", LocalDate.now());
        Student joana = new Student("joana", "joana@gmail.com", LocalDate.now());
        Student rudy = new Student("rudy", "rudy@gmail.com", LocalDate.now());
        Student edwin = new Student("edwin", "edwin@gmail.com", LocalDate.now());
        Student denilson = new Student("denilson", "denilson@gmail.com", LocalDate.now());
        Student jesus = new Student("jesus", "jesus@gmail.com", LocalDate.now());
        Student karen = new Student("karen", "karen@gmail.com", LocalDate.now());
        Student genesis = new Student("genesis", "genesis@gmail.com", LocalDate.now());
        Student sandra = new Student("sandra", "sandra@gmail.com", LocalDate.now());

        underTest.saveAll(List.of(jose_maria, darleen, carlos, jason, sara, jimena, esther,
                paola, dorian, joana, rudy, edwin, denilson, jesus, karen, genesis, sandra));
    }
}