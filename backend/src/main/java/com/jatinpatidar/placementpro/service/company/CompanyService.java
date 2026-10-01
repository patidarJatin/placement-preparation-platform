package com.jatinpatidar.placementpro.service.company;

import com.jatinpatidar.placementpro.dto.company.request.CompanyCreateRequest;
import com.jatinpatidar.placementpro.dto.company.response.CompanyResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse createCompany(CompanyCreateRequest request);
    List<CompanyResponse> getAllCompanies();
    CompanyResponse getCompanyById(Long id);
    CompanyResponse updateCompany(Long id,CompanyCreateRequest request);
    void deleteCompany(Long id);
}
