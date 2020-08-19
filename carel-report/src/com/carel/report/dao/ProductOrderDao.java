package com.carel.report.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carel.report.model.ProductOrder;

public class ProductOrderDao extends AbstractBaseDao<ProductOrder> {

	public boolean exists(String id) {
		ProductOrder order = first("serialid=?", id);
		return order != null;
	}

	@Override
	public List<ProductOrder> findAll() {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<ProductOrder> sc = new SqlCore<ProductOrder>(connection);
			return sc.findAll(ProductOrder.class);
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

		return new ArrayList<ProductOrder>();
	}

	@Override
	public List<ProductOrder> where(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<ProductOrder> sc = new SqlCore<ProductOrder>(connection);
			return sc.find(ProductOrder.class, query, params);
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

		return new ArrayList<ProductOrder>();
	}

	@Override
	public ProductOrder first(String query, Object... params) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<ProductOrder> sc = new SqlCore<ProductOrder>(connection);
			return sc.first(ProductOrder.class, query, params);
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
			SqlCore<ProductOrder> sc = new SqlCore<ProductOrder>(connection);
			return sc.delete(ProductOrder.class);
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
	public boolean insert(ProductOrder o) {
		Connection connection = null;
		try {
			connection = getConnection();
			SqlCore<ProductOrder> sc = new SqlCore<ProductOrder>(connection);
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
			SqlCore<ProductOrder> sc = new SqlCore<ProductOrder>(connection);
			return sc.delete(ProductOrder.class, query, params);
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
			SqlCore<ProductOrder> sc = new SqlCore<ProductOrder>(connection);
			return sc.update(ProductOrder.class, update, query, params);
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
