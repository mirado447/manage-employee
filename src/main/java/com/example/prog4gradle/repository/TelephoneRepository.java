package com.example.prog4gradle.repository;

import com.example.prog4gradle.repository.entity.TelephoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<TelephoneEntity,Long> {
    @Query("select t.numberPhone from Telephone t INNER join employee on t.id = :id_employee ")
    TelephoneEntity getTelephoneEntityByIdEmployee(@Param("id_employee")Long id_employee);
}
