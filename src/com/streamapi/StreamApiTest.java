package com.streamapi;

import com.streamapi.entity.Customer;
import com.streamapi.entity.Order;
import com.streamapi.entity.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamApiTest {

    public static void main(String[] args) {

        Product product1 = new Product(1, "Laptop", "Electronics", 57000, null);
        Product product2 = new Product(2, "Mobile", "Electronics", 20000, null);
        Product product3 = new Product(3, "Car", "Vehicle", 500000, null);
        Product product4 = new Product(4, "Bike", "Vehicle", 150000, null);
        Product product5 = new Product(5, "TV", "Electronics", 70000, null);
        Product product6 = new Product(6, "Desk", "Furniture", 15000, null);
        Product product7 = new Product(7, "Cupboard", "Furniture", 20000, null);

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        productList1.add(product3);

        List<Product> productList2 = new ArrayList<>();
        productList2.add(product4);
        productList2.add(product5);
        productList2.add(product6);

        List<Product> productList3 = new ArrayList<>();
        productList3.add(product7);

        List<Product> productList4 = new ArrayList<>();
        productList4.add(product2);
        productList4.add(product5);

        List<Product> productList5 = new ArrayList<>();
        productList5.add(product4);
        productList5.add(product3);

        Order order1 = new Order(10, "Pending", LocalDate.now(), LocalDate.parse("2023-01-01"), productList1);
        Order order2 = new Order(11, "Cancelled", LocalDate.now(), LocalDate.parse("2023-01-01"), productList2);
        Order order3 = new Order(12, "Processed", LocalDate.now(), LocalDate.parse("2023-01-03"), productList3);
        Order order4 = new Order(13, "Processed", LocalDate.now(), LocalDate.parse("2023-01-04"), productList4);
        Order order5 = new Order(14, "Processed", LocalDate.now(), LocalDate.parse("2023-01-05"), productList5);

        List<Order> orderList1 = new ArrayList<>();
        orderList1.add(order1);
        product1.setOrders(orderList1);

        List<Order> orderList2 = new ArrayList<>();
        orderList2.add(order1);
        orderList2.add(order4);
        product2.setOrders(orderList2);

        List<Order> orderList3 = new ArrayList<>();
        orderList3.add(order1);
        orderList3.add(order5);
        product3.setOrders(orderList3);

        List<Order> orderList4 = new ArrayList<>();
        orderList4.add(order2);
        orderList4.add(order5);
        product4.setOrders(orderList4);

        List<Order> orderList5 = new ArrayList<>();
        orderList5.add(order2);
        orderList5.add(order4);
        product5.setOrders(orderList5);

        List<Order> orderList6 = new ArrayList<>();
        orderList6.add(order2);
        product6.setOrders(orderList6);

        List<Order> orderList7 = new ArrayList<>();
        orderList7.add(order3);
        product7.setOrders(orderList7);

        List<Order> orderList8 = new ArrayList<>();
        orderList8.add(order1);
        orderList8.add(order2);
        orderList8.add(order3);

        List<Order> orderList9 = new ArrayList<>();
        orderList9.add(order4);
        orderList9.add(order5);

        List<Order> allOrders = new ArrayList<>();
        allOrders.add(order1);
        allOrders.add(order2);
        allOrders.add(order3);
        allOrders.add(order4);
        allOrders.add(order5);

        List<Product> allProducts = new ArrayList<>();
        allProducts.add(product1);
        allProducts.add(product2);
        allProducts.add(product3);
        allProducts.add(product4);
        allProducts.add(product5);
        allProducts.add(product6);
        allProducts.add(product7);


        Customer customer1 = new Customer(101, "Onkar", 51, orderList8);
        Customer customer2 = new Customer(102, "Sunny", 52, orderList9);

        List<Customer> allCustomers = new ArrayList<>();
        allCustomers.add(customer1);
        allCustomers.add(customer2);

        //Display order details of customer1
        //customer1.getOrders().forEach(System.out::println);

        //Display products of order2
        //order2.getProducts().forEach(System.out::println);

        //Display orders of product5
        //product5.getOrders().forEach(System.out::println);

        //Obtain a list of products belongs to category electronics and price > 15000
        List<Product> products = allProducts.stream()
                .filter(product -> "Electronics".equals(product.getCategory()) && product.getPrice() > 20000)
                .toList();
        System.out.println(products);

        //Obtain list of orders with products belongs to category Furniture
        List<Order> orders = allOrders.stream()
                .filter(order -> order.getProducts().stream().anyMatch(product -> "Furniture".equals(product.getCategory())))
                .collect(Collectors.toList());
        System.out.println(orders);

        //Obtain the list of products with category Vehicle then apply 10% discount
        List<Product> products2 = allProducts.stream()
                .filter(product -> "Vehicle".equals(product.getCategory()))
                .map(product -> {
                    product.setPrice(product.getPrice() + (product.getPrice() * 0.1));
                    return product;
                })
                .collect(Collectors.toList());
        System.out.println(products2);

        List<Product> orders2 = allOrders.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> "Furniture".equals(product.getCategory())))
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toList());
        System.out.println(orders2);

        //Get the cheapest product of books category
        Product product = allProducts.stream().min((p1, p2) -> p1.getPrice() > p2.getPrice() ? 1 : -1)
                .get();
        System.out.println(product);

        //Get the 3 most recent placed orders
        List<Order> orders3 = allOrders.stream()
                .sorted(Comparator.comparing(Order::getDeliveryDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(orders3);

        //Get list of orders which has delivery date "2023-01-01" log the order records on console and return the product list
        List<Product> products3 = allOrders.stream()
                .filter(order -> order.getDeliveryDate().compareTo(LocalDate.parse("2023-01-01")) == 0)
                .peek(order -> System.out.println(order))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(products3);

        //Calculate total lump sum of all orders placed in feb 2023
        double sum = allOrders.stream()
                .filter(order -> order.getDeliveryDate().isAfter(LocalDate.parse("2023-01-01")) && order.getDeliveryDate().isBefore(LocalDate.parse("2023-01-05")))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice).sum();
        System.out.println(sum);

        //Calculate order avg payment placed on 2023-01-01
        double asDouble = allOrders.stream()
                .filter(order -> order.getDeliveryDate().isEqual(LocalDate.parse("2023-01-01")))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice).average().getAsDouble();
        System.out.println(asDouble);

        //Obtain collection of statistics fig (sum, avg, min,...) for all products of category "Furniture"
        String string = allProducts.stream()
                .filter(p -> "Furniture".equals(p.getCategory()))
                .mapToDouble(Product::getPrice)
                .summaryStatistics().toString();
        System.out.println(string);

        //Obtain a data map with order id and order's product count
        Map<Integer, Long> collect = allOrders.stream().collect(
                Collectors.toMap(order -> order.getOrderId(), order -> order.getProducts().stream().count())
        );
        System.out.println(collect);

        //Produce a data map with order records group by Date
        Map<LocalDate, List<Order>> collect1 = allOrders.stream().collect(Collectors.groupingBy(Order::getDeliveryDate));
        System.out.println(collect1);

        //Produce a data map with order record and product total sum
        Map<Order, Object> collect2 = allOrders.stream()
                .collect(Collectors.toMap(Function.identity(),
                        order -> order.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum()));
        System.out.println(collect2);

        //Obtain a data map with list of product name by category
        Map<String, List<Object>> collect3 = allProducts.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getPrice, Collectors.toList())));
        System.out.println(collect3);


        //Obtain a data map with product count group by category
        Map<String, Long> collect4 = allProducts.stream().collect(
                Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.counting()))
        );
        System.out.println(collect4);

        //Obtain a data map with sum of product price group by category
        Map<String, Double> collect5 = allProducts.stream().collect(
                Collectors.groupingBy(Product::getCategory,
                        Collectors.summingDouble(Product::getPrice))
        );
        System.out.println(collect5);


        //Obtain a data map with partitioning the products having price >= 50000 and <=50000
        Map<Boolean, List<Product>> collect6 = allProducts.stream().collect(
                Collectors.partitioningBy(p8 -> p8.getPrice() >= 50000)
        );
        System.out.println(collect6);

        //Obtain all customers who have purchased products having price == 20000
        List<Customer> collect7 = allCustomers.stream()
                .filter(customer -> customer.getOrders().stream()
                        .anyMatch(order -> order.getProducts().stream()
                                .anyMatch(product8 -> product8.getPrice() == 20000)))
                .collect(Collectors.toList());
        System.out.println(collect7);

        //Obtain all products having price = 20000
        List<Product> collect9 = allCustomers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .filter(product8 -> product8.getPrice() == 20000)
                .collect(Collectors.toList());
        System.out.println(collect9);

        //Obtain all products having price grater than avg product price
        List<Product> collect8 = allProducts.stream()
                .filter(product8 -> product8.getPrice() >= allProducts.stream()
                        .mapToDouble(Product::getPrice).average().getAsDouble()).toList();
        System.out.println(collect8);

        //Obtain the orders that have none of the products belong to category "Furniture"
        List<Order> orderList = allOrders.stream()
                .filter(order -> order.getProducts().stream()
                        .noneMatch(product8 -> product8.getCategory().equals("Furniture")))
                .collect(Collectors.toList());
        System.out.println(orderList);

        //Obtain the customer names whose order's lump sum is > 600000.
        List<String> customers = allCustomers.stream()
                .filter(customer -> customer.getOrders()
                        .stream().anyMatch(order -> {
                            double sum1 = order.getProducts().stream()
                                    .mapToDouble(value -> value.getPrice())
                                    .sum();
                            return sum1 > 600000;
                        }))
                .map(customer -> customer.getCustomerName())
                .collect(Collectors.toList());

        System.out.println(customers);

        //Obtain the data map of customer and their max order total
        Map<String, Double> collect10 = allCustomers.stream()
                .collect(Collectors.toMap(Customer::getCustomerName,
                        customer -> customer.getOrders()
                                .stream().mapToDouble(order -> order.getProducts()
                                        .stream().mapToDouble(Product::getPrice).sum()
                                ).max().getAsDouble()));
        System.out.println(collect10);

        //Obtain all products having price > 50000
        List<String> collect11 = allCustomers.stream()
                .flatMap(customer -> customer.getOrders()
                        .stream().flatMap(order -> order.getProducts()
                                .stream().filter(product8 -> product8.getPrice() > 50000)))
                .distinct()
                .map(product8 -> product8.getName())
                .collect(Collectors.toList());
        System.out.println(collect11);


    }
}
