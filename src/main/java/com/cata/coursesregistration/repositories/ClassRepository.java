package com.cata.coursesregistration.repositories;

import com.cata.coursesregistration.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
}