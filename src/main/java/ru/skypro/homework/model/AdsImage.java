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

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "media_type")
    private String mediaType;

    private String url;

    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    private Ads ads;

    @Override
    public String toString() {
        return "class AdsImage {\n" +
                "    id: " + toIndentedString(id) + "\n" +
                "    fileSize: " + toIndentedString(fileSize) + "\n" +
                "    mediaType: " + toIndentedString(mediaType) + "\n" +
                "    url: " + toIndentedString(url) + "\n" +
                "    Ads: " + toIndentedString(ads) + "\n" +
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
