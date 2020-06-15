package org.vmware.finaltask.networkOfGiving.model;

public class Charity {
    private int charities_id;
    private String name;
    private String thumbnail_name;
    private String description;
    private int donation_required;
    private int participants_required;
    private int author_id;

    public Charity(){
    }

    public Charity(String name, String thumbnail_name, String description, int donation_required, int author_id) {
        this.name = name;
        this.thumbnail_name = thumbnail_name;
        this.description = description;
        this.donation_required = donation_required;
        this.author_id = author_id;
    }

    public Charity(String name, String thumbnail_name, String description, int donation_required, int participants_required, int author_id) {
        this.name = name;
        this.thumbnail_name = thumbnail_name;
        this.description = description;
        this.donation_required = donation_required;
        this.participants_required = participants_required;
        this.author_id = author_id;
    }

    public int getCharities_id() {
        return charities_id;
    }

    public void setCharities_id(int charities_id) {
        this.charities_id = charities_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail_name() {
        return thumbnail_name;
    }

    public void setThumbnail_name(String thumbnail_name) {
        this.thumbnail_name = thumbnail_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDonation_required() {
        return donation_required;
    }

    public void setDonation_required(int donation_required) {
        this.donation_required = donation_required;
    }

    public int getParticipants_required() {
        return participants_required;
    }

    public void setParticipants_required(int participants_required) {
        this.participants_required = participants_required;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
