package com.IusCloud.legalCore.core.features.branch.application.dto;


import com.IusCloud.legalCore.shared.enums.BranchTypesEnum;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class BranchRequestDTO {
    @NotBlank
    @Size(max = 150)
    private String name;

    @NotNull
    private BranchTypesEnum type;

    @NotBlank
    @Size(max = 150)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String country;

    @NotBlank
    @Size(max = 50)
    private String state;

    @NotBlank
    @Size(max = 150)
    private String address;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phone1;

    @Size(max = 20)
    private String phone2;

    @Size(max = 250)
    private String description;

    @Size(max = 80)
    private String legalRepresentative;
}
