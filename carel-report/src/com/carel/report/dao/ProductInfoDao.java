package com.carel.report.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carel.report.model.ProductInfo;

public class ProductInfoDao extends AbstractBaseDao<ProductInfo> {

	@Override
	public List<ProductInfo> findAll() {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<ProductInfo> sc = new SqlCore<ProductInfo>(connection);
			return sc.findAll(ProductInfo.class);
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

		return new ArrayList<ProductInfo>();
	}

	@Override
	public List<ProductInfo> where(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<ProductInfo> sc = new SqlCore<ProductInfo>(connection);
			return sc.find(ProductInfo.class, query, params);
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

		return new ArrayList<ProductInfo>();
	}

	@Override
	public ProductInfo first(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<ProductInfo> sc = new SqlCore<ProductInfo>(connection);
			return sc.first(ProductInfo.class, query, params);
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
			SqlCore<ProductInfo> sc = new SqlCore<ProductInfo>(connection);
			return sc.delete(ProductInfo.class);
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
	public boolean insert(ProductInfo o) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<ProductInfo> sc = new SqlCore<ProductInfo>(connection);
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
			SqlCore<ProductInfo> sc = new SqlCore<ProductInfo>(connection);
			return sc.delete(ProductInfo.class, query, params);
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
			SqlCore<ProductInfo> sc = new SqlCore<ProductInfo>(connection);
			return sc.update(ProductInfo.class, update, query, params);
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
