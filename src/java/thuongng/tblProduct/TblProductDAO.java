/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuongng.tblProduct;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thuongng.cart.CartObjectV2;
import thuongng.cart.CartObjectV2List;
import thuongng.utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class TblProductDAO {

    private List<TblProductDTO> products;

    public List<TblProductDTO> getProducts() {
        return products;
    }

    public void getAllProduct()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL Statement String
            if (con != null) {
                String sql = "Select sku, nameProduct, price, description "
                        + "From tblProduct";

                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    int sku = rs.getInt("sku");
                    String nameProduct = rs.getString("nameProduct");
                    int price = rs.getInt("price");
                    String description = rs.getString("description");
                    //create DTO instance
                    TblProductDTO product = new TblProductDTO(sku, nameProduct, price, description);
                    //add to accounts list
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }//end account is initialized
                    //account has existed
                    this.products.add(product);
                }//end rs has more than one record
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
