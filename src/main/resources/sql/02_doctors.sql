INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           2,
           'Dr. Shreya Saha',
           'aonkon@gmail.com',
           '01881264850',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Female',
           '1999-09-14',
           0
       );

INSERT IGNORE INTO user_roles (id, role)VALUES (2, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (2, 2);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           2, 'Dr. Shreya Saha', 'Cardiologist', 'LIC123456', 12,
           'City Hospital', '/images/doctor/doctor_two.jpeg', 'English,Bangla',
           4.8, TRUE,2
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (2, 'MBBS(RU)'),
    (2, 'MD(Cardiology)');

-- Doctor 2
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           3, 'Dr. Rahim Khan', 'rahim.khan@gmail.com', '01710000000',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Male', '1985-02-10', 1
       );

INSERT IGNORE INTO user_roles (id, role) VALUES (3, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (3, 3);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           3, 'Dr. Rahim Khan', 'Neurologist', 'LIC654321', 15,
           'HealthPlus Clinic', '/images/doctor/doctor_one.webp', 'English,Hindi',
           4.6, TRUE, 3
       );
INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (3, 'MBBS(DU)'),
    (3, 'DM(Neurology)');


-- Doctor 3
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           4, 'Dr. Susmita Sen',
           'mistymurray@wagner-santana.net',
           '91891634896',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Female',
           '1985-01-16',
           0
       );

INSERT IGNORE INTO user_roles (id, role) VALUES (4, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (4, 4);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           4, 'Dr. Susmita Sen',
           'Neurologist',
           'LIC852996',
           8,
           'Hall, Hancock and Smith Hospital',
           '/images/doctor/doctor_four.jpg',
           'those,scientist',
           4.0,
           TRUE,
           4
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (4, 'MBBS(DU)'),
    (4, 'MD(Cardiology)');


-- Doctor 4
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           5,
           'Dr. Shah Rukh Khan',
           'sherri46@yahoo.com',
           '02234500762',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Female',
           '1988-10-11',
           0
       );

INSERT IGNORE INTO user_roles (id, role)VALUES (5, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (5, 5);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           5,
           'Dr. Kelly Cook',
           'Dermatologist',
           'LIC173404',
           12,
           'Nunez and Sons Hospital',
           '/images/doctor/doctor_fourteen.webp',
           'special,good',
           3.1,
           FALSE,
           5
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (5, 'MBBS(CU)'),
    (5, 'MS(Orthopedics)');


-- Doctor 5
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           6,
           'Dr. John Ellis',
           'bosborne@george.com',
           '85367109795',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Female',
           '1974-06-18',
           0
       );

INSERT IGNORE INTO user_roles (id, role) VALUES (6, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (6, 6);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           6,
           'Dr. John Ellis',
           'Cardiologist',
           'LIC628101',
           19,
           'Mullen Group Hospital',
           '/images/doctor/doctor_eight.jpg',
           'son,true',
           3.1,
           TRUE,
           6
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (6, 'MBBS(BU)'),
    (6, 'MD(Dermatology)');


-- Doctor 6
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           7,
           'Dr. Melanie Patrick',
           'caseysean@gibson.com',
           '07408993318',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Male',
           '1981-08-19',
           0
       );

INSERT IGNORE INTO user_roles (id, role) VALUES (7, 'DOCTOR');

INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (7, 7);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           7,
           'Dr. Melanie Patrick',
           'Dermatologist',
           'LIC867136',
           20,
           'Dougherty-Thomas Hospital',
           '/images/doctor/doctor_nine.jpg', 'through,instead',
           4.7,
           TRUE,
           7
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (7, 'MBBS(RU)'),
    (7, 'DM(Neurology)');


-- Doctor 7
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           8,
           'Dr. Brian Brown',
           'jessicajohnson@yahoo.com',
           '60754151150',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Male',
           '1970-06-06',
           0
       );

INSERT IGNORE INTO user_roles (id, role) VALUES (8, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (8, 8);
INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           8,
           'Dr. Brian Brown',
           'Neurologist',
           'LIC458940',
           21,
           'Greer and Sons Hospital',
           '/images/doctor/doctor_eleven.jpeg',
           'standard,audience',
           3.5,
           FALSE,
           8
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (8, 'MBBS(RU)'),
    (8, 'DM(Neurology)');


-- Doctor 8
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           9,
           'Dr. Christine Harris',
           'carterhannah@hall.info',
           '42035574485',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Male',
           '1967-02-16',
           0
       );

INSERT IGNORE INTO user_roles (id, role)
VALUES (9, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (9, 9);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           9,
           'Dr. Christine Harris',
           'Orthopedic',
           'LIC696757',
           21,
           'Ferguson LLC Hospital',
           '/images/doctor/doctor_thirteen.jpeg',
           'choose,today',
           4.8,
           FALSE,
           9
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (9, 'MBBS(BU)'),
    (9, 'MD(Dermatology)');


-- Doctor 9
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           10,
           'Dr. Regina Stewart',
           'karicarter@alvarado-callahan.org',
           '61126486296',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Female',
           '1967-04-03',
           0
       );

INSERT IGNORE INTO user_roles (id, role)
VALUES (10, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (10, 10);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           10,
           'Dr. Regina Stewart',
           'ENT Specialist',
           'LIC824380',
           5,
           'Wallace LLC Hospital',
           '/images/doctor/doctor_fourteen.webp',
           'PM,more',
           3.9, TRUE, 10
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (10, 'MBBS(CU)'),
    (10, 'MS(Orthopedics)');


-- Doctor 10
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           11,
           'Dr. Adam Hayden',
           'sharris@yahoo.com',
           '21418927626',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Male',
           '1972-10-24',
           1
       );

INSERT IGNORE INTO user_roles (id, role)
VALUES (11, 'DOCTOR');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (11, 11);

INSERT IGNORE INTO doctors (
    id, doctor_name, designation, license_number, years_of_experience,
    hospital_or_clinic_name, profile_picture_url, languages_spoken,
    rating, availability_status, user_id
)
VALUES (
           11,
           'Dr. Adam Hayden',
           'Neurologist',
           'LIC351794',
           29,
           'Peterson-Mills Hospital',
           '/images/doctor/doctor_nine.jpg',
           'green,place',
           4.9,
           FALSE,
           11
       );

INSERT IGNORE INTO doctor_degrees (doctor_id, degree) VALUES
    (11, 'MBBS(SUST)'),
    (11, 'MS(Ear,Nose,and Throat)');


