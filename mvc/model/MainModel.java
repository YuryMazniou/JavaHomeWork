package by.it.mazniou.mvc.model;

import by.it.mazniou.mvc.bean.User;
import by.it.mazniou.mvc.model.service.UserService;
import by.it.mazniou.mvc.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model {
    private ModelData modelData=new ModelData();
    private UserService userService= new UserServiceImpl();
    @Override
    public ModelData getModelData() {
        return this.modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(getAllUsers());
        modelData.setDisplayDeletedUserList(false);
    }
    public void loadDeletedUsers() {
        modelData.setUsers(userService.getAllDeletedUsers());
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers(getAllUsers());
    }

    @Override
    public void loadUserById(long userId) {
        modelData.setActiveUser(userService.getUsersById(userId));
    }
    public void deleteUserById(long id){
        userService.deleteUser(id);
        modelData.setUsers(getAllUsers());
    }
    private List<User> getAllUsers(){
        return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1, 100));
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        //userService.createOrUpdateUser(name,id,level);
        modelData.setActiveUser(userService.createOrUpdateUser(name,id,level));
        modelData.setUsers(getAllUsers());
    }
}
