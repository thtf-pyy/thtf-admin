package com.thtf.generate.server.dao.sql;
import com.thtf.generate.server.conventer.CommonMetaDataConverter;
import com.thtf.generate.server.conventer.MySQL5MetaDataConverter;
import com.thtf.generate.server.conventer.OracleMetaDataConverter;
import com.thtf.generate.server.dao.IMetaDataConverter;

/**
 * ========================
 * 数据库查询语句文件
 * Author：pyy
 * Date：2019-9-25 16:17:49
 * Version: v1.0
 * ========================
 */
public enum DatabaseType {

	Oracle {

		@Override
		public String getFileName() {
			return FOLDER + "/Oracle.xml";
		}

		@Override
		public IMetaDataConverter getConverter() {
			return OracleMetaDataConverter.getInstance();
		}
		
	},
	
	MySql5 {
		@Override
		public String getFileName() {
			return FOLDER + "/MySQL5.xml";
		}

		@Override
		public IMetaDataConverter getConverter() {
			return MySQL5MetaDataConverter.getInstance();
		}
	}, 
	
	MSSQLServer {
		@Override
		public String getFileName() {
			return FOLDER + "/MSSQL.xml";
		}
	};
	
	private static final String FOLDER	=
			DatabaseType.class.getPackage().getName().replace('.', '/');
	
	abstract public String getFileName();
	
	public IMetaDataConverter getConverter() {
		return CommonMetaDataConverter.getInstance();
	}
}
