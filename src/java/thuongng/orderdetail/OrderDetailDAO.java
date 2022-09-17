/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import thuongng.cart.CartObjectV2;
import thuongng.cart.CartObjectV2List;
import thuongng.utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO implements Serializable {

    public void addOrderDetail(int OrderID, CartObjectV2List cart)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL Statement String
            if (con != null) {
                for (CartObjectV2 book : cart.getList()) {
                    String sql = "Insert INTO OrderDetail(quantity,price,productID,orderID) "
                            + "Values(?,?,?,?)";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, book.getQuantity());
                    stm.setInt(2, book.getProduct().getPrice());
                    stm.setInt(3, book.getProduct().getProductID());
                    stm.setInt(4, OrderID);
                    int row = stm.executeUpdate();
                }

                //3. Create Statement to set SQL
                //4. Execute Quer
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
    }
}
