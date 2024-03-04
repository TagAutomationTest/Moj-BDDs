package Pojos;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebsiteData {

    private String Url;
    private String Country1 ;
    private String Country2 ;
    private String Country3 ;
    private String NoOfPackageTypes ;
    private String PackageTypes1 ;
    private String PackageTypes2 ;
    private String PackageTypes3 ;
    private String Currency1 ;
    private String Currency2 ;
    private String Currency3 ;
    private String KSALite ;
    private String KSAClassic ;
    private String KSAPerimium ;
    public void setUrl(String url) {
        this.Url = url;
    }
    public String getUrl() {
        return Url;
    }
    public void setNoOfPackageTypes(String noOfPackageTypes) {
        this.NoOfPackageTypes = noOfPackageTypes;
    }
    public String getNoOfPackageTypes() {
        return NoOfPackageTypes;
    }
    public void setCountry1(String country1) {
        this.Country1 = country1;
    }
    public String getCountry1() {
        return Country1;
    }
    public void setCountry2(String country2) {
        this.Country2 = country2;
    }
    public String getCountry2() {
        return Country2;
    }
    public void setCountry3(String country3) {
        this.Country3 = country3;
    }
    public String getCountry3() {
        return Country3;
    }
    public void setCurrency1(String currency1) {
        this.Currency1 = currency1;
    }
    public String getCurrency1() {
        return Currency1;
    }
    public void setCurrency2(String currency2) {
        this.Currency2 = currency2;
    }
    public String getCurrency2() {
        return Currency2;
    }
    public void setCurrency3(String currency3) {
        this.Currency3 = currency3;
    }
    public String getCurrency3() {
        return Currency3;
    }

    public void setPackageTypes1(String packageTypes1) {
        this.PackageTypes1 = packageTypes1;
    }
    public String getPackageTypes1() {
        return PackageTypes1;
    }
    public void setPackageTypes2(String packageTypes2) {
        this.PackageTypes2 = packageTypes2;
    }
    public String getPackageTypes2() {
        return PackageTypes2;
    }
    public void setPackageTypes3(String packageTypes3) {
        this.PackageTypes3 = packageTypes3;
    }
    public String getPackageTypes3() {
        return PackageTypes3;
    }
//
public void setKSALite(String ksa_lite) {
    this.KSALite = ksa_lite;
}
    public String getKSALite() {
        return KSALite;
    }
    public void setKSAClassic(String ksa_classic) {
        this.KSAClassic = ksa_classic;
    }
    public String getKSAClassic() {
        return KSAClassic;
    }
    public void setKSAPerimium(String ksa_prem) {
        this.KSAPerimium = ksa_prem;
    }
    public String getKSAPerimium() {
        return KSAPerimium;
    }
}
