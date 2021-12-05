package com.genesiscode.practicespringboot.repository;

import com.genesiscode.practicespringboot.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.dob BETWEEN :start AND :end")
    Collection<Student> findByDobBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

}
