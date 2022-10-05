package ru.skypro.homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = AdsImage.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class AdsImage {

    public static final String TABLE_NAME = "ads_image";

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Ads ads;

    private String image;

    @Override
    public String toString() {
        return "class AdsImage {\n" +
                "    id: " + toIndentedString(id) + "\n" +
                "    Ads: " + toIndentedString(ads) + "\n" +
                "    image: " + toIndentedString(image) + "\n" +
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
