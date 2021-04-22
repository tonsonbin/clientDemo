package com.tb.pro.common.jdbc;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestJdbc extends JdbcBuild{

	private Logger log = Logger.getLogger(TestJdbc.class);
	
	@Test
	public void test01(){
		log.info(this.findAllList("select*from sys_user where name like ?", "%1%"));
	}
}
