package com.izzaz.wcc.javachallenge.repository;

import com.izzaz.wcc.javachallenge.model.dao.PostalCodeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@Transactional
public interface PostalCodeRepository extends JpaRepository<PostalCodeEntity, Long> {

    PostalCodeEntity findByPostcode(String postcode);

    @Modifying
    @Query("update PostalCodeEntity p set p.latitude = :latitude, p.longitude =:longitude where p.postcode = :postcode")
    void updatePostalCode(String postcode, BigDecimal latitude, BigDecimal longitude);
}
