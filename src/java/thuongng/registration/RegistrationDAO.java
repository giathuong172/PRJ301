/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thuongng.registration;

import java.sql.Connection;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thuongng.utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL Statement String
            if (con != null) {
                String sql = "Select username,password , lastname, isAdmin "
                        + "From Registration "
                        + "Where Username = ? And Password = ?";
                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
                if (rs.next()) {
                    String dtoLoginName = rs.getString("username");
                    String dtoPassword = rs.getString("password");
                    String dtoLastname = rs.getString("lastname");
                    boolean dtoIsAdmin = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(dtoLoginName, dtoPassword, dtoLastname, dtoIsAdmin);
                    return dto;
                }
            } //end if connection is existed
        } finally {
            if (rs != null) {
                rs.close();
                if (stm != null) {
                    stm.close();
                    if (con != null) {
                        con.close();
                    }
                }
            }
        }
        return null;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = searchValue.matches("[a-zA-Z]");
        if (check) {
            try {
                //1. Connect DB
                con = DBHelpers.makeConnection();
                //2. Create SQL Statement String
                if (con != null) {
                    String sql = "Select username, password, lastname, isAdmin "
                            + "From Registration "
                            + "Where lastname Like ?";
                    //3. Create Statement to set SQL
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + searchValue + "%");
                    //4. Execute Query
                    rs = stm.executeQuery();
                    //5. Process result
                    while (rs.next()) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        String lastname = rs.getString("lastname");
                        boolean role = rs.getBoolean("isAdmin");
                        //create DTO instance
                        RegistrationDTO dto
                                = new RegistrationDTO(username, password,
                                        lastname, role);
                        //add to accounts list
                        if (this.accounts == null) {
                            this.accounts = new ArrayList<>();
                        }//end account is initialized
                        //account has existed
                        this.accounts.add(dto);
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

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        PreparedStatement stmOrderDetail = null;
        PreparedStatement stmOrder = null;
        boolean result = false;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL Statement String
            if (con != null) {
                String sqlOrderDetail = "Delete From OrderDetail "
                        + "where orderID IN (Select orderID from Orders where username = ?)";
                String sqlOrder = "Delete From Orders "
                        + "Where username = ?";
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create Statement to set SQL
                stmOrderDetail = con.prepareStatement(sqlOrderDetail);
                stmOrderDetail.setString(1, username);
                int affectRowsOrderDetail = stmOrderDetail.executeUpdate();
                stmOrder = con.prepareStatement(sqlOrder);
                stmOrder.setString(1, username);
                int affectRowsOrder = stmOrder.executeUpdate();
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process
                if (affectedRows > 0) {
                    result = true;
                } //end if connection is existed
            }
        } finally {
            if (stmOrderDetail != null) {
                stmOrderDetail.close();
            }
            if (stmOrder != null) {
                stmOrder.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }

    public boolean updateAccount(String password, boolean isAdmin, String username, String fullName)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL Statement String
            if (con != null) {
                String sql = "Update Registration "
                        + "SET password = ? , isAdmin = ? , lastname = ? "
                        + "Where username = ?";
                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, fullName);
                stm.setString(4, username);
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process
                if (affectedRows > 0) {
                    result = true;
                } //end if connection is existed
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }

    public boolean createNewAccount(RegistrationDTO dto)
            throws SQLException, NamingException {

        if (dto == null) {
            return false;
        }

        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.  Connect to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Insert Into Registration "
                        + "Values(?, ?, ?, ?)";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getlastName());
                stm.setBoolean(4, dto.isRole());
                //4. Excecute Statement to get Result
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    return true;
                }
            }//end if connection has opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
