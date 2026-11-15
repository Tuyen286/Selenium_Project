package com.qa.tests.api;

import com.qa.framework.api.UserService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiTest{

    @Test
    public void testGetSingleUserSuccess() {
        Response response = UserService.getUserDetails(2);

        Assert.assertEquals(response.getStatusCode(), 200, "Wrong status code: " + response.getStatusCode());
        int id = response.jsonPath().getInt("data.id");
        String email = response.jsonPath().getString("data.email");
        String firstName = response.jsonPath().getString("data.first_name");

        Assert.assertEquals(id, 2);
        Assert.assertEquals(email, "janet.weaver@reqres.in");
        Assert.assertEquals(firstName, "Janet");
    }

    @Test
    public void testGetUserListSuccess() {
        Response response = UserService.getUserList(2);
        Assert.assertEquals(response.getStatusCode(), 200, "Wrong status code: " + response.getStatusCode());

        int currentPage = response.jsonPath().getInt("page");
        Assert.assertEquals(currentPage, 2, "❌ Wrong number of page!: " + currentPage);

        int perPage = response.jsonPath().getInt("per_page");
        int actualListSize = response.jsonPath().getList("data").size();
        Assert.assertEquals(actualListSize, perPage, "❌ The number of users in the list does not match `per_page`!");

        List<Object> userList = response.jsonPath().getList("data");
        assertThat(userList)
                .isNotEmpty()
                .hasSize(6)
                .allSatisfy(obj -> {
                    Map<?, ?> user = (Map<?, ?>) obj;
                    assertThat(user.get("id")).isNotNull();
                    assertThat(user.get("email")).asString().contains("@reqres.in");
                    assertThat(user.get("first_name")).isNotNull();
                });
    }

    @Test
    public void testCreateUser() {
        Response response = UserService.createUser("Tester", "Automation QC");
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        String id = response.jsonPath().getString("id");
        String today = LocalDate.now().toString();
        String createdAt = response.jsonPath().getString("createdAt");

        Assert.assertEquals(response.statusCode(), 201, "Wrong status code: " + response.getStatusCode());
        Assert.assertEquals(name, "Tester");
        Assert.assertEquals(job, "Automation QC");
        Assert.assertNotNull(id);
        Assert.assertTrue(createdAt.startsWith(today));
    }

    @Test
    public void testUpdateUser() {
        Response response = UserService.updateUser(2, "Updated User", "Manager QC");
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        String updatedAt = response.jsonPath().getString("updatedAt");
        String today = LocalDate.now().toString();

        Assert.assertEquals(response.statusCode(), 200, "Wrong status code: " + response.getStatusCode());
        Assert.assertEquals(name, "Updated User", "Wrong updated name: " + name);
        Assert.assertEquals(job, "Manager QC", "Wrong updated job: " + job);
        Assert.assertTrue(updatedAt.startsWith(today));
    }

    @Test
    public void deleteUser() {
        Response response = UserService.deleteUser(2);
        //Because ReqRes is a Mock API, so when I delete user, just show status code to practice, not really delete user on database
        Assert.assertEquals(response.statusCode(), 204);
        /*
        From real project, before deleting user, get username of deleted userId
        After deleting, get list of user to verify that deleted username is no longer displayed on list
         */
    }

    @Test
    public void testGetTokenAfterLoginUser() {
        String token = UserService.getAuthTokenAfterLogin("eve.holt@reqres.in", "cityslicka");
        System.out.println("Token: " + token);
        Assert.assertNotNull(token, "❌ Cannot get a Token!");
    }

    @Test
    public void testLoginWithoutEmail() {
        Response response = UserService.loginUser(null, "cityslicka");
        String error = response.jsonPath().getString("error");
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(error, "Missing email or username");
    }

    @Test
    public void testGetInvalidUser() {
        Response response = UserService.getUserDetails(23);
        String responseBody = response.getBody().asString().trim();
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(responseBody, "{}");
    }
}
