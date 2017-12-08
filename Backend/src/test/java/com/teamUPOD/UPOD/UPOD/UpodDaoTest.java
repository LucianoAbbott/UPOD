//package com.teamUPOD.UPOD.UPOD;
//
//
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import datatypes.Page;
//
//public class UpodDaoTest{
//
//	private UpodDao fixture;
//
//	
//	//TODO: construct should have the @Before annotation
//	public void connection() throws SQLException {
//		Page expected = new Page();
//		UpodDao fixture = Mockito.spy(UpodDao.class);
//		Statement mockStatement = Mockito.mock(Statement.class);
//
//		Mockito.when(fixture.createStatement()).thenReturn(mockStatement);    // Mock implementation
//		Mockito.when(mockStatement.executeQuery("SELECT * FROM PAGE WHERE pageId = " + 1)).thenReturn(null);
//		
//		Page page = fixture.getPage(1);
//		
//		
//		assertEquals(expectedTitle, page.getTitle());
//	}
//	
//	@Test
//	public void pageExistsTest() throws Exception {
//		construct();
//		Assert.assertFalse(fixture.pageExists(0));
//	}
//
//	@Test
//	public void addPageTest() {
//		construct();
//		fixture.setPage(new Page());
//	}
//	
//	@Test
//	public void deletePageTest() throws Exception {
//		construct();
//		Assert.assertFalse(fixture.deletePage(0));
//	}
//
//}
