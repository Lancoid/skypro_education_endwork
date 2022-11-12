package ru.skypro.homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Ads.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class Ads {

    public static final String TABLE_NAME = "ads";

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    private String title;

    private String description;

    private Long price;

    @OneToOne(mappedBy = "ads", cascade = CascadeType.DETACH)
    private AdsImage adsImage;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "ads_id")
    private List<AdsComment> comments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
    }

    @Override
    public String toString() {
        return "class Ads {\n" +
                "    id: " + toIndentedString(id) + "\n" +
                "    User: " + toIndentedString(user) + "\n" +
                "    createdAt: " + toIndentedString(createdAt) + "\n" +
                "    title: " + toIndentedString(title) + "\n" +
                "    description: " + toIndentedString(description) + "\n" +
                "    price: " + toIndentedString(price) + "\n" +
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
