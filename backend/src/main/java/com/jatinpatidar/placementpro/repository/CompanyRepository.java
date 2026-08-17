package com.jatinpatidar.placementpro.repository;

import com.jatinpatidar.placementpro.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findBySlug(String slug);
}
