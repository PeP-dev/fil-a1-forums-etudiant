package org.filmt.projetagile.groups.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.filmt.projetagile.exception.GroupNotFoundException;
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
        if (schoolService.getSchoolById(group.getSchoolId()).isEmpty()) {
             throw SchoolNotFoundException.genericById(group.getSchoolId());
        }
        return groupDAO.create(group);
    }

    @Override
    public Optional<Group> getGroupById(final String id) {
        return groupDAO.getGroupById(id);
    }

    @Override
    public List<Group> getGroupBySchoolId(final String schoolId) {
        if(schoolService.getSchoolById(schoolId).isEmpty()) {
            throw SchoolNotFoundException.genericById(schoolId);
        }
        return groupDAO.getGroupBySchoolId(schoolId);
    }

    @Override
    public Group update(final Group group) {
        if(schoolService.getSchoolById(group.getSchoolId()).isEmpty()) {
            throw SchoolNotFoundException.genericById(group.getSchoolId());
        }
        if (getGroupById(group.getId()).isEmpty()) {
            throw GroupNotFoundException.genericById(group.getId());
        }
        return groupDAO.update(group);
    }

    @Override
    public void delete(final String id) {
        if(getGroupById(id).isPresent()) {
            groupDAO.delete(id);
        } else {
            throw GroupNotFoundException.genericById(id);
        }
    }
}
