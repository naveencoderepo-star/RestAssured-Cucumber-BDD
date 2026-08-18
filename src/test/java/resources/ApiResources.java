package resources;

// Enum is a special class in java which have collection of constants and methods.
// It is used to define a set of named constants.
// In this case, we are using enum to define the API resources for our application.

public enum ApiResources {


    addPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String resource;

    ApiResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;

    }
}
