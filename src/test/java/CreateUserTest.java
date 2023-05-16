import dto.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CreateUserTest extends BaseTest {






    @Test
    public void getUser(){
        given().baseUri("https://studio-api.softr.io/v1/api")
                .when().log().all()
                .post("/users")
                .then().log().all();

    }

    @Test
    public void testCreateUser(){
        CreateUserRequest request = new CreateUserRequest("Roman Brit", "test23@gmail.com", "12345678","false");
        CreateUserResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201)
                .extract().body().as(CreateUserResponse.class);
                 assertNotNull(response.getEmail());
                 assertNotNull(response.getCreated());
                 assertNotNull(response.getUpdated());
                 assertNotNull(response.getFull_name());
                 assertEquals(request.getFull_name(),response.getFull_name());
                 assertEquals(request.getEmail(),response.getEmail());

                                     given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail())
                .then().log().all()
                .statusCode(200);
               // .extract().body().as(DeleteUserResponse.class);


    }



    @Test
    public void withoutEmail(){
        CreateUserRequest request = new CreateUserRequest("Roman Brit", "", "12345678", "false");
        InvalidErrorResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key","khIbAyJIU5CIuh1oDuBRx1s49")
                .header("Softr-Domain","jere237.softr.app")
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(400)
                .extract().body().as(InvalidErrorResponse.class);
                assertEquals("Something went wrong, please try again.", response.getMessage());
                assertEquals("Bad Request",response.getCode());

    }
    @Test
    public void withoutPassword(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = CreateUserRequest.builder()
                .full_name("FGH HGHj")
                .email(generatedEmail)
                .generate_magic_link("false").build();
        CreateUserResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201)
                .extract().body().as(CreateUserResponse.class);
                assertEquals(request.getFull_name(), response.getFull_name());
                assertEquals(request.getEmail(),response.getEmail());
                assertNotNull(response.getCreated());
                assertNotNull(response.getUpdated());

                                given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail())
                .then().log().all()
                .statusCode(200);


    }
    @Test
    public void withoutFullName(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = CreateUserRequest.builder()
                .full_name("Rita Hope")
                .password("qwerty")
                .email(generatedEmail)
                .generate_magic_link("false").build();
        CreateUserResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201)
                .extract().body().as(CreateUserResponse.class);
                assertEquals(request.getFull_name(),response.getFull_name());
                assertEquals(request.getEmail(),response.getEmail());
                assertNotNull(response.getCreated());
                assertNotNull(response.getUpdated());

                                    given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail())
                .then().log().all()
                .statusCode(200);


    }

    @Test
    public void withoutMagicLink(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = CreateUserRequest.builder()
                .password("qwerty")
                .email(generatedEmail).build();
        CreateUserResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201)
                .extract().body().as(CreateUserResponse.class);

                assertEquals(request.getFull_name(), response.getFull_name());
                assertEquals(request.getEmail(),response.getEmail());
                assertNotNull(response.getCreated());
                assertNotNull(response.getUpdated());

                                given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail())
                .then().log().all()
                .statusCode(200);



    }
    @Test
    public void MagicLinkTrue(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = new CreateUserRequest("Roman Brit", generatedEmail, "12345678","true");
        CreateUserResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201)
                .extract().body().as(CreateUserResponse.class);
                assertEquals(request.getFull_name(), response.getFull_name());
                assertEquals(request.getEmail(),response.getEmail());
                //assertEquals(request.getGenerate_magic_link(),response.getMagic_link());
                assertNotNull(response.getCreated());
                assertNotNull(response.getUpdated());

                                given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail())
                .then().log().all()
                .statusCode(200);


    }
    @Test
    public void MagicLinkFalse(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = new CreateUserRequest("Roman Brit", generatedEmail, "12345678","false");
        CreateUserResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201)
                .extract().body().as(CreateUserResponse.class);
        assertEquals(request.getFull_name(), response.getFull_name());
        assertEquals(request.getEmail(),response.getEmail());
        //assertEquals(request.getGenerate_magic_link(),response.getMagic_link());
        assertNotNull(response.getCreated());
        assertNotNull(response.getUpdated());

        given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail())
                .then().log().all()
                .statusCode(200);


    }
    @Test
    public void MagicLinkNull(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = new CreateUserRequest("Roman Brit", generatedEmail, "12345678","null");
        CreateUserResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(201)
                .extract().body().as(CreateUserResponse.class);
                assertEquals(request.getFull_name(), response.getFull_name());
                assertEquals(request.getEmail(),response.getEmail());
                //assertEquals(request.getGenerate_magic_link(),response.getMagic_link());
                assertNotNull(response.getCreated());
                assertNotNull(response.getUpdated());

                                    given()
                .baseUri(BASE_URI)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .delete("v1/api/users/" + request.getEmail())
                .then().log().all()
                .statusCode(200);


    }
    @Test
    public void MagicLinkWithValidValue(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = new CreateUserRequest("Roman Brit", generatedEmail, "12345678","fgakfdg");
        InvalidErrorResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(400)
                .extract().body().as(InvalidErrorResponse.class);
        assertEquals("Something went wrong, please try again.", response.getMessage());
        assertEquals("Bad Request",response.getCode());


    }
    @Test
    public void MagicLinkWithoutSoftrApiKey(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = new CreateUserRequest("Roman Brit", generatedEmail, "12345678","false");
        InvalidErrorResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key","")
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(401)
                .extract().body().as(InvalidErrorResponse.class);
        assertEquals("Wrong apiKey", response.getMessage());
        assertEquals("UNAUTHORIZED",response.getCode());


    }
    @Test
    public void MagicLinkWithoutSoftrDomain(){
        String generatedEmail = generateRandomEmail();
        CreateUserRequest request = new CreateUserRequest("Roman Brit", generatedEmail, "12345678","false");
        InvalidErrorResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain","")
                .when().log().all()
                .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(401)
                .extract().body().as(InvalidErrorResponse.class);
        assertEquals("There is no application with this domain ", response.getMessage());
        assertEquals("UNAUTHORIZED",response.getCode());

    }

    @Test
    public void MagicLinkWithoutContentType(){
        CreateUserRequest request = new CreateUserRequest("Roman Brit", "dp47hd@gmail.com", "12345678","false");
        InvalidErrorResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
               // .contentType(ContentType.JSON)
                .post("/v1/api/users")
                .then().log().all()
                .statusCode(400)
                .extract().body().as(InvalidErrorResponse.class);
        assertEquals("Something went wrong, please try again.", response.getMessage());
        assertEquals("Bad Request",response.getCode());


    }
    @Test
    public void MagicLinkWithMethodGet(){
        CreateUserRequest request = new CreateUserRequest("Roman Brit", "dp47hd@gmail.com", "12345678","false");
        InvalidErrorResponse response = given()
                .baseUri(BASE_URI)
                .body(request)
                .header("Softr-Api-Key",headerValidKey)
                .header("Softr-Domain",headerValidDomain)
                .when().log().all()
                .contentType(ContentType.JSON)
                .get("/v1/api/users")
                .then().log().all()
                .statusCode(405)
                .extract().body().as(InvalidErrorResponse.class);
        assertEquals("405 Method Not Allowed", response.getMessage());


    }


}
