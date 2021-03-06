package ru.netology.web.data;


import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.web.models.Credit;
import ru.netology.web.models.Payment;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Value
public class DbInteraction {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.pass");

    public static Payment getPayment() {
        String dataSQL = "SELECT * FROM payment_entity;";
        var runner = new QueryRunner();
        try (
            val conn = DriverManager.getConnection(
                    url, user, password)
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
                        url, user, password
                )
        ) {
            Credit register = runner.query(conn, dataSQL, new BeanHandler<>(Credit.class));
            return register;
        } catch (SQLException e) {
            System.out.println("Не удалось получить доступ к таблице Credit");
        }
        return null;

    }


    public static long getRecordCountOfPaymentEntity() {
        String countSQL = "SELECT COUNT(*) FROM payment_entity;";
        var runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                )
        ) {
            long count = runner.query(conn, countSQL, new ScalarHandler<>());
            return count;
        }
        catch (SQLException e) {
            System.out.println("Не удалось получить доступ к таблице Payment");
        }

        return 0;
    }

    public static long getRecordCountOfCreditEntity() {
        String countSQL = "SELECT COUNT(*) FROM credit_request_entity;";
        var runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
                )
        ) {
            long count = runner.query(conn, countSQL, new ScalarHandler<>()).hashCode();
            return count;
        }
        catch (SQLException e) {
            System.out.println("Не удалось получить доступ к таблице Payment");
        }

        return 0;
    }

    public static void clearTables() {
        var runner = new QueryRunner();
        String rmOrder = "DELETE FROM order_entity;";
        String rmPayment = "DELETE FROM payment_entity;";
        String rmCredit = "DELETE FROM credit_request_entity;";
        try (
                val conn = DriverManager.getConnection(
                        url, user, password
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
