package it.pkg.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import it.pkg.model.User;
import it.pkg.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springmvc.xml")
public class TestUser
{

	private static final Logger logger = LogManager.getLogger(TestUser.class.getName());

	@Autowired
	IUserService userService;

	@Test
	public void TestGetUser()
	{
		for (int i = 0; i < 1; ++i)
		{
			logger.trace("trace level");
			logger.debug("debug level");
			logger.info("info level");
			logger.warn("warn level");
			logger.error("error level");
			logger.fatal("fatal level");
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		User user = userService.getUserById("1");
		assertTrue(user.getName().equals("user1"));
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(user.getName());
	}

	@Test
	public void TestGetCount()
	{
		long recordCount = userService.getCount();
		logger.info("TestGetCount: " + recordCount);
	}

	@Test
	public void TestGetUsersPaginition()
	{
		List<User> users = userService.getUsersPagenition(1, 13);
		logger.info("TestGetUsersPaginition: " + JSON.toJSONStringWithDateFormat(users, "yyyy-MM-dd HH:mm:ss"));
	}
}