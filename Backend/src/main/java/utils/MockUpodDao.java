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
	public void deletePage(int id) {
	}
	
	
	@Override
	public Page getPage(int id) {
		return new Page(1, "title", "url");
	}
	
	@Override
	public ArrayList<Page> searchPages(String query) {
		return buildPages();
	}
	
	private ArrayList<Page> buildPages() {
		ArrayList<Page> result = new ArrayList<Page>();
		for (int i = 0; i < 7; i++) {
			result.add(new Page(i, "page #" + i, "page/" + i));
		}
		return result;
	}
}
