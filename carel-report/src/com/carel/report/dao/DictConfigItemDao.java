package com.carel.report.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carel.report.model.DictConfigItem;

public class DictConfigItemDao extends AbstractBaseDao<DictConfigItem> {
	@Override
	public List<DictConfigItem> findAll() {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<DictConfigItem> sc = new SqlCore<DictConfigItem>(connection);
			return sc.findAll(DictConfigItem.class);
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

		return new ArrayList<DictConfigItem>();
	}

	@Override
	public List<DictConfigItem> where(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<DictConfigItem> sc = new SqlCore<DictConfigItem>(connection);
			return sc.find(DictConfigItem.class, query, params);
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

		return new ArrayList<DictConfigItem>();
	}

	@Override
	public DictConfigItem first(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<DictConfigItem> sc = new SqlCore<DictConfigItem>(connection);
			return sc.first(DictConfigItem.class, query, params);
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
			SqlCore<DictConfigItem> sc = new SqlCore<DictConfigItem>(connection);
			return sc.update(DictConfigItem.class, update, query, params);
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
			SqlCore<DictConfigItem> sc = new SqlCore<DictConfigItem>(connection);
			return sc.delete(DictConfigItem.class, query, params);
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
			SqlCore<DictConfigItem> sc = new SqlCore<DictConfigItem>(connection);
			return sc.delete(DictConfigItem.class);
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
	public boolean insert(DictConfigItem o) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<DictConfigItem> sc = new SqlCore<DictConfigItem>(connection);
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
