import dto.CreateUserRequest;
import dto.InvalidDeleteUserResponse;
import dto.InvalidErrorResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class DeleteUserTest extends BaseTest{





    @Test
    public void testDeleteUser(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = new CreateUserRequest("Roman Brit", generatedEmail , "12345678","false");
                                    given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201);
        System.out.println("New Email " + request.getEmail());

                given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail() )
                .then().log().all()
                .statusCode(200);




    }
    @Test
    public void testDeleteInvalidUser(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = new CreateUserRequest("Roman Brit", generatedEmail , "12345678","false");
        given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201);
        System.out.println("New Email " + request.getEmail());

        given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail() )
                .then().log().all()
                .statusCode(200);

        InvalidErrorResponse response = given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail())
                .then().log().all()
                .statusCode(404)
                .extract().body().as(InvalidErrorResponse.class);
        assertEquals("Not Found", response.getCode()) ;
        assertEquals("User with email: " + request.getEmail()+ " not found", response.getMessage());



    }


}
