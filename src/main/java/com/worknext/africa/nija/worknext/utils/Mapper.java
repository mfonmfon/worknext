package com.worknext.africa.nija.worknext.utils;

import com.worknext.africa.nija.worknext.data.enums.UserRole;
import com.worknext.africa.nija.worknext.exceptions.RequiredFieldException;

public class Mapper {

    public static void validateAllNullAndEmptyInput(String email, String companyDescription, String companyLocation, String companyName, String password, UserRole role) throws RequiredFieldException {
        if(email == null || email.trim().isEmpty()) throw new RequiredFieldException("Email is required");
        if(companyName == null || companyName.trim().isEmpty()) throw new RequiredFieldException("Company name is required");
        if(password == null || password.trim().isEmpty()) throw new RequiredFieldException("Password is required");
        if(companyDescription == null || companyDescription.trim().isEmpty()) throw new RequiredFieldException("Company description is required");
        if(companyLocation == null || companyLocation.trim().isEmpty()) throw new RequiredFieldException("Company location is required");
        if (role == null || role.toString().trim().isEmpty()) throw new RequiredFieldException("Role is required");
    }



}
