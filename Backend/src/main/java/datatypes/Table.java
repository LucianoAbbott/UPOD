package datatypes;

/**
 * Enum of tables and their names
 * @author Luciano Abbott
 *
 */
public enum Table {
	PAGE("Page"), EQUATION("EQUATION"), VARIABLE("VARIABLE"), SECTION("SECTION"), GRAPHIC("GRAPHIC");
	private String name;
	public String getName() {
		return this.name;
	}
	Table(String name) {
		this.name = name;
	}
}
