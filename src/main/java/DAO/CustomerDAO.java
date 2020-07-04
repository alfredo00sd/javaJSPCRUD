package DAO;

import Config.Conexion;
import Interfaces.CRUD;
import Model.CustomerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CRUD {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    CustomerBean customerBean = new CustomerBean();

    public int getLastId() {

        String slq = "SELECT id+1 from customers order by id desc limit 0,1";
        int id = 0;

        try {
            conn = conexion.getConn();
            ps = conn.prepareStatement(slq);
            rs = ps.executeQuery();

            id = rs.next() ? id = rs.getInt("lastId") : -1;

        } catch (SQLException SQLEx) {
            SQLEx.printStackTrace();
        }
        return id;
    }

    @Override
    public List<CustomerBean> list() {
        //List to hold customers
        List<CustomerBean> list = new ArrayList<>();
        String getAllCustomersQuery = "select * from customers";

        try {
            conn = conexion.getConn();
            ps = conn.prepareStatement(getAllCustomersQuery);
            rs = ps.executeQuery();

            while (rs.next()) {
                CustomerBean customer = new CustomerBean();

                fillObject(customer);

                list.add(customer);
            }
        } catch (SQLException SqlEx) {
            SqlEx.printStackTrace();
        }
        return list;
    }

    private void fillObject(CustomerBean customer) throws SQLException {
        customer.setId(rs.getInt("id"));
        customer.setPhoto(rs.getString("foto"));
        customer.setName(rs.getString("nombres"));
        customer.setLastName(rs.getString("apellidos"));
        customer.setBirthDay(rs.getString("birthday"));
        customer.setAge((byte) rs.getInt("edad"));
        customer.setProfession(rs.getString("profesion"));
        customer.setGender(rs.getString("genero").charAt(0));
        customer.setHobbies(rs.getString("hobbies"));
        customer.setAddress(rs.getString("direccion"));
        customer.setLevelOfSatisfaction(rs.getString("satisfaccion"));
        customer.setFavoriteColor(rs.getString("color_favorito"));
    }

    @Override
    public CustomerBean listById(int id) {

        String sql = "select * from customers where id = '"+id+"'";

        try {
            conn = conexion.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {

                fillObject(customerBean);

            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return customerBean;
    }

    @Override
    public boolean add(CustomerBean customer) {

        String insertCustomerQuery =
                "INSERT INTO `customers` (`id`, `foto`, `nombres`, `apellidos`, `birthday`, `edad`, `profesion`, `genero`, `hobbies`, `direccion`, `satisfaccion`, `color_favorito`) " +
                        "VALUES (NULL, '"+customer.getPhoto()+"', '"+customer.getName()+"', '"+customer.getLastName()+"', '"+customer.getBirthDay()+"', '"+customer.getAge()+"', '"+customer.getProfession()+"', '"+customer.getGender()+"', '"+customer.getHobbies()+"', '"+customer.getAddress()+"', '"+customer.getLevelOfSatisfaction()+"', '"+customer.getFavoriteColor()+"')";

        try {
            conn = conexion.getConn();
            ps = conn.prepareStatement(insertCustomerQuery);
            ps.executeUpdate();

        } catch (SQLException SqlEx) {
            SqlEx.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean edit(CustomerBean customer) {
        String editQuery = "UPDATE `customers` SET `foto` = '"+customer.getPhoto()+"', `nombres` = '"+customer.getName()+"', `apellidos` = '"+customer.getLastName()+"'," +
                " `edad` = '"+customer.getAge()+"', `profesion` = '"+customer.getProfession()+"', `genero` = '"+customer.getGender()+"', `hobbies` = '"+customer.getHobbies()+"', " +
                "`direccion` = '"+customer.getAddress()+"', `satisfaccion` = '"+customer.getLevelOfSatisfaction()+"', `color_favorito` = '"+customer.getFavoriteColor()+"' WHERE `customers`.`id` = '"+customer.getId()+"' ";

        try {
            conn = conexion.getConn();
            ps = conn.prepareStatement(editQuery);
            ps.executeUpdate();

        } catch(SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {

        String sql = "delete from customers where id = '"+id+"' ";

        try {
            conn = conexion.getConn();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch(SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return false;
    }
}
