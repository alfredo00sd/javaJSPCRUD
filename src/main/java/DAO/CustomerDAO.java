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

                customer.setId(rs.getInt("id"));
                customer.setPhoto(rs.getString("foto"));
                customer.setName(rs.getString("nombres"));
                customer.setLastName(rs.getString("apellidos"));
                customer.setAge((byte) rs.getInt("edad"));
                customer.setProfession(rs.getString("profesion"));
                customer.setGender(rs.getString("genero").charAt(0));
                customer.setHobbies(rs.getString("hobbies"));
                customer.setAddress(rs.getString("direccion"));
                customer.setLevelOfSatisfaction(rs.getString("satisfaccion"));
                customer.setFavoriteColor(rs.getString("color_favorito"));

                list.add(customer);
            }
        } catch (SQLException SqlEx) {
            SqlEx.printStackTrace();
        }
        return list;
    }

    @Override
    public CustomerBean listById(int id) {
        return null;
    }

    @Override
    public boolean add(CustomerBean customer) {

        String insertCustomerQuery =
                "INSERT INTO `customers` (`id`, `foto`, `nombres`, `apellidos`, `edad`, `profesion`, `genero`, `hobbies`, `direccion`, `satisfaccion`, `color_favorito`) " +
                        "VALUES (NULL, '"+customer.getPhoto()+"', '"+customer.getName()+"', '"+customer.getLastName()+"', '"+customer.getAge()+"', '"+customer.getProfession()+"', '"+customer.getGender()+"', '"+customer.getHobbies()+"', '"+customer.getAddress()+"', '"+customer.getLevelOfSatisfaction()+"', '"+customer.getFavoriteColor()+"')";

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
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
