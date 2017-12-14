package com.teamUPOD.UPOD.UPOD;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import datatypes.Page;
import datatypes.Section;

public class PageServiceTest {

	private PageService fixture = new PageService(null);
	//TODO: everything about the testing
	
	@Test
	public void updatePageTest() {
	}

	@Test
	public void deletePageTest() throws Exception {
//		Assert.assertFalse(fixture.deletePage(0));
	}
	
	@Test
	public void getPageTest() throws Exception {
//		Assert.assertNull(fixture.getPage(0));
	}

	@Test
	public void sortPagesByRelevanceTest() {
		
	}
	
	@Test
	public void calculatePageRelevanceTest() {
		String helloInput = "hello"; // only in title
		String titleInput = "title"; // title of each section
		String emptyInput = "";
		
		Page page = buildPage();
		
		double helloActualRelevance, titleActualRelevance, emptyActualRelevance;
		double helloExpectedRelevance = (double)1 / (double)61;
		double titleExpectedRelevance = (double)10 / (double)61;
		double emptyExpectedRelevance = 0;
		
		helloActualRelevance = fixture.calculatePageRelevance(helloInput, page);
		titleActualRelevance = fixture.calculatePageRelevance(titleInput, page);
		emptyActualRelevance = fixture.calculatePageRelevance(emptyInput, page);
		
		
		Assert.assertEquals(helloActualRelevance, helloExpectedRelevance, 0.001);
		Assert.assertEquals(titleActualRelevance, titleExpectedRelevance, 0.001);
		Assert.assertEquals(emptyActualRelevance, emptyExpectedRelevance, 0.001);
	}

	private Page buildPage() {
		Page page = new Page();
		page.setTitle("hello");
		page.setSections(buildSections());
		return page;
	}

	private ArrayList<Section> buildSections() {
		ArrayList<Section> result = new ArrayList<Section>();
		Section s;
		for (int i = 0; i < 10; i++) {
			s = new Section(i, "title", "the five words per section", null, null);
			result.add(s);
		}
		return result;
	}
		
	
}
