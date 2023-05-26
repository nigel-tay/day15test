package sg.nus.iss.vttp.day15test.model;

import java.io.Serializable;

public class Pet implements Serializable {
    private String name;
    private String id;
    private int age;
    private String imageUrl;
    
    public Pet() {
    }
    

    public Pet(String name, String id, int age, String imageUrl) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.imageUrl = imageUrl;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Pet [name=" + name + ", age=" + age + ", imageUrl=" + imageUrl + "]";
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }    
}
