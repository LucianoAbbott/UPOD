package com.teamUPOD.UPOD.UPOD;

import java.sql.SQLException;

import datatypes.Page;
import datatypes.Table;

/**
 * Perform application logic related to the pages
 * @author luciano abbott
 */
public class PageService {
	private UpodDao upodDao;
	
	PageService() {
		upodDao = UpodDao.getInstance();
	}

	/**
	 * Update the page with the given id
	 * @param page
	 * @return true if page was updated
	 * @throws SQLException 
	 */
	public void setPage(Page page) throws SQLException {
		if (page.getId() == -1) {
			page.setId(upodDao.nextAvailableId(Table.PAGE));
		}
		upodDao.setPage(page);
	}

	/**
	 * Delete the page with the given id
	 * @param pageId
	 * @return true if the page was deleted
	 */
	public boolean deletePage(int pageId) throws SQLException {
		if (upodDao.pageExists(pageId)) {
			return upodDao.deletePage(pageId);
		}
		return false;
	}

	/**
	 * Get the page with the given id
	 * @param pageId
	 * @return the page, null if page not found
	 */
	public Page getPage(int pageId) throws SQLException {
		if (upodDao.pageExists(pageId)) {
			return upodDao.getPage(pageId);
		}
		return null;
	}

}
