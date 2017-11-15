package com.teamUPOD.UPOD.UPOD;


import org.junit.Assert;
import org.junit.Test;

public class PageServiceTest {

	@Test
	public void updatePageTest() {
		Assert.assertFalse(PageService.updatePage());
	}

	@Test
	public void deletePageTest() {
		Assert.assertFalse(PageService.deletePage());
	}
	
	@Test
	public void createPageTest() {
		Assert.assertFalse(PageService.createPage());
	}

}
