package com.example.myapp.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Task;


@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private List<Task> tasks = new ArrayList<>(
        List.of(
            new Task(1, "Naučiti Spring Boot", false),
            new Task(2, "Napraviti TODO aplikaciju", false),
            new Task(3, "Testirati aplikaciju", true)
            )
    );

@GetMapping
public List<Task> getAllTasks() {

    return tasks;
}
@GetMapping("/{id}")
public Task getTaskById(@PathVariable int id) {
    return tasks.stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Zadatak nije pronađen!"));
}

@PostMapping()
public String addTask(@RequestBody Task task) {
    tasks.add(task);
    return("Novi task je dodan");
}


@PatchMapping("/{id}")
public String patchTask(@PathVariable int id, @RequestBody Task updatedTask) {
    Task task = tasks.stream()
                     .filter(t -> t.getId() == id)
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException("Zadatak nije pronađen!"));
    if (updatedTask.getCompleted() != null) {
        task.setCompleted(updatedTask.getCompleted());
    }
    return "Status zadatka je ažuriran (PATCH)!";
}
    
 @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        tasks.removeIf(task -> task.getId() == id);
        return "Zadatak je obrisan!";
    }


    @RequestMapping(method = RequestMethod.OPTIONS)
    public void handleOptions() {
    }
}


