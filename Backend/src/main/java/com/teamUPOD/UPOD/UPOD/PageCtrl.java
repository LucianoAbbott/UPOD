package com.teamUPOD.UPOD.UPOD;

import org.springframework.web.bind.annotation.RestController;

import datatypes.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Home of the api endpoints for page operations
 * @author luciano
 */
@RestController
public class PageCtrl {
	//TODO: Tests for this class don't work
	private PageService pageService = new PageService();
	
	/**
	 * Endpoint to post a page to the database with a given id
	 * 
	 * @param pageId 	id of the page to update - if unused creates new page at that id
	 * @param page   	the page object to put in the database
	 * @return Success or failure http code
	 */
	@RequestMapping(method = RequestMethod.POST, value = "update/{pageid}")
    public ResponseEntity<Boolean> updatePage(@PathVariable("pageid") int pageId, @RequestBody Page page) {
    		if (pageService.updatePage(pageId, page)) {
    			return new ResponseEntity<Boolean>(HttpStatus.OK);
    		}
        return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
	/**
	 * Endpoint to delete a page from the database 
	 * 
	 * @param pageId 	id of the page to update - if unused creates new page at that id
	 * @return Success or failure http code
	 */
    @RequestMapping(method = RequestMethod.DELETE, value = "delete/{pageid}")
    public ResponseEntity<Boolean> deletePage(@PathVariable("pageid") int pageId) {
		if (pageService.deletePage(pageId)) {
			return new ResponseEntity<Boolean>(HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

	/**
	 * Endpoint to get a page from the database 
	 * 
	 * @param pageId 	id of the page to update - if unused creates new page at that id
	 * @return Success code & page object or failure http code 
	 */
    @RequestMapping(method = RequestMethod.GET, value = "get/{pageid}")
    public ResponseEntity<Page> getPage(@PathVariable("pageid") int pageId) {
    		Page page = pageService.getPage(pageId);
    		if (page != null) {
    			return new ResponseEntity<Page>(page, HttpStatus.OK);
    		}
		return new ResponseEntity<Page>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}