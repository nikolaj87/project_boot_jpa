package de.telran.lesson3.repository_layer;

import de.telran.lesson3.domain_layer.entity.Customer;

import java.util.List;

public class MySqlCustomerRepository implements CustomerRepository {

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public void add(String name) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void addToCartById(int customerId, int productId) {

    }

    @Override
    public void deleteFromCart(int customerId, int productId) {

    }

    @Override
    public void clearCart(int customerId) {

    }
}
