/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.naming.NamingException;
import thuongng.cart.CartObjectV2List;
import thuongng.utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class OrdersDAO implements Serializable {

    public int addOrder(CartObjectV2List cart,String userID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int orderID = 0;
        LocalDate date = java.time.LocalDate.now();
        Date dateNow = Date.valueOf(date);
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL Statement String
            if (con != null) {
                String sql = "Insert INTO Orders(OrderDate,total,username) "
                        + "Values(?,?,?)";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setDate(1, dateNow);
                stm.setInt(2, cart.getAllPrice());
                stm.setString(3, userID);
                //4. Execute Query
                int affectedRow = stm.executeUpdate();
                //5. Process result
                if (affectedRow > 0) {
                    String sqlNew = "Select OrderID "
                            + "From Orders "
                            + "Where OrderID >= ALL(Select OrderID From Orders) ";
                    PreparedStatement stm1 = con.prepareStatement(sqlNew);
                    ResultSet rs1 = stm1.executeQuery();
                    if (rs1.next()) {
                        orderID = rs1.getInt("OrderID");
                    }
                    if (rs1 != null) {
                        rs1.close();
                    }
                    if (stm1 != null) {
                        stm1.close();
                    }
                }

                //end traverse Result Set
            } //end if connection is existed
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return orderID;
    }

}
