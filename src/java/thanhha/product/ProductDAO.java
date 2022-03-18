package thanhha.product;

import thanhha.util.DBHelper;

import javax.naming.NamingException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Serializable {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public List<ProductDTO> getAllProducts(boolean status) throws SQLException, NamingException {
        List<ProductDTO> productList = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT id, [name], imageUrl, price, in_stock " +
                        " FROM product " +
                        " WHERE status = ?";
                statement = connection.prepareStatement(sql);
                statement.setBoolean(1, status);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String imageUrl = resultSet.getString("imageUrl");
                    BigDecimal price = resultSet.getBigDecimal("price");
                    int inStock = resultSet.getInt("in_stock");

                    ProductDTO product = new ProductDTO(id, name, imageUrl, price, inStock);
                    if (productList == null) {
                        productList = new ArrayList<>();
                    }
                    productList.add(product);
                }
            }
        } finally {
            DBHelper.closeConnection(connection, resultSet, statement);
        }
        return productList;
    }

    public ProductDTO getProductById(String idValue) throws SQLException, NamingException {
        ProductDTO product = null;
        try {
            connection = DBHelper.makeConnection();
            if (connection != null) {
                String sql = "SELECT id, [name], price, in_stock " +
                        " FROM product " +
                        " WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, idValue);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String imageUrl = resultSet.getString("imageUrl");
                    BigDecimal price = resultSet.getBigDecimal("price");
                    int in_stock = resultSet.getInt("in_stock");
                    product = new ProductDTO(id, name, imageUrl, price, in_stock);
                }
            }
        } finally {
            DBHelper.closeConnection(connection, resultSet, statement);
        }
        return product;
    }
}
