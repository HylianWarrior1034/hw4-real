import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonplaceholdersRestAPITest {

  @Test
  public void jsonPlaceHoldersGetAlbumsHasTitleOmnis() {
    when().get("https://jsonplaceholder.typicode.com/albums").then().body("title", hasItem("omnis laborum odio"));
  }

  @Test
  public void jsonPlaceHoldersGetCommentsHas200() {
    when().get("https://jsonplaceholder.typicode.com/comments").then().body("$", hasSize(greaterThan(200)));
  }

  @Test
  public void jsonPlaceHoldersGetUsersExists() {
    when().get("https://jsonplaceholder.typicode.com/users").then().body("find { it.name == 'Ervin Howell' && it.username == 'Antonette' && it.address.zipcode=='90566-7771' }", notNullValue());
  }

  @Test
  public void jsonPlaceHoldersGetCommentsEmailBiz() {
    when().get("https://jsonplaceholder.typicode.com/comments").then().body("any { it.email.matches('.*.biz') }", is(true));
  }

  @Test
  public void jsonPlaceHoldersPostPosts() {
    JSONObject requestParams = new JSONObject();
    requestParams.put("userId", 11);
    requestParams.put("id", 101);
    requestParams.put("title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    requestParams.put("body", "quia et suscipit\n suscipit recusandae consequuntur expedita et cum\n reprehenderit molestiae ut ut quas totam\n nostrum rerum est autem sunt rem eveniet architecto");
    given().header("Content-Type", "application/json").body(requestParams.toString()).when().post("https://jsonplaceholder.typicode.com/posts").then().statusCode(201);
  }
}
