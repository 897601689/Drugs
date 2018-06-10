package com.drugs.bean;

/**
 * 药品信息
 */
public class DrugsInfo {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getDosageandadministration() {
        return dosageandadministration;
    }

    public void setDosageandadministration(String dosageandadministration) {
        this.dosageandadministration = dosageandadministration;
    }

    public String getAdversereactions() {
        return adversereactions;
    }

    public void setAdversereactions(String adversereactions) {
        this.adversereactions = adversereactions;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getDrugPrice() {
        return drugprice;
    }

    public void setDrugPrice(String drugPrice) {
        this.drugprice = drugPrice;
    }

    @Override
    public String toString() {
        return "DrugsInfo{" +
                "id='" + id + '\'' +
                ", drugname='" + drugname + '\'' +
                ", description='" + description + '\'' +
                ", actions='" + actions + '\'' +
                ", indications='" + indications + '\'' +
                ", contraindications='" + contraindications + '\'' +
                ", dosageandadministration='" + dosageandadministration + '\'' +
                ", adversereactions='" + adversereactions + '\'' +
                ", precautions='" + precautions + '\'' +
                ", packages='" + packages + '\'' +
                ", storage='" + storage + '\'' +
                ", others='" + others + '\'' +
                '}';
    }

    private String id;                      // ID
    private String drugname;                //药品名称
    private String drugprice;                //药品价格
    private String description;             //性状
    private String actions;                 //药理作用
    private String indications;             //适应症
    private String contraindications;       //禁忌症
    private String dosageandadministration; //用量用法
    private String adversereactions;        //不良反应
    private String precautions;             //注意事项
    private String packages;                //包装
    private String storage;                 //储藏
    private String others;                  //其他

    public DrugsInfo() {
        super();
    }

    public DrugsInfo(String id, String drugname, String drugprice, String description, String actions,
                     String indications, String contraindications, String dosageandadministration,
                     String adversereactions, String precautions, String packages, String storage, String others) {
        super();
        this.id = id;
        this.drugname = drugname;
        this.description = description;
        this.actions = actions;
        this.indications = indications;
        this.contraindications = contraindications;
        this.dosageandadministration = dosageandadministration;
        this.adversereactions = adversereactions;
        this.precautions = precautions;
        this.packages = packages;
        this.storage = storage;
        this.others = others;
    }
}
