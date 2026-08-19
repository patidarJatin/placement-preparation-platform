package com.jatinpatidar.placementpro.dto.company.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String slug;

    private String logoUrl;
    private String websiteUrl;
    private String description;

}
