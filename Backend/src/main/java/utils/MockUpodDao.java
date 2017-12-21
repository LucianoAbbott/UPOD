package utils;

import java.util.ArrayList;

import UPOD.UpodDao;
import datatypes.Page;
import datatypes.Table;

public class MockUpodDao extends UpodDao {

	public MockUpodDao() {
		super(null);
	}
	
	@Override
	public boolean pageExists(int id) {
		return true;
	}
	
	@Override
	public void setPage(Page page) {
	}
	
	@Override
	public int nextAvailableId(Table table) {
		return 1;
	}

	@Override
	public boolean deletePage(int id) {
		return true;
	}
	
	
	@Override
	public Page getPage(int id) {
		return new Page(1, "title", "url");
	}
	
	@Override
	public ArrayList<Page> searchPages(String query) {
		return null;
	}
}
