package org.filmt.projetagile.groups.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.filmt.projetagile.exception.CategoryNotFoundException;
import org.filmt.projetagile.exception.GroupNotFoundException;
import org.filmt.projetagile.exception.SchoolNotFoundException;
import org.filmt.projetagile.groups.dao.GroupDAO;
import org.filmt.projetagile.groups.model.Category;
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
        Group created = groupDAO.create(group);

        if(created.getCategories() != null) {
            created.getCategories().forEach(cat->createCategory(created.getId(), cat));
        }
        return created;
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

    @Override
    public void createCategory(final String groupId, final Category category) {
        if (getGroupById(groupId).isEmpty()) {
            throw GroupNotFoundException.genericById(groupId);
        }
        category.setId(UUID.randomUUID().toString());
        groupDAO.createCategory(groupId, category);
    }

    @Override
    public void delete(final String groupId, final String categoryId) {
        Optional<Group> opt = getGroupById(groupId);
        if (opt.isEmpty()) {
            throw GroupNotFoundException.genericById(groupId);
        }
        boolean hasCategory = opt.map(Group::getCategories)
            .stream()
            .flatMap(List::stream)
            .map(Category::getId)
            .anyMatch(str -> str.equals(categoryId));
        if (!hasCategory) {
            throw CategoryNotFoundException.genericById(categoryId);
        }
        groupDAO.deleteCategory(groupId, categoryId);
    }
}
