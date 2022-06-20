package org.filmt.projetagile.groups.service.impl;

import java.util.List;
import java.util.UUID;

import org.filmt.projetagile.exception.SchoolNotFoundException;
import org.filmt.projetagile.groups.dao.GroupDAO;
import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.groups.service.GroupService;
import org.filmt.projetagile.school.service.SchoolService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    GroupDAO groupDAO;

    SchoolService schoolService;
    @Override
    public Group createGroup(final Group group) {
        group.setId(UUID.randomUUID().toString());
        schoolService.getSchoolById(group.getSchoolId()).orElseThrow(()->new SchoolNotFoundException(String.format("Couldn't find a school with a matching identifier : %s", group.getSchoolId())));
        return groupDAO.create(group);
    }

    @Override
    public Group getGroupById(final String id) {
        return groupDAO.getGroupById(id);
    }

    @Override
    public List<Group> getGroupBySchoolId(final String schoolId) {
        return groupDAO.getGroupBySchoolId(schoolId);
    }

    @Override
    public Group update(final Group group) {
        return groupDAO.update(group);
    }

    @Override
    public void delete(final String id) {
        groupDAO.delete(id);
    }
}
