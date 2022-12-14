package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.payment_methods.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

/*
Returns a specific Payment in the database based on the id of the Payment

 */
public class RetrievePaymentDB {

    public static Payment retrieve(int id) {

        String sql = "SELECT * FROM payments WHERE payment_id = " + id;

        Payment payment = null;
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String paymentType = queryResult.getString("paymentType");
            if (Objects.equals(paymentType, "creditdebit")) {
                String cardNumber = queryResult.getString("cardNumber");
                String ccv = queryResult.getString("ccv");
                LocalDate date = LocalDate.parse(queryResult.getString("validUntil"));
                payment = new CreditDebit(cardNumber, ccv, date.getMonthValue(), date.getYear());
            } else if (Objects.equals(paymentType, "paypal")) {
                String email = queryResult.getString("email");
                String pwd = queryResult.getString("pwd");
                payment = new Paypal(email, pwd);
            } else if (Objects.equals(paymentType, "googlepay")) {
                String email = queryResult.getString("email");
                String pwd = queryResult.getString("pwd");
                payment = new GooglePay(email, pwd);
            } else if (Objects.equals(paymentType, "vipps")) {
                String tlfnr = queryResult.getString("tlfnr");
                String pincode = queryResult.getString("pincode");
                payment = new Vipps(tlfnr, pincode);
            }
            return payment;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return payment;
    }
/*
    public static Payment retrieveWithPriorConnection(int id, Connection conn) throws SQLException {

        String sql = "SELECT * FROM payments WHERE payment_id = " + id;

        Payment payment = null;
        PreparedStatement str = conn.prepareStatement(sql);

        ResultSet queryResult = str.executeQuery();
        String paymentType = queryResult.getString("paymentType");
        if (Objects.equals(paymentType, "creditdebit")) {
            String cardNumber = queryResult.getString("cardNumber");
            String ccv = queryResult.getString("ccv");
            LocalDate date = LocalDate.parse(queryResult.getString("validUntil"));
            payment = new CreditDebit(cardNumber, ccv, date.getMonthValue(), date.getYear());
        } else if (Objects.equals(paymentType, "paypal")) {
            String email = queryResult.getString("email");
            String pwd = queryResult.getString("pwd");
            payment = new Paypal(email, pwd);
        } else if (Objects.equals(paymentType, "googlepay")) {
            String email = queryResult.getString("email");
            String pwd = queryResult.getString("pwd");
            payment = new GooglePay(email, pwd);
        } else if (Objects.equals(paymentType, "vipps")) {
            String tlfnr = queryResult.getString("tlfnr");
            String pincode = queryResult.getString("pincode");
            payment = new Vipps(tlfnr, pincode);
        }

        return payment;
    }*/


}