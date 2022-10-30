package com.example.RestApi_Controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Annotation --> in this class i will write api
public class MyControllers {
    // to list  of all api

    HashMap<Integer  , Users> users = new HashMap<>();

    // http://localhost:8080/get_users  rest api at browser in url
    @GetMapping("/get_users") // return all user when request
    public List <Users> getAllUsers(){
        // server side logic
        List<Users> listofusers = new ArrayList<>();
        for(Map.Entry<Integer , Users> entry : users.entrySet()){
            listofusers.add(entry.getValue());
        }
        return listofusers;

    }

    // http://localhost:8080/get_user?id = 10   rest api at browser in url
    // ? is use to differentiate between request param and endpoint  ? id =10 is request param (query parameter)
    @GetMapping("/get_user") // only specific user
    public Users getUser(@RequestParam("id") int id){
        return users.get(id);
    }

    // send info to hash map or insrt data into hashmap
    @PostMapping("/add_user")
    public void addUser(@RequestParam("id") int id,
                        @RequestParam("name") String name,
                        @RequestParam("country") String country,
                        @RequestParam("age") int age){

        Users u = new Users(id ,name , country ,age);
        users.put(id , u);

    }
    /*
    add user urp or api
    http://localhost:8080/add_user?id=1&name=mihir&country=india&age=23
    http://localhost:8080/add_user?id=2&name=harsh&country=india&age=25

     */

    // second fromat to add user with body first is using request param  second using requestBody annotation

    @PostMapping("/add_user_body")
    public void addUserBody(@RequestBody (required = true) Users u){
        users.put(u.getId() , u);
    }

    // put for modify data or hashmap
    @PutMapping("/modify_user")
    public Users modifyUser(@RequestBody (required = true) Users u){
        users.put(u.getId() , u);
        return u;
    }
    @DeleteMapping("/delete_user")
    public void deleteUser(@RequestParam("id") int id){
        users.remove(id);

    }
    @DeleteMapping("/delete_user_path/{id}")
    public void deleteUserPath(@PathVariable("id") int id){
        users.remove(id);
    }


}
