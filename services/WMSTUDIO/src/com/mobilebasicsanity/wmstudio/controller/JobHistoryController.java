/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.mobilebasicsanity.wmstudio.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.mobilebasicsanity.wmstudio.JobHistory;
import com.mobilebasicsanity.wmstudio.JobHistoryId;
import com.mobilebasicsanity.wmstudio.service.JobHistoryService;


/**
 * Controller object for domain model class JobHistory.
 * @see JobHistory
 */
@RestController("WMSTUDIO.JobHistoryController")
@Api(value = "JobHistoryController", description = "Exposes APIs to work with JobHistory resource.")
@RequestMapping("/WMSTUDIO/JobHistory")
public class JobHistoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobHistoryController.class);

    @Autowired
	@Qualifier("WMSTUDIO.JobHistoryService")
	private JobHistoryService jobHistoryService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new JobHistory instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobHistory createJobHistory(@RequestBody JobHistory jobHistory) {
		LOGGER.debug("Create JobHistory with information: {}" , jobHistory);

		jobHistory = jobHistoryService.create(jobHistory);
		LOGGER.debug("Created JobHistory with information: {}" , jobHistory);

	    return jobHistory;
	}

    @ApiOperation(value = "Returns the JobHistory instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobHistory getJobHistory(@RequestParam("employeeId") Integer employeeId, @RequestParam("startDate") LocalDateTime startDate) {

        JobHistoryId jobhistoryId = new JobHistoryId();
        jobhistoryId.setEmployeeId(employeeId);
        jobhistoryId.setStartDate(startDate);

        LOGGER.debug("Getting JobHistory with id: {}" , jobhistoryId);
        JobHistory jobHistory = jobHistoryService.getById(jobhistoryId);
        LOGGER.debug("JobHistory details with id: {}" , jobHistory);

        return jobHistory;
    }



    @ApiOperation(value = "Updates the JobHistory instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobHistory editJobHistory(@RequestParam("employeeId") Integer employeeId, @RequestParam("startDate") LocalDateTime startDate, @RequestBody JobHistory jobHistory) {

        jobHistory.setEmployeeId(employeeId);
        jobHistory.setStartDate(startDate);

        LOGGER.debug("JobHistory details with id is updated with: {}" , jobHistory);

        return jobHistoryService.update(jobHistory);
    }


    @ApiOperation(value = "Deletes the JobHistory instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteJobHistory(@RequestParam("employeeId") Integer employeeId, @RequestParam("startDate") LocalDateTime startDate) {

        JobHistoryId jobhistoryId = new JobHistoryId();
        jobhistoryId.setEmployeeId(employeeId);
        jobhistoryId.setStartDate(startDate);

        LOGGER.debug("Deleting JobHistory with id: {}" , jobhistoryId);
        JobHistory jobHistory = jobHistoryService.delete(jobhistoryId);

        return jobHistory != null;
    }


    /**
     * @deprecated Use {@link #findJobHistories(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of JobHistory instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<JobHistory> searchJobHistoriesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering JobHistories list by query filter:{}", (Object) queryFilters);
        return jobHistoryService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of JobHistory instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<JobHistory> findJobHistories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering JobHistories list by filter:", query);
        return jobHistoryService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of JobHistory instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<JobHistory> filterJobHistories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering JobHistories list by filter", query);
        return jobHistoryService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportJobHistories(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return jobHistoryService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportJobHistoriesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = JobHistory.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> jobHistoryService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of JobHistory instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countJobHistories( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting JobHistories");
		return jobHistoryService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getJobHistoryAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return jobHistoryService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service JobHistoryService instance
	 */
	protected void setJobHistoryService(JobHistoryService service) {
		this.jobHistoryService = service;
	}

}