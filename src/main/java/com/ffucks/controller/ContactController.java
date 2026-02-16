package com.ffucks.controller;

import com.ffucks.model.Contact;
import com.ffucks.repository.ContactRepository;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ContactController {

    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TableView<Contact> table;

    @FXML private TableColumn<Contact, Long> idColumn;
    @FXML private TableColumn<Contact, String> nameColumn;
    @FXML private TableColumn<Contact, String> phoneColumn;
    @FXML private TableColumn<Contact, String> emailColumn;

    private final ContactRepository repository = new ContactRepository();
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // 👇 Bind columns to Contact fields
        idColumn.setCellValueFactory(data -> new SimpleLongProperty(
                        data.getValue().getId()
                ).asObject());

        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(
                        data.getValue().getName()));

        phoneColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getPhone()));

        emailColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getEmail()));

        refreshTable();

        table.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                nameField.setText(selected.getName());
                phoneField.setText(selected.getPhone());
                emailField.setText(selected.getEmail());
            }
        });
    }

    @FXML
    private void saveContact() {

        try {
            Contact selected = table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                repository.save(new Contact(
                        nameField.getText(),
                        phoneField.getText(),
                        emailField.getText()
                ));
            } else {
                selected.setName(nameField.getText());
                selected.setPhone(phoneField.getText());
                selected.setEmail(emailField.getText());
                repository.update(selected);
            }

            refreshTable();
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@FXML
    private void saveContact() throws Exception {

        Contact selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            repository.save(new Contact(
                    nameField.getText(),
                    phoneField.getText(),
                    emailField.getText()
            ));
        } else {
            selected.setName(nameField.getText());
            selected.setPhone(phoneField.getText());
            selected.setEmail(emailField.getText());
            repository.update(selected);
        }

        refreshTable();
        clearFields();
    }*/

    @FXML
    private void deleteContact() {

        try {
            Contact selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                repository.delete(selected.getId());
                refreshTable();
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@FXML
    private void deleteContact() throws Exception {
        Contact selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            repository.delete(selected.getId());
            refreshTable();
            clearFields();
        }
    }*/

    private void refreshTable() {
        try {
            contacts.setAll(repository.findAll());
            table.setItems(contacts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void refreshTable() {
        try {
            contacts.setAll(repository.findAll());
            table.setItems(contacts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void clearFields() {
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        table.getSelectionModel().clearSelection();
    }
}
