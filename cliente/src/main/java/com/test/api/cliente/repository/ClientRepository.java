package com.test.api.cliente.repository;

import com.test.api.cliente.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("SELECT p FROM Client p WHERE p.name LIKE LOWER(CONCAT ('%',:name,'%'))")
    Page<Client> findClientsByName(@Param("name") String name, Pageable pageable);
}

