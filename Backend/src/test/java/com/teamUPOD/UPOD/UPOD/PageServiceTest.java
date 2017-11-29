package com.teamUPOD.UPOD.UPOD;


import org.junit.Assert;
import org.junit.Test;

import datatypes.Page;

public class PageServiceTest {

	private PageService fixture = new PageService();
	
	@Test
	public void updatePageTest() {
		Assert.assertFalse(fixture.setPage(0, new Page()));
	}

	@Test
	public void deletePageTest() {
		Assert.assertFalse(fixture.deletePage(0));
	}
	
	@Test
	public void getPageTest() {
		Assert.assertNull(fixture.getPage(0));
	}

}
