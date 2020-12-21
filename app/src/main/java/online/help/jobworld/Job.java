package online.help.jobworld;

public class Job {
    private String title, category, description, location, contact;

    public Job() {

    }

    public Job(String title, String category, String description, String location, String contact) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.location = location;
        this.contact = contact;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }
}
