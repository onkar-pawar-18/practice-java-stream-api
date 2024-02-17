package com.streamapi;

import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {
        displayCustomer(new Customer(1,"Onkar"));
        /*Consumer<Customer> customerConsumer = new Consumer<Customer>() {
            @Override
            public void accept(Customer customer) {
                System.out.println(customer);
            }
        };*/
        Consumer<Customer> customerConsumer =  customer -> System.out.println(customer);

        customerConsumer.accept(new Customer(2,"Pawar"));
    }

    static class Customer {
        private final int customerId;
        private final String customerName;

        Customer(int customerId, String customerName) {
            this.customerId = customerId;
            this.customerName = customerName;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "customerId=" + customerId +
                    ", customerName='" + customerName + '\'' +
                    '}';
        }
    }


    public static void displayCustomer(Customer customer) {
        System.out.println(customer);
    }

}
