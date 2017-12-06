package com.teamUPOD.UPOD.UPOD;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import datatypes.Page;
import utils.Logger;

/**
 * Home of the api endpoints for page operations
 * @author luciano
 */
@RestController
public class PageCtrl {
	//TODO: Tests for this class don't work
	private PageService pageService = new PageService();
	private final String ENDPOINT_PREFIX = "/page";
	private final String UPDATE_ENDPOINT = ENDPOINT_PREFIX + "/update";
	private final String GET_ENDPOINT = ENDPOINT_PREFIX + "/get/{pageid}";
	private final String DELETE_ENDPOINT = ENDPOINT_PREFIX + "/delete/{pageid}";
	
	/**
	 * Endpoint to post a page to the database with a given id
	 * 
	 * @param pageId 	id of the page to update - if INVALID_ID creates a new page with the next available id
	 * @param page   	the page object to put in the database
	 * @return Success or failure http code
	 * @throws SQLException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = UPDATE_ENDPOINT)
    public ResponseEntity<String> updatePage(@RequestBody Page page) throws SQLException {
		pageService.setPage(page);
		Logger.logUpdate(page);
		return new ResponseEntity<String>(page.getTitle() + " successfully created at " + page.getUrl(), HttpStatus.OK);
    }
    
	/**
	 * Endpoint to delete a page from the database 
	 * 
	 * @param pageId 	id of the page to update - if unused creates new page at that id
	 * @return Success or failure http code
	 * @throws SQLException 
	 */
    @RequestMapping(method = RequestMethod.DELETE, value = DELETE_ENDPOINT)
    public ResponseEntity<String> deletePage(@PathVariable("pageid") int pageId) throws SQLException {
    		pageService.deletePage(pageId);
    		Logger.logDelete(pageId);
		return new ResponseEntity<String>("Page successfully deleted", HttpStatus.OK);
    }

	/**
	 * Endpoint to get a page from the database 
	 * 
	 * @param pageId 	id of the page to update - if unused creates new page at that id
	 * @return Success code & page object or failure http code 
	 * @throws SQLException 
	 */
    @RequestMapping(method = RequestMethod.GET, value = GET_ENDPOINT)
    public ResponseEntity<Page> getPage(@PathVariable("pageid") int pageId) throws SQLException {
    		Page page = pageService.getPage(pageId);
    		Logger.logGet(page);
		return new ResponseEntity<Page>(page, HttpStatus.OK);
    }
}