package com.carel.report.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.carel.report.Config;

public class StartupServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Config.initialize();
	}

}
