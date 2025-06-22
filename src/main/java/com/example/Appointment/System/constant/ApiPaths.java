package com.example.Appointment.System.constant;

public  final class ApiPaths {
    public static final String BASE_API = "/api";

    public static final class Doctor {
        public static final String ROOT = BASE_API + "/doctor";
        public static final String FETCH_BY_ID = "/fetch/{id}";
        public static final String FETCH_ALL = "/fetch/all";
        public static final String FETCH = "/fetch";
        public static final String REGISTER = "/register";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
    }

    public static final class DiagnosticCenter {
        public static final String ROOT = BASE_API + "/diagnostic/center";
        public static final String FETCH_BY_ID = "/fetch/{id}";
        public static final String FETCH_ALL = "/fetch/all";
        public static final String FETCH = "/fetch";
        public static final String REGISTER = "/register";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
    }

    public static final class DoctorBooking {
        public static final String ROOT = BASE_API + "/doctor/booking";
        public static final String FETCH_BY_ID = "/fetch/{id}";
        public static final String FETCH_ALL = "/fetch/all";
        public static final String FETCH = "/fetch";
        public static final String REGISTER = "/register";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
        public static final String TIME_SLOT = "/fetch/time/slot";
        public static final String HISTORY = "/fetch/all/history";
    }

    public static final class LabTest {
        public static final String ROOT = BASE_API + "/lab/test";
        public static final String FETCH_BY_ID = "/fetch/{id}";
        public static final String FETCH_ALL = "/fetch/all";
        public static final String FETCH = "/fetch";
        public static final String REGISTER = "/register";
        public static final String UPDATE = "/update/{id}";
        public static final String DELETE = "/delete/{id}";
    }

    public static final class LabTestBooking {
        public static final String ROOT = BASE_API + "/booking/lab/test";
        public static final String FETCH_BY_ID ="/fetch/{id}";
        public static final String FETCH_ALL ="/fetch/all";
        public static final String FETCH = "/fetch";
        public static final String REGISTER ="/register";
        public static final String UPDATE ="/update/{id}";
        public static final String DELETE ="/delete/{id}";
        public static final String HISTORY ="/fetch/all/history";
    }

    public static final class TimeSlot {
        public static final String ROOT = BASE_API + "/time/slot";
        public static final String REGISTER ="/register";
        public static final String UPDATE ="/update/{id}";
        public static final String FETCH = "/fetch";
    }

    public static final class UserAuth {
        public static final String REGISTER = "/register";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/signout";
        public static final String HOME = "/home";
        public static final String FETCH_USER = "/fetch/user";
        public static final String UPDATE_PROFILE = "/update/profile";
        public static final String UPDATE_PASSWORD = "/user/change-password";
        public static final String DELETE = "/delete/{id}";
    }

    public static final class UserRole {
        public static final String ROOT = BASE_API + "/user/role";
        public static final String SET = "/set";
        public static final String UPDATE = "/update/{id}";
    }

    private ApiPaths() {}
}
