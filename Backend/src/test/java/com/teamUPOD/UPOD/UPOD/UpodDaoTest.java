package com.teamUPOD.UPOD.UPOD;

import org.junit.Assert;
import org.junit.Test;

import datatypes.Page;

public class UpodDaoTest{

	private UpodDao fixture;
	
	//TODO: construct should have the @Before annotation
	public void construct() {
		fixture = UpodDao.getInstance();
	}
	
	@Test
	public void pageExistsTest() {
		construct();
		Assert.assertFalse(fixture.pageExists(0));
	}

	@Test
	public void addPageTest() {
		construct();
		Assert.assertFalse(fixture.updatePage(0, new Page()));
	}
	
	@Test
	public void deletePageTest() {
		construct();
		Assert.assertFalse(fixture.deletePage(0));
	}

}
