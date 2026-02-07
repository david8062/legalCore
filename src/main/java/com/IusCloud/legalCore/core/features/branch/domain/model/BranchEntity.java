package com.IusCloud.legalCore.core.features.branch.domain.model;


import com.IusCloud.legalCore.core.base.BaseModel;
import com.IusCloud.legalCore.shared.enums.BranchTypesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "branches")
@Getter
@Setter

public class BranchEntity extends BaseModel {

    @Column(name = "tenant_id")
    UUID tenantId;
    @Column(name = "name", nullable = false,length = 150)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private BranchTypesEnum type;
    @Column(name = "city", nullable = false,length = 150)
    private String city;
    @Column(name = "country", nullable = false,length = 50)
    private String country;
    @Column(name = "state", nullable = false,length = 50)
    private  String state;
    @Column(name = "address", nullable = false, length = 150)
    private String address;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "phone1", nullable = false)
    private Integer phone1;
    @Column(name = "phone2")
    private Integer phone2;
    @Column(name = "description", length = 250)
    private String description;
    @Column(name = "legal_representative", length = 80)
    private String legalRepresentative;

}
