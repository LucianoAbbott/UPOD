package com.teamUPOD.UPOD.UPOD;

import java.sql.SQLException;

public class VariableService {
	private UpodDao upodDao;
	
	VariableService() {
		upodDao = UpodDao.getInstance();
	}
	
	public void deleteVariable (int varId) throws SQLException {
		upodDao.deleteVariable(varId);
	}
}
