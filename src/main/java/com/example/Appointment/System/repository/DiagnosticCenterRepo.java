package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.DiagnosticCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DiagnosticCenterRepo extends JpaRepository<DiagnosticCenter,Long> {
    @Query("SELECT dc FROM DiagnosticCenter dc" +
            " WHERE dc.diagnosticCenterName = :name" +
            " AND dc.country = :country AND dc.city = :city " +
            "AND dc.address = :address AND dc.roadNo = :roadNo " +
            "AND dc.holdingNo = :holdingNo")
    Optional<DiagnosticCenter> findDiagnosticCenter(@Param("name") String name,
                                             @Param("country") String country,
                                             @Param("city") String city,
                                             @Param("address") String address,
                                             @Param("roadNo") String roadNo,
                                             @Param("holdingNo") String holdingNo);

    Optional<DiagnosticCenter> findByDiagnosticCenterName(String diagnosticCenterName);

    boolean existsByDiagnosticCenterName(String diagnosticCenterName);
}
