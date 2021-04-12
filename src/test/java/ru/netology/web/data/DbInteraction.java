package ru.netology.web.data;


import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbInteraction {

    public static Payment getPayment() {
        String dataSQL = "SELECT * FROM payment_entity;";
        var runner = new QueryRunner();
        try (
            val conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/app", "app", "pass"
            )
        ) {
            Payment register = runner.query(conn, dataSQL, new BeanHandler<>(Payment.class));
            return register;
        } catch (SQLException e) {
            System.out.println("Не удалось получить доступ к таблице Payment");
        }
        return null;

    }

    public static Credit getCredit() {
        String dataSQL = "SELECT * FROM credit_request_entity;";
        var runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            Credit register = runner.query(conn, dataSQL, new BeanHandler<>(Credit.class));
            return register;
        } catch (SQLException e) {
            System.out.println("Не удалось получить доступ к таблице Credit");
        }
        return null;

    }

    public static void checkRegisterCount(String tabName, long expectedRecordCount) {
        String countSQL = "SELECT COUNT(*) FROM " + tabName;
        var runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            assertEquals(expectedRecordCount, (long) runner.query(conn, countSQL, new ScalarHandler<>())
                    ,"неверное количество записей в таблице " + tabName+" ");
            return ;
        } catch (SQLException e) {
            System.out.println("Не удалось получить доступ к таблице " + tabName );
        }
        return ;
    }

    public static void clearTables() {
        var runner = new QueryRunner();
        String rmOrder = "DELETE FROM order_entity;";
        String rmPayment = "DELETE FROM payment_entity;";
        String rmCredit = "DELETE FROM credit_request_entity;";
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            runner.update(conn, rmOrder);
            runner.update(conn, rmPayment);
            runner.update(conn, rmCredit);

        } catch (SQLException e) {
            System.out.println("Не удалось очистить таблицы базы данных");
        }
    }


}
