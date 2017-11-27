package utils;

import java.util.Collections;
import java.util.HashMap;

import datatypes.Table;

/**
 * Class that maps tables to their Ids
 * @author luciano
 *
 */
public class TableIdMap {
	public static final String PAGE_TABLE_ID = "PageId";
	public static final String EQUATION_TABLE_ID = "equId";
	public static final String VARIABLE_TABLE_ID = "varId";
	public static final String SECTION_TABLE_ID = "sectionId";
	public static final String GRAPHIC_TABLE_ID = "graphicId";

	private static final HashMap<Table, String> TABLE_ID_MAP;
	static {
		HashMap<Table, String> map = new HashMap<Table, String>();
		map.put(Table.PAGE, PAGE_TABLE_ID);
		map.put(Table.EQUATION, EQUATION_TABLE_ID);
		map.put(Table.VARIABLE, VARIABLE_TABLE_ID);
		map.put(Table.SECTION, SECTION_TABLE_ID);
		map.put(Table.GRAPHIC, GRAPHIC_TABLE_ID);

		TABLE_ID_MAP = (HashMap<Table, String>) Collections.unmodifiableMap(map);
	}
	
	public static String getId(Table tableName) {
		return TABLE_ID_MAP.get(tableName);
	}
}
