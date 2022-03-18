/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thanhha.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import thanhha.util.DBHelper;

/**
 *
 * @author DELL
 */
public class CartDAO {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;
    
    public boolean insertItemToCart(String productId, String customerId) throws NamingException, SQLException {
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "INSERT INTO cart(productId, customerId) " +
                        "VALUES(?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, productId);
                statement.setString(2, customerId);
                int rows = statement.executeUpdate();

                if (rows > 0) {
                    return true;
                }
            }
        } finally {
            DBHelper.closeConnection(connection, resultSet, statement);
        }
        return false;
    }
    
    public boolean updateItemInCart(String productId, String customerId) throws NamingException, SQLException {
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "UPDATE cart "
                        + "SET quantity = quantity + 1 "
                        + "WHERE customerId = ? and productId = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, customerId);
                statement.setString(2, productId);
                int rows = statement.executeUpdate();

                if (rows > 0) {
                    return true;
                }
            }
        } finally {
            DBHelper.closeConnection(connection, resultSet, statement);
        }
        return false;
    }
    
    public boolean deleteItemInCart(String productId, String customerId) throws NamingException, SQLException {
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "DELETE FROM cart "
                        + "WHERE customerId = ? and productId = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, customerId);
                statement.setString(2, productId);
                int rows = statement.executeUpdate();

                if (rows > 0) {
                    return true;
                }
            }
        } finally {
            DBHelper.closeConnection(connection, resultSet, statement);
        }
        return false;
    }
    
    public Map<String, Integer> getItemsInCart(String customerId) 
            throws NamingException, SQLException {
        Map<String, Integer> items = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT productId, quantity "
                        + " FROM cart "
                        + " WHERE customerId = ? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, customerId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String productId = resultSet.getString("productId");
                    int quantity = resultSet.getInt("quantity");
                    
                    if (items == null) {
                        items = new HashMap<>();
                    }
                    items.put(productId, quantity);
                }
            }
        } finally {
            DBHelper.closeConnection(connection, resultSet, statement);
        }
        return items;
    }
    
    public List<CartDTO> getAllItemsInCart(String customerId) 
            throws NamingException, SQLException {
        List<CartDTO> cart = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT productId, quantity "
                        + " FROM cart "
                        + " WHERE customerId = ? ";
                statement = connection.prepareStatement(sql);
                statement.setString(1, customerId);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String productId = resultSet.getString("productId");
                    int quantity = resultSet.getInt("quantity");
                    
                    CartDTO item = new CartDTO(customerId, productId, quantity);
                    
                    if (cart == null) {
                        cart = new ArrayList<>();
                    }
                    cart.add(item);
                }
            }
        } finally {
            DBHelper.closeConnection(connection, resultSet, statement);
        }
        return cart;
    }
}
