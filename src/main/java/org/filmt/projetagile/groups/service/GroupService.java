package org.filmt.projetagile.groups.service;

import java.util.List;

import org.filmt.projetagile.groups.model.Group;

public interface GroupService {

    Group createGroup(Group group);

    Group getGroupById(String id);

    List<Group> getGroupBySchoolId(String schoolId);

    Group update(final Group group);

    void delete(final String id);
}
