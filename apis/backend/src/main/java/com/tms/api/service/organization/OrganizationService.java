package com.tms.api.service.organization;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.common.objects.models.api.ResponseModel;
import com.common.repository.logging.LoggingApiRepository;
import com.tms.api.objects.models.organization.OrganizationDetailModel;
import com.tms.api.objects.models.organization.OrganizationModel;
import com.tms.api.objects.models.organization.api.AddNewOrganizationRequestModel;
import com.tms.api.objects.models.organization.document.OrganizationDocument;
import com.tms.api.objects.models.organization.document.UserOrganizationLinkedDocument;
import com.tms.api.objects.types.user.UserRoleType;
import com.tms.api.objects.viewmodels.organization.OrganizationViewModel;
import com.tms.api.repository.database.organization.OrganizationRepository;
import com.tms.api.repository.database.organization.UserOrganizationLinkedRepository;
import com.tms.api.service.BaseService;

@Service
public class OrganizationService extends BaseService {
    
    private final OrganizationRepository _organizationRepository;
    private final UserOrganizationLinkedRepository _userOrganizationLinkedRepository;


    public OrganizationService(ModelMapper modelMapper,
        OrganizationRepository organizationRepository,
        UserOrganizationLinkedRepository userOrganizationLinkedRepository,
        LoggingApiRepository loggingApiRepository)
    {
        super(loggingApiRepository, modelMapper);

        _organizationRepository = organizationRepository;
        _userOrganizationLinkedRepository = userOrganizationLinkedRepository;
    }


    public ResponseModel<List<OrganizationViewModel>> GetOrganizations(String userId) {
        try {
            var userOrganizationLinkedDocument = GetUserOrganizationLinkedDocument(userId);
            var organizationDetails =  userOrganizationLinkedDocument.organizationDetails();

            var organizationIds = organizationDetails
                .stream()
                .map(od -> od.organizationId())
                .collect(Collectors.toList());

            var userOrganizations = _organizationRepository.findAllById(organizationIds);

            var response = userOrganizations
                .stream()
                .map(o -> new OrganizationViewModel(new OrganizationModel(o.id(), o.name(), o.createdDate(), o.modifiedDate()), GetOrganizationDetail(organizationDetails, o.id())))
                .collect(Collectors.toList());

            return GetResponse(response, null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "GetOrganizations");
            map.put("Request", MessageFormat.format("UserId: {0}", userId));
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            
            LogException(exception, map);
            return GetResponse(Collections.emptyList(), exception.toString());
        }
    }

    public ResponseModel<OrganizationModel> SaveOrganization(OrganizationModel saveOrganizationRequest) {
        try {
            var organizationDocument = _modelMapper.map(saveOrganizationRequest, OrganizationDocument.class);
            var insertedOrganizationDocument = _organizationRepository.save(organizationDocument);

            var insertedOrganization = _modelMapper.map(insertedOrganizationDocument, OrganizationModel.class);
            return GetResponse(insertedOrganization, null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "SaveOrganization");
            map.put("Request", saveOrganizationRequest);
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<OrganizationModel> getOrganizationById(String id) {
        try {
            var organizationDocument = _organizationRepository.findById(id).orElse(null);
            var response = _modelMapper.map(organizationDocument, OrganizationModel.class);
            return GetResponse(response, null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "GetOrganizations");
            map.put("Request", MessageFormat.format("OrganizationId: {0}", id));
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    public ResponseModel<String> AddOrganization(AddNewOrganizationRequestModel request) {
        try {
            var organizationDocumentRequest = new OrganizationDocument(null, request.organizationName(), GetDateNow(), null);
            var newOrganization = _organizationRepository.save(organizationDocumentRequest);

            var organizationDetail = new OrganizationDetailModel(newOrganization.id(), UserRoleType.Owner, GetDateNow(), request.userId(), null, null);

            var userOrganizationLinkedDocument = GetUserOrganizationLinkedDocument(request.userId());
            if (userOrganizationLinkedDocument == null) {
                var organizationDetails = new ArrayList<OrganizationDetailModel>();
                organizationDetails.add(organizationDetail);

                userOrganizationLinkedDocument = new UserOrganizationLinkedDocument(null, organizationDetails, request.userId(), GetDateNow());
            } else {
                userOrganizationLinkedDocument.organizationDetails().add(organizationDetail);
            }

            _userOrganizationLinkedRepository.save(userOrganizationLinkedDocument);
            return GetResponse(newOrganization.id(), null);
        } catch (Exception exception) {
            var map = new HashMap<String, Object>();
            map.put("Method", "AddOrganization");
            map.put("Request", request);
            map.put("Exception", ExceptionUtils.getStackTrace(exception));
            LogException(exception, map);

            return GetResponse(null, exception.toString());
        }
    }

    private OrganizationDetailModel GetOrganizationDetail(List<OrganizationDetailModel> organizationDetails, String organizationId) {
        var organizationDetail = organizationDetails
            .stream()
            .filter(od -> od.organizationId().equals(organizationId))
            .findFirst()
            .orElse(null);

        return organizationDetail;
    }

    private UserOrganizationLinkedDocument GetUserOrganizationLinkedDocument(String userId) {
        var userOrganizationLinkedDocument = _userOrganizationLinkedRepository.findByUserId(userId);
        return userOrganizationLinkedDocument;
    }
}
