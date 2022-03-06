package cn.luischen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySiteApplicationTests {

	@Autowired
	DataSource dataSource;
	@Test
	public void contextLoads() throws SQLException {
		System.out.println("连接的数据库-》"+dataSource.getConnection());

	}

}
