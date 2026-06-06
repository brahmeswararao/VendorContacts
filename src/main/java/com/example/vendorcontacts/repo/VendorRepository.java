package com.example.vendorcontacts.repo;

import com.example.vendorcontacts.model.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Query("select v from Vendor v where (:name is null or lower(v.name) like lower(concat('%', :name, '%'))) " +
            "and (:city is null or lower(v.city) = lower(:city)) " +
            "and (:active is null or v.active = :active)")
    Page<Vendor> search(
            @Param("name") String name,
            @Param("city") String city,
            @Param("active") Boolean active,
            Pageable pageable
    );
}
