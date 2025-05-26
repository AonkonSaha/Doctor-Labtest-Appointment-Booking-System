INSERT IGNORE INTO diagnostic_centers (
    id,diagnostic_center_name, country, city, zip_code, address, road_no, holding_no,
    profile_picture_url, contact_number, email, operating_hours, accreditation,
    website_url, rating, is_active
) VALUES
      (1,'HealthFirst Diagnostics', 'USA', 'New York', '10001', '123 Main St', '5th Ave', '101A', 'https://example.com/images/healthfirst.png', '+1-212-555-0101', 'contact@healthfirst.com', '08:00 - 20:00', 'NABL', 'https://healthfirst.com', 4.5, true),
      (2,'MediCare Labs', 'USA', 'Los Angeles', '90001', '456 Sunset Blvd', '7th St', '202B', 'https://example.com/images/medicare.png', '+1-310-555-0202', 'support@medicare.com', '09:00 - 18:00', 'CAP', 'https://medicarelabs.com', 4.2, true),
      (3,'CityLab Diagnostics', 'UK', 'London', 'EC1A 1BB', '78 Baker St', '3rd Ave', '303C', 'https://example.com/images/citylab.png', '+44-20-7946-0303', 'info@citylab.uk', '07:00 - 19:00', 'ISO', 'https://citylab.uk', 4.7, true),
      (4,'Prime Health Lab', 'India', 'Delhi', '110001', '89 Connaught Place', '2nd Block', '404D', 'https://example.com/images/primehealth.png', '+91-11-5555-0404', 'prime@healthlab.in', '08:30 - 20:30', 'NABL', 'https://primehealth.in', 4.6, true),
      (5,'Global Path Lab', 'Canada', 'Toronto', 'M5H 2N2', '321 King St', '1st Rd', '505E', 'https://example.com/images/globalpath.png', '+1-416-555-0505', 'service@globalpath.ca', '09:00 - 17:00', 'CAP', 'https://globalpath.ca', 4.3, true),
      (6,'Apex Diagnostics', 'Australia', 'Sydney', '2000', '222 George St', '6th Ave', '606F', 'https://example.com/images/apex.png', '+61-2-5550-0606', 'hello@apex.com.au', '08:00 - 19:00', 'NATA', 'https://apexlabs.au', 4.8, true),
      (7,'Sunrise Lab Center', 'USA', 'Chicago', '60601', '100 Lake Shore Dr', '4th St', '707G', 'https://example.com/images/sunrise.png', '+1-312-555-0707', 'contact@sunriselabs.com', '07:30 - 18:30', 'NABL', 'https://sunriselabs.com', 4.4, true),
      (8,'SureTest Diagnostics', 'Germany', 'Berlin', '10115', 'Karl Marx Str.', '9th Ave', '808H', 'https://example.com/images/suretest.png', '+49-30-5550-0808', 'support@suretest.de', '09:00 - 20:00', 'DAkkS', 'https://suretest.de', 4.1, true),
      (9,'MediTrust Labs', 'India', 'Mumbai', '400001', 'Carter Road', '5th Lane', '909I', 'https://example.com/images/meditrust.png', '+91-22-5555-0909', 'meditrust@labs.in', '08:00 - 21:00', 'NABL', 'https://meditrust.in', 4.5, true),
      (10,'Elite Diagnostics', 'UAE', 'Dubai', '00000', 'Sheikh Zayed Rd', '12th St', '1010J', 'https://example.com/images/elite.png', '+971-4-555-1010', 'info@elitediagnostics.ae', '08:00 - 22:00', 'JCI', 'https://elitediagnostics.ae', 4.9, true);

