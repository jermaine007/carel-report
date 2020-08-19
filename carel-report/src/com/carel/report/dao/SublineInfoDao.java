package com.carel.report.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carel.report.model.SublineInfo;

public class SublineInfoDao extends AbstractBaseDao<SublineInfo> {
	@Override
	public List<SublineInfo> findAll() {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<SublineInfo> sc = new SqlCore<SublineInfo>(connection);
			return sc.findAll(SublineInfo.class);
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

		return new ArrayList<SublineInfo>();
	}

	@Override
	public List<SublineInfo> where(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<SublineInfo> sc = new SqlCore<SublineInfo>(connection);
			return sc.find(SublineInfo.class, query, params);
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

		return new ArrayList<SublineInfo>();
	}

	@Override
	public SublineInfo first(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<SublineInfo> sc = new SqlCore<SublineInfo>(connection);
			return sc.first(SublineInfo.class, query, params);
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
			SqlCore<SublineInfo> sc = new SqlCore<SublineInfo>(connection);
			return sc.update(SublineInfo.class, update, query, params);
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
			SqlCore<SublineInfo> sc = new SqlCore<SublineInfo>(connection);
			return sc.delete(SublineInfo.class, query, params);
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
			SqlCore<SublineInfo> sc = new SqlCore<SublineInfo>(connection);
			return sc.delete(SublineInfo.class);
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
	public boolean insert(SublineInfo o) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<SublineInfo> sc = new SqlCore<SublineInfo>(connection);
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
