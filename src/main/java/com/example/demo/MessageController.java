package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    //curl -X GET http://localhost:8080/messages
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }

    //curl -X GET http://localhost:8080/messages/from/0
    @GetMapping("messages/from/{index}")
    public ResponseEntity<List<String>> getMessages(@PathVariable("index") Integer index) {
        List<String> answermessages = new ArrayList<>();
        for(int i = index; i < messages.size(); i++){
            answermessages.add(messages.get(i));
        }
        return ResponseEntity.ok(answermessages);
    }

    //curl -X POST -H "Content-Type: text/plain" http://localhost:8080/messages -d "Majula"
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET http://localhost:8080/messages/2
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }

    //curl -X DELETE http://localhost:8080/messages/1
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT -H "Content-Type: text/plain" http://localhost:8080/messages/1 -d "Pinwheel"
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET http://localhost:8080/messages/search/ababa
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> getMessage(@PathVariable("text") String text) {
        for(int i = 0; i < messages.size(); i++){
            if(messages.get(i).contains(text)){
                return ResponseEntity.ok(i+1);
            }
        }
        return ResponseEntity.ok(-1);
    }

    //curl -X GET http://localhost:8080/messages/count
    @GetMapping("messages/count")
    public ResponseEntity<Integer> getMessage(){
        return ResponseEntity.ok(messages.size());
    }

    //curl -X POST -H "Content-Type: text/plain" http://localhost:8080/messages/4/create
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> addMessage(@PathVariable("index") Integer index) {
        messages.add(index, "");
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE http://localhost:8080/messages/search/Nashandra
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteText(@PathVariable("text") String text) {
        int temp = 0;
        while (temp != messages.size()){
            if(messages.get(temp).contains(text)){
                messages.remove(temp);
                temp = 0;
            } else{
                temp++;
            }
        }
        return ResponseEntity.noContent().build();
    }

    //curl -X POST -H "Content-Type: application/json" localhost:8080/users -d "{\"name\":\"Vendrik\",\"age\":\"200\"}"
    @PostMapping("users")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET localhost:8080/users
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(users);
    }

    //curl -X GET localhost:8080/users/1
    @GetMapping("users/{index}")
    public ResponseEntity<User> getUser(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(users.get(index));
    }

    //curl -X DELETE http://localhost:8080/users/2
    @DeleteMapping("users/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer index) {
        users.remove((int)index);
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT -H "Content-Type: application/json" http://localhost:8080/users/change/age/1 -d "10"
    @PutMapping("users/change/age/{index}")
    public ResponseEntity<Void> updateAge(@PathVariable("index") Integer index, @RequestBody Integer age) {
        User changeableUser = users.get(index);
        changeableUser.setAge(age);
        users.remove((int) index);
        users.add(index, changeableUser);
        return ResponseEntity.accepted().build();
    }
}