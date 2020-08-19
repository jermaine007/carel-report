package com.carel.report.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carel.report.model.FailureProductSum;

public class FailureProductSumDao extends AbstractBaseDao<FailureProductSum> {

	@Override
	public List<FailureProductSum> findAll() {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProductSum> sc = new SqlCore<FailureProductSum>(connection);
			return sc.findAll(FailureProductSum.class);
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

		return new ArrayList<FailureProductSum>();
	}

	@Override
	public List<FailureProductSum> where(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProductSum> sc = new SqlCore<FailureProductSum>(connection);
			return sc.find(FailureProductSum.class, query, params);
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

		return new ArrayList<FailureProductSum>();
	}

	@Override
	public FailureProductSum first(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProductSum> sc = new SqlCore<FailureProductSum>(connection);
			return sc.first(FailureProductSum.class, query, params);
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
	public boolean delete() {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProductSum> sc = new SqlCore<FailureProductSum>(connection);
			return sc.delete(FailureProductSum.class);
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
	public boolean insert(FailureProductSum o) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProductSum> sc = new SqlCore<FailureProductSum>(connection);
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

	@Override
	public boolean delete(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProductSum> sc = new SqlCore<FailureProductSum>(connection);
			return sc.delete(FailureProductSum.class, query, params);
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
	public boolean update(String update, String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<FailureProductSum> sc = new SqlCore<FailureProductSum>(connection);
			return sc.update(FailureProductSum.class, update, query, params);
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
