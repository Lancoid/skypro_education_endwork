package ru.skypro.homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = User.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class User {

    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    private String email;

    private String password;

    private String phone;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Role role;

    public User(String email, String password, String phone, String firstName, String lastName, Role role) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
    }

    @Override
    public String toString() {
        return "class User {\n" +
                "    id: " + toIndentedString(id) + "\n" +
                "    createdAt: " + toIndentedString(createdAt) + "\n" +
                "    email: " + toIndentedString(email) + "\n" +
                "    password: " + toIndentedString(password) + "\n" +
                "    phone: " + toIndentedString(phone) + "\n" +
                "    firstName: " + toIndentedString(firstName) + "\n" +
                "    lastName: " + toIndentedString(lastName) + "\n" +
                "    role: " + toIndentedString(role) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }

        return o.toString().replace("\n", "\n    ");
    }

}
