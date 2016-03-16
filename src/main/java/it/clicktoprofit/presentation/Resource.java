package it.clicktoprofit.presentation;

import it.clicktoprofit.business.BusinessDelegate;
import it.clicktoprofit.email.*;
import it.clicktoprofit.utility.JsonTransformer;
import it.clicktoprofit.utility.Log;
import spark.servlet.SparkApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by Gioele on 31/12/2015.
 */
public class Resource implements SparkApplication {

    private static final String API_CONTEXT = "/api/v1";
    private BusinessDelegate businessDelegate;

    @Override
    public void init() {
        staticFileLocation("/public");
        this.businessDelegate = new BusinessDelegate();
        setupEndpoints();
    }

    private void setupEndpoints() {

        get(API_CONTEXT + "/autenticate", "application/json", (request, response) -> {
            ArrayList<String> data = new ArrayList<>();
            data.add(request.queryParams("email"));
            data.add(request.queryParams("password"));
            try {
                return businessDelegate.autenticate(data);
            } catch (Exception e) {
                return e.toString() +
                        " --- " + e.getStackTrace().toString() +
                        " --- {{" + e.getCause().toString() +
                        " --- " + e.getCause().getStackTrace() +
                        " --- " + e.getCause().getMessage() + "}}";
            }
        }, new JsonTransformer());

        // ADMIN

        get(API_CONTEXT + "/addAdmin", "application/json", (request, response) -> {
            try {
                return businessDelegate.addAdmin(request.queryParams("id_user"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/deleteAdmin", "application/json", (request, response) -> {
            try {
                return businessDelegate.deleteAdmin(request.queryParams("id_user"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getAdmins", "application/json", (request, response) -> {
            try {
                return businessDelegate.getAdmins();
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getHomeStrings", "application/json", (request, response) -> {
            try {
                return businessDelegate.getHomeStrings();
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/updateHomeStrings", "application/json", (request, response) -> {
            try {
                ArrayList<String> arr = new ArrayList<>();
                arr.add(request.queryParams("header1"));
                arr.add(request.queryParams("header2"));
                arr.add(request.queryParams("chi_siamo"));
                arr.add(request.queryParams("faq1_question"));
                arr.add(request.queryParams("faq1"));
                arr.add(request.queryParams("faq2_question"));
                arr.add(request.queryParams("faq2"));
                arr.add(request.queryParams("faq3_question"));
                arr.add(request.queryParams("faq3"));
                arr.add(request.queryParams("faq4_question"));
                arr.add(request.queryParams("faq4"));
                arr.add(request.queryParams("payments"));
                arr.add(request.queryParams("contacts"));
                businessDelegate.updateHomeStrings(arr);
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        // CAMPAIGNS

        get(API_CONTEXT + "/addCampaign", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("company"));
                data.add(request.queryParams("type"));
                data.add(request.queryParams("notes"));
                data.add(request.queryParams("link"));
                data.add(request.queryParams("profit"));
                data.add(request.queryParams("deadline"));
                return businessDelegate.addCampaign(data);
            } catch (Exception e) {
                e.printStackTrace();
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getAllCampaigns", "application/json", (request, response) -> {
            try {
                return businessDelegate.getAllCampaigns();
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getCampaigns", "application/json", (request, response) -> {
            try {
                return businessDelegate.getCampaigns();
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        // PAYMENT METHODS

        get(API_CONTEXT + "/addPaymentMethod", "application/json", (request, response) -> {
            try {
                return businessDelegate.addPaymentMethod(request.queryParams("method"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getPaymentMethods", "application/json", (request, response) -> {
            try {
                return businessDelegate.getPaymentMethods();
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        // PAYMENT

        get(API_CONTEXT + "/addPayment", "application/json", (request, response) -> {
            try {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(request.queryParams("id_payment_method"));
                arr.add(request.queryParams("id_user"));
                arr.add(request.queryParams("payment"));
                arr.add(request.queryParams("date"));
                businessDelegate.addPayment(arr);
                arr = new ArrayList<>();
                arr.add(request.queryParams("email"));
                arr.add(request.queryParams("method"));
                arr.add(request.queryParams("payment"));
                arr.add(request.queryParams("date"));
                AbstractEmail email = new PaymentAdded(arr);
                email.start();
                return response;
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getUserPayments", "application/json", (request, response) -> {
            try {
                return businessDelegate.getUserPayments(request.queryParams("id_user"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        // PAYMENTS TEMP

        get(API_CONTEXT + "/addPaymentTemp", "application/json", (request, response) -> {
            try {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(request.queryParams("payment_method"));
                arr.add(request.queryParams("notes"));
                arr.add(request.queryParams("payment"));
                arr.add(request.queryParams("id_user"));
                return businessDelegate.addPaymentTemp(arr);
            } catch (Exception e) {
                e.printStackTrace();
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getPaymentsTemp", "application/json", (request, response) -> {
            try {
                return businessDelegate.getPaymentsTemp();
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/deletePaymentTemp", "application/json", (request, response) -> {
            try {
                return businessDelegate.deletePaymentTemp(request.queryParams("id_user"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        // USERS

        get(API_CONTEXT + "/addUser", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("email"));
                data.add(request.queryParams("password"));
                data.add(request.queryParams("name"));
                data.add(request.queryParams("surname"));
                data.add(request.queryParams("birthdate"));
                return businessDelegate.addUser(data);
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/updateUserPassword", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("id_user"));
                data.add(request.queryParams("password"));
                return businessDelegate.updateUserPassword(data);
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/updateUserData", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("id_user"));
                data.add(request.queryParams("email"));
                data.add(request.queryParams("name"));
                data.add(request.queryParams("surname"));
                data.add(request.queryParams("birthdate"));
                return businessDelegate.updateUserData(data);
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/updateUserCurrentBalance", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("id_user"));
                data.add(request.queryParams("current_balance"));
                return businessDelegate.updateUserCurrentBalance(data);
            } catch (SQLException e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        get(API_CONTEXT + "/getIdByEmail", "application/json", (request, response) -> {
            try {
                return businessDelegate.getIdByEmail(request.queryParams("email"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getUserData", "application/json", (request, response) -> {
            try {
                return businessDelegate.getUserData(request.queryParams("id_user"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getAllUsers", "application/json", (request, response) -> {
            try {
                return businessDelegate.getAllUsers();
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getUserCurrentBalance", "application/json", (request, response) -> {
            try {
                return businessDelegate.getUserCurrentBalance(request.queryParams("id_user"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        // USERS OPERATIONS

        get(API_CONTEXT + "/addOperation", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("id_user"));
                data.add(request.queryParams("id_campaign"));
                data.add(request.queryParams("profit"));
                data.add(request.queryParams("date"));
                data.add(request.queryParams("time"));
                return businessDelegate.addOperation(data);
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/acceptOperation", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("id_user"));
                data.add(request.queryParams("id_campaign"));
                businessDelegate.acceptOperation(data);
                data = new ArrayList<>();
                data.add(request.queryParams("email"));
                data.add(request.queryParams("company"));
                data.add(request.queryParams("type"));
                data.add(request.queryParams("link"));
                data.add(request.queryParams("profit"));
                data.add(request.queryParams("date"));
                data.add(request.queryParams("time"));
                AbstractEmail email = new OperationAccepted(data);
                email.start();
                return response;
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/deleteOperation", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("id_user"));
                data.add(request.queryParams("id_campaign"));
                businessDelegate.deleteOperation(data);
                data = new ArrayList<>();
                data.add(request.queryParams("email"));
                data.add(request.queryParams("company"));
                data.add(request.queryParams("type"));
                data.add(request.queryParams("link"));
                data.add(request.queryParams("profit"));
                data.add(request.queryParams("date"));
                data.add(request.queryParams("time"));
                AbstractEmail email = new OperationRefused(data);
                email.start();
                return response;
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getUserOperations", "application/json", (request, response) -> {
            try {
                return businessDelegate.getUserOperations(request.queryParams("id_user"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/getAllNotAcceptedOperations", "application/json", (request, response) -> {
            try {
                return businessDelegate.getAllNotAcceptedOperations();
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        // USERS PAYMENT METHODS

        get(API_CONTEXT + "/getUserPaymentMethods", "application/json", (request, response) -> {
            try {
                return businessDelegate.getUserPaymentMethods(request.queryParams("id_user"));
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());
        get(API_CONTEXT + "/addUserPaymentMethod", "application/json", (request, response) -> {
            try {
                ArrayList<String> data = new ArrayList<String>();
                data.add(request.queryParams("id_user"));
                data.add(request.queryParams("idPaymentMethod"));
                data.add(request.queryParams("notes"));
                return businessDelegate.addUserPaymentMethod(data);
            } catch (Exception e) {
                halt(500, e.toString());
            }
            return null;
        }, new JsonTransformer());

        //EMAIL METHODS
        get(API_CONTEXT + "/sendEmailForm", "application/json", (request, response) -> {
            AbstractEmail mailForm = new MailForm(request.queryParams("object"), request.queryParams("text"), request.queryParams("email"));
            mailForm.start();
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "/userPasswordRecovery", "application/json", (request, response) -> {
            String id_user = businessDelegate.getIdByEmail(request.queryParams("email"));
            String newPsw = getSaltString();
            ArrayList<String> arr = new ArrayList<>();
            arr.add(id_user);
            arr.add(newPsw);
            businessDelegate.updateUserPassword(arr);
            arr = new ArrayList<String>();
            arr.add(request.queryParams("email"));
            arr.add(newPsw);
            AbstractEmail userPasswordRecovery = new UserPasswordRecovery(arr);
            userPasswordRecovery.start();
            return response;
        }, new JsonTransformer());

    }

    private String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvxwyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
