/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author huynh
 */
public class OrderDAO {
    private static final String ADD_ORDER = "INSERT INTO [tblOrders](foodId, userId, quantity, price, money) VALUES(?,?,?,?,?)";
    
        public void addOrder(OrderDTO order) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_ORDER);
                ptm.setString(1, order.getFoodId());
                ptm.setString(2, order.getUserId());
                ptm.setInt(3, order.getQuantity());
                ptm.setDouble(4, order.getPrice());
                ptm.setDouble(5, order.getMoney());
                ptm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
