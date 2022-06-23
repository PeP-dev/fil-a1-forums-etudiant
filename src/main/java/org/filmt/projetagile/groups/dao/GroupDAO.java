package org.filmt.projetagile.groups.dao;

import org.filmt.projetagile.groups.model.Category;
import org.filmt.projetagile.groups.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDAO {

    Optional<Group> getGroupById(String groupId);

    List<Group> getGroupBySchoolId(String schoolID);

    Group create(Group group);

    Group update(Group group);

    void delete(String id);

    void createCategory(String groupId, Category category);

    void deleteCategory(String groupId, String categoryId);
}
