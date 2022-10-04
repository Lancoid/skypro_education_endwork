package ru.skypro.homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = AdsComment.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class AdsComment {

    public static final String TABLE_NAME = "ads_comment";

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "ads_id", referencedColumnName = "id")
    private Ads ads;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    private String text;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
    }

    @Override
    public String toString() {
        return "class AdsComment {\n" +
                "    id: " + toIndentedString(id) + "\n" +
                "    Ads: " + toIndentedString(ads) + "\n" +
                "    User: " + toIndentedString(user) + "\n" +
                "    createdAt: " + toIndentedString(createdAt) + "\n" +
                "    text: " + toIndentedString(text) + "\n" +
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
