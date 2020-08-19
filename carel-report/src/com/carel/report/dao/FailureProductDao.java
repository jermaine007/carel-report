package com.carel.report.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carel.report.model.FailureProduct;


public class FailureProductDao extends AbstractBaseDao<FailureProduct> {

	@Override
	public List<FailureProduct> findAll() {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProduct> sc = new SqlCore<FailureProduct>(connection);
			return sc.findAll(FailureProduct.class);
		} catch (SQLException e) {
			getLogger().error("error:", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					getLogger().error("error:", e);
				}
			}
		}

		return new ArrayList<FailureProduct>();
	}

	@Override
	public List<FailureProduct> where(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProduct> sc = new SqlCore<FailureProduct>(connection);
			return sc.find(FailureProduct.class, query, params);
		} catch (SQLException e) {
			getLogger().error("error:", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					getLogger().error("error:", e);
				}
			}
		}

		return new ArrayList<FailureProduct>();
	}

	@Override
	public FailureProduct first(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProduct> sc = new SqlCore<FailureProduct>(connection);
			return sc.first(FailureProduct.class, query, params);
		} catch (SQLException e) {
			getLogger().error("error:", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					getLogger().error("error:", e);
				}
			}
		}
		return null;
	}

	@Override
	public boolean update(String update, String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProduct> sc = new SqlCore<FailureProduct>(connection);
			return sc.update(FailureProduct.class, update, query, params);
		} catch (SQLException e) {
			getLogger().error("error:", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					getLogger().error("error:", e);
				}
			}
		}
		return false;
	}

	@Override
	public boolean delete(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProduct> sc = new SqlCore<FailureProduct>(connection);
			return sc.delete(FailureProduct.class, query, params);
		} catch (SQLException e) {
			getLogger().error("error:", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					getLogger().error("error:", e);
				}
			}
		}
		return false;
	}

	@Override
	public boolean delete() {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProduct> sc = new SqlCore<FailureProduct>(connection);
			return sc.delete(FailureProduct.class);
		} catch (SQLException e) {
			getLogger().error("error:", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					getLogger().error("error:", e);
				}
			}
		}
		return false;
	}

	@Override
	public boolean insert(FailureProduct o) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProduct> sc = new SqlCore<FailureProduct>(connection);
			return sc.insert(o);
		} catch (SQLException e) {
			getLogger().error("error:", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					getLogger().error("error:", e);
				}
			}
		}
		return false;
	}

}
