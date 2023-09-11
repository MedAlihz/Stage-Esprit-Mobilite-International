//package com.example.ahmed.Service;
//
//import com.example.ahmed.Entity.Choice;
//import com.example.ahmed.Entity.Group;
//import com.example.ahmed.Interface.IGroup;
//import com.example.ahmed.Repository.GroupRep;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GroupImplService implements IGroup {
//    @Autowired
//    GroupRep groupRep;
//
//    @Override
//    public List<Group> afficherGroup() {
//        return groupRep.findAll();
//    }
//
//    @Override
//    public Group ajouterGroup(Group group) {
//        return groupRep.save(group);
//    }
//
//    @Override
//    public void supprimerGroup(Integer idGroup) {
//        groupRep.deleteById(idGroup);
//    }
//}
