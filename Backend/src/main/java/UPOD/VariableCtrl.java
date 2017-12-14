package UPOD;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home of the endpoints for variable operations
 * @author luciano
 */
@RestController
public class VariableCtrl {
	private VariableService variableService;
	
	/**
	 * Endpoint to delete a page from the database 
	 * 
	 * @param pageId 	id of the page to update - if unused creates new page at that id
	 * @return Success or failure http code
	 * @throws SQLException 
	 */
    @RequestMapping(method = RequestMethod.DELETE, value = "variable/{varid}/delete")
    public ResponseEntity<String> deleteVariable(@PathVariable("varid") int varId) throws SQLException {
    		variableService.deleteVariable(varId);
		return new ResponseEntity<String>("Variable successfully deleted", HttpStatus.OK);
    }
}
