package org.filmt.projetagile.groups.controller;

import java.util.List;

import org.filmt.projetagile.exception.GroupNotFoundException;
import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.groups.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupController {

    GroupService groupService;

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable String groupId) {
        groupService.delete(groupId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        return ResponseEntity.ok(groupService.createGroup(group));
    }

    @GetMapping("{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable final String id) {
        return ResponseEntity.ok(groupService.getGroupById(id).orElseThrow(()->new GroupNotFoundException(String.format("Couldn't find a group with matching id : %s", id))));
    }

    @GetMapping(value = "", params = "schoolId")
    public ResponseEntity<List<Group>> getGroupBySchoolId(@RequestParam final String schoolId) {
        return ResponseEntity.ok(groupService.getGroupBySchoolId(schoolId));
    }

    @PutMapping("")
    public ResponseEntity<Group> update(@RequestBody final Group group) {
        return ResponseEntity.ok(groupService.update(group));
    }
}
