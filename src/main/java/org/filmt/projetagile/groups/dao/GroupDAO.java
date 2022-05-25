package org.filmt.projetagile.groups.dao;

import org.filmt.projetagile.groups.model.Group;

import java.util.List;

public interface GroupDAO {

    Group getGroupById(String postId);

    List<Group> getGroupBySchoolId(String schoolID);

    Group create(Group group);

    Group update(Group group);

    void delete(String id);
}
