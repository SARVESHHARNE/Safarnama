package com.backend.safarnama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.safarnama.model.Package;

public interface PackageReository extends JpaRepository<Package, Long> {
}
