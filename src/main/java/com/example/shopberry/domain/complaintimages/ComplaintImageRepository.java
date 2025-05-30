package com.example.shopberry.domain.complaintimages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintImageRepository extends JpaRepository<ComplaintImage, Long> {
}
