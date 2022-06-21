package org.filmt.projetagile.groups.service;

import java.util.List;
import java.util.Optional;

import org.filmt.projetagile.groups.model.Group;

public interface GroupService {

    Group createGroup(Group group);

    Optional<Group> getGroupById(String id);

    List<Group> getGroupBySchoolId(String schoolId);

    Group update(final Group group);

    void delete(final String id);
}
