package com.example.webshop_be.domain.authority;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Optional<Authority> findByName(String name);

    void deleteByName(String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from role_authority where authority_id = ?")
    void deleteRelationsToRolesById(String id);
}
