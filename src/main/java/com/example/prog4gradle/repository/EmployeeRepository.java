package com.example.prog4gradle.repository;

import com.example.prog4gradle.repository.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity , Long> {
    @Query("select e from employee e where lower(e.firstName) like %:finder% or lower(e.lastName) like %:finder% " +
            "or lower(e.sex) like %:finder% or lower(e.post) like %:finder% or lower(e.countryCode) like %:finder%")
    List<EmployeeEntity> findByFirstNameOrLastNameOrSexOrPostContainingIgnoreCase(@Param("finder") String finder);
    @Query("select e from employee e where e.embaucheDate = :DateFinder")
    List<EmployeeEntity> findByEmbaucheDate(@Param("DateFinder") String DateFinder);

    EmployeeEntity getByFirstName(String firstName);

}

