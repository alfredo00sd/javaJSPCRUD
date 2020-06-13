package Interfaces;

import Model.CustomerBean;

import java.util.List;

public interface CRUD {

    List<CustomerBean> list();
    CustomerBean listById(int id);
    boolean add(CustomerBean customer);
    boolean edit(CustomerBean customer);
    boolean delete(int id);
}
