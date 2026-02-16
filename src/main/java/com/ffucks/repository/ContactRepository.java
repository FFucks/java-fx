package com.ffucks.repository;

import com.ffucks.config.DbConfig;
import com.ffucks.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    public void save(Contact contact) throws Exception {

        String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getPhone());
            stmt.setString(3, contact.getEmail());
            stmt.executeUpdate();
        }

    }

    public void update(Contact contact) throws Exception {

        String sql = "UPDATE contacts SET name=?, phone=?, email=? WHERE id=?";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getPhone());
            stmt.setString(3, contact.getEmail());
            stmt.setLong(4, contact.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(Long id) throws Exception {

        String sql = "DELETE FROM contacts WHERE id=?";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Contact> findAll() throws Exception {

        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";

        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contacts.add(new Contact(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }
        }

        return contacts;
    }
}
