package com.tms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.objects.models.api.ResponseModel;
import com.tms.api.objects.models.organization.OrganizationModel;
import com.tms.api.objects.models.organization.api.AddNewOrganizationRequestModel;
import com.tms.api.objects.viewmodels.organization.OrganizationViewModel;
import com.tms.api.service.organization.OrganizationService;


@RestController
@RequestMapping("organization")
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService _organizationService;
    
    @GetMapping("getOrganizations")
    public ResponseModel<List<OrganizationViewModel>> GetOrganizations(String userId) {
        var organizations = _organizationService.GetOrganizations(userId);
        return organizations;
    }

    @GetMapping("getOrganizationById")
    public ResponseModel<OrganizationModel> GetOrganizationById(@RequestParam String Id) {
        var organization = _organizationService.getOrganizationById(Id);
        return organization;
    }
    
    @PostMapping("saveOrganization")
    public ResponseModel<OrganizationModel> SaveOrganization(@RequestBody OrganizationModel organization) {
        return _organizationService.SaveOrganization(organization);
    }

    @PostMapping("addOrganization")
    public ResponseModel<String> AddOrganization(@RequestBody AddNewOrganizationRequestModel request) {
        return _organizationService.AddOrganization(request);
    }
    
}
