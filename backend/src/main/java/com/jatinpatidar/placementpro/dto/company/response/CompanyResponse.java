package com.jatinpatidar.placementpro.dto.company.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

    private Long id;
    private String companyName;
    private String slug;
    private String logoUrl;
    private String websiteUrl;
    private String description;
    private LocalDateTime lastUpdated;

}
