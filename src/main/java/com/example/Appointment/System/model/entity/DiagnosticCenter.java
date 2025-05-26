package com.example.Appointment.System.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diagnostic_centers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiagnosticCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String diagnosticCenterName;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private String roadNo;
    private String holdingNo;
    private String profilePictureUrl;
    private String contactNumber;
    private String email;
    private String operatingHours;
    private String accreditation;
    private String websiteUrl;
    private Double rating;
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="clinic_vs_lab_test",
            joinColumns = @JoinColumn(name="clinic_id"),
            inverseJoinColumns = @JoinColumn(name="lab_test_id"))
    private Set<LabTest> labTests=new HashSet<>();

    @OneToMany(mappedBy = "diagnosticCenter",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<LabTestBooking> labTestBookings=new HashSet<>();
}
