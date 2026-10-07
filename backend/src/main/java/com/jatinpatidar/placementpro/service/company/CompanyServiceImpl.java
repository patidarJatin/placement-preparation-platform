package com.jatinpatidar.placementpro.service.company;

import com.jatinpatidar.placementpro.dto.company.request.CompanyCreateRequest;
import com.jatinpatidar.placementpro.dto.company.response.CompanyResponse;
import com.jatinpatidar.placementpro.entity.Company;
import com.jatinpatidar.placementpro.exceptions.CompanyAlreadyExistsException;
import com.jatinpatidar.placementpro.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponse createCompany(CompanyCreateRequest request){

        Optional<Company> exitingCompany = companyRepository.findBySlug(request.getSlug());

        if(exitingCompany.isPresent()){
            throw new CompanyAlreadyExistsException("Company Already Present");
        }

        Company company = new Company();

        company.setCompanyName(request.getCompanyName());
        company.setSlug(request.getSlug());
        company.setLogoUrl(request.getLogoUrl());
        company.setWebsiteUrl(request.getWebsiteUrl());
        company.setDescription( request.getDescription());

        Company savedCompany = companyRepository.save(company);

        CompanyResponse response = new CompanyResponse(
                savedCompany.getId(),
                savedCompany.getCompanyName(),
                savedCompany.getSlug(),
                savedCompany.getLogoUrl(),
                savedCompany.getWebsiteUrl(),
                savedCompany.getDescription(),
                savedCompany.getLastUpdated());

        return response;
    }

    @Override
    public List<CompanyResponse> getAllCompanies(){
       List<Company> companies = companyRepository.findAll();

       List<CompanyResponse> responses = new ArrayList<>();

       for(Company company :companies){
           CompanyResponse response = new CompanyResponse(
           company.getId(),
           company.getCompanyName(),
           company.getSlug(),
           company.getLogoUrl(),
           company.getWebsiteUrl(),
           company.getDescription(),
           company.getLastUpdated()
           );
           responses.add(response);
       }
    return responses;
    }


}
