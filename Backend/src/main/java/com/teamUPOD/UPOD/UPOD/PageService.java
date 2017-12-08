package com.teamUPOD.UPOD.UPOD;

import java.sql.SQLException;
import java.util.ArrayList;

import datatypes.FrequencyMap;
import datatypes.Page;
import datatypes.Table;
import utils.SearchUtils;

/**
 * Perform application logic related to the pages
 * @author luciano abbott
 */
public class PageService {
	private UpodDao upodDao;
	private static final int MAX_QUERY_RESULT_COUNT = 7;
	
	
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

	/**
	 * Given query string, return a list of pages sorted by relevance to that string
	 * @param query
	 * @return 
	 */
	public ArrayList<Page> queryPages(String query) {
		SearchUtils.cleanQuery(query);
		ArrayList<Page> pages = upodDao.queryPages(query);
		return sortPagesByRelevance(query, pages);
	}
	
	/**
	 * Given list of pages and a query string, sort the pages by their relevance to that string
	 * @param query
	 * @param pages
	 * @return
	 */
	public ArrayList<Page> sortPagesByRelevance(String query, ArrayList<Page> pages) {
		for (Page page : pages) {
			page.setRelevance(calculatePageRelevance(query, page));
		}
		pages.sort(null);
		return (ArrayList<Page>) pages.subList(0, MAX_QUERY_RESULT_COUNT);
	}
	
	/**
	 * Calculate the relevance of page to query
	 * @param query
	 * @param page
	 * @return
	 */
	public double calculatePageRelevance(String query, Page page) {
		double relevance = 0;
		String pageText = SearchUtils.cleanQuery(page.toString());
		FrequencyMap frequencyMap = SearchUtils.countWords(pageText);
		String[] terms = query.split(" ");
		for (String term : terms) {
			relevance += frequencyMap.get(term);
			relevance /= frequencyMap.getWordCount();
		}
		return relevance;
	}
}
