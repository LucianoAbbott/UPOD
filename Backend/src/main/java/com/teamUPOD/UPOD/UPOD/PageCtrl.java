package com.teamUPOD.UPOD.UPOD;

import org.springframework.web.bind.annotation.RestController;

import datatypes.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PageCtrl {

	//TODO: Mapping should be /pagename/update
    //TODO: ResponseEntity might need more info
    @RequestMapping("/update")
    public ResponseEntity<Page> updatePage() {
    		if (PageService.updatePage()) {
    			return new ResponseEntity<Page>(HttpStatus.OK);
    		}
        return new ResponseEntity<Page>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
	//TODO: Mapping should be /pagename/delete
    //TODO: ResponseEntity might need more info
    @RequestMapping("/delete")
    public ResponseEntity<Page> deletePage() {
		if (PageService.updatePage()) {
			return new ResponseEntity<Page>(HttpStatus.OK);
		}
		return new ResponseEntity<Page>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
	//TODO: Mapping should be /pagename/create
    //TODO: ResponseEntity might need more info
    @RequestMapping("/create")
    public ResponseEntity<Page> createPage() {
		if (PageService.updatePage()) {
			return new ResponseEntity<Page>(HttpStatus.OK);
		}
		return new ResponseEntity<Page>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}