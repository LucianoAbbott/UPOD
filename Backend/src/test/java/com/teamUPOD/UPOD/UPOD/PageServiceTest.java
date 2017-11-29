package com.teamUPOD.UPOD.UPOD;


import org.junit.Assert;
import org.junit.Test;

public class PageServiceTest {

	private PageService fixture = new PageService();
	//TODO: everything about the testing
	
	
	@Test
	public void updatePageTest() {
	}

	@Test
	public void deletePageTest() throws Exception {
		Assert.assertFalse(fixture.deletePage(0));
	}
	
	@Test
	public void getPageTest() throws Exception {
		Assert.assertNull(fixture.getPage(0));
	}

}
