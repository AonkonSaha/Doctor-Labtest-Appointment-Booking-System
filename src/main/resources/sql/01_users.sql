-------------------------------Default USER----------------------------------------
INSERT IGNORE INTO users (id, name, email, contact, password, gender, date_of_birth, is_active)
VALUES (
           1,
           'Aonkon Saha',
           'aonkon@gmail.com',
           '01881264859',
           '$2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe',
           'Male',
           '1999-09-14',
           0
       );

INSERT IGNORE INTO user_roles (id, role) VALUES (1, 'PATIENT');
INSERT IGNORE INTO user_roles (id, role) VALUES (2, 'ADMIN');
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (1, 1);
INSERT IGNORE INTO user_vs_role (role_id, user_id) VALUES (2, 1);

INSERT IGNORE INTO patients (id, address, date_of_birth, patient_name, profile_picture_url, user_id)
VALUES (
           1,
           'Mirpur-2,Dhaka,Bangladesh',
           '1999-09-14',
           'Aonkon Saha',
           '/images/default_profile.jpg',
           1
       );
