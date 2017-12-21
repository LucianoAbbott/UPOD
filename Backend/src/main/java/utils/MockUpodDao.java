package utils;

import java.util.ArrayList;

import UPOD.UpodDao;
import datatypes.Graphic;
import datatypes.Page;
import datatypes.Section;
import datatypes.Table;
import datatypes.Variable;

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
		Page page = new Page(1, "title", "url");
		page.setSections(buildSections());
		return page;
	}

	@Override
	public Page getPage(String s) {
		Page page = new Page(1, "title", "url");
		page.setSections(buildSections());
		return page;
	}

	private Section[] buildSections() {
		Section[] result = new Section[Page.MAX_SECTION_COUNT];
		Section s;
		for (int i = 0; i < 10; i++) {
			s = new Section(i, "title", "the five words per section", null, null);
			s.setGraphic(new Graphic(1, "https://lucianoabbott.github.io/UPOD/img/_i.gif", "test image pls ignor"));
			s.addVariables(buildVariables());
			s.setEquations("2 + 2 is 4 minus 1 thats 3 quik maffs");
			result[i] = s;
		}
		return result;
	}
	
	private ArrayList<Variable> buildVariables() {
		ArrayList<Variable> result = new ArrayList<Variable>();
		result.add(new Variable("V", "Velocity", "we fast", "the fuck is this url for", 0));
		return result;
	}

	
	@Override
	public ArrayList<Page> searchPages(String query) {
		return buildPages();
	}
	
	private ArrayList<Page> buildPages() {
		ArrayList<Page> result = new ArrayList<Page>();
		Page page;
		for (int i = 0; i < 7; i++) {
			page = new Page(i, "page #" + i, "page/" + i);
			page.setSections(buildSections());
			result.add(page);
		}
		return result;
	}
}
