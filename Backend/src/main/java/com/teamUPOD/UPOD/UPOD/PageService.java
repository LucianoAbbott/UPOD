package com.teamUPOD.UPOD.UPOD;

import datatypes.Page;

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
	 * Find an available Id and create a page at that id
	 * @param page
	 * @return true if page was created successfully
	 */
	public boolean createPage(Page page) {
		return upodDao.updatePage(upodDao.nextAvailableId(UpodDao.PAGE_TABLE_KEY), page);
	}
	
	/**
	 * Update the page with the given id
	 * @param pageId
	 * @param page
	 * @return true if page was updated
	 */
	public boolean updatePage(int pageId, Page page) {
		if (upodDao.pageExists(pageId)) {
			return upodDao.updatePage(pageId, page);
		}
		return false;
	}

	/**
	 * Delete the page with the given id
	 * @param pageId
	 * @return true if the page was deleted
	 */
	public boolean deletePage(int pageId) {
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
	public Page getPage(int pageId) {
		if (upodDao.pageExists(pageId)) {
			return upodDao.getPage(pageId);
		}
		return null;
	}

}
