package com.IusCloud.legalCore.core.features.branch.application.dto;


import com.IusCloud.legalCore.core.base.BaseDTO;
import com.IusCloud.legalCore.shared.enums.BranchTypesEnum;




public class BranchResponseDTO extends BaseDTO {


    private String name;

    private BranchTypesEnum type;

    private String city;

    private String country;

    private String state;

    private String address;

    private String email;

    private String phone1;

    private String phone2;

    private String description;

    private String legalRepresentative;


}
